import Vue from 'vue'
import VueRouter from "vue-router"
import axios from "axios";
import VueCookies from "vue-cookies"
import token_service from "@/services/token_service";

Vue.use(VueCookies);
Vue.use(VueRouter);

const instance = axios.create({
    baseURL: 'http://localhost:7777/'
});

let isTokenReissuing = false;
let refreshSubscribers = [];

const onTokenReissuing = (accessToken) => {
    refreshSubscribers.map((callback) => callback(accessToken));
}

const addReissuingSubscriber = (callback) => {
    refreshSubscribers.push(callback)
}

instance.interceptors.request.use(
    async(config) => {
        console.log("accessTokenExp: " + token_service.getAccessTokenExp())
        console.log("time Now: " + Date.now())
        
        // AccessToken이 만료되지 않은경우 Header에 Authorization 추가
        if (token_service.getAccessTokenExp() > Date.now() + 5000) { // 1000 * 60 milliseconds = 60초
            console.log("Request: AccessToken 만료되지 않은경우")
            config.headers.Authorization = `Bearer ${token_service.getAccessToken()}`;
            return config;
        }
        // AccessToken이 만료되었으며, RefreshToken이 만료되지 않은경우 Token Reissue 후 Authorization 추가
        else if (token_service.getRefreshTokenExp() > Date.now()) {
            console.log("Request: AccessToken 만료되었고, RefreshToken은 만료되지 않았을때 Reissue")
            console.log("refreshtokenExp: " + token_service.getRefreshTokenExp())
            console.log("time Now: " + Date.now())
            const originalConfig = config;
            if(!isTokenReissuing) {
                isTokenReissuing = true;
                const {userInfo} = await axios.post(
                    'http://localhost:7777/auth/reissue',
                    {},
                    {
                        headers: {
                            Authorization: `Bearer ${token_service.getAccessToken()}`,
                            refreshToken: `Bearer ${token_service.getRefreshToken()}`,
                        }
                    });
                await token_service.setTokens(userInfo.data);
                isTokenReissuing = false;
                originalConfig.headers.Authorization = `Bearer ${token_service.getAccessToken()}`;
                onTokenReissuing(token_service.getAccessToken());
            }
            return new Promise((resolve) => {
                addReissuingSubscriber(() => {
                    originalConfig.headers.Authorization = 'Bearer ' + token_service.getAccessToken()
                    resolve(instance(originalConfig));
                });
            });
        }
        // Token이 둘다 만료된경우 => LoginPage로 Redirect
        else {
            alert("로그인이 필요합니다.")
            return this.$router.push("@/views/Account/AccountLoginPage.vue");
        }

    },

    async(error) => {
        return Promise.reject(error)
    }
)

instance.interceptors.response.use(
    async (response) => {
        return response
    },

    async (error) => {
        const {
            config,
            response: {status},
        } = error;
        const originalConfig = config;
        if (status === 401) {
            console.log("Response: 401에러가 떴다면")
            if (error.response.data.message === "TokenExpired") {
                console.log("Response: Message가 TokenExpired일 경우")
                const {userInfo} = await axios.post(
                    'http://localhost:7777/auth/reissue',
                    {},
                    {
                        headers: {
                            Authorization: `Bearer ${token_service.getAccessToken()}`,
                            refreshToken: `Bearer ${token_service.getRefreshToken()}`,
                        }
                    });
                await token_service.setTokens(userInfo.data);
                isTokenReissuing = false;
                originalConfig.headers.Authorization = `Bearer ${token_service.getAccessToken()}`;
                onTokenReissuing(token_service.getAccessToken());
            }
            return new Promise((resolve) => {
                addReissuingSubscriber(() => {
                    originalConfig.headers.Authorization = 'Bearer ' + token_service.getAccessToken()
                    resolve(instance(originalConfig));
                });
            });
        }
        return Promise.reject(error);
    }
);

export default instance;
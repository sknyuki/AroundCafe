import Vue from 'vue'
import VueCookies from "vue-cookies";

Vue.use(VueCookies)
Vue.$cookies.config("14d");

class TokenService {
    getAccessToken() {
        return localStorage.getItem("accessToken")
    }

    getAccessTokenExp() {
        return Number(localStorage.getItem("accessTokenExp"))
    }

    getRefreshToken() {
        return Vue.$cookies.get("refreshToken")
    }

    getRefreshTokenExp() {
        return Number(Vue.$cookies.get("refreshTokenExp"))
    }

    setTokens(userInfo) {
        localStorage.setItem("accessToken",  userInfo["accessToken"]);
        localStorage.setItem("accessTokenExp", String(Date.now() + Number(userInfo["accessTokenExp"])));
        Vue.$cookies.set("refreshToken", userInfo["refreshToken"]);
        Vue.$cookies.set("refreshTokenExp", String(Date.now() + Number(userInfo["refreshTokenExp"])));
    }
}

export default new TokenService();
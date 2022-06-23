import Vue from "vue"
import VueRouter from "vue-router"
import axios from "axios"
import VueCookies from "vue-cookies"
import token_service from "@/services/tokenService"

Vue.use(VueCookies)
Vue.use(VueRouter)

//const axios = axios.create({
//    baseURL: 'http://localhost:7777/'
//});

let isTokenReissuing = false
let refreshSubscribers = []

const onTokenReissuing = (accessToken) => {
  refreshSubscribers.map((callback) => callback(accessToken))
}

const addReissuingSubscriber = (callback) => {
  refreshSubscribers.push(callback)
}

axios.interceptors.request.use(
  async (config) => {
    console.log("Interceptors Request Start")
    // AccessToken이 만료되지 않은경우 Header에 Authorization 추가
    if (token_service.getAccessTokenExp() > Date.now() + 5000) {
      // 1000 * 60 milliseconds = 60초
      config.headers.Authorization = `Bearer ${token_service.getAccessToken()}`
      return config
    }
    // AccessToken이 만료되었으며, RefreshToken이 만료되지 않은경우 Token Reissue 후 Authorization 추가
    else if (token_service.getRefreshTokenExp() > Date.now()) {
      console.log("Interceptors Request Token Reissuing Start")
      const originalConfig = config
      if (!isTokenReissuing) {
        isTokenReissuing = true
        const userInfo = await axios.post(
          "http://localhost:7777/auth/reissue",
          {},
          {
            headers: {
              Authorization: `Bearer ${token_service.getAccessToken()}`,
              refreshToken: `Bearer ${token_service.getRefreshToken()}`,
            },
          }
        )
        token_service.setTokens(userInfo.data)
        isTokenReissuing = false
        originalConfig.headers.Authorization = `Bearer ${token_service.getAccessToken()}`
        onTokenReissuing(token_service.getAccessToken())
        console.log("Interceptors Request Token Reissuing finished")
      }
      return new Promise((resolve) => {
        addReissuingSubscriber(
          (accessToken = token_service.getAccessToken()) => {
            console.log(
              "Interceptors Request Token Reissuing % Request Restart"
            )
            originalConfig.headers.Authorization = "Bearer " + accessToken
            resolve(axios(originalConfig))
          }
        )
      })
    }
    // Token이 둘다 만료된경우 => LoginPage로 Redirect
    else {
      alert("로그인이 필요합니다.")
      return this.$router.push("/login")
    }
  },

  async (error) => {
    return Promise.reject(error)
  }
)

axios.interceptors.response.use(
  async (response) => {
    console.log("Interceptors Response")
    return response
  },

  async (error) => {
    const {
      config,
      response: { status },
    } = error
    const originalConfig = config
    if (status === 401) {
      if (error.response.data.message === "TokenExpired") {
        console.log("Interceptors Response Token Reissuing Start")
        const userInfo = await axios.post(
          "http://localhost:7777/auth/reissue",
          {},
          {
            headers: {
              Authorization: `Bearer ${token_service.getAccessToken()}`,
              refreshToken: `Bearer ${token_service.getRefreshToken()}`,
            },
          }
        )
        token_service.setTokens(userInfo.data)
        isTokenReissuing = false
        originalConfig.headers.Authorization = `Bearer ${token_service.getAccessToken()}`
        onTokenReissuing(token_service.getAccessToken())
        console.log("Interceptors Response Token Reissuing Finished")
      }
      return new Promise((resolve) => {
        addReissuingSubscriber(
          (accessToken = token_service.getAccessToken()) => {
            console.log("Interceptors Response Token Reissuing & Restart")
            originalConfig.headers.Authorization = "Bearer " + accessToken
            resolve(axios(originalConfig))
          }
        )
      })
    }
    return Promise.reject(error)
  }
)

export default axios

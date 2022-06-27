import axios from "axios"
import tokenService from "@/services/tokenService"
import userService from "@/services/userService"

const instance = axios.create({
  //baseURL: "http://localhost:7777/",
  timeout: 1000,
})

let isTokenReissuing = false
let reissuingSubscribers = []

function onTokenReissuing(accessToken) {
  reissuingSubscribers.map((callback) => callback(accessToken))
  reissuingSubscribers = []
}

function addReissuingSubscriber(callback) {
  reissuingSubscribers.push(callback)
}

async function tokenReissueAndRetry(error) {
  try {
    const { response: errorResponse } = error

    const retryOriginalRequest = new Promise((resolve, reject) => {
      addReissuingSubscriber(async (accessToken) => {
        try {
          errorResponse.config.headers.Authorization = `Bearer ${accessToken}`
          resolve(axios(errorResponse.config))
        } catch (error) {
          reject.error
        }
      })
    })

    if (!isTokenReissuing) {
      isTokenReissuing = true
      await reissuingTokens()
      isTokenReissuing = false

      onTokenReissuing(tokenService.getAccessToken())
    }
    return retryOriginalRequest
  } catch (error) {
    alert("로그인이 필요합니다.")
    logout()
    return Promise.reject()
  }
}

function logout() {
  userService.deleteUserInfo()
  tokenService.deleteTokens()
  window.location.href = "/login"
}

async function reissuingTokens() {
  const userInfo = await axios.post(
    "http://localhost:7777/auth/reissue",
    {},
    {
      headers: {
        Authorization: `Bearer ${tokenService.getAccessToken()}`,
        refreshToken: `Bearer ${tokenService.getRefreshToken()}`,
      },
    }
  )
  await tokenService.setTokens(userInfo.data)
}

axios.interceptors.request.use(
  async (config) => {
    // AccessToken이 만료되지 않은경우 Header에 Authorization 추가
    if (tokenService.getAccessToken()) {
      config.headers.Authorization = `Bearer ${tokenService.getAccessToken()}`
      return config
    }
  },
  async (error) => {
    return Promise.reject(error)
  }
)

axios.interceptors.response.use(
  async (response) => {
    return response
  },

  async (error) => {
    console.log(error)
    const {
      config,
      response: { status, data },
    } = error
    const originalConfig = config
    if (status === 401 && data === "token expired" && !originalConfig._retry) {
      originalConfig._retry = true
      return await tokenReissueAndRetry(error)
    }

    return Promise.reject(error)
  }
)

export default axios

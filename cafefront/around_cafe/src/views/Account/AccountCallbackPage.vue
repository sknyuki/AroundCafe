<template>
  <div />
</template>

<script>
import axios from "axios"
import tokenService from "@/services/tokenService"
import userService from "@/services/userService"

export default {
  name: "AccountCallbackPage",
  async beforeCreate() {
    const socialType = await this.$route.query.socialType
    const code = await this.$route.query.code
    if (code != null && socialType != null) {
      await this.getUserInfo(socialType, code)
    }
  },
  methods: {
    async getUserInfo(socialType, code) {
      const url = "http://localhost:5000/oauth/loginWithSNS"
      const body = {
        socialType: socialType,
        code: code,
      }
      const config = {
        headers: {
          "Content-Type": "Application/json",
        },
      }
      try {
        const unInterceptedAxiosInstance = axios.create()
        const response = await unInterceptedAxiosInstance.post(
          url,
          JSON.stringify(body),
          config
        )
        const userinfo = response.data
        tokenService.setTokens(userinfo)
        userService.setUserInfo(userinfo)
        opener.location.href = "http://localhost:8080/main"
        await window.self.close()
      } catch (error) {
        if (error.data != null) {
          alert(error.data)
        } else {
          console.log(error)
          alert("로그인 실패")
        }
      }
    },
  },
}
</script>

<style scoped></style>

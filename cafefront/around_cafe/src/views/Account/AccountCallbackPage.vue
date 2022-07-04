<template>
  <div />
</template>

<script>
import axios from "axios"
import tokenService from "@/services/tokenService"
import userService from "@/services/userService"

export default {
  name: "AccountCallbackPage",
  async created() {
    const [socialType, code] = await Promise.all([
      this.$route.query.socialType,
      this.$route.query.code,
    ])
    if (code != null && socialType != null) {
      await this.getUserInfo(socialType, code)
    } else {
      alert("로그인 실패")
      opener.location.href = "http://localhost:8080/main"
      window.self.close()
    }
  },
  methods: {
    async getUserInfo(socialType, code) {
      try {
        const response = await axios.post(
          "http://localhost:5000/oauth/loginWithSNS",
          JSON.stringify({
            socialType: socialType,
            code: code,
          }),
          {
            headers: {
              "Content-Type": "Application/json",
            },
          }
        )
        const userinfo = response.data
        tokenService.setTokens(userinfo)
        userService.setUserInfo(userinfo)
        opener.location.href = "http://localhost:8080/main"
        window.self.close()
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

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
    const [socialType, code] = await Promise.all([
      this.$route.query.socialType, this.$route.query.code
    ])
    if (code != null && socialType != null) {
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
    } else {
      opener.location.href = "http://localhost:8080/main"
      window.self.close()
    }
  },
}
</script>

<style scoped></style>

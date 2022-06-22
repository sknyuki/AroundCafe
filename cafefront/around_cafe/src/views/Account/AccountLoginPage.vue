<template>
  <AccountLogin @submit="onSubmit" />
</template>
<script>
import AccountLogin from "@/components/Account/AccountLogin.vue"
import axios from "axios"
import tokenService from "@/services/tokenService"
import userService from "@/services/userService"

export default {
  components: { AccountLogin },
  name: "AccountLoginPage",
  methods: {
    onSubmit(payload) {
      const unInterceptedAxiosInstance = axios.create()
      const { email, password } = payload
      unInterceptedAxiosInstance
        .post(`http://localhost:7777/auth/login`, { email, password })
        .then((response) => {
          const userinfo = response.data
          tokenService.setTokens(userinfo)
          userService.setUserInfo(userinfo)
          alert("로그인 완료!")
          this.$router.push("/main")
        })
        .catch((error) => {
          if (error.data != null) {
            alert(error.data)
          } else {
            alert("로그인 실패")
          }
        })
    },
  },
}
</script>

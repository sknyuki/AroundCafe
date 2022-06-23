<template>
  <AccountSignUp @submit="onSubmit" />
</template>
<script>
import AccountSignUp from "@/components/Account/AccountSignUp.vue"
import axios from "axios"
export default {
  components: { AccountSignUp },
  name: "AccountSignUpPage",
  methods: {
    onSubmit(payload) {
      const unInterceptedAxiosInstance = axios.create()
      const {
        email,
        password,
        memCheckPw,
        username,
        birth,
        socialType,
        role,
        cafeBisNo,
        phoneNum,
      } = payload

      unInterceptedAxiosInstance
        .post(`http://localhost:7777/auth/register`, {
          email,
          password,
          memCheckPw,
          username,
          birth,
          socialType,
          role,
          cafeBisNo,
          phoneNum,
        })
        .then(() => {
          alert("회원가입 완료!")
          this.$router.push("/login")
        })
        .catch(() => {
          alert("회원가입 실패")
        })
    },
  },
}
</script>

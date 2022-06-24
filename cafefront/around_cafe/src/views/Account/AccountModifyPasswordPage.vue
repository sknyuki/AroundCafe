<template>
  <AccountModifyPassword :email="email" @submit="onSubmit" />
</template>

<script>
import AccountModifyPassword from "@/components/Account/AccountModifyPassword"
import axios from "axios"

export default {
  components: { AccountModifyPassword },
  name: "AccountModifyPasswordPage",
  props: {
    email: { type: String, required: true },
  },
  methods: {
    onSubmit(payload) {
      const unInterceptedAxiosInstance = axios.create()

      const { memId, memPw } = payload

      unInterceptedAxiosInstance
        .put(`http://localhost:7777/auth/changePassword`, {
          memId,
          memPw,
        })
        .then(() => {
          alert("비밀번호 변경 완료!")
          this.$router.push("/login")
        })
        .catch(() => {
          alert("비밀번호 변경 실패")
        })
    },
  },
}
</script>

<style scoped></style>

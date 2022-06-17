<template>
  <AccountLogin @submit="onSubmit"/>
</template>
<script>
import AccountLogin from "@/components/Account/AccountLogin.vue"
import axios from "axios";
import token_service from "@/services/token_service";

export default {
  components: { AccountLogin },
  async beforeCreate() {
    const socialType = await this.$route.query.socialType
    const code = await this.$route.query.code
    if (code != null && socialType != null) {
      await this.getUserInfo(socialType,code)
    }
  },
  name: "AccountLoginPage",
  methods: {
    onSubmit(payload) {
      const {email, password} = payload
      axios.post(`http://localhost:7777/auth/login`, {email, password})
          .then((response) => {
            const userinfo = response.data
            token_service.setTokens(userinfo)
            alert('로그인 완료!')
            this.$router.push('/main')
          })
          .catch((error) => {
            if(error.data != null) {
              alert(error.data)
            } else {
              alert('로그인 실패')
            }
          })
    },
    async getUserInfo(socialType, code) {
      const url = 'http://localhost:5000/oauth/loginWithSNS'
      const body = {
        'socialType': socialType,
        'code': code
      }
      const config = {
        headers: {
          'Content-Type': 'Application/json'
        }
      }
      try {
        const response = await axios.post(url, JSON.stringify(body), config)
        const userinfo = response.data
        token_service.setTokens(userinfo)
        await this.$router.push('/main')
      } catch (error) {
        if(error.data != null) {
          alert(error.data)
        } else {
          alert('로그인 실패')
        }
      }
    }
  }

}
</script>

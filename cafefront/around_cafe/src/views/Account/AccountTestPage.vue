<template>
  <div class="sign-form">
    <input class="form-input input-48" v-model="time" />
    <v-btn @click="timerStart()">확인</v-btn>
    <div class="sign-form-label">{{ timeString }}</div>
    <v-btn @click="logout()">로그아웃</v-btn>
  </div>
</template>

<script>
import TimerBeforeHour from "@/utils/timer"
import tokenService from "@/services/tokenService";
import axios from "axios";
import userService from "@/services/userService";

export default {
  name: "AccountTestPage",
  data() {
    return {
      timerId: "",
      time: "",
    }
  },
  computed: {
    timeString: {
      get() {
        if (this.time > 0) {
          return TimerBeforeHour.timeToString(this.time)
        } else {
          clearInterval(this.timerId)
          return "finished"
        }
      },
    },
  },
  methods: {
    timerStart() {
      this.timerId = setInterval(() => {
        this.time--
      }, 1000)
    },
    logout() {
      const url = "http://localhost:7777/auth/logout"
      const refreshToken = tokenService.getRefreshToken()
      const config = {
        headers : {
          "refresh_token" : `${refreshToken}`,
        },
      }
      axios.delete(url, config).then(() => {
        userService.deleteUserInfo()
        tokenService.deleteTokens()
      }).catch((err) => {
        alert(err)
      })
    }
  },
}
</script>

<style lang="scss" scoped>
@import "~@/assets/scss/components/account/sign";
</style>

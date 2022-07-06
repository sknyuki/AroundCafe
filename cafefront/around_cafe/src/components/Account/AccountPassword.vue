<template>
  <div>
    <div class="password" v-show="!emailVerifyUse">
      <div class="password-container">
        <div>가입한 이메일 주소를 입력해주세요.</div>
        <div class="account-input">
          <input
            v-model="email"
            class="form-input input-48"
            type="email"
            placeholder="이메일"
            required
          />
          <v-btn
            @click="existByEmail(email)"
            :disabled="email.length < 1 || isEmailExists"
            class="password-btns"
            type="button"
            >확인</v-btn
          >
        </div>
        <v-btn
          @click="sendVerifyEmail(email)"
          :disabled="!isEmailExists"
          class="password-btn"
          type="submit"
          >이메일로 인증코드 받기</v-btn
        >
        <div class="password-support">
          회원가입 시 입력한 정보가 기억나지 않는다면?
          <a href="tel:1611-0828" aria-label="고객센터 전화하기"
            >고객센터 문의하기(1611-0828)</a
          >
        </div>
      </div>
    </div>
    <div class="password" v-show="emailVerifyUse">
      <div class="password-container">
        <div>이메일로 전송된 인증코드를 입력해주세요.</div>
        <div class="account-input">
          <input
            v-model="emailCode"
            class="form-input input-48"
            type="text"
            placeholder="인증코드 6자리 입력"
            required
          />
          <span>{{ timeString }}</span>
          <v-btn
            :disabled="emailCode.length < 1 || isEmailVerified || time === 0"
            class="password-btns"
            type="button"
            @click="checkEmailCode()"
            >확인</v-btn
          >
        </div>
        <div v-if="time <= 0">인증코드가 만료되었습니다.</div>
        <div style="margin-bottom: 10px">
          <div style="display: inline-block">이메일을 받지 못하셨나요?</div>
          <a
            style="
              display: inline-block;
              margin-left: 6px;
              font-weight: bold;
              text-decoration: underline;
            "
            @click="sendVerifyEmail(email)"
          >
            이메일 재전송하기</a
          >
        </div>
        <v-btn
          class="password-btn"
          type="button"
          :disabled="!isEmailExists || !isEmailVerified"
          @click="transfer2ModifyPasswordPage(email)"
        >
          비밀번호 재설정하기
        </v-btn>
        <div class="password-support">
          회원가입 시 입력한 정보가 기억나지 않는다면?
          <a href="tel:1611-0828">고객센터 문의하기(1611-0828)</a>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios"
import TimerBeforeHour from "@/utils/timer"

export default {
  name: "PasswordForm",
  data() {
    return {
      email: "",
      emailCode: "",
      emailCodeFromServer: "",
      time: 0,
      isEmailExists: false,
      isEmailVerified: false,
      emailVerifyUse: false,
    }
  },
  computed: {
    timeString: {
      get() {
        if (this.time > 0) {
          return TimerBeforeHour.timeToString(this.time)
        } else {
          clearInterval(this.timerId)
          return "00:00"
        }
      },
    },
  },
  methods: {
    //router-link : data 같이
    transfer2ModifyPasswordPage(email) {
      this.$router.push({
        name: "AccountModifyPasswordPage",
        params: { email: email },
      })
    },
    timerStart() {
      this.timerId = setInterval(() => {
        this.time--
        //this.timeString = TimerBeforeHour.timeToString(time)
      }, 1000)
    },
    checkEmailCode() {
      if (this.emailCode === this.emailCodeFromServer) {
        this.isEmailVerified = true
        alert("Email이 인증되셨습니다.")
      } else {
        alert("Email Code가 다릅니다. 다시 입력 바랍니다.")
      }
    },
    async sendVerifyEmail(email) {
      const url = "http://localhost:5000/mail/verifyEmail"
      const data = { email: email }
      const config = {
        headers: {
          "Content-Type": "Application/json",
        },
      }
      this.time = 180
      alert("인증 메일이 발송되었습니다.")
      this.timerStart()
      this.emailVerifyUse = true
      const unInterceptedAxiosInstance = axios.create()
      const code = await unInterceptedAxiosInstance.post(
        url,
        JSON.stringify(data),
        config
      )
      // 유저가 확인할 수 없는 장소가 어디일까요?
      // 확인요망 -- 애매하면 그냥 redis 서버에 저장
      this.emailCodeFromServer = code.data["code"]
    },
    existByEmail(email) {
      const unInterceptedAxiosInstance = axios.create()
      const url = "http://localhost:7777/auth/isExists"
      const data = {
        memId: email,
      }
      const config = {
        headers: {
          "Content-Type": "Application/json",
        },
      }
      unInterceptedAxiosInstance
        .post(url, JSON.stringify(data), config)
        .then((res) => {
          if (res.data === false) {
            this.isEmailExists = false
            alert("존재하지 않는 이메일입니다.")
          } else {
            alert("확인 완료")
            this.isEmailExists = true
          }
        })
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/account/password";
</style>

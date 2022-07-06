<template>
  <div class="login">
    <header class="login-header">
      <router-link :to="{ name: 'MainPage' }">
        <h2>Around Cafe</h2>
      </router-link>
    </header>

    <section class="login-section">
      <div>
        <form class="login-form" @submit.prevent="onSubmit" method="POST">
          <div class="login-form-member">
            <div class="login-form-id">
              <div class="account-input">
                <input
                  v-model="email"
                  class="form-input input-48"
                  type="email"
                  placeholder="이메일"
                  required
                />
              </div>
            </div>
            <div class="login-form-pw">
              <div class="account-input">
                <input
                  v-model="password"
                  class="form-input input-48"
                  type="password"
                  placeholder="비밀번호"
                  required
                />
              </div>
            </div>
          </div>

          <div class="login-button">
            <v-btn class="login-button-vuti" type="submit"> 로그인 </v-btn>
          </div>

          <div class="login-util">
            <div class="login-util-content">
              <ul>
                <li class="login-util-pw">
                  <router-link :to="{ name: 'AccountPasswordPage' }"
                    >비밀번호 재설정</router-link
                  >
                </li>
                <li class="login-util-sign">
                  <router-link :to="{ name: 'AccountSignUpPage' }"
                    >회원 가입</router-link
                  >
                </li>
              </ul>
            </div>
          </div>

          <section>
            <div class="login-social">SNS계정으로 간편 로그인/회원가입</div>
            <div class="login-social-group">
              <v-btn
                type="button"
                v-on:click="redirect2AuthServer('kakao')"
                medium
                fab
                color="yellow"
                aria-label="카카오 로그인 버튼"
              >
                <i class="icKaKaoTalk" aria-hidden="true"></i>
              </v-btn>
              <v-btn
                type="button"
                v-on:click="redirect2AuthServer('naver')"
                medium
                fab
                aria-label="네이버 로그인 버튼"
              >
                <i class="icNaver" aria-hidden="true"></i>
              </v-btn>
              <v-btn type="button" medium fab aria-label="페이스북 로그인 버튼">
                <i class="icFacebook" aria-hidden="true"></i>
              </v-btn>
              <v-btn type="button" medium fab aria-label="애플 로그인 버튼">
                <v-icon aria-hidden="true">mdi-apple</v-icon>
              </v-btn>
            </div>
          </section>
        </form>
      </div>
    </section>
  </div>
</template>
<script>
export default {
  name: "LoginForm",
  data() {
    return {
      email: "",
      password: "",
    }
  },
  methods: {
    onSubmit() {
      this.$emit("submit", { email: this.email, password: this.password })
    },
    redirect2AuthServer(socialType) {
      let url = `http://localhost:5000/oauth/${socialType}/login`
      // location=no는 신뢰할수 있는 사이트만 가능
      window.open(
        url,
        "_blank",
        "width=617, height=942, menubar=no, toolbar=no, location=no, status=no, scrollbars=no"
      )
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/account/login";
</style>

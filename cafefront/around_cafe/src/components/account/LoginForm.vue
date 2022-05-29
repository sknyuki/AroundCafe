<template>
  <div class="login">
    <header class="login-header">
      <h2>Arround Cafe</h2>
    </header>

    <section class="login-section">
      <div>
        <form class="login-form" action="/" method="POST">
          <div class="login-form-member">
            <div class="login-form-id">
              <input
                class="login-input"
                type="email"
                placeholder="이메일"
                required
              />
            </div>
            <div class="login-form-pw">
              <input
                class="login-input"
                type="password"
                placeholder="비밀번호"
                required
              />
            </div>
          </div>

          <div class="login-button">
            <v-btn class="login-button-vuti" type="submit"> 로그인 </v-btn>
          </div>

          <div class="login-util">
            <div class="login-util-content">
              <ul>
                <li class="login-util-pw">
                  <a href="">비밀번호 찾기</a>
                </li>
                <li class="login-util-sign">
                  <router-link :to="{ name: 'SignUpPage' }"
                    >회원 가입</router-link
                  >
                </li>
              </ul>
            </div>
          </div>

          <section>
            <div class="login-social">SNS계정으로 간편 로그인/회원가입</div>
            <div class="login-social-group">
              <v-btn medium fab color="yellow">
                <i class="icKaKaoTalk"></i>
              </v-btn>
              <v-btn medium fab>
                <i class="icNaver"></i>
              </v-btn>
              <v-btn medium fab>
                <i class="icFacebook"></i>
              </v-btn>
              <v-btn medium fab>
                <v-icon>mdi-apple</v-icon>
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
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/account/login";
</style>

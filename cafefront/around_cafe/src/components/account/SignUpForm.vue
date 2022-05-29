<template>
  <section class="sign">
    <div class="sign-container">
      <div class="sign-header">
        <h2>회원가입</h2>
      </div>
      <form>
        <div class="sign-email">
          <label for="">이메일</label>
          <div>
            <span>
              <input type="email" placeholder="이메일" value />
            </span>
          </div>
        </div>
        <div class="sign-email-btn">
          <v-btn>이메일 인증하기</v-btn>
        </div>
        <div></div>
      </form>
    </div>
  </section>
</template>
<script>
export default {
  name: "SignUpForm",
}
</script>
<style lang=""></style>

<template>
  <div class="password">
    <div class="password-container">
      <div>가입한 이메일 주소를 입력해주세요.</div>
      <div class="account-input">
        <input class="form-input input-48" type="email" placeholder="이메일" />
        <v-btn class="password-btns">확인</v-btn>
      </div>
      <v-btn class="password-btn">이메일로 인증코드 받기</v-btn>
      <div class="password-support">
        회원가입 시 입력한 정보가 기억나지 않는다면?
        <a href="tel:1611-0828">고객센터 문의하기(1611-0828)</a>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "PasswordForm",
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/account/password";
</style>

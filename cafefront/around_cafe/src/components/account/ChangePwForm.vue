<template>
  <div class="change-pw">
    <header class="change-header">
      <h2>비밀번호 변경</h2>
    </header>
    <section class="change-pw-section">
      <div>
        <form class="change-pw-form">
          <div>
            <label for="">새 비밀번호</label>
            <div class="sign-form-recommend">
              영문, 숫자를 포함한 8자 이상의 비밀번호를 입력해주세요.
            </div>
            <div class="account-input">
              <span>
                <input
                  class="form-input"
                  type="password"
                  placeholder="새 비밀번호"
                />
              </span>
            </div>
          </div>
          <div>
            <label for="">새 비밀번호 확인</label>
            <div class="account-input">
              <span>
                <input
                  class="form-input"
                  type="password"
                  placeholder="새 비밀번호 확인"
                />
              </span>
            </div>
          </div>
          <div class="sign-email-btn">
            <v-btn class="sign-button-vuti">비밀번호 변경</v-btn>
          </div>
        </form>
      </div>
    </section>
  </div>
</template>
<script>
export default {
  name: "ChangePwform",
}
</script>
<style lang="scss" scoped></style>

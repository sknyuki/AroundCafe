<template>
  <div class="modify-info">
    <header class="modify-info-header">
      <h2>회원정보수정</h2>
    </header>

    <form>
      <div class="modify-info-id">
        <label>이메일</label>
        <div class="account-input">
          <span>
            <input class="form-input" type="email" placeholder="이메일" />
          </span>
        </div>
      </div>
      <div class="modify-info-id">
        <label>이메일</label>
        <div class="account-input">
          <span>
            <input class="form-input" type="email" placeholder="이메일" />
          </span>
        </div>
      </div>
    </form>
  </div>
</template>
<script>
export default {
  name: "AccountModifyinfo",
}
</script>
<style lang="scss" scoped></style>

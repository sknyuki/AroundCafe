<template>
  <div class="edit">
    <header class="edit-header">
      <h2>회원정보수정</h2>
    </header>

    <form>
      <div class="edit-id">
        <label class="edit-id-label" for="">상호명(법인명)</label>
        <div class="account-input">
          <span>
            <input class="form-input" type="email" placeholder="이메일" />
          </span>
        </div>
      </div>
      <div class="edit-id">
        <label class="edit-id-label" for="">사업자등록번호</label>
        <div class="account-input">
          <span>
            <input class="form-input" type="email" placeholder="이메일" />
          </span>
        </div>
      </div>
      <div class="edit-id">
        <label class="edit-id-label" for="">이메일</label>
        <div class="account-input">
          <span>
            <input class="form-input" type="email" placeholder="이메일" />
          </span>
        </div>
      </div>

      <div class="edit-id">
        <label class="edit-id-label" for="">생년월일</label>
        <div class="account-input">
          <span>
            <input class="form-input" type="email" placeholder="이메일" />
          </span>
        </div>
      </div>
      <div class="edit-id">
        <label class="edit-id-label" for="">휴대폰 번호</label>
        <div class="account-input">
          <span>
            <input class="form-input" type="email" placeholder="이메일" />
          </span>
        </div>
      </div>
      <div class="edit-id">
        <label class="edit-id-label" for="">프로필 사진</label>
        <div class="account-input">
          <button>
            <img src="@/assets/images/img-user-default.png" alt="" />
          </button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  name: "EditInfoForm",
}
</script>

<style lang="scss" scoped></style>

<template>
  <div class="modify-info">
    <header class="modify-info-header">
      <h2>내 정보수정</h2>
      <button>탈퇴하기</button>
    </header>

    <form>
      <div class="modify-info-item">
        <div class="modify-info-title">
          이메일
          <div class="modify-info-title-require">* 필수항목</div>
        </div>
        <div class="modify-info-group">
          <div class="account-input">
            <span>
              <label>
                <input
                  class="form-input input-40"
                  type="email"
                  placeholder="이메일"
                />
              </label>
            </span>
          </div>
        </div>
      </div>
      <div class="modify-info-item">
        <div class="modify-info-title">
          닉네임
          <div class="modify-info-title-require">* 필수항목</div>
        </div>
        <div class="modify-info-group">
          <div class="account-input">
            <span>
              <label>
                <input
                  class="form-input input-40"
                  type="email"
                  placeholder="닉네임"
                />
              </label>
            </span>
          </div>
        </div>
      </div>
      <div class="modify-info-item">
        <div class="modify-info-title">
          휴대폰 번호
          <div class="modify-info-title-require">* 필수항목</div>
        </div>
        <div class="modify-info-group">
          <div class="account-input">
            <span>
              <label>
                <input
                  class="form-input input-40"
                  type="email"
                  placeholder="휴대폰 번호"
                />
              </label>
            </span>
          </div>
        </div>
      </div>
      <div class="modify-info-item">
        <div class="modify-info-title">생년월일</div>
        <div class="modify-info-group">
          <div class="account-input">
            <span>
              <label>
                <input
                  class="form-input input-40"
                  type="email"
                  placeholder="YYYY-MM-DD"
                />
              </label>
            </span>
          </div>
        </div>
      </div>
      <div class="modify-info-item">
        <div class="modify-info-title">프로필 이미지</div>
        <div class="modify-info-group">
          <button @click="onUpload" type="button" aria-label="사진 첨부하기">
            <img v-if="image != ''" :src="image" />
            <img
              v-if="image == ''"
              src="@/assets/images/avatar.webp"
              alt="기본 이미지"
            />
          </button>
          <v-btn
            @click="imageDelete"
            class="btn-red"
            v-if="image != ''"
            small
            aria-label="첨부 파일 삭제하기"
          >
            <v-icon>mdi-delete </v-icon>삭제
          </v-btn>
          <input
            class="visually-hidden"
            id="files"
            type="file"
            ref="files"
            v-if="uploadReady"
            @change="userFileUpload()"
          />
        </div>
      </div>
      <v-btn class="btn-indigo btn-48">회원 정보 수정</v-btn>
    </form>
  </div>
</template>
<script>
export default {
  name: "AccountModifyinfo",
  data() {
    return {
      image: "",
      files: "",
      uploadReady: true,
    }
  },
  methods: {
    onUpload() {
      this.$refs.files.click()
    },
    userFileUpload() {
      var image = this.$refs["files"].files[0]
      this.image = URL.createObjectURL(image)
      this.files = this.$refs.files.files[0]
    },

    imageDelete() {
      this.image = ""

      this.clearImage()
    },
    clearImage() {
      this.uploadReady = false
      this.$nextTick(() => {
        this.uploadReady = true
      })
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/account/modify";
</style>

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
                  v-model="memId"
                  disabled
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
                  v-model="memNick"
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
                  v-model="phoneNum"
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
                  v-model="memBirth"
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
            <template v-if="image.length > 0">
              <img v-if="image != ''" :src="image" />
            </template>
            <template v-else>
              <img
                v-if="image == '' || memImg == ''"
                :src="require(`@/assets/images/avatar.webp`)"
                alt="기본 이미지"
              />
              <img v-if="findKakao > 5 && memImg != ''" :src="memImg" />
              <img
                v-if="findKakao < 0"
                v-bind:src="require(`@/assets/images/memberImg/${memImg}`)"
              />
            </template>
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
      <v-btn class="btn-indigo btn-48" @click="onSumbit">회원 정보 수정</v-btn>
    </form>
    <!-- <div v-if="userInfo.socialType == 'LOCAL'">
        등록된 이미지
        <img
          v-if="userInfo.memImg != null"
          v-bind:src="require(`@/assets/images/memberImg/${userInfo.memImg}`)"
        />
        <img v-else src="@/assets/images/memberImg/noMemberImg.png" />
      </div>
      <div>
        <img v-if="userInfo.socialType == 'KAKAO'" src="userInfo.memImg" />
      </div> -->
    <!-- <img v-if="userInfo.socialType == 'KAKAO'" :src="userInfo.memImg" />
    <img
      v-if="userInfo.socialType == 'LOCAL'"
      v-bind:src="require(`@/assets/images/memberImg/${userInfo.memImg}`)"
      style="width: 30px"
    />
    <img
      v-if="userInfo.memImg == null"
      src="@/assets/images/memberImg/noMemberImg.png"
      style="width: 20px"
    /> -->
  </div>
</template>
<script>
import { mapState, mapActions } from "vuex"

export default {
  name: "AccountModifyinfo",
  data() {
    return {
      image: "",
      files: "",
      uploadReady: true,
      memId: "",
      memNick: "",
      phoneNum: "",
      memBirth: "",
      memImg: "",
      findKakao: "",
    }
  },
  computed: {
    ...mapState(["userInfo"]),
  },
  mounted() {
    this.fetchUserInfo()
    setTimeout(this.change, 70)
  },
  methods: {
    change() {
      this.memId = this.userInfo.memId
      this.memNick = this.userInfo.memNick
      this.phoneNum = this.userInfo.phoneNum
      this.memBirth = this.userInfo.memBirth
      this.memImg = this.userInfo.memImg
      this.findKakao = this.memImg.indexOf("k.kakaocdn.net")
    },
    ...mapActions(["fetchUserInfo"]),
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
      this.memImg = ""

      this.clearImage()
    },
    clearImage() {
      this.uploadReady = false
      this.$nextTick(() => {
        this.uploadReady = true
      })
    },
    onSumbit() {
      var result = confirm("등록 하시겠습니까?")
      if (result) {
        const { memId, memNick, phoneNum, memBirth, files } = this
        this.$emit("submitContents", {
          memId,
          memNick,
          phoneNum,
          memBirth,
          files,
        })
      }
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/account/modify";
</style>

<template>
  <div class="menu-register-content">
    <header class="menu-register-header">
      <h1>메뉴 관리</h1>
    </header>
    <div class="menu-register-group">
      <v-btn class="btn-indigo btn-40" @click="onMenuRegister" type="submit"
        >메뉴 등록</v-btn
      >
    </div>
    <form class="menu-register-form">
      <v-dialog class="menu-register-dialog" v-model="menuDialog">
        <v-card class="menu-register-card">
          <div class="menu-register-file">
            <div class="men-register-file header">사진 첨부</div>

            <div class="review-card-btn">
              <div class="select-picture" v-if="image">
                <img
                  class="select-picture-contents"
                  :src="image"
                  alt="첨부한 사진"
                />
                <button
                  @click="imageDelete"
                  class="delete-button"
                  type="button"
                  aria-label="첨부 파일 삭제하기"
                >
                  <i class="icClose" aria-hidden="true"></i>
                </button>
              </div>
              <input
                class="visually-hidden"
                id="files"
                type="file"
                ref="files"
                multiple
                v-if="uploadReady"
                v-on:change="commentFileUpload()"
              />
              <v-btn
                class="btn-indigo btn-32"
                @click="onUpload"
                aria-label="사진 첨부하기"
              >
                사진 첨부하기
              </v-btn>
            </div>
          </div>
          <div class="menu-register-name">
            <label for="">메뉴 이름</label>
            <div class="account-input">
              <span>
                <input
                  v-model="menu_name"
                  class="form-input input-32"
                  type="text"
                  placeholder="메뉴 이름"
                />
              </span>
            </div>
          </div>
          <div class="menu-register-price">
            <label for="">메뉴 가격</label>
            <div class="account-input">
              <span>
                <input
                  v-model="menu_price"
                  class="form-input input-32"
                  type="text"
                  placeholder="메뉴 가격"
                />
              </span>
            </div>
          </div>
          <div class="menu-register-int">
            <div class="menu-register-int-header">메뉴 소개</div>
            <textarea
              v-model="menu_content"
              class="form-control"
              minlength="20"
              maxlength="100"
              cols="120"
              rows="32"
              placeholder="메뉴 소개"
            ></textarea>
          </div>
          <div class="menu-register-btn">
            <v-btn class="btn-indigo btn-32" @click="onSubmitMenu"
              >등록하기</v-btn
            >
            <v-btn
              @click="menuDialog = false"
              class="btn-red btn-32"
              type="button"
              >취소하기</v-btn
            >
          </div>
        </v-card>
      </v-dialog>
    </form>
  </div>
</template>

<script>
export default {
  name: "CafeRegisterDialog",

  data() {
    return {
      menu_name: "",
      menu_price: "",
      menu_content: "",
      image: "",
      files: [],
      menuDialog: false,
      uploadReady: true,
      modi_name: "",
      per_menu_quantity: 1,
      per_menu_total_price: "",
    }
  },
  methods: {
    onMenuRegister() {
      this.menuDialog = true
    },
    imageDelete() {
      this.image = null

      this.clearImage()
    },
    clearImage() {
      this.uploadReady = false
      this.$nextTick(() => {
        this.uploadReady = true
      })
    },
    onUpload() {
      this.$refs.files.click()
    },
    commentFileUpload() {
      var image = this.$refs["files"].files[0]
      this.image = URL.createObjectURL(image)
      this.files = this.$refs.files.files[0]
    },
    onSubmitMenu() {
      this.per_menu_total_price = this.menu_price
      const {
        menu_name,
        menu_price,
        menu_content,
        per_menu_quantity,
        per_menu_total_price,
      } = this
      const file = this.$refs.files.files[0]
      this.$emit("submit", {
        menu_name,
        menu_price,
        menu_content,
        per_menu_quantity,
        per_menu_total_price,
        file,
      })
    },
  },
  watch: {
    menuDialog: function (val) {
      if (val) {
        this.menu_name = ""
        this.menu_price = ""
        this.menu_content = ""
        this.per_menu_quantity = 1
        this.per_menu_total_price = ""
        this.image = null
        this.clearImage()
      }
    },
  },
}
</script>

<style lang="scss" scoped>
@import "~@/assets/scss/components/cafe/cafe-menu";
</style>

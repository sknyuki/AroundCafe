<template>
  <div class="cafe-register">
    <ImgBox class="mb-10" />
    <div class="container">
      <div class="row">
        <div class="col-sm-4 col-md-3 col-lg-3">
          <CafeSidebar />
        </div>
        <div class="col-sm-8 col-md-9 col-lg-9">
          <header>
            <h1>카페 관리</h1>
          </header>
          <form class="cafe-register-form" action="">
            <div class="cafe-register-name">
              <label for="">카페 이름</label>
              <div class="account-input">
                <span>
                  <input
                    class="form-input"
                    type="text"
                    placeholder="카페 이름"
                  />
                </span>
              </div>
            </div>
            <div class="cafe-register-phone">
              <label for="">연락처</label>
              <div class="account-input">
                <span>
                  <input class="form-input" type="text" placeholder="연락처" />
                </span>
              </div>
            </div>
            <div class="cafe-register-adr">
              <label for="">주소</label>
              <div class="account-input">
                <span>
                  <input class="form-input" type="text" placeholder="주소" />
                </span>
              </div>
            </div>
            <div class="cafe-register-time">
              <label for="">영업 시간</label>
              <div class="account-input">
                <span>
                  <input
                    class="form-input"
                    type="text"
                    placeholder="영업시간"
                  />
                </span>
              </div>
            </div>
            <div class="cafe-register-int">
              <div class="cafe-register-int-header">카페 소개</div>
              <textarea
                v-model="comment"
                style="border-radius: 4px"
                class="form-control"
                minlength="20"
                maxlength="1000"
                cols="120"
                rows="32"
                placeholder="카페 소개하는 글 작성"
              ></textarea>
            </div>
            <div class="cafe-register-img">
              <div class="cafe-register-img-header">카페 사진</div>
              <div class="cafe-register-img-content">
                <div v-if="image" class="cafe-register-img-preview">
                  <img :src="image" alt="첨부한사진" />
                </div>
                <div v-else @click="clickInputTag()">
                  <button>
                    <div>
                      <span>사진 올리기</span>
                      <input
                        @change="uploadImage()"
                        multiple="multiple"
                        name="image"
                        class="visually-hidden"
                        type="file"
                        id="files"
                        ref="files"
                      />
                    </div>
                  </button>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import CafeSidebar from "@/components/cafe/CafeSidebar.vue"
import ImgBox from "../mypage/ImgBox.vue"
import axios from "axios"

export default {
  components: { ImgBox, CafeSidebar },
  name: "CafeRegisterForm",

  data() {
    return {
      images: "",
      comment: "",
      files: [],
    }
  },
  methods: {
    uploadImage() {
      let form = new FormData()
      let image = this.$refs["image"].files[0]

      form.append("image", image)

      axios
        .post("/upload", form, {
          header: { "Content-Type": "multipart/form-data" },
        })
        .then(({ data }) => {
          this.images = data
        })
        .catch((err) => console.log(err))
    },

    clickInputTag() {
      this.$refs.files.click()
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/cafe/cafe-register";
</style>

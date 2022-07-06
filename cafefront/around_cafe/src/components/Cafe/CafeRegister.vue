<template>
  <div class="cafe-register">
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <ImgBox />
        </div>
        <div class="col-sm-4 col-md-3 col-lg-3">
          <CafeSidebar />
        </div>
        <div class="col-sm-8 col-md-9 col-lg-9">
          <header>
            <h1>카페 관리</h1>
          </header>
          <form class="cafe-register-form" @submit.prevent="onSubmit">
            <div class="cafe-register-name">
              <label for="">카페 이름</label>
              <div class="account-input">
                <span>
                  <input
                    v-model="cafeName"
                    class="form-input input-40"
                    type="text"
                    placeholder="카페 이름"
                  />
                </span>
              </div>
            </div>
            <div class="cafe-register-phone">
              <label for="">사업자등록증</label>
              <div class="account-input">
                <span>
                  <input
                    v-model="cafeBusNo"
                    class="form-input input-40"
                    type="text"
                    placeholder="사업자등록증"
                  />
                </span>
              </div>
            </div>
            <div class="cafe-register-phone">
              <label>연락처</label>
              <div class="account-input">
                <span>
                  <input
                    v-model="cafeCall"
                    class="form-input input-40"
                    type="text"
                    placeholder="연락처"
                  />
                </span>
              </div>
            </div>
            <div class="cafe-register-adr">
              <label>주소</label>
              <div class="account-input">
                <span>
                  <CafeAddress />
                  <input
                    type="text"
                    placeholder="우편번호"
                    class="form-input input-40"
                    v-model="post"
                    readonly
                    style="margin-right: 10px"
                  />
                  <input
                    type="text"
                    placeholder="상세주소"
                    class="form-input input-40"
                    v-model="address"
                  />
                </span>
              </div>
            </div>
            <div class="cafe-register-time">
              <label for="">영업 시간</label>
              <div class="account-input">
                <span>
                  <input
                    v-model="cafeTime"
                    class="form-input input-40"
                    type="text"
                    placeholder="영업시간"
                  />
                </span>
              </div>
            </div>
            <div class="cafe-register-int">
              <div class="cafe-register-int-header">카페 소개</div>
              <textarea
                @keyup="charCount()"
                v-model="cafeContent"
                class="form-control"
                minlength="20"
                maxlength="1000"
                cols="120"
                rows="32"
                placeholder="카페 소개하는 글"
              ></textarea>
              <span>{{ totalcharacter }} / 1000</span>
            </div>
            <div>내가 등록한 이미지 보기</div>
            <div
              v-for="(item, index) in cafeImgLists"
              :key="index"
              class="select-picture"
            >
              <img
                v-bind:src="
                  require(`@/assets/cafe/cafeMypage/${item.cafe_img}`)
                "
                width="280px;"
                class="select-picture-contents"
              />
            </div>
            <div>
              <div v-if="!files.length" class="cafe-register-image">
                <button
                  @click="onUpload"
                  type="button"
                  aria-label="사진 첨부하기"
                >
                  <div class="cafe-register-image wrap">
                    <v-icon>mdi-camera</v-icon>
                    <span>사진 올리기</span>
                    <span>(*최대 10장까지)</span>
                  </div>
                </button>
                <input
                  @change="imageUpload"
                  class="visually-hidden"
                  ref="files"
                  type="file"
                  id="file"
                  multiple
                />
              </div>

              <div v-else>
                <div>
                  <div
                    v-for="(file, index) in files"
                    :key="index"
                    class="select-picture"
                  >
                    <button
                      @click="fileDeleteButton"
                      :name="file.number"
                      class="delete-button"
                      type="button"
                      aria-label="첨부 파일 삭제하기"
                    >
                      <i class="icClose" aria-hidden="true"></i>
                    </button>
                    <img :src="file.preview" class="select-picture-contents" />
                  </div>
                  <div class="cafe-register-contents">
                    <div>
                      <v-btn @click="onUpload" class="btn-outlined btn-40"
                        >사진 추가하기</v-btn
                      >
                      <input
                        @change="imageAddUpload"
                        class="visually-hidden"
                        ref="files"
                        type="file"
                        id="file"
                        multiple
                      />
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <v-btn class="btn-indigo btn-40" type="submit">등록하기 </v-btn>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import CafeSidebar from "@/components/Cafe/CafeSidebar.vue"
import CafeAddress from "@/components/Cafe/CafeAddress.vue"
import ImgBox from "@/components/ImgBox.vue"
import { EventBus } from "@/utils/eventBus"

export default {
  components: { ImgBox, CafeSidebar, CafeAddress },
  name: "CafeRegister",
  props: {
    cafeBoard: {
      type: Object,
      required: true,
    },
    cafeImgLists: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      images: "",
      comment: "",
      files: [],
      isDragged: false,
      post: "",
      address: "",

      totalcharacter: 0,
      cafeName: "",
      cafeBusNo: "",
      cafeTime: "",
      cafeContent: "",
      cafeCall: "",
    }
  },
  mounted() {
    setTimeout(this.change, 50)
    EventBus.$on("findPostalcode", ({ post, address }) => {
      this.post = post
      this.address = address
    })
  },

  methods: {
    change() {
      this.cafeName = this.cafeBoard.cafe_name
      this.cafeBusNo = this.cafeBoard.cafe_bis_no
      this.cafeTime = this.cafeBoard.cafe_time
      this.cafeContent = this.cafeBoard.cafe_content
      this.cafeCall = this.cafeBoard.cafe_call
      this.post = this.cafeBoard.post
      this.address = this.cafeBoard.address
    },
    onUpload() {
      this.$refs.files.click()
    },

    imageUpload() {
      console.log(this.$refs.files.files)

      let num = -1
      for (let i = 0; i < this.$refs.files.files.length; i++) {
        this.files = [
          ...this.files,
          {
            file: this.$refs.files.files[i],
            preview: URL.createObjectURL(this.$refs.files.files[i]),
            number: i,
          },
        ]
        num = i
      }
      this.uploadImageIndex = num + 1
      console.log(this.files)
    },

    imageAddUpload() {
      console.log(this.$refs.files.files)

      let num = -1
      for (let i = 0; i < this.$refs.files.files.length; i++) {
        console.log(this.uploadImageIndex)
        this.files = [
          ...this.files,
          {
            file: this.$refs.files.files[i],
            preview: URL.createObjectURL(this.$refs.files.files[i]),
            number: i + this.uploadImageIndex,
          },
        ]
        num = i
      }
      this.uploadImageIndex = this.uploadImageIndex + num + 1

      console.log(this.files)
      // console.log(this.filesPreview);
    },
    fileDeleteButton(e) {
      const name = e.target.getAttribute("name")
      this.files = this.files.filter((data) => data.number !== Number(name))
      console.log(this.files)
    },
    onSubmit() {
      var result = confirm("등록 하시겠습니까?")
      if (result) {
        const {
          cafeName,
          cafeBusNo,
          cafeTime,
          cafeContent,
          cafeCall,
          post,
          address,
          files,
        } = this
        this.$emit("submitContents", {
          cafeName,
          cafeBusNo,
          cafeTime,
          cafeContent,
          cafeCall,
          post,
          address,
          files,
        })
      }
    },
    charCount() {
      this.totalcharacter = this.cafeContent.length
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/cafe/cafe-register";
</style>

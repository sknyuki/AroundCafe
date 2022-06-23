/* eslint-disable no-debugger */
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
          <form class="cafe-register-form" action="" @submit.prevent="onSubmit">
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
              <label for="">연락처</label>
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
              <label for="">주소</label>
              <div class="account-input">
                <span>
                  <td width="0">
                    <v-select
                      v-model="cafeAdr1"
                      :items="states1"
                      label="시"
                      dense
                      solo
                      style="width: 120px; font-size: 15px"
                    ></v-select>
                  </td>

                  <td width="0">
                    <v-select
                      v-model="cafeAdr2"
                      :items="states2"
                      label="구"
                      dense
                      solo
                      style="width: 120px; font-size: 15px"
                    ></v-select>
                  </td>
                  <td width="0">
                    <input
                      v-model="cafeAdr3"
                      class="form-input"
                      type="text"
                      placeholder="상세주소"
                    />
                  </td>
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
                  multiple
                  id="file"
                  type="file"
                  ref="files"
                  class="visually-hidden"
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
                      aria-label="첨부 파일 삭제하기"
                      type="button"
                    >
                      <i class="icClose"></i>
                    </button>
                    <img class="select-picture-contents" :src="file.preview" />
                  </div>
                  <div class="cafe-register-contents">
                    <div>
                      <v-btn class="btn-outlined btn-40" @click="onUpload"
                        >사진 추가하기</v-btn
                      >
                      <input
                        class="visually-hidden"
                        type="file"
                        id="file"
                        ref="files"
                        @change="imageAddUpload"
                        multiple
                      />
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div v-for="(item, index) in cafeImgLists" :key="index">
              <img
                v-bind:src="
                  require(`@/assets/cafe/cafeMypage/${item.cafe_img}`)
                "
                width="280px;"
              />
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
import ImgBox from "@/components/ImgBox.vue"

export default {
  components: { ImgBox, CafeSidebar },
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
      cafeName: "",
      cafeBusNo: "",
      cafeTime: "",
      cafeContent: "",
      cafeCall: "",
      cafeAdr1: "",
      cafeAdr2: "",
      cafeAdr3: "",
      states1: ["서울시"],
      states2: [
        "강남구",
        "강동구",
        "강북구",
        "강서구",
        "관악구",
        "광진구",
        "구로구",
        "금천구",
        "노원구",
        "도봉구",
        "동대문구",
        "동작구",
        "마포구",
        "서대문구",
        "서초구",
        "성동구",
        "성북구",
        "송파구",
        "양천구",
        "영등포구",
        "용산구",
        "은평구",
        "종로구",
        "중구",
        "중랑구",
      ],
      totalcharacter: 0,
    }
  },
  created() {
    this.cafeName = this.cafeBoard.cafe_name
    this.cafeBusNo = this.cafeBoard.cafe_bis_no
    this.cafeTime = this.cafeBoard.cafe_time
    this.cafeContent = this.cafeBoard.cafe_content
    this.cafeCall = this.cafeBoard.cafe_call
    this.cafeAdr1 = this.cafeBoard.cafe_adr1
    this.cafeAdr2 = this.cafeBoard.cafe_adr2
    this.cafeAdr3 = this.cafeBoard.cafe_adr3
  },
  methods: {
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
          cafeAdr1,
          cafeAdr2,
          cafeAdr3,
          files,
        } = this
        this.$emit("submitContents", {
          cafeName,
          cafeBusNo,
          cafeTime,
          cafeContent,
          cafeCall,
          cafeAdr1,
          cafeAdr2,
          cafeAdr3,
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

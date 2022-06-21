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
                class="form-control"
                minlength="20"
                maxlength="1000"
                cols="120"
                rows="32"
                placeholder="카페 소개하는 글 작성"
              ></textarea>
            </div>
            <div>
              <div
                v-if="!files.length"
                class="room-file-upload-example-container"
              >
                <div class="room-file-upload-example">
                  <div class="room-file-notice-item room-file-upload-button">
                    <div class="image-box">
                      <!-- <div class="image-profile">
                              <img :src="profileImage" />
                              </div>-->
                      <label for="file">일반 사진 등록</label>
                      <input
                        type="file"
                        id="file"
                        ref="files"
                        @change="imageUpload"
                        multiple
                      />
                    </div>
                  </div>
                </div>
              </div>
              <div v-else class="file-preview-content-container">
                <div class="file-preview-container">
                  <div
                    v-for="(file, index) in files"
                    :key="index"
                    class="file-preview-wrapper"
                  >
                    <div
                      class="file-close-button"
                      @click="fileDeleteButton"
                      :name="file.number"
                    >
                      x
                    </div>
                    <img :src="file.preview" />
                  </div>
                  <div class="file-preview-wrapper-upload">
                    <div class="image-box">
                      <label for="file">추가 사진 등록</label>
                      <input
                        type="file"
                        id="file"
                        ref="files"
                        @change="imageAddUpload"
                        multiple
                      />
                    </div>
                    <!-- <div class="file-close-button" @click="fileDeleteButton" :name="file.number">x</div> -->
                  </div>
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
import CafeSidebar from "@/components/Cafe/CafeSidebar.vue"
import ImgBox from "@/components/ImgBox.vue"

export default {
  components: { ImgBox, CafeSidebar },
  name: "CafeRegisterForm",
  props: {
    cafeBoard: {
      type: Object,
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
    imageUpload() {
      console.log(this.$refs.files.files)

      // this.files = [...this.files, this.$refs.files.files];
      //하나의 배열로 넣기
      let num = -1
      for (let i = 0; i < this.$refs.files.files.length; i++) {
        this.files = [
          ...this.files,
          //이미지 업로드
          {
            //실제 파일
            file: this.$refs.files.files[i],
            //이미지 프리뷰
            preview: URL.createObjectURL(this.$refs.files.files[i]),
            //삭제및 관리를 위한 number
            number: i,
          },
        ]
        num = i
        //이미지 업로드용 프리뷰
        // this.filesPreview = [
        //   ...this.filesPreview,
        //   { file: URL.createObjectURL(this.$refs.files.files[i]), number: i }
        // ];
      }
      this.uploadImageIndex = num + 1 //이미지 index의 마지막 값 + 1 저장
      console.log(this.files)
      // console.log(this.filesPreview);
    },

    imageAddUpload() {
      console.log(this.$refs.files.files)

      // this.files = [...this.files, this.$refs.files.files];
      //하나의 배열로 넣기c
      let num = -1
      for (let i = 0; i < this.$refs.files.files.length; i++) {
        console.log(this.uploadImageIndex)
        this.files = [
          ...this.files,
          //이미지 업로드
          {
            //실제 파일
            file: this.$refs.files.files[i],
            //이미지 프리뷰
            preview: URL.createObjectURL(this.$refs.files.files[i]),
            //삭제및 관리를 위한 number
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
      // console.log(this.files);
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/cafe/cafe-register";
</style>

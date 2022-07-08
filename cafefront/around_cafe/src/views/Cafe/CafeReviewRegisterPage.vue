<template>
  <div>
    <a @click="onReviewDialog">리뷰쓰기</a>
    <v-dialog max-width="750" v-model="reviewDialog">
      <CafeReviewDialog @submit="onSubmit" @input="close" />
    </v-dialog>
  </div>
</template>

<script>
import axios from "axios"
import CafeReviewDialog from "@/components/CafeReview/CafeReviewDialog"
export default {
  name: "CafeReviewRegisterPage",
  components: {
    CafeReviewDialog,
  },
  data() {
    return {
      reviewDialog: false,
      membNo: this.$store.state.user.memNo,
    }
  },
  props: {
    cafeNo: {
      type: String,
      required: true,
    },
  },
  methods: {
    onSubmit(payload) {
      const { star_score, review_content, file } = payload
      let formData = new FormData()
      if (file != null) {
        formData.append("file", file)
      }
      formData.append("star_score", star_score)
      formData.append("review_content", review_content)
      formData.append("cafeNum", this.cafeNo)

      let membNo = this.membNo
      axios
        .post(
          `http://localhost:7777/cafe/review/register/${membNo}`,
          formData,
          {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          }
        )
        .then(() => {
          alert("Successfully submitted")
          this.$router.go()
        })
        .catch(() => {
          console.log("문제 발생!")
        })
    },
    onReviewDialog() {
      if (this.membNo == null) {
        alert("로그인을 해주세요!")
      } else this.reviewDialog = true
    },
    close() {
      this.reviewDialog = false
    },
  },
}
</script>

<style lang="scss" scoped>
::v-deep .v-dialog {
  @include no-scrollbar;
  overflow-y: auto;
  overflow-x: hidden;
}
</style>
e>

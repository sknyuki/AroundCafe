<template>
  <v-container class="writeForm">
    <v-row>
      <v-btn @click="onReviewDialog">Review</v-btn>
      <v-dialog max-width="750" v-model="reviewDialog">
        <CafeReviewDialog @submit="onSubmit" />
      </v-dialog>
    </v-row>
  </v-container>
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
    }
  },

  methods: {
    onSubmit(payload) {
      const { star_score, review_content, cafeNum, file } = payload
      let formData = new FormData()
      if (file != null) {
        formData.append("file", file)
      }
      formData.append("star_score", star_score)
      formData.append("review_content", review_content)
      formData.append("cafeNum", cafeNum)

      axios
        .post(`http://localhost:7777/cafe/review/register`, formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then(() => {
          alert("Successfully submitted")
          this.$router.push({
            name: "CafeReviewListPage",
          })
        })
        .catch(() => {
          alert("문제 발생!")
        })
    },
    onReviewDialog() {
      this.reviewDialog = true
    },
    closeDialog() {
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

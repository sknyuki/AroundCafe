<template>
  <div>
    <CafeReviewList :reviewList="reviewList" />
  </div>
</template>
<script>
import CafeReviewList from "@/components/CafeReview/CafeReviewList.vue"
import axios from "axios"

export default {
  components: { CafeReviewList },
  name: "CafeReviewListPage",
  data() {
    return {
      writer: JSON.parse(localStorage.getItem("user")).memNo,
      cafeNo: JSON.parse(localStorage.getItem("user")).cafeNo,
      reviewList: [],
    }
  },
  mounted() {
    this.fetchReviewList(this.cafeNo, this.writer)
  },

  methods: {
    fetchReviewList(cafeNo, memNo) {
      if (memNo == null) {
        this.memNo = 0
      }

      axios
        .get(`http://localhost:7777/cafe/review/list/${cafeNo}/${memNo}`)
        .then((res) => {
          this.reviewList = res.data
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

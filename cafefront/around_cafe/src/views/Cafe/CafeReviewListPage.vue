<template>
  <div>
    <CafeReviewList :reviewList="reviewList" :myHelps="myHelps" />
  </div>
</template>
<script>
import CafeReviewList from "@/components/CafeReview/CafeReviewList.vue"
import { mapActions, mapState } from "vuex"
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
  computed: {
    ...mapState(["reviews", "myHelps"]),
  },

  mounted() {
    this.fetchMyHelpsList(this.writer)
    setTimeout(() => {
      this.fetchReviewList(this.cafeNo, this.writer)
    }, 10)
  },

  methods: {
    ...mapActions(["fetchMyHelpsList"]),
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

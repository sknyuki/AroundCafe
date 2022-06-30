<template>
  <div>
    <CafeReviewList :reviews="reviews" :myHelps="myHelps" />
  </div>
</template>
<script>
import CafeReviewList from "@/components/CafeReview/CafeReviewList.vue"
import { mapActions, mapState } from "vuex"

export default {
  components: { CafeReviewList },
  name: "CafeReviewListPage",
  data() {
    return {
      writer: JSON.parse(localStorage.getItem("user")).memNo,
      cafeNo: JSON.parse(localStorage.getItem("user")).cafeNo,
    }
  },
  computed: {
    ...mapState(["reviews", "myHelps"]),
  },

  mounted() {
    this.fetchReviewList(this.cafeNo)
    this.fetchMyHelpsList(this.writer)
  },

  methods: {
    ...mapActions(["fetchReviewList", "fetchMyHelpsList"]),

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

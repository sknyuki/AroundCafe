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
  computed: {
    ...mapState(["reviews", "myHelps"]),
  },

  mounted() {
    this.fetchReviewList(1)
    this.fetchMyHelpsList(1)
    this.fetchLikesList(1)
  },

  methods: {
    ...mapActions(["fetchReviewList", "fetchMyHelpsList", "fetchLikesList"]),

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

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
    this.fetchReviewList(2)
    this.fetchMyHelpsList(3)
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

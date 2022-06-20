<template>
  <div>
    <CafeReviewList :reviews="reviews" />
  </div>
</template>
<script>
import CafeReviewList from "@/components/Cafe/CafeReviewList.vue"
import { mapActions, mapState } from "vuex"

export default {
  components: { CafeReviewList },
  name: "CafeReviewListPage",
  computed: {
    ...mapState(["reviews"]),
  },

  mounted() {
    this.fetchReviewList(2)
  },

  methods: {
    ...mapActions(["fetchReviewList"]),

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

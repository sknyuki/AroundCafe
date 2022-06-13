<template>
  <div>
    <v-btn @click="onReviewDialog">테스트</v-btn>
    <v-dialog max-width="750" v-model="reviewDialog">
      <cafe-review-dialog @close="closeDialog" />
    </v-dialog>
  </div>
</template>

<script>
import CafeReviewDialog from "@/components/Cafe/CafeReviewDialog"
export default {
  name: "TestTest",
  components: {
    CafeReviewDialog,
  },
  data() {
    return {
      reviewDialog: false,
    }
  },
  methods: {
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

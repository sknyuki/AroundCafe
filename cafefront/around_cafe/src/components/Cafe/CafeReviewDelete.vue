<template>
  <button
    class="delete-button"
    aria-label="해당 리뷰 삭제하기"
    type="button"
    v-if="this.memNic == this.review.memNick || this.checkRole == 'CAFE'"
  >
    <i @click="onDelete()" class="icClose"></i>
  </button>
</template>

<script>
import axios from "axios"

export default {
  name: "CafeReviewDelete",

  props: {
    review: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      memNic: JSON.parse(localStorage.getItem("user")).nickname,
      checkRole: JSON.parse(localStorage.getItem("user")).role,
    }
  },

  methods: {
    onDelete() {
      const { reviewNo, fileName } = this.review

      axios
        .delete(`http://localhost:7777/cafe/review/${reviewNo}`, { fileName })
        .then(() => {
          alert("Done! Files deleted successfully")
          window.location.reload()
          //this.$router.push({ name: 'CafeReviewListPage' })
        })
        .catch(() => {
          alert("삭제 실패")
        })
    },
  },
}
</script>

<style lang="scss" scoped>
@import "~@/assets/scss/components/cafe/cafe-review-dialog";
</style>

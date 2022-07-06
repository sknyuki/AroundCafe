<template>
  <div>
    <div class="cafe-review-footer" v-if="review.helps == true">
      <v-btn
        @click="onLikes()"
        aria-label="좋아요 버튼"
        class="btn-indigo"
        type="button"
      >
        <i class="icCheck" aria-hidden="true"></i> 도움됨
      </v-btn>
      <p>
        <strong>
          <span> {{ review.likeCnt }}</span
          >명</strong
        >에게 도움이 되었습니다.
      </p>
    </div>

    <div class="cafe-review-footer" v-else>
      <v-btn
        @click="onLikes()"
        aria-label="좋아요 버튼"
        class="btn-outlined"
        type="button"
      >
        도움이 돼요
      </v-btn>

      <p>
        <strong>
          <span> {{ review.likeCnt }}</span
          >명</strong
        >에게 도움이 되었습니다.
      </p>
    </div>
  </div>
</template>

<script>
import axios from "axios"
export default {
  name: "CafeReviewLike",

  props: {
    review: {
      type: Object,
      require: true,
    },
  },
  data() {
    return {
      onHelp: true,
      membNo: this.$store.state.user.memNo,
    }
  },
  methods: {
    onLikes() {
      axios
        .post(
          `http://localhost:7777/cafe/like/${this.review.reviewNo}/${this.membNo}`,
          {},
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        )
        .then(() => {
          history.go(0)
        })
        .catch(() => {
          alert("로그인을 해주세요!")
        })
    },
  },
}
</script>

<style lang="scss" scoped>
@import "~@/assets/scss/components/cafe/cafe-review-btn";
</style>

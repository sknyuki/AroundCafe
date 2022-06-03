<template>
  <form class="cafe-review">
    <div
      v-for="reviewContent in reviewData"
      :key="reviewContent.reviewNo"
      class="cafe-review-content"
    >
      <ol class="cafe-review-list">
        <li class="cafe-review-item">
          <article class="cafe-review-card">
            <header class="cafe-review-header">
              <h3 class="visually-hidden">{{ reviewContent.writer }}</h3>

              <a class="avatar-24">
                <img src="@/assets/images/img-user-default.png" sralt="" />
              </a>

              <div class="inf">
                <a class="username" href="">
                  <strong>{{ reviewContent.writer }}</strong>
                </a>

                <div class="detail">
                  <div class="star-rating-13">
                    <StarRating
                      :inline="true"
                      :read-only="true"
                      :star-size="10"
                      :show-rating="false"
                      v-model="reviewContent.rating"
                      :incremane="0.1"
                      active-color="#fee500"
                      :star-points="[
                        23, 2, 14, 17, 0, 19, 10, 34, 7, 50, 23, 43, 38, 50, 36,
                        34, 46, 19, 31, 17,
                      ]"
                    />
                  </div>
                  <div class="misc">
                    <time>{{ reviewContent.regDate }} </time>
                    <span> {{ reviewContent.cafeName }}</span>
                  </div>
                </div>
              </div>
            </header>
            <div class="cafe-review-body">
              <div class="review-image">
                <img
                  :src="require(`@/assets/images/img-review-01.jpg`)"
                  alt="리뷰사진"
                />
              </div>
              <p>{{ reviewContent.comment }}</p>
            </div>
            <footer class="cafe-review-footer">
              <v-btn type="button" aria-label="좋아요 버튼">
                <i class="icCheck"></i>
                도움됨
              </v-btn>
              <p>
                <strong> <span>2</span>명</strong>에게 도움이 되었습니다.
              </p>
            </footer>
            <button
              class="delete-button"
              aria-label="해당 리뷰 삭제하기"
              type="button"
            >
              <i class="icClose"></i>
            </button>
          </article>
        </li>
      </ol>
    </div>
  </form>
</template>
<script>
import StarRating from "vue-star-rating"
export default {
  name: "ReviewForm",
  components: {
    StarRating,
  },
  props: {
    reviewData: {
      type: Array,
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/cafe/cafe-review";
</style>

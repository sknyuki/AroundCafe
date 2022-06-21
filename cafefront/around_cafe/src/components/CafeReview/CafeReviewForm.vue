<template>
  <form class="cafe-review">
    <div
      v-for="review in reviews"
      :key="review.reviewNo"
      class="cafe-review-content"
    >
      <ol class="cafe-review-list">
        <li class="cafe-review-item">
          <article class="cafe-review-card">
            <header class="cafe-review-header">
              <h3 class="visually-hidden">{{ review.reviewNo }}</h3>

              <a class="avatar-24">
                <img src="@/assets/images/img-user-default.png" sralt="" />
              </a>

              <div class="inf">
                <a class="username" href="">
                  <strong> cafe.no {{ review.cafeNum }}</strong>
                </a>

                <div class="detail">
                  <div class="star-rating-13">
                    <StarRating
                      :inline="true"
                      :read-only="true"
                      :star-size="10"
                      :show-rating="false"
                      v-model="review.star_score"
                      :incremane="0.1"
                      active-color="#fee500"
                      :star-points="[
                        23, 2, 14, 17, 0, 19, 10, 34, 7, 50, 23, 43, 38, 50, 36,
                        34, 46, 19, 31, 17,
                      ]"
                    />
                  </div>
                  <div class="misc">
                    <time>{{ review.regDate }} </time>
                    <span>cafe.no{{ review.cafeNum }}</span>
                  </div>
                </div>
              </div>
            </header>

            <div class="cafe-review-body">
              <div class="review-image">
                <img
                  v-if="review.fileName !== null && review.fileName !== 'null'"
                  class="addImg"
                  :src="require(`@/assets/review/${review.fileName}`)"
                />
              </div>

              <p>{{ review.review_content }}</p>
            </div>

            <div class="cafe-review-footer">
              <v-btn @click="like_count()" aria-label="좋아요 버튼">
                <i class="icCheck"></i> 도움됨
              </v-btn>

              <p>
                <strong>
                  <span> {{ review.likeCnt }}</span
                  >명</strong
                >에게 도움이 되었습니다.
              </p>
            </div>

            <button
              class="delete-button"
              aria-label="해당 리뷰 삭제하기"
              type="button"
            >
              <i class="icClose"></i>
            </button>

            <CafeReviewModify
              v-if="review"
              :review="review"
              @submit="onModify"
            />

            <!--
            <button @click="onReviewDialog()" class="delete-button" aria-label="다이얼로그 등장" type="button">

              <i class="icHeart">  </i>
      
            </button>
            

            <v-dialog max-width="750" v-model="reviewDialog">

            <cafe-review-modify v-if="review" :review="review" @submit="onModify()"/>

            </v-dialog>-->
          </article>
        </li>
      </ol>
    </div>
  </form>
</template>
<script>
import StarRating from "vue-star-rating"
import CafeReviewModify from "@/components/CafeReview/CafeReviewModify"
import axios from "axios"

export default {
  name: "ReviewForm",
  components: {
    StarRating,
    CafeReviewModify,
  },
  props: {
    reviews: {
      type: Array,
      required: true,
    },
  },

  data() {
    return {
      reviewDialog: false,
      reviewNo: "",
    }
  },

  methods: {
    onModify(payload) {
      console.log(this.reviewNo)
      const { reviewNo, star_score, review_content, cafeNum, file } = payload
      let formData = new FormData()
      if (file != null) {
        formData.append("file", file)
      }
      formData.append("star_score", star_score)
      formData.append("review_content", review_content)
      formData.append("cafeNum", cafeNum)
      formData.append("reviewNo", reviewNo)

      axios
        .put(`http://localhost:7777/cafe/review/${reviewNo}`, formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then(() => {
          alert("Successfully submitted")
          window.location.reload()

          this.$router.push({
            name: "CafeReviewListPage",
          })
        })
        .catch(() => {
          alert("문제 발생!")
        })
    },
    onReviewDialog() {
      this.reviewDialog = true
    },
    closeDialog() {
      this.reviewDialog = false
    },
    created() {
      this.fetchReview(this.reviewNo).catch(() => {
        alert("게시물 DB조회 실패!")
      })
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/cafe/cafe-review";
</style>

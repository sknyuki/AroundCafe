<template>
  <form class="cafe-review">
    <div
      v-for="(review, index) in reviews"
      :key="index"
      class="cafe-review-content"
    >
      <ol class="cafe-review-list">
        <li class="cafe-review-item">
          <article class="cafe-review-card">
            <header class="cafe-review-header">
              <h3 class="visually-hidden">{{ review.reviewNo }}</h3>

              <a class="avatar-24">
                <img
                  v-if="review.memImg == null"
                  src="@/assets/images/img-user-default.png"
                  alt=""
                />
                <img
                  v-else
                  :src="require(`@/assets/images/memberImg/${review.memImg}`)"
                  alt=""
                />
              </a>

              <div class="inf">
                <a class="username" href="">
                  <strong> {{ review.memNick }}</strong>
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
                    <span>{{ review.updDate }}</span>
                  </div>
                </div>
              </div>
            </header>
            <div class="cafe-review-body">
              <div
                class="review-image"
                v-if="review.fileName !== null && review.fileName !== 'null'"
              >
                <img
                  class="addImg"
                  :src="require(`@/assets/review/${review.fileName}`)"
                />
              </div>

              <p>{{ review.review_content }}</p>
            </div>

            <CafeReviewLike
              v-if="review"
              :review="review"
              :myHelps="myHelps"
              :index="index"
            />
            <CafeReviewDelete :review="review" />
            <!--
            <button
              class="delete-button"
              aria-label="해당 리뷰 삭제하기"
              type="button"
            >
              <i @click="deleteReview()" class="icClose"></i>
            </button>-->

            <CafeReviewModify
              v-if="review"
              :review="review"
              @submit="onModify"
            />
          </article>
        </li>
      </ol>
    </div>
    <PaginationForm
      :pageSetting="pageDataSetting(total, limit, block, this.page)"
      @paging="pagingMethod"
    />
  </form>
</template>
<script>
import StarRating from "vue-star-rating"
import CafeReviewModify from "@/components/CafeReview/CafeReviewModify"
import CafeReviewLike from "@/components/CafeReview/CafeReviewLike"
import CafeReviewDelete from "@/components/Cafe/CafeReviewDelete"
import axios from "axios"
import { mapState, mapActions } from "vuex"
import PaginationForm from "@/components/PaginationForm.vue"

export default {
  name: "CafeReviewForm",
  components: {
    StarRating,
    CafeReviewModify,
    CafeReviewLike,
    CafeReviewDelete,
    PaginationForm,
  },
  props: {
    reviews: {
      type: Array,
      required: true,
    },
    myHelps: {
      type: Array,
      required: true,
    },
    cafeNo: {
      type: String,
      required: true,
    },
  },

  data() {
    return {
      loginInfo: this.$store.state.user.memNo,
      reviewDialog: false,
      reviewNo: "",
      listData: [],
      page: 1,
      limit: 3,
      block: 5,
      pageNo: "",
      total: "",
    }
  },
  computed: {
    ...mapState(["reviews"]),
  },
  mounted() {
    this.fetchReviewList(this.cafeNo)
    this.pagingMethod(this.page)
  },
  methods: {
    ...mapActions(["fetchReviewList"]),
    onModify(payload) {
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
    deleteReview() {
      axios
        .delete(`http://localhost:7777/cafe/review/${this.review.reviewNo}`)
        .then(() => {
          alert("삭제 완료")
          this.$router.push({
            name: "CafeReviewListPage",
          })
        })
        .catch(() => {
          alert("삭제 실패!")
        })
    },
    pagingMethod(page) {
      this.listData = this.reviews.slice(
        (page - 1) * this.limit,
        page * this.limit
      )
      this.page = page
      let total = this.reviews.length
      this.pageDataSetting(total, this.limit, this.block, page)
    },
    pageDataSetting(total, limit, block, page) {
      total = this.reviews.length
      const totalPage = Math.ceil(total / limit)
      let currentPage = page
      const first =
        currentPage > 1 ? parseInt(currentPage, 10) - parseInt(1, 10) : null
      const end =
        totalPage !== currentPage
          ? parseInt(currentPage, 10) + parseInt(1, 10)
          : null

      let startIndex = (Math.ceil(currentPage / block) - 1) * block + 1
      let endIndex =
        startIndex + block > totalPage ? totalPage : startIndex + block - 1
      let list = []
      for (let index = startIndex; index <= endIndex; index++) {
        list.push(index)
      }
      return { first, end, list, currentPage }
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/cafe/cafe-review";
</style>

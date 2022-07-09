<template>
  <form class="cafe-review">
    <div
      v-for="(review, index) in listData"
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
                  v-if="
                    userInfo.socialType != 'LOCAL' && userInfo.memImg != null
                  "
                  :src="userInfo.memImg"
                  alt=""
                />
                <img
                  v-if="
                    userInfo.socialType == 'LOCAL' && userInfo.memImg != null
                  "
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
                    <span>{{ review.updDate }} {{ review.cafeName }}</span>
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
              :review="review"
              :myHelps="myHelps"
              :index="index"
            />

            <CafeReviewDelete :review="review" />

            <CafeReviewModify
              v-if="review"
              :review="review"
              @submit="onModify"
              @input="close"
              v-model="reviewDialog"
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
// import axios from "axios"
//import { mapState, mapActions } from "vuex"
import CafeReviewModify from "@/components/CafeReview/CafeReviewModify"
import CafeReviewLike from "@/components/CafeReview/CafeReviewLike"
import CafeReviewDelete from "@/components/Cafe/CafeReviewDelete"
import PaginationForm from "@/components/PaginationForm.vue"
import axios from "axios"
import { mapActions, mapState } from "vuex"

export default {
  name: "UserReviewList",
  components: {
    StarRating,
    CafeReviewModify,
    CafeReviewLike,
    CafeReviewDelete,
    PaginationForm,
  },
  props: {
    myHelps: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      listData: [],
      page: 1,
      limit: 3,
      block: 5,
      pageNo: "",
      total: "",
      reviewDialog: false,
      memNo: this.$store.state.user.memNo,
      reviewList: [],
    }
  },
  created() {
    axios
      .get(
        `http://localhost:7777/cafe/review/user/list/${this.$store.state.user.memNo}`
      )
      .then((res) => {
        for (let i = 0; i < res.data.length; i++) {
          this.reviewList.push(res.data[i])
        }
        this.pagingMethod(this.page)
      })
  },
  computed: {
    ...mapState(["userInfo"]),
  },
  mounted() {
    this.fetchUserInfo()
  },

  methods: {
    ...mapActions(["fetchUserInfo"]),
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
    close() {
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
      this.listData = this.reviewList.slice(
        (page - 1) * this.limit,
        page * this.limit
      )
      this.page = page
      let total = this.reviewList.length
      this.pageDataSetting(total, this.limit, this.block, page)
    },
    pageDataSetting(total, limit, block, page) {
      total = this.reviewList.length
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

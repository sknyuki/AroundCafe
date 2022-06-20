<template>
  <div class="cafe-review">
    <ImgBox class="mb-10" />
    <div class="container">
      <div class="row">
        <div class="col-sm-4 col-md-3 col-lg-3">
          <CafeSidebar />
        </div>

        <div class="test col-sm-8 col-md-9 col-lg-9">
          <header>
            <h1>리뷰</h1>
          </header>
          <ReviewForm :listData="listData" :reviews="reviews" />

          <div class="cafe-review-pag">
            <PaginationForm
              :pageSetting="pageDataSetting(total, limit, block, this.page)"
              @paging="pagingMethod"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import CafeSidebar from "@/components/Cafe/CafeSidebar.vue"
import ImgBox from "@/components/ImgBox.vue"
import ReviewForm from "@/components/ReviewForm.vue"
import PaginationForm from "@/components/PaginationForm.vue"

export default {
  name: "CafeReviewList",
  components: { ImgBox, CafeSidebar, ReviewForm, PaginationForm },
  props: {
    reviews: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      reviewNo: "",
      listData: [],
      page: 1,
      limit: 10,
      block: 5,
      pageNo: "",
      total: "",
    }
  },
  mounted() {
    this.pagingMethod(this.page)
  },
  methods: {
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
      list.push
      for (let index = startIndex; index <= endIndex; index++) {
        list.push(index)
      }
      return { first, end, list, currentPage }
    },
  },
}
</script>
<style lang="scss" scoped>
.cafe-review {
  &-pag {
    @include flexbox(end);
    margin-bottom: 16px;
  }
}
</style>

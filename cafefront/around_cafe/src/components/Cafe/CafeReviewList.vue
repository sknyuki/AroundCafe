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
          <ReviewForm :reviewData="reviewData" />
          <div class="cafe-review-pag">
            <PaginationForm />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import CafeSidebar from "../Cafe/CafeSidebar.vue"
import ImgBox from "../ImgBox.vue"
import PaginationForm from "../PaginationForm.vue"
import ReviewForm from "../ReviewForm.vue"

export default {
  name: "CafeReviewList",
  components: { ImgBox, CafeSidebar, ReviewForm, PaginationForm },

  data() {
    return {
      reviewData: [
        {
          reviewNo: 1,
          writer: "박성빈",
          regDate: "2022-06-02",
          comment: "아아아아아아아아아아아아아아아아아아아아",
          rating: 5,
          cafeName: "벤투라",
        },
        {
          reviewNo: 2,
          writer: "박성빈",
          regDate: "2022-06-02",
          comment:
            "아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아",
          rating: 3,
          cafeName: "벤투라",
        },
        {
          reviewNo: 3,
          writer: "박성빈",
          regDate: "2022-06-02",
          comment: "아아아아아아아아아아아아아아아아아아아아",
          rating: 1,
          cafeName: "벤투라",
        },
      ],
    }
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

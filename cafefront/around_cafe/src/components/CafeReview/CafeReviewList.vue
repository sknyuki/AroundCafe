<template>
  <div class="cafe-review">
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <ImgBox />
        </div>
        <div class="col-sm-4 col-md-3 col-lg-3">
          <CafeSidebar />
        </div>

        <div class="test col-sm-8 col-md-9 col-lg-9">
          <header>
            <h1>리뷰</h1>
          </header>
          <CafeReviewForm />

          <div class="cafe-review-pag">
            <PaginationForm />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import CafeSidebar from "@/components/Cafe/CafeSidebar.vue"
import ImgBox from "@/components/ImgBox.vue"
import CafeReviewForm from "@/components/CafeReview/CafeReviewForm.vue"
import PaginationForm from "@/components/PaginationForm.vue"

export default {
  name: "CafeReviewList",
  components: { ImgBox, CafeSidebar, CafeReviewForm, PaginationForm },
  props: {
    reviews: {
      type: Array,
      required: true,
    },
    myHelps: {
      type: Array,
      required: true,
    },
  },

  data() {
    return {
      reviewNo: "",
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

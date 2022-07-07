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
          <CafeReviewForm :cafeNo="cafeNo" />
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import CafeSidebar from "@/components/Cafe/CafeSidebar.vue"
import ImgBox from "@/components/ImgBox.vue"
import CafeReviewForm from "@/components/CafeReview/CafeReviewForm.vue"

export default {
  name: "CafeReviewList",
  components: { ImgBox, CafeSidebar, CafeReviewForm },
  props: {
    cafeNo: {
      type: String,
      required: true,
    },
  },

  data() {
    return {}
  },
}
</script>

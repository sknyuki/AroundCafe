<template>
  <div class="cafe-review">
    <ImgBox class="mb-10" />
    <div class="container">
      <div class="row">
        <div class="col-sm-4 col-md-3 col-lg-3">
          <CafeSidebar />
        </div>
        <!--
            <span v-if="board.writer == this.userId">  -->
    
       
        <div class="test col-sm-8 col-md-9 col-lg-9">
          <header>
            <h1>리뷰</h1>
          </header>
          <ReviewForm :reviews="reviews" />
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
import ReviewForm from "@/components/ReviewForm.vue"
import PaginationForm from "@/components/PaginationForm.vue"
import { mapActions,mapState } from 'vuex'

export default {
  name: "CafeReviewList",
  components: { ImgBox, CafeSidebar, ReviewForm, PaginationForm },
  props: {
    reviews: {
     type: Array
    },
    rating:{
      type: Object
    },
    reviewNo: {
      type: String,
      required: true
    },
  },
  computed: {
    ...mapState(['reviews'])
  },
  mounted () {
    this.fetchReviewList()
  },
   methods: {
    ...mapActions(['fetchReviewList']),   
    onReviewDialog() {
      this.reviewDialog = true
    },
    closeDialog() {
      this.reviewDialog = false
    },  
  },

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

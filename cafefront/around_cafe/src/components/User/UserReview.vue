<template>
  <div class="cafe-review">
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <ImgBox />
        </div>
        <div class="col-sm-4 col-md-3 col-lg-3">
          <UserSidebar />
        </div>

        <div class="test col-sm-8 col-md-9 col-lg-9">
          <header>
            <h1>리뷰</h1>
          </header>
          <UserReviewList :userReviews="userReviews" :myHelps="myHelps" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ImgBox from "@/components/ImgBox.vue"
import UserSidebar from "@/components/User/UserSidebar.vue"
import UserReviewList from "@/components/User/UserReviewList.vue"
import { mapState, mapActions } from "vuex"
export default {
  name: "UserReview",
  components: { ImgBox, UserSidebar, UserReviewList },
  data() {
    return {
      memNo: this.$store.state.user.memNo,
    }
  },
  computed: {
    ...mapState(["userReviews", "myHelps"]),
  },
  mounted() {
    this.fetchUserReviewList(this.memNo)
    this.fetchMyHelpsList(this.memNo)
  },
  methods: {
    ...mapActions([
      "fetchUserReviewList",
      "fetchMyHelpsList",
      "fetchUserInfo",
      "fetchMyLikesList",
    ]),
  },
}
</script>

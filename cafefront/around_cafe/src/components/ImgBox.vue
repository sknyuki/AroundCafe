<template>
  <div class="img-box">
    <v-img
      class="br-10 d-flex cover-img align-end"
      cover
      :src="require('@/assets/images/cover-img.png')"
      gradient="to top, rgba(0,0,0,.7), rgba(0,0,0,0)"
    >
      <div
        class="img-box-content d-flex justify-center align-center justify-md-space-between mx-10 cover-image-content"
      >
        <div
          class="d-md-flex d-block align-center text-md-left text-center flex-wrap"
        >
          <label for="avatarUpload" class="me-4">
            <div class="avatar-upload">
              <div class="avatar-48">
                <img
                  v-if="userInfo.memImg == null"
                  src="@/assets/images/avatar.webp"
                  alt=""
                />
                <img
                  v-else
                  v-bind:src="
                    require(`@/assets/images/memberImg/${userInfo.memImg}`)
                  "
                  alt=""
                />
              </div>
            </div>
          </label>
          <div class="img-box-info">
            <span class="name">{{ userInfo.memNick }}</span>
            <div class="location" v-if="cafeBoard.address != null">
              <v-icon>mdi-map-marker</v-icon>
              <span>{{ cafeBoard.address }}</span>
            </div>
          </div>
        </div>

        <div class="d-none d-md-flex" v-if="cafeNo == null">
          <div class="text-center">
            <span class="number">{{ userReviews.length }}</span>
            <span class="text">Reviews</span>
          </div>

          <div class="text-center">
            <span class="number">{{ myLikes.length }}</span>
            <span class="text">Like</span>
          </div>

          <div class="text-center">
            <span class="number">{{ userInfo.memPoint }}</span>
            <span class="text">Point</span>
          </div>
        </div>

        <div class="d-none d-md-flex" v-else>
          <div class="text-center">
            <span class="number">{{ this.reviewList.length }}</span>
            <span class="text">Reviews</span>
          </div>

          <div class="text-center">
            <span class="number">{{ cafeReviews.length }}</span>
            <span class="text">Like</span>
          </div>

          <div class="text-center">
            <span class="number">{{ menuLists.length }}</span>
            <span class="text">Menus</span>
          </div>
        </div>
      </div>
    </v-img>
  </div>
</template>
<script>
import { mapActions, mapState } from "vuex"
import axios from "axios"

export default {
  name: "ImgBox",
  data() {
    return {
      memNo: this.$store.state.user.memNo,
      cafeNo: this.$store.state.user.cafeNo,
      reviewList: [],
    }
  },
  computed: {
    ...mapState([
      "userInfo",
      "cafeBoard",
      "myLikes",
      "userReviews",
      "menuLists",
      "cafeReviews",
    ]),
  },
  mounted() {
    this.fetchUserInfo()
    this.fetchMyLikesList(this.memNo)
    this.fetchcafeBoard(this.cafeNo)
    this.fetchUserReviewList(this.memNo)
    this.fetchMenuLists(this.cafeNo)
    this.fetchReviewList(this.cafeNo, this.memNo)
    this.fetchCafeReviewList(this.cafeNo)
  },
  methods: {
    ...mapActions([
      "fetchUserInfo",
      "fetchcafeBoard",
      "fetchMyLikesList",
      "fetchUserReviewList",
      "fetchMenuLists",
      "fetchCafeReviewList",
    ]),
    fetchReviewList(cafeNo, memNo) {
      if (memNo == null) {
        this.memNo = 0
      }

      axios
        .get(`http://localhost:7777/cafe/review/list/${cafeNo}/${memNo}`)
        .then((res) => {
          this.reviewList = res.data
        })
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/img-box";
</style>

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
                <img src="@/assets/images/avatar.webp" alt="" />
              </div>
            </div>
          </label>
          <div class="img-box-info">
            <span class="name">박성빈</span>
            <div class="location">
              <v-icon>mdi-map-marker</v-icon>
              <span>서울특별시</span>
            </div>
          </div>
        </div>

        <div class="d-none d-md-flex">
          <div class="text-center">
            <span class="number">24</span>
            <span class="text">Reviews</span>
          </div>

          <div class="text-center">
            <span class="number">45</span>
            <span class="text">Like</span>
          </div>

          <div class="text-center">
            <span class="number">70000</span>
            <span class="text">Point</span>
          </div>
        </div>
      </div>
    </v-img>
  </div>
</template>
<script>
export default {
  name: "ImgBox",
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/img-box";
</style>

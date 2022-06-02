<template>
  <div class="cafe-register">
    <img-box class="mb-10" />
    <div class="container">
      <div class="row">
        <div class="col-sm-4 col-md-3 col-lg-3">
          <MySidebar />
        </div>
        <div class="col-sm-4 col-md-9 col-lg-9">
          <form action="">이것이테스트다</form>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import MySidebar from "@/components/mypage/MySidebar.vue"
import ImgBox from "../mypage/ImgBox.vue"

export default {
  name: "MyOrderStausForm",
  components: { ImgBox, MySidebar },
}
</script>
<style></style>

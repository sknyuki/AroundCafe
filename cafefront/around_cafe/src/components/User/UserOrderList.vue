<template>
  <div class="cafe-register">
    <img-box class="mb-10" />
    <div class="container">
      <div class="row">
        <div class="col-sm-4 col-md-3 col-lg-3">
          <UserSidebar />
        </div>
        <div class="col-sm-4 col-md-9 col-lg-9">
          <form action="">이것이테스트다</form>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import UserSidebar from "@/components/User/UserSidebar.vue"
import ImgBox from "@/components/ImgBox.vue"

export default {
  name: "MyOrderStausForm",
  components: { ImgBox, UserSidebar },
}
</script>
<style></style>

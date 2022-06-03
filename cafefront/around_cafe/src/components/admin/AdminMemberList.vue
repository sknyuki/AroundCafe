<template>
  <div class="cafe-register">
    <img-box class="mb-10" />
    <div class="container">
      <div class="row">
        <div class="col-sm-4 col-md-3 col-lg-3">
          <AdminSidebar />
        </div>
        <div class="col-sm-4 col-md-9 col-lg-9">
          <form action="">테스트</form>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import AdminSidebar from "@/components/admin/AdminSidebar.vue"
import ImgBox from "../mypage/ImgBox.vue"

export default {
  name: "AdminMemberList",
  components: { ImgBox, AdminSidebar },
}
</script>
<style></style>

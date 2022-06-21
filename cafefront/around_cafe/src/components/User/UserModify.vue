<template>
  <div class="edit">
    <img-box class="mb-10" />
    <div class="container">
      <div class="row">
        <div class="col-sm-4 col-md-3 col-lg-3">
          <UserSidebar />
        </div>
        <div class="col-sm-8 col-md-9 col-lg-9">
          <AccountModifyInfo />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ImgBox from "@/components/ImgBox.vue"
import UserSidebar from "@/components/User/UserSidebar.vue"
import AccountModifyInfo from "@/components/Account/AccountModifyInfo.vue"
export default {
  components: { ImgBox, UserSidebar, AccountModifyInfo },
  name: "UserModify",
}
</script>

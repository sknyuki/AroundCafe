<template>
  <div class="edit">
    <ImgBox class="mb-10" />
    <div class="container">
      <div class="row">
        <div class="col-sm-4 col-md-3 col-lg-3">
          <CafeSidebar />
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
import CafeSidebar from "@/components/Cafe/CafeSidebar.vue"
import AccountModifyInfo from "@/components/Account/AccountModifyInfo.vue"

export default {
  name: "CafeEditMemberInfo",
  components: { ImgBox, CafeSidebar, AccountModifyInfo },
}
</script>

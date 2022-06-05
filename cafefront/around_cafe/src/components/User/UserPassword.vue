<template>
  <div class="change-pw">
    <img-box class="mb-10" />
    <div class="container">
      <div class="row">
        <div class="col-sm-4 col-md-3 col-lg-3">
          <UserSidebar />
        </div>
        <div class="col-sm-4 col-md-3 col-lg-3">
          <ChangePwForm />
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import ChangePwForm from "../ChangePwForm.vue"
import ImgBox from "../ImgBox.vue"
import UserSidebar from "./UserSidebar.vue"

export default {
  name: "CafeUserPassword",
  components: { UserSidebar, ChangePwForm, ImgBox },
}
</script>

<style lang="scss" scoped></style>

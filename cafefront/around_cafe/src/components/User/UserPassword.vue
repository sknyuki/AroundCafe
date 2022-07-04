<template>
  <div class="change-pw">
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <ImgBox />
        </div>
        <div class="col-sm-4 col-md-3 col-lg-3">
          <UserSidebar />
        </div>
        <div class="col-sm-8 col-md-9 col-lg-9">
          <ChangePwForm />
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import ImgBox from "@/components/ImgBox.vue"
import UserSidebar from "@/components/User/UserSidebar.vue"
import ChangePwForm from "@/components/ChangePwForm.vue"

export default {
  name: "CafeUserPassword",
  components: { UserSidebar, ChangePwForm, ImgBox },
  data() {
    return {}
  },
}
</script>

<style lang="scss" scoped></style>

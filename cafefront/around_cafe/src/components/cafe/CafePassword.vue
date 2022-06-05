<template>
  <div class="change-pw">
    <img-box class="mb-10" />
    <div class="container">
      <div class="row">
        <div class="col-sm-4 col-md-3 col-lg-3">
          <CafeSidebar />
        </div>
        <div class="col-sm-8 col-md-9 col-lg-9">
          <ChangePwForm />
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import ChangePwForm from "@/components/ChangePwForm.vue"
import CafeSidebar from "./CafeSidebar.vue"
import ImgBox from "../ImgBox.vue"
export default {
  name: "CafeChangePw",
  components: {
    ChangePwForm,
    ImgBox,
    CafeSidebar,
  },
}
</script>
<style lang=""></style>

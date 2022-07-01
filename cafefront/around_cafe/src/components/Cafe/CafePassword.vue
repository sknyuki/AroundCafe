<template>
  <div class="change-pw">
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <ImgBox />
        </div>

        <div class="col-sm-4 col-md-3 col-lg-3">
          <CafeSidebar />
        </div>
        <div class="col-sm-8 col-md-9 col-lg-9">
          <ChangePwForm @submit="changePw" />
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import ChangePwForm from "@/components/ChangePwForm.vue"
import CafeSidebar from "@/components/Cafe/CafeSidebar.vue"
import ImgBox from "@/components/ImgBox.vue"
import axios from "@/plugins/axiosInterceptors"

export default {
  name: "CafeChangePw",
  components: {
    ChangePwForm,
    ImgBox,
    CafeSidebar,
  },
  data() {
    return {
      writer: JSON.parse(localStorage.getItem("user")).memNo,
    }
  },
  methods: {
    changePw(payload) {
      const { memPw } = payload

      axios
        .put("http://localhost:7777/members/changePassword", {
          memNo: this.writer,
          memPw: memPw,
        })
        .then(() => {
          alert("수정되었습니다!")
          this.$router.go()
        })
        .catch(() => {
          alert("수정 실패!")
        })
    },
  },
}
</script>
<style lang=""></style>

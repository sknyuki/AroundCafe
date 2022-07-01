<template>
  <div class="edit">
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <ImgBox />
        </div>
        <div class="col-sm-4 col-md-3 col-lg-3">
          <CafeSidebar />
        </div>
        <div class="col-sm-8 col-md-9 col-lg-9">
          <AccountModifyInfo @submitContents="onSumbit" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ImgBox from "@/components/ImgBox.vue"
import CafeSidebar from "@/components/Cafe/CafeSidebar.vue"
import AccountModifyInfo from "@/components/Account/AccountModifyInfo.vue"
import axios from "@/plugins/axiosInterceptors"

export default {
  name: "CafeEditMemberInfo",
  components: { ImgBox, CafeSidebar, AccountModifyInfo },

  data() {
    return {
      writer: JSON.parse(localStorage.getItem("user")).memNo,
    }
  },
  methods: {
    onSumbit(payload) {
      const { memId, memNick, phoneNum, memBirth, files } = payload

      let formData = new FormData()

      let fileInfo = {
        memNo: this.writer,
        memId: memId,
        memNick: memNick,
        phoneNum: phoneNum,
        memBirth: memBirth,
        memImg: files.name,
      }

      formData.append(
        "info",
        new Blob([JSON.stringify(fileInfo)], { type: "application/json" })
      )

      if (files != null) {
        formData.append("fileList", files)
      }
      axios
        .put("http://localhost:7777/members/modifyMember", formData)
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

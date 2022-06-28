<template>
  <div>
    <CafeRegister
      v-if="cafeBoard"
      :cafeBoard="cafeBoard"
      @submitContents="contentsSubmit"
      :cafeImgLists="cafeImgLists"
    />
    <p v-else>로딩중</p>
  </div>
</template>

<script>
import CafeRegister from "@/components/Cafe/CafeRegister.vue"
import { mapState, mapActions } from "vuex"
import axios from "axios"

export default {
  name: "CafeRegisterPage",
  components: { CafeRegister },
  computed: {
    ...mapState(["user", "cafeBoard", "cafeImgLists"]),
  },
  mounted() {
    this.fetchUser(), this.fetchcafeBoard(this.user.cafeNo)
    this.fetchCafeImgLists(this.user.memNo)
  },
  methods: {
    ...mapActions(["fetchUser"]),
    ...mapActions(["fetchcafeBoard", "fetchCafeImgLists"]),
    contentsSubmit(payload) {
      const {
        cafeName,
        cafeBusNo,
        cafeTime,
        cafeContent,
        cafeCall,
        cafeAdr1,
        cafeAdr2,
        cafeAdr3,
        files,
      } = payload

      let formData = new FormData()

      let fileInfo = {
        cafeNo: this.cafeBoard.cafeNo,
        cafe_name: cafeName,
        cafe_time: cafeTime,
        cafe_content: cafeContent,
        cafe_call: cafeCall,
        cafe_adr1: cafeAdr1,
        cafe_adr2: cafeAdr2,
        cafe_adr3: cafeAdr3,
        cafe_bis_no: cafeBusNo,
      }

      formData.append(
        "info",
        new Blob([JSON.stringify(fileInfo)], { type: "application/json" })
      )

      if (files.length > 0) {
        for (let idx = 0; idx < files.length; idx++) {
          formData.append("fileList", files[idx].file)
        }
      }

      console.log(fileInfo)
      let memNo = this.user.memNo
      axios
        .put(`http://localhost:7777/cafe/modify/${memNo}`, formData)
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

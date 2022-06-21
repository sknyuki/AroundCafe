<template>
  <div>
    <CafeRegister
      v-if="cafeBoard"
      :cafeBoard="cafeBoard"
      @submitContents="contentsSubmit"
      :cafeImgLists="cafeImgLists"
    />
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
    ...mapState(["cafeBoard", "cafeImgLists"]),
  },
  mounted() {
    this.fetchcafeBoard(2)
    this.fetchCafeImgLists(this.cafeBoard.cafeNo)
  },
  methods: {
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
      let membNo = 2
      axios
        .put(`http://localhost:7777/cafe/modify/${membNo}`, formData)
        .then(() => {
          alert("수정되었습니다!")
        })
        .catch(() => {
          alert("수정 실패!")
        })
    },
  },
}
</script>

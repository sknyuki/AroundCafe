<template>
  <v-container>
    <cafe-modify-component
      v-if="cafeBoard"
      :cafeBoard="cafeBoard"
      :cafeImgLists="cafeImgLists"
      @submitContents="contentsSubmit"
    />
  </v-container>
</template>

<script>
import CafeModifyComponent from "@/components/MyPageComponents/CafeMypageComponents/CafeModifyComponent.vue"
import axios from "axios"
import { mapState, mapActions } from "vuex"

export default {
  name: "CafeModifyView",
  components: { CafeModifyComponent },

  data() {
    return {
      fileList: [],
    }
  },

  computed: {
    ...mapState(["cafeBoard", "cafeImgLists"]),
  },
  mounted() {
    this.fetchcafeBoard(2)
    this.fetchCafeImgLists(1)
  },
  methods: {
    ...mapActions(["fetchcafeBoard", "fetchCafeImgLists"]),
    contentsSubmit(payload) {
      const {
        mcafe_name,
        mcafe_content,
        mcafe_call,
        mcafe_time,
        mcafe_adr1,
        mcafe_adr2,
        mcafe_adr3,
        files,
      } = payload

      let formData = new FormData()

      let fileInfo = {
        cafeNo: this.cafeBoard.cafeNo,
        cafe_name: mcafe_name,
        cafe_time: mcafe_time,
        cafe_content: mcafe_content,
        cafe_call: mcafe_call,
        cafe_adr1: mcafe_adr1,
        cafe_adr2: mcafe_adr2,
        cafe_adr3: mcafe_adr3,
        cafe_bis_no: this.cafeBoard.cafe_bis_no,
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

<template>
  <div>
    <CafeQnARegister @submit="onSubmit" :qnaList="qnaList" />
    <CafeQnAList :qnaLists="qnaLists" />
  </div>
</template>

<script>
import CafeQnARegister from "@/components/MyPageComponents/CafeMypageComponents/CafeQnARegister.vue"
import axios from "axios"
import { mapActions, mapState } from "vuex"
import CafeQnAList from "@/components/MyPageComponents/CafeMypageComponents/CafeQnAList.vue"
// import CafeQnAComment from '@/components/MyPageComponents/CafeMypageComponents/CafeQnAComment.vue'

export default {
  name: "CafeQnAPage",
  components: {
    CafeQnARegister,
    CafeQnAList,
    //CafeQnAComment
  },
  data() {
    return {
      checkQnaNo: "",
      qnaList: {
        type: Array,
      },
      memNo: this.$store.state.user.memNo,
      role: this.$store.state.user.role,
      cafeNo: 1,
    }
  },
  computed: {
    ...mapState(["qnaLists"]),
  },
  mounted() {
    this.fetchQnALists(this.memNo)
  },
  methods: {
    ...mapActions(["fetchQnALists"]),
    onSubmit(payload) {
      const { content, findQna, files1 } = payload

      let formData = new FormData()

      let fileInfo = {
        memNo: this.$store.state.user.memNo,
        received_no: this.cafeNo,
        type: findQna,
        content: content,
      }

      formData.append(
        "info",
        new Blob([JSON.stringify(fileInfo)], { type: "application/json" })
      )

      if (files1.length > 0) {
        for (let idx = 0; idx < files1.length; idx++) {
          formData.append("fileList", files1[idx])
        }
      }

      // let membNo
      // if (this.role == "ADMIN") {
      //   membNo = 0
      // } else membNo = this.memNo

      axios
        .post(`http://localhost:7777/qna/register`, formData)
        .then((res) => {
          alert(res.data)
          this.$router.go()
        })
        .catch(() => {
          alert("문의사항 등록에 실패하였습니다.")
        })
    },
  },
}
</script>

<style scoped></style>

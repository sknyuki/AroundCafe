<template>
  <div>
    <h2>{{ cafeBoard.cafe_name }}님에게 문의하기</h2>
    <span>보통 하루 이내에 응답</span><br />
    <span>(여기 밑줄)</span><br />
    <span>어떤 것을 문의하고 싶으신가요?</span><br />
    <select v-model="findQna">
      <option disabled value="">문의사항 클릭</option>
      <option
        v-for="(item, index) in selectQna"
        :key="index"
        :value="item.name"
      >
        {{ item.name }}
      </option></select
    ><br />
    <span>(여기 밑줄)</span>
    <h3>카페에게 메시지를 보내 문의하세요.</h3>
    <br />
    <textarea
      v-model="content"
      maxlength="70"
      cols="70"
      rows="32"
      placeholder="문의사항을 입력해주세요."
    ></textarea>
    <v-btn @click="registerQna">문의하기</v-btn>
  </div>
</template>
<script>
import axios from "axios"

export default {
  name: "CafeDetailQnaForm",
  props: {
    cafeNo: {
      type: String,
      required: true,
    },
    cafeBoard: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      selectQna: [
        { name: "상품 문의", id: "1" },
        { name: "픽업 관련", id: "2" },
        { name: "환불 문의", id: "3" },
        { name: "기타", id: "4" },
      ],
      content: "",
      findQna: "",
    }
  },
  methods: {
    registerQna() {
      let formData = new FormData()

      let fileInfo = {
        memNo: this.$store.state.user.memNo,
        received_no: this.cafeNo,
        type: this.findQna,
        content: this.content,
      }

      formData.append(
        "info",
        new Blob([JSON.stringify(fileInfo)], { type: "application/json" })
      )

      // let membNo
      // if (this.role == "ADMIN") {
      //   membNo = 0
      // } else membNo = this.memNo

      axios
        .post(`http://localhost:7777/qna/register`, formData)
        .then((res) => {
          alert(res.data)
          window.open("http://localhost:8080/chat", "_blank")
        })
        .catch(() => {
          alert("문의사항 등록에 실패하였습니다.")
        })
    },
  },
}
</script>
<style></style>

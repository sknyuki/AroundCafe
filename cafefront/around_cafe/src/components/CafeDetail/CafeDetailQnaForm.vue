<template>
  <div class="qna">
    <section class="qna-section">
      <div class="qna-section-wrap">
        <div class="qna-info">
          <h3>{{ cafeBoard.cafe_name }}님에게 문의하기</h3>
          <span>보통 하루 이내에 응답</span>
        </div>
        <div class="avatar-48">
          <img
            v-if="
              !userInfo || (Array.isArray(userInfo) && userInfo.length === 0)
            "
            src="@/assets/images/avatar.webp"
            alt=""
          />
          <!-- <img
            v-else
            v-bind:src="require(`@/assets/images/memberImg/${userInfo.memImg}`)"
          /> -->
        </div>
      </div>
    </section>
    <section class="qna-section">
      <h3>무엇을 문의하고 싶으신가요?</h3>
      <div class="qna-section-radio">
        <label v-for="(item, index) in selectQna" :key="index">
          <input
            v-model="findQna"
            :value="item.name"
            :name="index"
            type="radio"
          />{{ item.name }}
        </label>
      </div>
    </section>
    <section class="qna-section">
      <h3>카페에게 메시지를 보내 문의하세요.</h3>
      <textarea
        v-model="content"
        maxlength="100"
        cols="120"
        rows="32"
        placeholder="문의사항을 입력해주세요."
      ></textarea>
    </section>
    <v-btn class="btn-indigo btn-40" @click="registerQna" type="submit"
      >메시지 전송하기</v-btn
    >
  </div>
</template>
<script>
import { mapState, mapActions } from "vuex"
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
  computed: {
    ...mapState(["userInfo"]),
  },
  mounted() {
    this.fetchUserInfo()
  },
  methods: {
    ...mapActions(["fetchUserInfo"]),
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
<style lang="scss" scoped>
@import "~@/assets/scss/components/detail/detail-qna";
</style>

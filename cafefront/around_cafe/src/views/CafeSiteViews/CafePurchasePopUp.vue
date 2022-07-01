<template>
  <div>
    <div v-if="this.paymentResult === `success`">
<!--      그냥 바로 팝업 종료시킬지 고민중-->
      <h1>결제에 성공하셨습니다.</h1>
      <v-btn @click="router2CompletePage">확인</v-btn>
    </div>
    <div v-if="this.paymentResult === `fail`">
      <h1>결제에 실패하셨습니다.</h1>
      <h1>다시 결제 하시겠습니까?</h1>
      <v-btn>확인</v-btn>
      <v-btn @click="router2Main">메인 화면으로 돌아가기</v-btn>
    </div>
    <div v-if="this.paymentResult === `cancel`">
      <h1>결제를 취소하셨습니다.</h1>
      <v-btn>확인</v-btn>
      <v-btn @click="router2Main">메인 화면으로 돌아가기</v-btn>
    </div>
  </div>
</template>

<script>
export default {
  name: "CafePurchasePopUp",
  created() {
    this.paymentNo = this.$route.query.paymentNo
    this.paymentResult = this.$route.query.paymentResult
  },
  data() {
    return {
      paymentResult: "",
      paymentNo: "",
    }
  },
  methods: {
    router2CompletePage() {
      opener.location.href = `http://localhost:8080/cafe/purchase/complete?paymentNo=${this.paymentNo}`
      window.close()
    },
    router2Main() {
      opener.location.href = "http://localhost:8080/main"
      window.close()
    },
  }
}
</script>

<style scoped>

</style>
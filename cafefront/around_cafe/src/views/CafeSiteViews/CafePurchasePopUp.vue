<template>
  <div class="popup">
    <div>
      <v-icon> mdi-check-circle</v-icon>
      <div v-if="this.paymentResult === `success`">
        <h1>결제에 성공했습니다</h1>
      </div>
      <div class="popup-btn">
        <v-btn @click="router2CompletePage" text-aline:center>확인</v-btn>
      </div>
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
      <v-btn>메인 화면으로 돌아가기</v-btn>
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
  },
}
</script>

<style lang="scss" scoped>
.popup {
  @include flexbox;
  h1 {
    margin-top: 30px;
  }
  &-btn {
    @include flexbox;
    margin-top: 50px;
    .v-btn {
      background-color: $indigo !important;
      color: $white;
    }
  }
}

.mdi:before,
.mdi-set {
  font-size: inherit;
}
.v-icon {
  font-size: 200px;
  display: flex;
  color: $indigo;
  margin-top: 100px;
}
</style>

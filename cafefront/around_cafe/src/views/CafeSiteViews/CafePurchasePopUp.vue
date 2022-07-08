<template>
  <div class="popup">
    <div>
      <div v-if="this.paymentResult === `fail`">
        <v-icon> mdi-close-box</v-icon>
        <h1>결제에 실패하셨습니다</h1>
        <h1>다시 결제 하시겠습니까?</h1>
        <div class="popup-btn">
          <v-btn @click="cancelConfirm()">확인</v-btn>
        </div>
        <v-btn @click="returnCafe()">카페로 돌아가기</v-btn>
      </div>
    </div>
    <div>
      <div v-if="this.paymentResult === `cancel`">
        <v-icon> mdi-close-box</v-icon>
        <h1>결제를 취소하셨습니다.</h1>
        <div class="popup-btn">
          <v-btn @click="cancelConfirm()">확인</v-btn>
        </div>
        <div class="popup-btn">
          <v-btn @click="returnCafe()">카페로 돌아가기</v-btn>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CafePurchasePopUp",
  created() {
    this.paymentNo = this.$route.query.paymentNo
    const result = this.$route.query.paymentResult
    this.paymentResult = result

    if (result === "success") {
      this.router2CompletePage()
    }
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
    returnCafe() {
      opener.history.back()
      window.close()
    },
    cancelConfirm() {
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
    margin-top: 40px;
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

<template>
  <div class="complete">
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <div class="complete-content">
            <div class="complete-content-header">
              <h1>주문완료</h1>
            </div>
            <div class="complete-alarm">
              <span
                >{{ this.memberInfo.memNick }} 고객님 주문이
                완료되었습니다.</span
              >
            </div>
            <div class="complete-number">
              <span>
                {{ this.dateToString(this.paymentInfo.paymentDate) }}에 주문하신
                주문의
                <span class="order-number"
                  >주문번호는 {{ this.paymentInfo.paymentNo }}번</span
                >입니다.
              </span>
            </div>
            <div class="complete-info">
              <span
                >받는 분 정보 : {{ this.memberInfo.memNick }}
                {{ this.memberInfo.phoneNum }}</span
              >
            </div>
            <div class="complete-pay">
              <div>
                <span
                  >주문금액 {{ paymentInfo.totalAmount | pricePoint }}원 -
                  포인트 {{ paymentInfo.totalPointAmount | pricePoint }}P =
                  <span class="inf">최종결제금액</span>
                  <span class="price">
                    {{ totalPaymentAmount | pricePoint }}원</span
                  ></span
                >
              </div>
            </div>
          </div>
        </div>
        <div class="col-sm-6">
          <div class="complete-content">
            <div class="complete-content-header">
              <h2>결제내역</h2>
            </div>
            <div class="complete-content-history">
              <span>
                {{ this.getPaymentMethod(this.paymentInfo.paymentMethod) }}
              </span>
              <span> {{ totalPaymentAmount | pricePoint }} 원 </span>
            </div>
          </div>
        </div>
        <div class="col-sm-6">
          <div class="complete-content">
            <div class="complete-content-header">
              <h2>적립예정내역</h2>
            </div>
            <div class="complete-content-history">
              <span>어라운드 포인트 </span>
              <span
                >{{
                  parseInt(totalPaymentAmount * pointPercentage) | pricePoint
                }}
                P
              </span>
            </div>
          </div>
        </div>
      </div>
      <div class="btn-group">
        <v-btn class="btn-indigo btn-48" @click="this.routeToMain"
          >쇼핑계속하기</v-btn
        >
        <v-btn class="btn-indigo btn-48" @click="this.showPaymentData"
          >주문상세조회</v-btn
        >
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios"

export default {
  name: "CafeSitePurchaseComplete",
  data() {
    return {
      paymentInfo: {},
      totalPaymentAmount: 0,
      memberInfo: {},
      pointPercentage: 0.025,
    }
  },
  async created() {
    const paymentNo = this.$route.query.paymentNo
    const paymentUrl = `http://localhost:7777/payment/${paymentNo}`
    const paymentInfo = (await axios.get(paymentUrl)).data
    const memberUrl = `http://localhost:7777/members/${paymentInfo.memNo}`
    const memberInfo = (await axios.get(memberUrl)).data
    const totalPaymentAmount =
      paymentInfo.totalAmount - paymentInfo.totalPointAmount

    this.paymentInfo = paymentInfo
    this.memberInfo = memberInfo
    this.totalPaymentAmount = totalPaymentAmount
  },
  computed: {},
  methods: {
    getPaymentMethod(paymentMethod) {
      if (paymentMethod === "KAKAO") {
        return "카카오페이"
      } else return paymentMethod
    },
    dateToString(date) {
      return (
        date.substr(0, 4) +
        "년 " +
        date.substr(5, 2) +
        "월 " +
        date.substr(8, 2) +
        "일"
      )
    },
    showPaymentData() {
      this.$router.push("/main")
    },
    routeToMain() {
      this.$router.push("/main")
    },
  },
}
</script>

<style lang="scss" scoped>
@import "~@/assets/scss/components/order/order-complete";
</style>

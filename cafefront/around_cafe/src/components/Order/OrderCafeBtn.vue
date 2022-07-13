<template>
  <div>
    <div class="btn-group" v-if="role === 'CAFE'">
      <v-btn
        type="button"
        class="btn-40"
        @click="cancel(order.paymentNo, order.paymentStatus)"
        >취소</v-btn
      >

      <v-btn
        type="button"
        class="btn-40"
        @click="ready(order.paymentNo, order.paymentStatus)"
        >준비중</v-btn
      >

      <v-btn
        type="button"
        class="btn-indigo btn-40"
        @click="completed(order.paymentNo, order.paymentStatus)"
        >완료</v-btn
      >
    </div>
    <div class="btn-group" v-if="role === 'USER'">
      <v-btn
        type="button"
        class="btn-40"
        @click="cancel(order.paymentNo, order.paymentStatus)"
        >취소</v-btn
      >
      <v-btn type="button" class="btn-40" @click="goDetails(order.paymentNo)"
        >상세보기</v-btn
      >
    </div>
  </div>
</template>

<script>
import axios from "axios"

export default {
  name: "OrderCafeBtn",

  props: {
    user: {
      type: Object,
      required: true,
    },
    order: {
      type: Object,
      required: true,
    },
    role: {
      type: String,
      required: true,
    },
  },
  data() {
    return {}
  },

  methods: {
    transfer(paymentStatus) {
      switch (paymentStatus) {
        case "PAYMENT_READY":
          return 0

        case "PAYMENT_CONFIRMED":
          return 1

        case "CAFE_READY":
          return 2

        case "PICK_UP_FINISHED":
          return 3

        case "PAYMENT_CANCELED":
          return 4
      }
    },

    async ready(paymentNo, paymentStatus) {
      let status = this.transfer(paymentStatus) + 1
      const data = {
        paymentStatus: status,
      }
      try{
        if (paymentStatus === "PAYMENT_CONFIRMED") {
          await axios.put(`http://localhost:7777/payment/order/status/${paymentNo}`, data)
          await this.sendEmail("CAFE_READY")
          alert("주문이 준비중입니다")
          this.$router.go()
        } else {
          alert("이미 준비중입니다")
        }
      } catch {
        alert("수정 실패!")
      }
    },

    async completed(paymentNo, paymentStatus) {
      let tmp = paymentStatus
      tmp = "PICK_UP_FINISHED"
      const data = {
        paymentStatus: tmp,
      }
      try {
        await axios.put(
          `http://localhost:7777/payment/order/status/${paymentNo}`,
          data
        )
        await this.sendEmail(tmp)
        alert("음료가 준비되었습니다")
        this.$router.go()
      } catch {
        alert("준비중 실패")
      }
    },
    async cancel(paymentNo, paymentStatus) {
      let tmp = paymentStatus
      tmp = "PAYMENT_CANCELED"

      let status = this.transfer(tmp)
      const data = {
        paymentStatus: status,
      }
      if (paymentStatus === "PAYMENT_CONFIRMED") {
        try {
          await axios.put(
            `http://localhost:7777/payment/order/status/${paymentNo}`,
            data
          )
          await this.sendEmail(tmp)
        } catch {
          alert("주문취소 실패")
        }
        alert("주문이 취소되었습니다")
        this.$router.go()
      }
    },
    goDetails(paymentNo) {
      this.$router.push({
        name: "UserOrderHistoryDetailPage",
        params: { paymentNo: paymentNo },
      })
    },
    async sendEmail(paymentStatus) {
      const sendInfo = {
        role: this.role,
        mem_no: this.user.memNo,
        cafe_no: this.order.cafeNo,
        status: paymentStatus,
      }
      await axios.post("http://localhost:5000/mail/sendInfo", sendInfo)
    },
  },
}
</script>

<style lang="scss" scoped>
@import "~@/assets/scss/components/order/order-history";
.btn-group {
  .v-btn {
    width: 70px;
    margin: 5px;
    &:nth-child(1) {
      background-color: $red;
      color: $white;
    }
    &:nth-child(2) {
      background-color: $white;
      color: $indigo;
      border: 10px;
      border-color: rgb(255, 0, 43) !important;
      &:hover {
        background-color: rgba(82, 118, 165, 0.5) !important;
        //color: rgba(0, 0, 255, 0.116) !important;

        //margin: 1px;
      }
    }
  }
}
</style>

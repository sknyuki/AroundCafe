<template>
  <aside @submit.prevent="onSubmit" class="order-sidebar navigation">
    <div class="order-sidebar contents">
      <div class="order-sidebar-contents title">
        <div class="order-sidebar-flexbox title">결제금액</div>
      </div>
      <div class="order-sidebar-contents content">
        <div class="order-sidebar-flexbox left">총 상품 금액</div>
        <div class="order-sidebar-flexbox right">
          {{ this.nullValueCheck(paymentInfo.totalAmount, digit) }} 원
        </div>
      </div>
      <div class="order-sidebar-contents content">
        <div class="order-sidebar-flexbox left">포인트 사용</div>
        <div class="order-sidebar-flexbox right">
          {{ this.nullValueCheck(paymentInfo.totalPointAmount, digit) }} 원
        </div>
      </div>
      <v-divider />
      <div class="order-sidebar-contents footer">
        <div class="order-sidebar-footer leftBox">최종 결제 금액</div>
        <div class="order-sidebar-footer rightBox">
          <div>
            <span class="order-sidebar-footer color-text">
              {{
                this.nullValueCheck(
                  paymentInfo.totalAmount - paymentInfo.totalPointAmount,
                  digit
                )
              }}
            </span>
            <span class="order-sidebar-footer text">원</span>
          </div>
          <div style="text-align: right">
            <span class="order-sidebar-contents point">
              {{
                this.nullValueCheck(
                  parseInt(
                    (paymentInfo.totalAmount - paymentInfo.totalPointAmount) *
                      pointAdd
                  ),
                  digit
                )
              }}
              P
            </span>
            <span class="order-sidebar-contents point2">적립 예정</span>
          </div>
        </div>
      </div>
    </div>
    <v-btn
      :disabled="isPointValidated === false"
      class="order-sidebar button"
      @click="onSubmit()"
      >{{
        this.nullValueCheck(
          paymentInfo.totalAmount - paymentInfo.totalPointAmount,
          3
        )
      }}원 결제하기</v-btn
    >
  </aside>
</template>

<script>
import Number2String from "@/utils/number2String"
import {pricePoint} from "@/utils/filters";

export default {
  name: "CafeSitePurchaseSidebar",
  props: {
    paymentInfo: { Type: Object, required: true },
    totalPrice: { Type: Number, required: true },
    isPointValidated: { Type: Boolean, required: true },
  },
  data() {
    return {
      digit: 3,
      pointAdd: 0.025,
    }
  },
  methods: {
    numberToString(number, digit) {
      return Number2String.do(number, digit)
    },
    nullValueCheck(number) {
      let newNumber
      if (number === "" || number === null || number < 0) {
        newNumber = 0
      } else {
        newNumber = number
      }
      return pricePoint(newNumber)
    },
    onSubmit() {
      if(this.paymentInfo.paymentMethod !== "" && this.paymentInfo.paymentMethod !== null){
        this.$emit("submit", this.paymentInfo)
      } else {
       alert("결제 방법을 선택하셔야 합니다.")
      }
    },
  },
}
</script>

<style lang="scss" scoped>
@import "~@/assets/scss/components/order/order-sidebar";
</style>

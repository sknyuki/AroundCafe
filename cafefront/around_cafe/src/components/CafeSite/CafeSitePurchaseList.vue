<template>
  <form class="order-purchase-form">
    <section class="order-purchase-section-title">주문/결제</section>
    <section class="order-purchase-section">
      <section class="order-purchase-content-box title">
        <div class="order-purchase-flexbox section-title">주문상품</div>
      </section>
      <hr class="order-purchase-line" />
      <section
        v-for="item in paymentInfo.orderItems"
        :key="item.cafeMenuNo"
        class="order-purchase-content-box content"
      >
        <div class="order-purchase-list box">
          <div class="order-purchase-list header">
            <div class="order-purchase-flexbox list-title">Cafe Name</div>
          </div>
          <div class="order-purchase-list content">
            <div class="order-purchase-flexbox content">
              <div class="order-purchase-image holder">
                <div class="order-purchase-image" v-html="item.image" />
              </div>
              <div>
                <div style="margin-bottom: 3px" v-html="item.itemName" />
                <span v-html="numberToString(item.amount, 3)" />
                <span style="margin-left: 5px; margin-right: 5px" />I
                <!--              SVG 이미지파일 삽입-->
                <span v-html="item.quantity" />
              </div>
            </div>
          </div>
        </div>
      </section>
    </section>
    <section class="order-purchase-section">
      <section class="order-purchase-content-box title">
        <div class="order-purchase-flexbox section-title">포인트</div>
      </section>
      <hr class="order-purchase-line" />
      <section class="order-purchase-content-box content">
        <div class="order-purchase-flexbox content">
          <input
            :disabled="userInfo.memPoint < minimumPoint"
            v-model="paymentInfo.totalPointAmount"
            class="order-purchase-point input"
            placeholder="포인트"
          />
          <v-btn
            class="order-purchase-point btn"
            :disabled="userInfo.memPoint < minimumPoint"
            @click="useAllPoint()"
            >전액사용</v-btn
          >
          <div class="order-purchase-point text-box">
            <span class="order-purchase-point text">사용 가능 포인트</span>
            <span class="order-purchase-point color-text">{{
              userInfo.memPoint
            }}</span>
            <span class="order-purchase-point color-text">P</span>
            <span class="order-purchase-point text"
              >(포인트는 {{ minimumPoint }}P 이상부터 사용 가능합니다)</span
            >
          </div>
        </div>
        <div
          v-show="isPointValidated === false"
          class="order-purchase-point validate"
        >
          {{ pointValidatedMessage }}
        </div>
      </section>
    </section>
    <section class="order-purchase-section">
      <section class="order-purchase-content-box title">
        <div class="order-purchase-flexbox section-title">결제수단</div>
      </section>
      <hr class="order-purchase-line" />
      <section class="order-purchase-content-box content">
        <v-btn @click="setPaymentMethod(`CARD`)">카드</v-btn>
        <v-btn @click="setPaymentMethod(`BANKING`)">무통장입금</v-btn>
        <v-btn @click="setPaymentMethod(`KAKAO`)">카카오페이</v-btn>
        <v-btn @click="setPaymentMethod(`MOBILE`)">핸드폰</v-btn>
      </section>
    </section>
  </form>
</template>

<script>
import Number2String from "@/utils/number2String"

export default {
  name: "CafeSitePurchaseList",
  props: {
    // basketList: { type: Object, required: true },
    userInfo: { type: Object, required: true },
  },
  data() {
    return {
      paymentInfo: {
        itemInitName: "",
        paymentMethod: "",
        totalQuantity: 0,
        totalAmount: 0,
        totalPointAmount: "",
        orderItems: [],
        cafeNo: 1,
        memNo: "",
      },
      minimumPoint: 2000,
      basketList: [
        {
          menu_no: 1,
          menu_name: "아메리카노",
          number: 1,
          menu_price: 5000,
        },
        {
          menu_no: 2,
          menu_name: "라떼",
          number: 1,
          menu_price: 6000,
        },
      ],
      isPointValidated: true,
      pointValidatedMessage: "",
    }
  },
  async created() {
    this.basketList.forEach(function (obj) {
      let newOrderItemObj = {
        cafeMenuNo: obj.menu_no,
        itemName: obj.menu_name,
        quantity: obj.number,
        amount: obj.menu_price,
      }
      this.paymentInfo.totalAmount += Number(obj.menu_price)
      this.paymentInfo.totalQuantity += Number(obj.number)
      this.paymentInfo.orderItems.push(newOrderItemObj)
    }, this)
    this.paymentInfo.itemInitName = `${
      this.paymentInfo.orderItems[0].itemName
    } 외 ${this.paymentInfo.totalQuantity - 1}개`
    this.paymentInfo.memNo = this.userInfo.memNo
    console.log(this.paymentInfo)
    this.$emit("getPaymentInfo", this.paymentInfo)
  },
  watch: {
    paymentInfo: {
      deep: true,
      handler() {
        this.checkPointValidate()
        this.$emit("getPaymentInfo", this.paymentInfo)
      },
    },
    isPointValidated: {
      handler() {
        this.$emit("getIsPointValidated", this.isPointValidated)
      },
    },
  },
  methods: {
    useAllPoint() {
      this.paymentInfo.totalPointAmount = this.userInfo.memPoint
    },
    setPaymentMethod(method) {
      this.paymentInfo.paymentMethod = method
    },
    numberToString(number, digit) {
      return Number2String.do(number, digit)
    },
    checkPointValidate() {
      if (
        this.paymentInfo.totalPointAmount < this.minimumPoint &&
        this.paymentInfo.totalPointAmount > 0 &&
        this.paymentInfo.totalPointAmount !== null
      ) {
        this.pointValidatedMessage = `${this.numberToString(
          this.minimumPoint,
          3
        )}P 이상의 포인트를 사용하셔야 합니다.`
        this.isPointValidated = false
      } else if (
        this.paymentInfo.totalPointAmount > this.paymentInfo.totalAmount
      ) {
        this.pointValidatedMessage =
          "결제금액보다 더 많은 포인트를 사용하실 수 없습니다."
        this.isPointValidated = false
      } else if (this.paymentInfo.totalPointAmount > this.userInfo.memPoint) {
        this.pointValidatedMessage =
          "보유하신 포인트보다 더 많은 포인트를 사용하실 수 없습니다."
        this.isPointValidated = false
      } else {
        this.isPointValidated = true
      }
    },
    getMenuImage() {},
  },
}
</script>

<style lang="scss" scoped>
@import "~@/assets/scss/components/order/order-purchase.scss";
</style>

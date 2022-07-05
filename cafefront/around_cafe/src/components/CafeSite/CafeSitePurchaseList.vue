<template>
  <main class="order-purchase-form">
    <h1 class="order-purchase-section-title">주문/결제</h1>
    <section class="order-purchase-section">
      <section class="order-purchase-content-box title">
        <div class="order-purchase-flexbox section-title">주문상품</div>
      </section>
      <hr class="order-purchase-line" />
      <section
          v-for="item in basketList"
          :key="item.menu_no"
          class="order-purchase-content-box content"
      >
        <div class="order-purchase-list box">
          <div class="order-purchase-list header">
            <div class="order-purchase-flexbox list-title">
              {{ cafeInfo.cafe_name }}
            </div>
          </div>
          <div class="order-purchase-list content">
            <div class="order-purchase-flexbox content">
              <div class="order-purchase-image holder">
                <img class="order-purchase-image"
                     :src = getMenuImage(item.menu_img)
                     alt = "" />
              </div>
              <div>
                <div style="margin-bottom: 3px" v-html="item.menu_name" />
                <span v-html="numberToString(item.per_menu_total_price, 3)" />
                <span style="margin-right: 5px" />|
                <!--              SVG 이미지파일 삽입-->
                <span v-html="item.per_menu_quantity" />
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
        <div class="order-purchase-wrap">
<<<<<<<<< Temporary merge branch 1
          <button @click="setPaymentMethod(`CARD`)" >
=========
          <button
            v-for="(orderBtn, index) in orderBtns"
            :key="index"
            :class="{ 'is-active': orderBtn.isActive }"
            @click.native="setPaymentMethod(orderBtn)"
          >
>>>>>>>>> Temporary merge branch 2
            <picture>
              <source />
              <img :src="orderBtn.img" alt="" />
            </picture>
            <div>{{ orderBtn.name }}</div>
          </button>
<<<<<<<<< Temporary merge branch 1
=========
          <!-- <button @click="setPaymentMethod(`CARD`)"></button>

>>>>>>>>> Temporary merge branch 2
          <button type="button" @click="setPaymentMethod(`BANK`)">
            <picture>
              <source />
              <img src="@/assets/images/order/img_vbank.webp" alt="" />
            </picture>
            <div>무통장입금</div>
          </button>
          <button type="button" @click="setPaymentMethod(`KAKAO`)">
            <picture>
              <source />
              <img src="@/assets/images/order/img_kakaopay.webp" alt="" />
            </picture>
            <div>카카오페이</div>
          </button>
          <button type="button" @click="setPaymentMethod(`TOSS`)">
            <picture>
              <source />
              <img src="@/assets/images/order/img_toss.webp" alt="" />
            </picture>
            <div>토스</div>
          </button>
          <button type="button" @click="setPaymentMethod(`PAYCO`)">
            <picture>
              <source />
              <img src="@/assets/images/order/img_payco.webp" alt="" />
            </picture>
            <div>페이코</div>
          </button>
          <button type="button" @click="setPaymentMethod(`NAVER`)">
            <picture>
              <source />
              <img src="@/assets/images/order/img_naver.webp" alt="" />
            </picture>
            <div>네이버페이</div>
          </button>
          <button type="button" @click="setPaymentMethod(`CHAI`)">
            <picture>
              <source />
              <img src="@/assets/images/order/img_chai.webp" alt="" />
            </picture>
            <div>차이</div>
          </button>
          <button type="button" @click="setPaymentMethod(`MOBILE`)">
            <picture>
              <source />
              <img src="@/assets/images/order/img_phone.webp" alt="" />
            </picture>
            <div>핸드폰</div>
          </button> -->
        </div>
      </section>
    </section>
  </main>
</template>

<script>
import Number2String from "@/utils/number2String"

export default {
  name: "CafeSitePurchaseList",
  props: {
    basketList: { type: Array, required: true },
    userInfo: { type: Object, required: true },
    cafeInfo: { type: Object, required: true },
  },
  data() {
    return {
      test: "",
      paymentInfo: {
        itemInitName: "",
        paymentMethod: "",
        totalQuantity: 0,
        totalAmount: 0,
        totalPointAmount: "",
        orderItems: [],
        cafeNo: 0,
        memNo: "",
      },
      orderBtns: [
        {
          img: require(`@/assets/images/order/img_card.webp`),
          name: "카드",
          isActive: false,
          type: "CARD",
        },
        {
          img: require(`@/assets/images/order/img_vbank.webp`),
          name: "무통장 입금",
          isActive: false,
          type: "BANK",
        },
        {
          img: require(`@/assets/images/order/img_kakaopay.webp`),
          name: "카카오페이",
          isActive: false,
          type: "KAKAO",
        },
        {
          img: require(`@/assets/images/order/img_toss.webp`),
          name: "토스",
          isActive: false,
          type: "TOSS",
        },
        {
          img: require(`@/assets/images/order/img_payco.webp`),
          name: "페이코",
          isActive: false,
          type: "PAYCO",
        },
        {
          img: require(`@/assets/images/order/img_naver.webp`),
          name: "네이버",
          isActive: false,
          type: "NAVER",
        },
        {
          img: require(`@/assets/images/order/img_chai.webp`),
          name: "차이",
          isActive: false,
          type: "CHAI",
        },
        {
          img: require(`@/assets/images/order/img_phone.webp`),
          name: "핸드폰",
          isActive: false,
          type: "CARD",
        },
      ],

      minimumPoint: 2000,
<<<<<<<<< Temporary merge branch 1
=========
      basketList: [
        {
          menu_content: "",
          menu_img: "",
          menu_name: "아메리카노",
          menu_no: 1,
          menu_price: 5000,
          per_menu_quantity: 1,
          per_menu_total_price: 5000,
          signature: false,
        },
        {
          menu_content: "",
          menu_img: "",
          menu_name: "라떼",
          menu_no: 2,
          menu_price: 6000,
          per_menu_quantity: 1,
          per_menu_total_price: 6000,
          signature: false,
        },
      ],
      cafeInfo: {
        cafeNo: 1,
        cafeName: "카페",
        totalPrice: 11000,
      },
>>>>>>>>> Temporary merge branch 2
      isPointValidated: true,
      pointValidatedMessage: "",
    }
  },
  async created() {
    this.basketList.forEach(function (obj) {
      let newOrderItemObj = {
        cafeMenuNo: obj.menu_no,
        itemName: obj.menu_name,
        quantity: obj.per_menu_quantity,
        amount: obj.per_menu_total_price,
      }
      this.paymentInfo.totalQuantity += Number(newOrderItemObj.quantity)
      this.paymentInfo.orderItems.push(newOrderItemObj)
    }, this)

    if(this.basketList.length > 1) {
      this.paymentInfo.itemInitName = `${
          this.paymentInfo.orderItems[0].itemName
      } 외 ${this.paymentInfo.totalQuantity - 1}개`
    } else {
      this.paymentInfo.itemInitName = `${
          this.paymentInfo.orderItems[0].itemName
      }`
    }

    this.paymentInfo.memNo = this.userInfo.memNo
    this.paymentInfo.totalAmount = this.cafeInfo.totalPrice
    this.paymentInfo.cafeNo = this.cafeInfo.cafeNo
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
      let myPoint = this.userInfo.memPoint
      if (myPoint >= this.paymentInfo.totalPointAmount) {
        this.paymentInfo.totalPointAmount = this.paymentInfo.totalAmount
      } else {
        this.paymentInfo.totalPointAmount = myPoint
      }
    },
    setPaymentMethod(orderBtn) {
      this.paymentInfo.paymentMethod = orderBtn.type
      console.log(this.paymentInfo)
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
    getMenuImage(img) {
      try {
        return require(`@/assets/cafe/cafeMenu/${img}`)
      } catch(_) {
        return require(`@/assets/cafe/cafeMenu/imgNull.png`)
      }
    },
  },
}
</script>

<style lang="scss" scoped>
@import "~@/assets/scss/components/order/order-purchase.scss";
</style>

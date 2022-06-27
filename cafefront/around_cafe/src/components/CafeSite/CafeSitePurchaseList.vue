<template>
  <form class="order-purchase-form">
    <section class="order-purchase-section-title">주문/결제</section>
    <section class="order-purchase-section">
      <section class="order-purchase-content-box title">
        <div class="order-purchase-flexbox section-title">주문 상품</div>
      </section>
      <hr class="order-purchase-line" />
      <section v-for="item in orderItem" :key="item.cafeMenuNo" class="order-purchase-content-box content">
        <hr class="order-purchase-line" />
        <div class="order-purchase-list header">
          <div class="order-purchase-flexbox list-title">Cafe Name</div>
        </div>
        <div class="order-purchase-list content">
          <div class="order-purchase-flexbox content">
            <div class="order-purchase-image holder">
              <div class="order-purchase-image" v-html="item.image" />
            </div>
            <div>
              <div style="margin-bottom: 3px;" v-html="item.itemName" />
              <span v-html="item.amount" />
              <span style="margin-left: 5px; margin-right: 5px;" />I
<!--              이미지파일 삽입-->
              <span v-html="item.quantity" />
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
            v-model="memPoint"
            class="order-purchase-point input"
            placeholder="포인트"
          />
          <v-btn
            class="order-purchase-point button"
            :disabled="userInfo.memPoint < minimumPoint"
            @click="userAllPoint()"
            >전액사용</v-btn
          >
        <div class="order-purchase-point text-box">
          <span class="order-purchase-point text">사용 가능 포인트</span>
          <span class="order-purchase-point color-text">{{ userInfo.memPoint }}</span>
          <span class="order-purchase-point color-text">P</span>
          <span class="order-purchase-point text">(포인트는 {{minimumPoint}}P 이상부터 사용 가능합니다)</span>
          </div>
        </div>
      </section>
    </section>
    <section class="order-purchase-section">
      <section class="order-purchase-content-box title">
        <div class="order-purchase-flexbox section-title">결제수단</div>
      </section>
      <hr class="order-purchase-line" />
      <section class="order-purchase-content-box content">
        <v-btn>카드</v-btn>
        <v-btn>무통장입금</v-btn>
        <v-btn>카카오페이</v-btn>
        <v-btn>핸드폰</v-btn>
      </section>
    </section>
  </form>
</template>

<script>
export default {
  name: "CafeSitePurchaseList",
  props: {
    backetlist: { type: Object, required: true },
    userInfo: { type: Object, required: true },
  },
  data() {
    return {
      memPoint: "",
      itemInitName: "",
      paymentMethod: "",
      totalAmount: "",
      totalPointAmount: "",
      cafeNo: "",
      minimumPoint: 2000,
      orderItem: [
        {
          cafeMenuNo: 1,
          itemName: "아메리카노",
          quantity: 1,
          amount: 5000,
          image: "image",
        },
        {
          cafeMenuNo: 2,
          itemName: "라떼",
          quantity: 1,
          amount: 6000,
          image: "image",
        },
      ],
    }
  },
  methods: {
    userAllPoint() {
      this.memPoint = this.userInfo.memPoint
    },
  },
}
</script>

<style lang="scss" scoped>
@import "~@/assets/scss/components/order/order-purchase.scss";
</style>

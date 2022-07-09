<template>
  <div class="order">
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <div class="order-detail-content">
            <header>
              <h1>주문 상세</h1>
            </header>
          </div>
          <div class="order-detail-info">
            <span class="number">
              주문번호 {{ paymentDetail.paymentNo }} |
            </span>
            <span class="date">{{ paymentDetail.paymentDate | yyyyMMdd }}</span>
          </div>

          <div class="order-detail-status">
            <div>
              <span>{{ paymentDetail.paymentStatus }}</span>
            </div>
            <CafeReviewRegisterPage
              class="review-btn"
              :cafeNo="paymentDetail.cafeNo"
            />
          </div>
          <section
            v-for="orderItem in paymentDetail.orderItems"
            :key="orderItem.menuNo"
            class="order-detail wrap"
          >
            <div class="order-detail-image">
              <picture>
                <source />
                <img :src="getMenuImage(orderItem.imageUrl)" alt="" />
              </picture>
            </div>
            <div class="product">
              <strong>
                {{ orderItem.itemName }}
              </strong>
            </div>
            <div class="price">
              <span> {{ orderItem.amount | pricePoint }}원 </span>
            </div>
            <div class="quantity">
              <span> {{ orderItem.quantity | pricePoint }}개 </span>
            </div>
          </section>
        </div>

        <div class="col-sm-6">
          <div class="order-detail-content">
            <h2>결제정보</h2>
          </div>
          <div class="order-detail">
            <div>
              <div class="order-detail-payment wrap">
                <span>주문 금액</span>
                <span>{{ paymentDetail.totalAmount | pricePoint }}원</span>
              </div>
              <div class="order-detail-payment wrap">
                <span>포인트 사용</span>
                <span
                  >- {{ paymentDetail.totalPointAmount | pricePoint }}원</span
                >
              </div>
              <div class="order-detail-payment wrap">
                <span>총 결제 금액</span>
                <span>{{ paymentDetail.totalAmount | pricePoint }}원</span>
              </div>
              <div class="order-detail-payment-list">
                <span>결제내역</span>
              </div>
              <div class="order-detail-payment wrap">
                <span class="method">{{ paymentDetail.paymentMethod }}</span>
                <span class="price">{{ totalCashUsed | pricePoint }}원</span>
              </div>
            </div>
          </div>
        </div>

        <div class="col-sm-6">
          <div class="order-detail-content">
            <h2>구매혜택</h2>
          </div>
          <div class="order-detail">
            <div class="order-detail-payment wrap">
              <span class="point">어라운드포인트</span>
              <span class="price"
                >{{ (totalCashUsed * 0.025) | pricePoint }}P</span
              >
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CafeReviewRegisterPage from "@/views/Cafe/CafeReviewRegisterPage"
export default {
  name: "OrderHistoryDetail",
  components: { CafeReviewRegisterPage },
  data() {
    return {
      isClicked: false,
    }
  },
  props: {
    paymentDetail: { Type: Object, required: true },
    totalCashUsed: { Type: Number, required: true },
  },
  methods: {
    getMenuImage(img) {
      try {
        return require(`@/assets/cafe/cafeMenu/${img}`)
      } catch (_) {
        return require(`@/assets/cafe/cafeMenu/imgNull.png`)
      }
    },
  },
}
</script>

<style lang="scss" scoped>
@import "~@/assets/scss/components/order/order-history-detail.scss";
</style>

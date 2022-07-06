<template>
  <div class="container">
    <div class="row">
      <div class="col-sm-12">
        <header>
          <h1>주문 상세</h1>
        </header>
        <div class="order-history-detail-content-box">
          <div class="order-history-detail-payment-info">
            <span class="order-history-detail-title">{{
              paymentDetail.paymentDate | yyyyMMdd
            }}</span>
            <span class="order-history-detail-order-num">
              <strong>주문번호</strong> {{ paymentDetail.paymentNo }}
            </span>
          </div>
          <div>
            <div style="font-weight: 700;">{{ paymentDetail.paymentStatus }}</div>
            <!--    테이블로 작성-->
            <table class="order-history-detail-table">
              <tr
                class="order-history-detail-table table-row"
                v-for="orderItem in paymentDetail.orderItems"
                :key="orderItem.menuNo"
              >
                <td class="order-history-detail-table image">
                  <img :src="getMenuImage(orderItem.imageUrl)" alt="" />
                </td>
                <td class="order-history-detail-table item">
                  {{ orderItem.itemName }}
                </td>
                <td class="order-history-detail-table price-info">
                  <span class="order-history-detail-table price">{{ orderItem.amount | pricePoint }}</span>
                  <span class="order-history-detail-table won">원</span>
                  <hr class="order-history-detail-table line" />
                  <span class="order-history-detail-table amount">수량 {{ orderItem.quantity | pricePoint }}개</span>
                </td>
              </tr>
            </table>
            <ul>
              <CafeReviewRegisterPage
                class="text-btn"
                :cafeNo="paymentDetail.cafeNo"
              />
            </ul>
          </div>
        </div>
      </div>
      <div class="col-sm-6" style="padding-right: 20px">
        <div>
          <div class="order-history-detail-bottom-box payment-header-box">
            <h3 class="order-history-detail-bottom-box payment-header">결제정보</h3>
          </div>
          <div class="order-history-detail-bottom-box payment-content-box">
            <div class="order-history-detail-bottom-box payment-content-row">
              <span class="order-history-detail-bottom-box payment-content-title">주문 금액</span>
              <span class="order-history-detail-bottom-box payment-content-money">{{ paymentDetail.totalAmount | pricePoint }}원</span>
            </div>
            <div class="order-history-detail-bottom-box payment-content-row">
              <span class="order-history-detail-bottom-box payment-content-title">포인트 사용</span>
              <span class="order-history-detail-bottom-box payment-content-money">- {{ paymentDetail.totalPointAmount | pricePoint }}원</span>
            </div>
            <v-divider/>
            <div class="order-history-detail-bottom-box payment-content-row">
              <span class="order-history-detail-bottom-box payment-content-title">총 결제금액</span>
              <span class="order-history-detail-bottom-box payment-content-money">{{ paymentDetail.totalAmount | pricePoint }}원</span>
            </div>
          </div>
        </div>
        <div class="order-history-detail-bottom-box payment-header2-box">
          <h4 class="order-history-detail-bottom-box payment-header2">결제내역</h4>
        </div>
        <div class="order-history-detail-bottom-box payment-content-box">
          <div class="order-history-detail-bottom-box payment-content-row">
            <span class="order-history-detail-bottom-box payment-content-title">{{ paymentDetail.paymentMethod }}</span>
            <span class="order-history-detail-bottom-box payment-content-money">{{ totalCashUsed | pricePoint }}원</span>
          </div>
        </div>
      </div>
      <div class="col-sm-6" style="padding-left: 20px">
        <div class="order-history-detail-bottom-box payment-header-box">
          <h3 class="order-history-detail-bottom-box payment-header">구매혜택</h3>
        </div>
        <div class="order-history-detail-bottom-box payment-content-box">
          <div class="order-history-detail-bottom-box payment-content-row">
            <span class="order-history-detail-bottom-box payment-content-title">어라운드포인트</span>
            <span class="order-history-detail-bottom-box payment-content-money">{{ (totalCashUsed * 0.025) | pricePoint }}P</span>
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

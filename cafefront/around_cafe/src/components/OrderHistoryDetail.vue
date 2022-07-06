<template>
  <main>
    <div>
      <span>{{ paymentDetail.paymentDate | yyyyMMdd }}</span>
      <span>주문번호 {{ paymentDetail.paymentNo }}</span>
    </div>
    <div>{{ paymentDetail.paymentStatus }}</div>
    <!--    테이블로 작성-->
    <table
      v-for="orderItem in paymentDetail.orderItems"
      :key="orderItem.menuNo"
    >
      <tr>
        <td>
          <div>
            <img :src="getMenuImage(orderItem.imageUrl)" alt="" />
          </div>
        </td>
        <td>{{ orderItem.itemName }}</td>
        <td>
          <span>{{ orderItem.amount }}원</span>
          <v-divider />
          <span>수량 {{ orderItem.quantity }}개</span>
        </td>
      </tr>
    </table>
    <ul>
      <CafeReviewRegisterPage
          class="text-btn"
          :cafeNo="paymentDetail.cafeNo"
      />
    </ul>
    <div>
      <div>
        <div>
          <h2>결제정보</h2>
          <div>
            <span>주문 금액</span>
            <span>{{ paymentDetail.totalAmount | pricePoint }}원</span>
          </div>
          <div>
            <span>포인트 사용</span>
            <span>-{{ paymentDetail.totalPointAmount | pricePoint }}원</span>
          </div>
          <div>
            <span>총 결제금액</span>
            <span>{{ paymentDetail.totalAmount | pricePoint }}원</span>
          </div>
        </div>
        <div>
          <h2>결제내역</h2>
          <div>
            <span>{{ paymentDetail.paymentMethod }}</span>
            <span>{{ totalCashUsed | pricePoint }}원</span>
          </div>
        </div>
      </div>
      <div>
        <h2>구매혜택</h2>
        <div>
          <span>어라운드포인트</span>
          <span>{{ (totalCashUsed * 0.025) | pricePoint }}원</span>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
import CafeReviewRegisterPage from "@/views/Cafe/CafeReviewRegisterPage";
export default {
  name: "OrderHistoryDetail",
  components: {CafeReviewRegisterPage},
  data() {
    return {
      isClicked: false
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
@import "~@/assets/scss/components/detail/detail";
</style>

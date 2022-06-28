<template>
  <div class="order-sidebar navigation">
    <aside>
      <div class="order-sidebar contents">
        <div class="order-sidebar-contents title">
          <div class="order-sidebar-flexbox title">결제금액</div>
        </div>
        <div class="order-sidebar-contents content">
          <div class="order-sidebar-flexbox left">총 상품 금액</div>
          <div class="order-sidebar-flexbox right">0원</div>
        </div>
        <div class="order-sidebar-contents content">
          <div class="order-sidebar-flexbox left">포인트 사용</div>
          <div class="order-sidebar-flexbox right">0원</div>
        </div>
        <v-divider />
        <div class="order-sidebar-contents footer">
          <div class="order-sidebar-footer leftBox">최종 결제 금액</div>
          <div class="order-sidebar-footer rightBox">
            <div>
              <span class="order-sidebar-footer color-text">
                AAA,AAA,AAA
              </span>
              <span class="order-sidebar-footer text">원</span>
            </div>
            <div style="text-align: right; margin-top: 6px;">
              <span class="order-sidebar-contents point">31,333P</span>
              <span class="order-sidebar-contents point2">적립 예정</span>
            </div>
          </div>
        </div>
      </div>
      <v-btn class="order-sidebar button">AAA,AAA,AAA원 결제하기</v-btn>
    </aside>
  </div>
</template>

<script>
export default {
  name: "CafeSitePurchaseSidebar"
}
</script>


<style lang="scss" scoped>
@import "~@/assets/scss/components/order/order-sidebar";
</style>

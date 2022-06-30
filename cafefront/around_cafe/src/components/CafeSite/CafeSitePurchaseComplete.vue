<template>
  <div class="complete">
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <div class="complete-content">
            <div class="complete-content-header">
              <h1>주문완료</h1>
            </div>
            <!-- 이태호고객 유저네임으로 데이터 바인딩 -->
            <div class="complete-alarm">
              <span>이태호 고객님 주문이 완료되었습니다.</span>
            </div>

            <!-- 날짜부분과 주문번호만 데이터 바인딩 -->

            <div class="complete-number">
              <span>
                2022년 06월 27일(월)에 주문하신 주문의
                <span class="order-number">주문번호는 1번</span>입니다.
              </span>
            </div>
            <!-- 유저네임 폰번호 데이터 바인딩 -->

            <div class="complete-info">
              <span>받는 분 정보 : 이태호 010-0000-0000</span>
            </div>

            <div class="complete-pay">
              <div>
                <span
                  >주문금액 1,000,000원 - 포인트100P =
                  <span class="inf">최종결제금액</span>
                  <span class="price"> 1,000,000원</span></span
                >
              </div>
            </div>
          </div>
        </div>

        <!-- 결제내역 데이터 바인딩(무엇으로 얼마를 결제했는가) -->
        <div class="col-sm-6">
          <div class="complete-content">
            <div class="complete-content-header">
              <h2>결제내역</h2>
            </div>
            <div class="complete-content-history">
              <span>카카오페이 </span>
              <span>628,000원 </span>
            </div>
          </div>
        </div>

        <!-- 포인트 데이터 바인딩 -->

        <div class="col-sm-6">
          <div class="complete-content">
            <div class="complete-content-header">
              <h2>적립예정내역</h2>
            </div>
            <div class="complete-content-history">
              <span>어라운드 포인트 </span>
              <span>100P </span>
            </div>
          </div>
        </div>
      </div>
      <div class="btn-group">
        <v-btn class="btn-indigo btn-48">쇼핑계속하기</v-btn>
        <v-btn class="btn-indigo btn-48">주문상세조회</v-btn>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CafeSitePurchaseComplete",
}
</script>

<style lang="scss" scoped>
@import "~@/assets/scss/components/order/order-complete";
</style>

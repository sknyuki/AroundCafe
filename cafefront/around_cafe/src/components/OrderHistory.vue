<template>
  <div>
    <div class="order-history">
      <div class="order-history-filter">
        <div class="order-history-filter list">
          <ul class="order-history-filter left">
            <li class="order-history-filter item">
              <button>
                기간
                <i class="icCaret"></i>
              </button>
            </li>

            <li class="order-history-filter item">
              <button>
                주문상태
                <i class="icCaret"></i>
              </button>
            </li>
          </ul>
        </div>
      </div>
      <section v-for="order in orderItem" :key="order.orderNo">
        <!-- 상태 -->
        <div class="order-history-day">
          <div class="order-history-content">
            <span>{{ order.orderNo }} | </span>{{ order.orderDay }}
          </div>
          <div class="btn-group">
            <v-btn class="btn-indigo btn-40">리뷰 작성</v-btn>
          </div>
        </div>
        <!-- 내용 -->
        <div>
          <div class="order-history-info">
            <h3>
              {{ order.orderStatus }}
              <span class=""> ·</span>
              <span> {{ order.orderTime }}</span>
            </h3>
            <div class="order-history-contents">
              <div class="order-history-test">
                <div class="order-history-image">
                  <picture>
                    <source />
                    <img src="@/assets/images/cold.jpg" alt="" />
                  </picture>
                </div>
                <div class="order-history-text">
                  <div class="order-history-text inf">
                    <div class="order-history-text name">
                      <span> {{ order.orderName }}</span>
                    </div>
                    <div class="order-history-text location">
                      <span> {{ order.orderLocation }}</span>
                    </div>
                    <div class="order-history-text time">
                      <span> {{ order.orderTime }}</span>
                    </div>
                  </div>
                  <div class="order-history-text menu">
                    <div class="order-history-text">
                      <span> {{ order.orderMenu }}</span>
                    </div>
                    <div class="order-history-text price">
                      <span>
                        {{ order.orderPrice | pricePoint }} |
                        {{ order.orderQuantity }}개</span
                      >
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
  </div>
</template>

<script>
export default {
  name: "OrderHistory",

  props: {
    orderItem: {
      type: Array,
      required: true,
    },
  },
}
</script>

<style lang="scss" scoped>
@import "~@/assets/scss/components/order/order-history";
</style>

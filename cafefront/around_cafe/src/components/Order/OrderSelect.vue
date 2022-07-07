<template>
  <div>
    <div class="order-history">
      <div class="order-history-filter">
        <div class="order-history-filter list">
          <ul class="order-history-filter left"></ul>
        </div>
      </div>
      <section v-for="order in orderStatuslists" :key="order.payment_no">
        <!-- 상태 -->
        <div class="order-history-day">
          <div class="order-history-content">
            <span>{{ order.paymentNo }} | </span
            >{{ order.paymentDate | yyyyMMdd }}
          </div>
          <OrderCafeBtn v-if="order" :order="order" />
        </div>
        <!-- 내용 -->
        <div>
          <div class="order-history-info">
            <h3>
              {{ order.paymentStatus }}
              <span class="">.</span>
              <span> {{ order.paymentDate | HHmm }}</span>
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
                    <div v-if="order.orderItems.length > 0">
                      <div v-for="(item, idx) in order.orderItems" :key="idx">
                        <div class="order-history-text name">
                          <span>
                            {{ item.itemName }}&nbsp; &nbsp;
                            {{ item.quantity }}&nbsp;개</span
                          >
                        </div>
                      </div>
                    </div>

                    <div class="order-history-text time">
                      <span> {{ order.paymentMethod }}</span>
                    </div>
                  </div>
                  <div class="order-history-text menu">
                    <div class="order-history-text">
                      <span> {{ order.orderMenu }}</span>
                    </div>
                    <div class="order-history-text price">
                      <span>
                        {{ order.totalAmount | pricePoint }}|{{
                          order.totalPointAmount | pricePoint
                        }}
                        | {{ order.totalQuantity }}개</span
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
import OrderCafeBtn from "@/components/Order/OrderCafeBtn.vue"

export default {
  name: "OrderSelect",
  components: {
    OrderCafeBtn,
  },

  props: {
    orderStatuslists: {
      type: Array,
      required: true,
    },
  },

  data() {
    return {
      paymentStatus: [],
      paymentNo: "",
      status: "",
    }
  },
}
</script>

<style lang="scss" scoped>
@import "~@/assets/scss/components/order/order-history";
.selectDate {
  width: 90px;
  height: 20px;
}
</style>

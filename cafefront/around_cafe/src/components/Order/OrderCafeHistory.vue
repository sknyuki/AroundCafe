<template>
  <div>
    <div class="order-history">
      <div class="order-history-filter">
        <div class="order-history-filter list">
          <ul class="order-history-filter left">
            <li class="order-history-filter item">
              <button type="button" aria-label="기간">
                기간
                <i class="icCaret"></i>
              </button>
            </li>

            <li class="order-history-filter item">
              <button type="button" aria-label="주문상태">
                주문상태
                <i class="icCaret"></i>
              </button>
            </li>
          </ul>
        </div>
      </div>
      <section v-for="order in orderCafeLists" :key="order.payment_no">
        <!-- 상태 -->
        <div class="order-history-day">
          <div class="order-history-content">
            <span>{{ order.paymentNo }} | </span
            >{{ order.paymentDate | yyyyMMdd }}
          </div>
          <div class="btn-group">
            <v-btn
              type="button"
              aria-label="주문확인"
              class="btn-indigo btn-40"
              @click="changeOrder(order.paymentNo, order.paymentStatus)"
              >주문확인</v-btn
            >
          </div>
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
//import { mapState } from "vuex"
import axios from "axios"
export default {
  name: "OrderCafeHistory",

  props: {
    user: {
      type: Object,
      required: true,
    },
    orderCafeLists: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      paymentStatus: [],
      paymentNo: "",
    }
  },

  methods: {
    changeOrder(paymentNo, paymentStatus) {
      //this.orderCafeLists.paymentNo[testNum]

      this.paymentNo = paymentNo
      this.paymentStatus = paymentStatus

      axios
        .put(`http://localhost:7777/payment/order/status/${this.paymentNo}`)
        .then(() => {
          alert("수정되었습니다!")
          this.$router.go()
        })
        .catch(() => {
          alert("수정 실패!")
        })
    },
  },
  // computed: {
  //   ...mapState(["orderCafeLists"]),
  // },
  // data() {
  //   return {
  //     orderCafeLists: [],
  //   }
  // },

  // mounted() {
  //   for (let i = 0; this.orderCafeLists.orderItems.length; i++) {
  //     if (this.orderCafeLists.orderItems.length > 1) {
  //       this.orderCafeLists.itemInitName =
  //         this.orderCafeLists.orderItems[i].itemName
  //     } else {
  //       this.orderCafeLists.itemInitName =
  //         this.orderCafeLists.orderItems[0].itemName
  //     }
  //   }
  // },
}
</script>

<style lang="scss" scoped>
@import "~@/assets/scss/components/order/order-history";
</style>

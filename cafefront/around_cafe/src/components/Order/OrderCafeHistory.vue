<template>
  <div>
    <div class="order-history">
      <div class="order-history-filter">
        <div class="order-history-filter list">
          <ul class="order-history-filter left">
            <li class="order-history-filter item">
              <v-select
                class="selectDate"
                v-model="findList2"
                :items="selectPeriod"
                label="기간"
                style="width: 200px"
              ></v-select>
            </li>
            <li class="order-history-filter item">
              <v-select
                class="selectDate"
                v-model="findList"
                :items="selectStatus"
                label="주문 상태"
                style="width: 200px"
              ></v-select>
            </li>
          </ul>
        </div>
      </div>
      <div>
        <section v-for="order in orderCafeLists" :key="order.payment_no">
          <!-- 상태 -->
          <div
            v-show="
              status(order.paymentStatus, findList) && period(order.paymentDate)
            "
          >
            <div class="order-history-day">
              <div class="order-history-content">
                <span>{{ order.paymentNo }} | </span
                >{{ order.paymentDate | yyyyMMdd }}
              </div>
              <OrderCafeBtn v-if="order" :order="order" :role="role" :user="user" />
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
                        <img
                          v-if="order.orderItems[0].imageUrl == null"
                          :src="require(`@/assets/cafe/cafeMenu/imgNull.png`)"
                        />

                        <img
                          v-else
                          :src="
                            require(`@/assets/cafe/cafeMenu/${order.orderItems[0].imageUrl}`)
                          "
                        />
                      </picture>
                    </div>
                    <div class="order-history-text">
                      <div class="order-history-text inf">
                        <div v-if="order.orderItems.length > 0">
                          <div
                            v-for="(item, idx) in order.orderItems"
                            :key="idx"
                          >
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
                            {{ order.totalAmount | pricePoint }} |
                            {{ order.totalPointAmount | pricePoint
                            }}<span class="point">P</span> |
                            {{ order.totalQuantity }}개</span
                          >
                        </div>
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
  </div>
</template>

<script>
import OrderCafeBtn from "@/components/Order/OrderCafeBtn.vue"

export default {
  name: "OrderCafeHistory",
  components: {
    OrderCafeBtn,
  },

  props: {
    user: {
      type: Object,
      required: true,
    },
    orderCafeLists: {
      type: Array,
      required: true,
    },
    role: {
      type: String,
      required: true,
    },
  },

  data() {
    return {
      paymentStatus: [],

      paymentNo: "",
      findList: null,
      findList2: null,
      selectStatus: [
        "전체보기",
        "PAYMENT_CONFIRMED",
        "CAFE_READY",
        "PICK_UP_FINISHED",
        "PAYMENT_CANCELED",
      ],
      selectPeriod: ["전체보기", "1달", "3달", "6달", "1년"],
      newStatusArr: [],
      newPeriodArr: [],
      selectPart: ["all", "select"],
      orderStatuslists: [],
      orderPeriodlists: [],
      dateStart: "",
    }
  },

  methods: {
    findPeriod() {
      switch (this.findList2) {
        case "1달":
          return this.getPeriod(1)
        case "3달":
          return this.getPeriod(3)
        case "6달":
          return this.getPeriod(6)
        case "1년":
          return this.getPeriod(12)
      }
    },
    getPeriod(changeMonth) {
      let today = new Date()
      const newDate = new Date(
        new Date().setFullYear(
          today.getFullYear(),
          today.getMonth() - changeMonth,
          today.getDate() + 1
        )
      )
      return newDate
    },
    status(status, findlist) {
      return status === findlist || findlist === "전체보기" || findlist === null
    },
    period(paymentDate) {
      const newPaymentDate = new Date(paymentDate)
      const newDate = this.findPeriod()
      return (
        newPaymentDate >= newDate ||
        this.findList2 === "전체보기" ||
        this.findList2 === null
      )
    },
  },
}
</script>

<style lang="scss" scoped>
@import "~@/assets/scss/components/order/order-history";
.selectDate {
  width: 90px;
  height: 20px;
  margin: 20px;
}
.point {
  color: $red;
  font-weight: 700;
}
</style>

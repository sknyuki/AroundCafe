<template>
  <div>
    <div class="order-history">
      <div class="order-history-filter">
        <div class="order-history-filter list">
          <ul class="order-history-filter left">
            <li class="order-history-filter item">
              <!-- <button type="button" aria-label="기간">
                기간
                <i class="icCaret"></i>
              </button>
              <button type="button" aria-label="주문상태">
                주문상태
                <i class="icCaret"></i>
              </button> -->
            </li>
            <li class="order-history-filter item">
              <v-select
                class="selectDate"
                v-model="findList"
                :items="selectStatus"
                label="주문 상태"
                style="width: 200px"
                @change="findStatus"
              ></v-select>
            </li>
            <li class="order-history-filter item">
              <v-select
                class="selectDate"
                v-model="findList2"
                :items="selectPeriod"
                label="기간"
                style="width: 200px"
                @change="findPeriod"
              ></v-select>
            </li>
          </ul>
        </div>
      </div>
      <div v-if="this.selectPart == 'select'">
        <OrderSelect :orderStatuslists="orderStatuslists" />
      </div>
      <div v-if="this.selectPart == 'select'">
        <OrderPeriod :orderPeriodlists="orderPeriodlists" @click="getPeriod" />
      </div>
      <div v-else>
        <section v-for="order in orderCafeLists" :key="order.payment_no">
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
  </div>
</template>

<script>
import OrderCafeBtn from "@/components/Order/OrderCafeBtn.vue"
import OrderSelect from "@/components/Order/OrderSelect.vue"
import OrderPeriod from "@/components/Order/OrderPeriod.vue"

export default {
  name: "OrderCafeHistory",
  components: {
    OrderCafeBtn,
    OrderPeriod,
    OrderSelect,
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
        "PAYMENT_READY",
      ],
      selectPeriod: ["전체보기", "1달", "3달", "6달", "1년", "2년"],
      newStatusArr: [],
      newPeriodArr: [],
      selectPart: ["all", "select"],
      orderStatuslists: [],
      orderPeriodlists: [],
      dateStart: "",
    }
  },

  methods: {
    findStatus() {
      this.orderPeriodlists = []
      this.orderStatuslists = []
      this.newStatusArr = new Array()
      if (this.findList == "전체보기") {
        return (this.selectPart = "all")
      } else {
        for (let i = 0; i < this.orderCafeLists.length; i++) {
          if (this.orderCafeLists[i].paymentStatus == this.findList) {
            this.orderStatuslists.push(this.orderCafeLists[i])
          } else {
            continue
          }
          this.selectPart = "select"
        }
      }
    },
    findPeriod() {
      this.orderStatuslists = []
      this.orderPeriodlists = []
      this.newPeriodArr = new Array()

      switch (this.findList2) {
        case "전체보기":
          return (this.selectPart = "all")
        case "1달":
          this.getPeriod(1)

          break
        case "3달":
          this.getPeriod(3)

          break
        case "6달":
          this.getPeriod(6)

          break
      }

      if (this.findList2 == "전체보기") {
        return (this.selectPart = "all")
      } else {
        for (let i = 0; i < this.orderCafeLists.length; i++) {
          if (new Date(this.orderCafeLists[i].paymentDate) >= this.dateStart) {
            this.orderPeriodlists.push(this.orderCafeLists[i])
          } else {
            continue
          }
          this.selectPart = "select"
        }
      }
    },
    getPeriod(changeMonth) {
      let today = new Date()
      this.dateStart = new Date(
        new Date().setFullYear(
          today.getFullYear(),
          today.getMonth() - changeMonth,
          today.getDate() + 1
        )
      )
      // this.orderCafeLists.forEach((item) => {
      //   if (this.dateStart <= item.paymentDate) {
      //     orderPeriodlists.push(item)
      //   }
      // })
    },
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

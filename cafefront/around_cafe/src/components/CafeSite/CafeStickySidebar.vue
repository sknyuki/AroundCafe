<template>
  <div
    class="sidebar"
    style="
      border: 1px solid rgb(221, 221, 221);
      border-radius: 12px;
      padding: 24px;
      box-shadow: rgba(0, 0, 0, 0.12) 0px 6px 16px;
    "
  >
    <aside>
      <h2 class="sidebar-header">Selected menu</h2>
      <v-divider></v-divider>
      <br />
      <ul class="list-flex">
        <li v-for="basket in basketlist" :key="basket.menu_no">
          <span> menu:{{ basket.menu_name }} </span>
          <br />
          <span>
            price:{{ basket.menu_price | pricePoint }} <br />
            수량 :{{ basket.number }}

            <v-btn class="show-btns-up" icon @click="UP(basket)">
              <v-icon> mdi-arrow-up </v-icon>
            </v-btn>
            <v-btn class="show-btns-down" icon @click="DOWN(basket)">
              <v-icon> mdi-arrow-down </v-icon>
            </v-btn>
          </span>
          <br />PerMenuPrices:{{ basket.PerMenuPrices | pricePoint }}
          <br />
          <br />
        </li>

        <v-divider></v-divider>
        <span class="total-price">
          total price: {{ totalPrice | pricePoint }}
        </span>
      </ul>

      <div class="버튼">
        <v-row justify="center">
          <v-col cols="3">
            <v-btn
              text
              color="black"
              rounded
              x-large
              style="padding: 10px; width: 150px"
              @click="SelectdeMenuSubmit()"
              >주문하기
            </v-btn>
          </v-col>
        </v-row>
      </div>
    </aside>
  </div>
</template>

<script>
export default {
  name: "CafeStickySidebar",
  components: {},
  data() {
    return {
      totalPrice: 0,
      //basket반영시 변경

      // baskets: [
      //   { menu: "아메리카노", Price: 4900, PerMenuPrices: 0, number: 0 },
      //   { menu: "카페모카", Price: 6700, PerMenuPrices: 0, number: 0 },
      //   { menu: "마끼아또", Price: 7200, PerMenuPrices: 0, number: 0 },
      //   { menu: "프라프치노", Price: 7200, PerMenuPrices: 0, number: 0 },
      // ],
    }
  },
  props: {
    basketlist: {
      type: Array,
    },
  },
  methods: {
    UP(basket) {
      // console.log("number의 값")
      // console.log(basket.number)
      // console.log(basket.PerMenuPrice)
      // console.log(" 길이")
      // console.log(this.baskets.length)

      basket.number += 1

      basket.PerMenuPrices = basket.menu_price * basket.number
      // console.log(basket.menu_price)
      // console.log(basket.number)
      // console.log(basket.PerMenuPrices)
      this.totalPrice = 0
      for (let i = 0; i < this.basketlist.length; i++) {
        // console.log(i + 1 + "번째 메뉴 루프")
        // console.log(i + 1 + "번째 메뉴의 총 가격")
        // console.log(this.basketlist[i].PerMenuPrices)
        this.totalPrice += this.basketlist[i].PerMenuPrices
        // console.log("전체 총가격")
        // console.log(this.totalPrice)
      }
    },
    DOWN(basket) {
      // console.log("number의 값")
      // console.log(basket.number)
      basket.number -= 1
      basket.PerMenuPrices = basket.menu_price * basket.number
      this.totalPrice = 0
      for (let i = 0; i < this.basketlist.length; i++) {
        this.totalPrice += this.basketlist[i].PerMenuPrices
      }
    },

    SelectdeMenuSubmit() {
      var result = confirm("결제 페이지로 이동하시겠습니까?")
      if (result) {
        //const { totalPrice, basketlist } = this
        // console.log("넘어가는 파일:")
        // console.log(this.totalPrice, this.basketlist)
        this.$router.push({
          name: "CafePurchase",
          params: {
            totalPrice: this.totalPrice,
            basketlist: this.basketlist,
          },
        })
        console.log("넘어가는 파일:")
        console.log(this.totalPrice, this.basketlist)
      }
    },

    // this.$emit("selectedMenu", { totalPrice, basketlist })
  },
}
</script>
<style scoped>
.sidebar {
  position: sticky;
  top: 100px;
}
.total-price {
  color: primary-color;
  font-weight: 700;
  text-transform: uppercase;
}
</style>

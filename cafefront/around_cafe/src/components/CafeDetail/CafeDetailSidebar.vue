<template>
  <aside class="detail-sidebar-sticky">
    <dl
      v-for="(basket, index) in basketList"
      :key="index"
      class="detail-sidebar"
    >
      <div class="detail-sidebar-info">
        <div class="detail-sidebar-image">
          <template>
            <img
              v-if="basket.menu_img != null"
              v-bind:src="require(`@/assets/cafe/cafeMenu/${basket.menu_img}`)"
            />
            <img
              v-else
              v-bind:src="require(`@/assets/cafe/cafeMenu/imgNull.png`)"
            />
          </template>
        </div>
        <div class="detail-sidebar-menu">
          <dd>{{ basket.menu_name }}</dd>

          <div class="detail-sidebar-price">
            <dd>
              <span>{{ basket.menu_price }}</span
              >원
            </dd>
          </div>
        </div>
      </div>
      <div class="detail-sidebar-price per">
        <dt>
          <button @click="decreaseQuantity(basket, index)">-</button>
          <span> {{ basket.per_menu_quantity }} </span>
          <button @click="increaseQuantity(basket)">+</button>
        </dt>
        <dd>
          <span>{{ basket.per_menu_total_price }}</span
          >원
        </dd>
      </div>
    </dl>

    <div class="detail-sidebar-price total">
      <dt>총 상품금액</dt>
      <dd>
        <span>{{ totalPrice }}</span
        >원
      </dd>
    </div>
    <v-btn @click="selectedMenuSubmit()" class="btn-indigo btn-48">
      주문하기
    </v-btn>
  </aside>
</template>

<script>
export default {
  name: "CafeDetailSidebar",
  props: {
    basketList: {
      type: Array,
    },
    cafeBoard: {
      type: Object,
      required: true,
    },
    cafeNo: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      totalPrice: 0,
    }
  },
  beforeUpdate() {
    this.totalPrice = 0
    for (let i = 0; i < this.basketList.length; i++) {
      this.totalPrice += this.basketList[i].per_menu_total_price
    }
  },

  methods: {
    increaseQuantity(basket) {
      basket.per_menu_quantity += 1
      basket.per_menu_total_price = basket.menu_price * basket.per_menu_quantity

      this.totalPrice = 0
      for (let i = 0; i < this.basketList.length; i++) {
        this.totalPrice += this.basketList[i].per_menu_total_price
      }
    },
    decreaseQuantity(basket, index) {
      if (basket.per_menu_quantity == 0) {
        this.basketList.splice(index)
      } else {
        basket.per_menu_quantity -= 1
        basket.per_menu_total_price =
          basket.menu_price * basket.per_menu_quantity
      }

      this.totalPrice = 0
      for (let i = 0; i < this.basketList.length; i++) {
        this.totalPrice += this.basketList[i].per_menu_total_price
      }
    },
    selectedMenuSubmit() {
      console.log(this.basket)

      var result = confirm("결제 페이지로 이동하시겠습니까?")
      if (result) {
        this.$router.push({
          name: "CafePurchase",
          params: {
            cafeInfo: {
              totalPrice: this.totalPrice,
              cafeNo: this.cafeNo,
              cafe_name: this.cafeBoard.cafe_name,
            },
            basketList: this.basketList,
          },
        })
        // console.log("넘어가는 파일:")
        // console.log(this.cafeInfo, this.basketList)
      }
    },
  },
}
</script>

<style lang="scss" scoped>
@import "~@/assets/scss/components/detail/detail-sidebar";
</style>

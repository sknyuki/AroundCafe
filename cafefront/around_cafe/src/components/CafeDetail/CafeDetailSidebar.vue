<template>
  <aside class="detail-sidebar-sticky">
    <dl
      v-for="basket in basketList"
      :key="basket.menu_no"
      class="detail-sidebar"
    >
      <div class="detail-sidebar-info">
        <div class="detail-sidebar-image">
          <template>
            <img src="@/assets/images/cold.jpg" />
          </template>
        </div>
        <div class="detail-sidebar-menu">
          <dd>{{ basket.menu_name }}</dd>

          <div class="detail-sidebar-price">
            <dd>
              <span>{{ basket.menu_price | pricePoint }}</span
              >원
            </dd>
          </div>
        </div>
      </div>
      <div class="detail-sidebar-price per">
        <dt>
          <button @click="increaseQuantity(basket)">+</button>
          <span> {{ basket.number }} </span>
          <button @click="decreaseQuantity(basket)">-</button>
        </dt>
        <dd>
          <span>{{ basket.PerMenuPrices | pricePoint }}</span
          >원
        </dd>
      </div>
    </dl>

    <div class="detail-sidebar-price total">
      <dt>총 상품금액</dt>
      <dd>
        <span>{{ totalPrice | pricePoint }}</span
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
  },
  data() {
    return {
      totalPrice: 0,
    }
  },
  methods: {
    increaseQuantity(basket) {
      basket.number += 1
      basket.PerMenuPrices = basket.menu_price * basket.number

      this.totalPrice = 0
      for (let i = 0; i < this.basketList.length; i++) {
        this.totalPrice += this.basketList[i].PerMenuPrices
      }
    },
    decreaseQuantity(basket) {
      basket.number -= 1
      basket.PerMenuPrices = basket.menu_price * basket.number

      this.totalPrice = 0
      for (let i = 0; i < this.basketList.length; i++) {
        this.totalPrice += this.basketList[i].PerMenuPrices
      }
    },
    selectedMenuSubmit() {
      var result = confirm("결제 페이지로 이동하시겠습니까?")
      if (result) {
        this.$router.push({
          name: "CafePurchase",
          params: {
            totalPrice: this.totalPrice,
            basketList: this.basketList,
          },
        })
        console.log("넘어가는 파일:")
        console.log(this.totalPrice, this.basketList)
      }
    },
  },
}
</script>

<style lang="scss" scoped>
@import "~@/assets/scss/components/detail/detail-sidebar";
</style>

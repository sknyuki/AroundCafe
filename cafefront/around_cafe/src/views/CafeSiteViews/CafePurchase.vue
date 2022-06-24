<template>
  <div class="container">
    <div class="menu-item-btn">
      <v-btn icon @click="check()"> 값 확인 </v-btn>
    </div>
  </div>
</template>

<script>
export default {
  name: "CafePurchase",
  props: {
    totalPrice: { type: Number },
    basketlist: { type: Object },
  },
  methods: {
    check() {
      console.log("total Price 확인")
      console.log(this.totalPrice)
      console.log("결제할 메뉴 목록")
      console.log(this.basketlist)
    },
  },
}
</script>

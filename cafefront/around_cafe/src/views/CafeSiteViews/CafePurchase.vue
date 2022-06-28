<template>
  <div class="container">
    <div class="row">
      <div class="col-sm-12 col-md-12 col-lg-8">
        <cafe-site-purchase-list
          :userInfo="userInfo"
          :backetList="basketList"
          @getPaymentInfo="getPaymentInfo"
          @getIsPointValidated="getIsPointValidated"
        />
      </div>
      <div class="col-sm-0 col-md-0 col-lg-4" style="padding-left: 20px">
        <cafe-site-purchase-sidebar
          :totalPrice="totalPrice"
          :paymentInfo="paymentInfo"
          :isPointValidated="isPointValidated"
        />
      </div>
    </div>
  </div>
</template>

<script>
import CafeSitePurchaseList from "@/components/CafeSite/CafeSitePurchaseList"
import { mapActions, mapState } from "vuex"
import CafeSitePurchaseSidebar from "@/components/CafeSite/CafeSitePurchaseSidebar"
export default {
  name: "CafePurchase",
  components: { CafeSitePurchaseSidebar, CafeSitePurchaseList },
  computed: {
    ...mapState(["userInfo"]),
  },
  mounted() {
    this.fetchUserInfo()
  },
  data() {
    return {
      totalPointAmount: 0,
      paymentInfo: {},
      isPointValidated: true,
    }
  },
  props: {
    totalPrice: { type: Number },
    basketList: { type: Object },
  },
  methods: {
    ...mapActions(["fetchUserInfo"]),
    check() {
      console.log("total Price 확인")
      console.log(this.totalPrice)
      console.log("결제할 메뉴 목록")
      console.log(this.basketList)
    },
    getPaymentInfo(paymentInfo) {
      this.paymentInfo = paymentInfo
    },
    getIsPointValidated(isPointValidated) {
      this.isPointValidated = isPointValidated
    }
  },
}
</script>

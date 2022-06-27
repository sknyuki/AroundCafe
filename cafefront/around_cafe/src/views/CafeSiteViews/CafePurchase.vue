<template>
  <div class="container">
    <div class="row">
      <div class="col-sm-12 col-md-12 col-lg-8">
        <cafe-site-purchase-list
          :userInfo="userInfo"
          :totalPrice="totalPrice"
          :backetList="basketlist"
        />
      </div>
      <div class="col-sm-0 col-md-0 col-lg-4" style="padding-left: 20px;">
        <cafe-site-purchase-sidebar />
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
  props: {
    totalPrice: { type: Number },
    basketlist: { type: Object },
  },
  methods: {
    ...mapActions(["fetchUserInfo"]),
    check() {
      console.log("total Price 확인")
      console.log(this.totalPrice)
      console.log("결제할 메뉴 목록")
      console.log(this.basketlist)
    },
  },
}
</script>

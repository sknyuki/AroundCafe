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
          @submit="onSubmit"
        />
      </div>
    </div>
  </div>
</template>

<script>
import CafeSitePurchaseList from "@/components/CafeSite/CafeSitePurchaseList"
import CafeSitePurchaseSidebar from "@/components/CafeSite/CafeSitePurchaseSidebar"
import { mapActions, mapState } from "vuex"
import axios from "axios"
export default {
  name: "CafePurchase",
  components: { CafeSitePurchaseSidebar, CafeSitePurchaseList },
  computed: {
    ...mapState(["userInfo"]),
  },
  created() {
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
    onSubmit(paymentInfo) {
      if(paymentInfo.totalPointAmount == null || paymentInfo.totalPointAmount === "") {
        paymentInfo.totalPointAmount = 0
      }
      const config = {
        headers: {
          "Content-Type": "Application/json",
        },
      }
      axios
        .post(
          `http://localhost:7777/payment`,
          JSON.stringify(paymentInfo),
          config
        )
        .then((response) => {
          window.open(
            `http://localhost:5000/payment/ready/${this.paymentInfo.paymentMethod}/${response.data}`,
            "_blank",
            "width=440, height=680, menubar=no, toolbar=no, location=no, status=no, resizable=no, scrollbars=no"
          )
        })
        .catch((err) => {
          alert(err)
        })
    },
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
    },
  },
}
</script>

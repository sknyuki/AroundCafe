<template>
  <div>
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <ImgBox />
        </div>
        <div class="col-sm-4 col-md-3 col-lg-3">
          <UserSidebar />
        </div>
        <div class="col-sm-8 col-md-9 col-lg-9">
          <OrderHistoryDetail
            :paymentDetail="paymentDetail"
            :totalCashUsed="totalCashUsed"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import OrderHistoryDetail from "@/components/OrderHistoryDetail"
import UserSidebar from "@/components/User/UserSidebar"
import ImgBox from "@/components/ImgBox"
import { mapActions, mapState } from "vuex"

export default {
  name: "UserOrderHistoryDetail",
  components: { ImgBox, UserSidebar, OrderHistoryDetail },
  data() {
    return {
      totalCashUsed: 0,
    }
  },
  props: {
    paymentNo: { Type: Number, required: true },
  },
  computed: {
    ...mapState(["paymentDetail"]),
  },
  methods: {
    ...mapActions(["fetchPaymentDetail"]),
  },
  async created() {
    await this.fetchPaymentDetail(this.paymentNo)
    this.totalCashUsed =
      parseInt(this.paymentDetail.totalAmount) -
      parseInt(this.paymentDetail.totalPointAmount)
  },
}
</script>

<style scoped></style>

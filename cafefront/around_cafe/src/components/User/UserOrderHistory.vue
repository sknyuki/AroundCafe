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
          <header>
            <h1>주문 내역</h1>
          </header>
          <OrderCafeHistory
            :orderCafeLists="paymentList"
            :user="user"
            role="USER"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ImgBox from "@/components/ImgBox.vue"
import UserSidebar from "@/components/User/UserSidebar.vue"
import { mapActions, mapState } from "vuex"
import OrderCafeHistory from "@/components/Order/OrderCafeHistory"

export default {
  components: { OrderCafeHistory, ImgBox, UserSidebar },
  name: "UserOrderHistory",
  data() {
    return {}
  },
  async mounted() {
    await this.fetchUser()
    console.log(this.user)
    await this.fetchPaymentList(this.user.memNo)
    console.log(this.paymentList)
  },
  computed: {
    ...mapState(["paymentList", "user"]),
  },
  methods: {
    ...mapActions(["fetchPaymentList", "fetchUser"]),
  },
}
</script>

<style lang="scss" scoped></style>

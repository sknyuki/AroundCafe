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
          <template v-if="loading">
            <LoadingSpinner :loading="loading" />
          </template>
          <template v-else>
            <OrderCafeHistory
              :orderCafeLists="paymentList"
              :user="user"
              role="USER"
            />
          </template>
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
import LoadingSpinner from "@/components/LoadingSpinner.vue"

export default {
  components: { OrderCafeHistory, ImgBox, UserSidebar, LoadingSpinner },
  name: "UserOrderHistory",
  data() {
    return {
      loading: false,
    }
  },
  async mounted() {
    this.loading = true
    await this.fetchUser()
    console.log(this.user)
    await this.fetchPaymentList(this.user.memNo)
    console.log(this.paymentList)
    this.loading = false
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

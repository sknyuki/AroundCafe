<template>
  <div>
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <ImgBox />
        </div>
        <div class="col-sm-4 col-md-3 col-lg-3">
          <CafeSidebar />
        </div>
        <div class="col-sm-8 col-md-9 col-lg-9">
          <header>
            <h1>주문 내역</h1>
          </header>
          <template v-if="loading">
            <LoadingSpinner :loading="loading" />
          </template>
          <template v-else>
            <OrderCafeHistory :orderCafeLists="orderCafeLists" :user="user" role="CAFE"/>
          </template>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ImgBox from "@/components/ImgBox.vue"
import OrderCafeHistory from "@/components/Order/OrderCafeHistory.vue"
import { mapState, mapActions } from "vuex"
import CafeSidebar from "../Cafe/CafeSidebar.vue"
import LoadingSpinner from "../LoadingSpinner.vue"

export default {
  components: { ImgBox, OrderCafeHistory, CafeSidebar, LoadingSpinner },
  name: "OrderCafeList",

  data() {
    return {
      loading: false,
    }
  },
  computed: {
    ...mapState(["orderCafeLists", "user"]),
  },

  async mounted() {
    this.loading = true
    await this.fetchOrderCafeList(this.user.cafeNo)
    await setTimeout(() => {
      this.loading = false
    }, 1500)
  },

  methods: {
    ...mapActions(["fetchOrderCafeList", "fetchUser"]),
  },
}
</script>

<style lang="scss" scoped></style>

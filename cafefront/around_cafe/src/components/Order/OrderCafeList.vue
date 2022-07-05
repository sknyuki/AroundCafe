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
          <OrderCafeHistory :orderCafeLists="orderCafeLists" :user="user" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ImgBox from "@/components/ImgBox.vue"
import UserSidebar from "@/components/User/UserSidebar.vue"
import OrderCafeHistory from "@/components/Order/OrderCafeHistory.vue"
import { mapState, mapActions } from "vuex"

export default {
  components: { ImgBox, UserSidebar, OrderCafeHistory },
  name: "OrderCafeList",

  data() {
    return {
      // orderCafeLists: [],
    }
  },
  computed: {
    ...mapState(["orderCafeLists", "user"]),
  },

  mounted() {
    this.fetchOrderCafeList(this.user.cafeNo)
  },

  methods: {
    ...mapActions(["fetchOrderCafeList", "fetchUser"]),
  },
}
</script>

<style lang="scss" scoped></style>

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
          <OrderHistory :orderList="orderList" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ImgBox from "@/components/ImgBox.vue"
import UserSidebar from "@/components/User/UserSidebar.vue"
import OrderHistory from "../OrderHistory.vue"

export default {
  components: { ImgBox, UserSidebar, OrderHistory },
  name: "UserOrderHistory",

  data() {
    return {
      orderList: [
        {
          orderNo: "19292999",
          orderDay: "2022-06-21",
          orderStatus: "주문완료",
          orderTime: "18:30",
          orderName: "스타벅스",
          orderMenu: "콜드브루",
          orderLocation: "서울 강북구",
          orderPrice: "5000",
          orderQuantity: "1",
        },
        {
          orderNo: "19299923",
          orderDay: "2022-06-22",
          orderStatus: "주문완료",
          orderTime: "14:30",
          orderName: "스타벅스",
          orderMenu: "콜드브루",
          orderLocation: "서울 성북구",
          orderPrice: "10000",
          orderQuantity: "2",
        },
        {
          orderNo: "19292999",
          orderDay: "2022-07-05",
          orderStatus: "주문완료",
          orderTime: "11:30",
          orderName: "스타벅스",
          orderMenu: "콜드브루",
          orderLocation: "서울 강남구",
          orderPrice: "20000",
          orderQuantity: "4",
        },
      ],
    }
  },
}
</script>

<style lang="scss" scoped></style>

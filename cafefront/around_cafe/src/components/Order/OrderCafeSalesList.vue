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
            <h1>매출 확인</h1>
          </header>
          <OrderCafeSalesDays />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CafeSidebar from "@/components/Cafe/CafeSidebar.vue"
import ImgBox from "@/components/ImgBox.vue"
import OrderCafeSalesDays from "./OrderCafeSalesDays.vue"
export default {
  components: { ImgBox, CafeSidebar, OrderCafeSalesDays },
  name: "OrderCafeSalesList",
}
</script>
<style lang="scss" scoped></style>

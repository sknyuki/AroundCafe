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
          <select @change="showMenu($event)">
            <option
              v-for="(item, index) in selectSales"
              :key="index"
              :value="item.id"
            >
              {{ item.name }}
            </option>
          </select>
          <OrderCafeSalesDays v-if="salesDays" />
          <OrderCafeSalesMenu v-if="salesMenu" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CafeSidebar from "@/components/Cafe/CafeSidebar.vue"
import ImgBox from "@/components/ImgBox.vue"
import OrderCafeSalesDays from "./OrderCafeSalesDays.vue"
import OrderCafeSalesMenu from "./OrderCafeSalesMenu.vue"
export default {
  name: "OrderCafeSalesList",
  components: { ImgBox, CafeSidebar, OrderCafeSalesDays, OrderCafeSalesMenu },

  data() {
    return {
      selectSales: [
        { id: 1, name: "일자 별 확인" },
        { id: 2, name: "메뉴 별 확인" },
      ],
      salesDays: true,
      salesMenu: false,
    }
  },
  methods: {
    showMenu(event) {
      console.log(event.target.value)
      let value = event.target.value
      if (value == 2) {
        this.salesDays = false
        this.salesMenu = true
      } else if (value == 1) {
        this.salesDays = true
        this.salesMenu = false
      }
    },
  },
}
</script>
<style lang="scss" scoped></style>

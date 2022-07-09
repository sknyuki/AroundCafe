<template>
  <div>
    <div class="col-sm-4 col-md-9 col-lg-9">
      <div class="member-list">
        <header class="member-list-header">
          <h2></h2>
        </header>
        <template
          v-if="
            !salesMenuList ||
            (Array.isArray(salesMenuList) && salesMenuList.length === 0)
          "
          ><span>매출액이 없습니다.</span></template
        >
        <table class="member-list-table" v-else>
          <thead>
            <tr>
              <th scope="col" align="center" style="width: 40px">No</th>
              <th scope="col" align="center" style="width: 120px">메뉴 이름</th>
              <th scope="col" align="center" style="width: 120px">판매 수량</th>
              <th scope="col" align="center" style="width: 100px">판매 금액</th>
            </tr>
          </thead>
          <tbody v-for="(item, index) in listData" :key="index">
            <tr v-if="item != null">
              <td align="center">{{ index + 1 }}</td>
              <td align="center">{{ item.itemName }}</td>
              <td align="center">{{ item.count }}</td>
              <td align="center">{{ item.sum | pricePoint }} 원</td>
            </tr>
          </tbody>
        </table>
      </div>
      <PaginationForm
        :pageSetting="pageDataSetting(total, limit, block, this.page)"
        @paging="pagingMethod"
      />
    </div>
  </div>
</template>

<script>
import axios from "axios"
import PaginationForm from "../PaginationForm.vue"

export default {
  components: { PaginationForm },
  name: "OrderCafeSalesMenu",
  data() {
    return {
      salesMenuList: "",
      listData: [],
      page: 1,
      limit: 10,
      block: 5,
      pageNo: "",
      total: "",
    }
  },
  created() {
    axios
      .get(
        `http://localhost:7777/payment/sales/menu/${this.$store.state.user.cafeNo}`
      )
      .then((res) => {
        this.salesMenuList = res.data
        this.pagingMethod(this.page)
      })
  },
  methods: {
    pagingMethod(page) {
      this.listData = this.salesMenuList.slice(
        (page - 1) * this.limit,
        page * this.limit
      )
      this.page = page
      let total = this.salesMenuList.length
      this.pageDataSetting(total, this.limit, this.block, page)
    },
    pageDataSetting(total, limit, block, page) {
      total = this.salesMenuList.length
      const totalPage = Math.ceil(total / limit)
      let currentPage = page
      const first =
        currentPage > 1 ? parseInt(currentPage, 10) - parseInt(1, 10) : null
      const end =
        totalPage !== currentPage
          ? parseInt(currentPage, 10) + parseInt(1, 10)
          : null

      let startIndex = (Math.ceil(currentPage / block) - 1) * block + 1
      let endIndex =
        startIndex + block > totalPage ? totalPage : startIndex + block - 1
      let list = []
      for (let index = startIndex; index <= endIndex; index++) {
        list.push(index)
      }
      return { first, end, list, currentPage }
    },
    findDetailSales(item) {
      let findDate = item.date
      this.$router.push({
        name: "OrderCafeSalesDetailPage",
        params: { findDate },
      })
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/admin/admin-member-list";
</style>

<template>
  <div>
    <div class="col-sm-4 col-md-9 col-lg-9">
      <div class="member-list">
        <header class="member-list-header">
          <h2></h2>
        </header>
        <template
          v-if="
            !salesList || (Array.isArray(salesList) && salesList.length === 0)
          "
          ><span>매출액이 없습니다.</span></template
        >
        <table class="member-list-table" v-else>
          <thead>
            <tr>
              <th scope="col" align="center" style="width: 30px">No</th>
              <th scope="col" align="center" style="width: 80px">매출 일자</th>
              <th scope="col" align="center" style="width: 120px">매출 수량</th>
              <th scope="col" align="center" style="width: 40px">매출액</th>
              <th scope="col" align="center" style="width: 70px">
                자세히 보기
              </th>
            </tr>
          </thead>
          <tbody v-for="(item, index) in listData" :key="index">
            <tr v-if="item != null">
              <td align="center">{{ index + 1 }}</td>
              <td align="center">{{ item.date }}</td>
              <td align="center">{{ item.total_quantity }}</td>
              <td align="center">{{ item.total_amount | pricePoint }} 원</td>
              <td align="center">
                <button @click="findDetailSales(item)">
                  <v-icon color="indigo">mdi-arrow-right-drop-circle</v-icon>
                </button>
              </td>
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
  name: "OrderCafeSalesDays",
  components: { PaginationForm },
  data() {
    return {
      salesList: [],
      listData: [],
      page: 1,
      limit: 10,
      block: 5,
      pageNo: "",
      total: "",
      cafeNo: this.$store.state.user.cafeNo,
    }
  },
  created() {
    axios
      .get(`http://localhost:7777/payment/sales/${this.cafeNo}`)
      .then((res) => {
        this.salesList = res.data
        this.pagingMethod(this.page)
      })
  },
  methods: {
    pagingMethod(page) {
      this.listData = this.salesList.slice(
        (page - 1) * this.limit,
        page * this.limit
      )
      this.page = page
      let total = this.salesList.length
      this.pageDataSetting(total, this.limit, this.block, page)
    },
    pageDataSetting(total, limit, block, page) {
      total = this.salesList.length
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

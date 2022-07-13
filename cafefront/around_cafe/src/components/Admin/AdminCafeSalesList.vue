<template>
  <div class="cafe-register">
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <ImgBox />
        </div>
        <div class="col-sm-4 col-md-3 col-lg-3">
          <AdminSidebar />
        </div>
        <div class="col-sm-4 col-md-9 col-lg-9">
          <div class="member-list">
            <header class="member-list-header">
              <h2>카페 매출</h2>
            </header>
            <template v-if="loading">
              <LoadingSpinner :loading="loading" />
            </template>
            <template v-else>
              <table class="member-list-table">
                <thead>
                  <tr>
                    <th scope="col" align="center" style="width: 40px">
                      판매순위
                    </th>
                    <th scope="col" align="center" style="width: 60px">
                      카페명
                    </th>
                    <th scope="col" align="center" style="width: 90px">
                      판매 수량
                    </th>

                    <th scope="col" align="center" style="width: 70px">
                      판매액
                    </th>
                    <th scope="col" align="center" style="width: 80px">
                      판매 상세 보기
                    </th>
                    <th scope="col" align="center" style="width: 40px">
                      메뉴 별 매출
                    </th>
                  </tr>
                </thead>
                <tbody v-for="(cafe, index) in listData" :key="index">
                  <tr>
                    <td align="center">{{ index + 1 }}</td>
                    <td align="center">{{ cafe.cafe_name }}</td>
                    <td align="center">{{ cafe.total_quantity }}</td>
                    <td align="center">
                      {{ cafe.total_amount | pricePoint }}원
                    </td>
                    <td align="center">
                      <button @click="findDetailSales(cafe)">
                        <v-icon color="indigo"
                          >mdi-arrow-right-drop-circle</v-icon
                        >
                      </button>
                    </td>
                    <td align="center">
                      <button @click="findMenuSales(cafe)">
                        <v-icon color="indigo"
                          >mdi-arrow-right-drop-circle</v-icon
                        >
                      </button>
                    </td>
                  </tr>
                </tbody>
              </table>
              <PaginationForm
                :pageSetting="pageDataSetting(total, limit, block, this.page)"
                @paging="pagingMethod"
              />
            </template>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios"
import AdminSidebar from "@/components/Admin/AdminSidebar.vue"
import ImgBox from "@/components/ImgBox.vue"
import PaginationForm from "../PaginationForm.vue"
import LoadingSpinner from "../LoadingSpinner.vue"

export default {
  name: "AdminCafeSalesList",
  components: { ImgBox, AdminSidebar, PaginationForm, LoadingSpinner },

  data() {
    return {
      listData: [],
      salesList: [],
      page: 1,
      limit: 10,
      block: 5,
      pageNo: "",
      total: "",
      loading: false,
    }
  },
  created() {
    this.loading = true
    axios.get(`http://localhost:7777/payment/sales/cafe`).then((res) => {
      this.salesList = res.data
      this.pagingMethod(this.page)
      setTimeout(() => {
        this.loading = false
      }, 500)
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
      let cafeNo = item.cafe_no
      this.$router.push({
        name: "AdminCafeSalesDetailPage",
        params: { cafeNo },
      })
    },
    findMenuSales(item) {
      let cafeNo = item.cafe_no
      this.$router.push({
        name: "AdminCafeSalesMenuPage",
        params: { cafeNo },
      })
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/admin/admin-member-list";
</style>

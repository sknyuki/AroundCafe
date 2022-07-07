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
        <div class="col-sm-4 col-md-9 col-lg-9">
          <div class="member-list">
            <header class="member-list-header">
              <h2>매출 상세 현황</h2>
            </header>

            <table class="member-list-table">
              <thead>
                <tr>
                  <th scope="col" align="center" style="width: 100px">
                    주문 번호
                  </th>
                  <th scope="col" align="center" style="width: 160px">
                    회원 정보
                  </th>
                  <th scope="col" align="center" style="width: 160px">
                    메뉴 이름
                  </th>
                  <th scope="col" align="center" style="width: 80px">수량</th>
                  <th scope="col" align="center" style="width: 100px">가격</th>
                  <th scope="col" align="center" style="width: 160px">
                    구매 일자
                  </th>
                </tr>
              </thead>
              <tbody v-for="(item, index) in listData" :key="index">
                <tr v-if="item != null">
                  <td align="center">{{ item.paymentNo }}</td>
                  <td align="center">{{ item.memNick }}</td>
                  <td align="center">{{ item.itemName }}</td>
                  <td align="center">{{ item.quantity }}</td>
                  <td align="center">{{ item.amount | pricePoint }}원</td>
                  <td align="center">
                    {{ item.paymentDate | yyyyMMdd }}
                    {{ item.paymentDate | HHmm }}
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <PaginationForm
            :pageSetting="pageDataSetting(total, limit, block, this.page)"
            @paging="pagingMethod"
          />
          <div>
            <v-btn>
              <router-link :to="'/cafe/sales'">목록</router-link>
            </v-btn>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios"
import CafeSidebar from "@/components/Cafe/CafeSidebar.vue"
import ImgBox from "@/components/ImgBox.vue"
import PaginationForm from "../PaginationForm.vue"

export default {
  components: { ImgBox, CafeSidebar, PaginationForm },
  name: "OrderCafeSalesDetailList",
  data() {
    return {
      salesDetailList: [],
      listData: [],
      page: 1,
      limit: 10,
      block: 5,
      pageNo: "",
      total: "",
      cafeNo: this.$store.state.user.cafeNo,
    }
  },
  props: {
    findDate: {
      type: String,
      required: true,
    },
  },
  created() {
    axios
      .get(
        `http://localhost:7777/payment/sales/${this.$store.state.user.cafeNo}/${this.findDate}`
      )
      .then((res) => {
        this.salesDetailList = res.data
        this.pagingMethod(this.page)
      })
  },
  methods: {
    pagingMethod(page) {
      this.listData = this.salesDetailList.slice(
        (page - 1) * this.limit,
        page * this.limit
      )
      this.page = page
      let total = this.salesDetailList.length
      this.pageDataSetting(total, this.limit, this.block, page)
    },
    pageDataSetting(total, limit, block, page) {
      total = this.salesDetailList.length
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

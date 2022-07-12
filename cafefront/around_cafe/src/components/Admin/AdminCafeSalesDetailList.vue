<template>
  <div>
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
              <h2>매출 상세 보기</h2>
            </header>
            <template v-if="loading">
              <LoadingSpinner :loading="loading" />
            </template>
            <template v-else>
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
                    <th scope="col" align="center" style="width: 100px">
                      가격
                    </th>
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
              <PaginationForm
                :pageSetting="pageDataSetting(total, limit, block, this.page)"
                @paging="pagingMethod"
              />
              <div>
                <v-btn>
                  <router-link :to="'/admin/cafe/sales'">목록</router-link>
                </v-btn>
              </div>
            </template>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios"
import ImgBox from "@/components/ImgBox.vue"
import PaginationForm from "../PaginationForm.vue"
import AdminSidebar from "./AdminSidebar.vue"
import LoadingSpinner from "../LoadingSpinner.vue"

export default {
  name: "AdminCafeSalesDetailList",
  components: { ImgBox, PaginationForm, AdminSidebar, LoadingSpinner },
  data() {
    return {
      salesDetailList: [],
      listData: [],
      page: 1,
      limit: 10,
      block: 5,
      pageNo: "",
      total: "",
      loading: false,
    }
  },
  props: {
    cafeNo: {
      type: String,
      required: true,
    },
  },
  async created() {
    let cafeNo = this.cafeNo
    this.loading = true
    await axios
      .get(`http://localhost:7777/payment/sales/cafe/detail/${cafeNo}`)
      .then((res) => {
        this.salesDetailList = res.data
        this.pagingMethod(this.page)
        setTimeout(() => {
          this.loading = false
        }, 800)
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
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/admin/admin-member-list";
</style>

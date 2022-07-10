<template>
  <div class="cafe-register">
    <div class="container">
      <br />

      <div>
        <div class="space-between" title="flex-direction:column">
          <h5>{{ this.memNick }} 님의 이용 현황입니다.</h5>
          <p>보유포인트:{{ userInfo.memPoint }}</p>
        </div>
        <table class="member-list-table">
          <thead>
            <tr>
              <th scope="col" align="center" style="width: 30px">No</th>
              <th scope="col" align="center" style="width: 50px">결제 날짜</th>
              <th scope="col" align="center" style="width: 50px">카페</th>
              <th scope="col" align="center" style="width: 80px">주문메뉴</th>
              <th scope="col" align="center" style="width: 70px">지불 방법</th>
              <th scope="col" align="center" style="width: 50px">이용 총액</th>
            </tr>
          </thead>
          <tbody v-for="(usage, index) in listData" :key="index">
            <tr>
              <td align="center">{{ index + 1 }}</td>
              <td align="center">{{ usage.paymentDate | yyyyMMdd }}</td>
              <td align="center">{{ usage.cafeName }}</td>
              <td align="center">{{ usage.itemInitName }}</td>
              <td align="center">{{ usage.paymentMethod }}</td>
              <td align="center">{{ usage.totalAmount | pricePoint }}원</td>
            </tr>
          </tbody>
        </table>
        (총 이용 금액: {{ this.totalPurchaseAmount | pricePoint }}원)
      </div>
    </div>
    <PaginationForm
      :pageSetting="pageDataSetting(total, limit, block, this.page)"
      @paging="pagingMethod"
    />
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex"
import PaginationForm from "../PaginationForm.vue"
export default {
  name: "AdminUsageMemberForm",
  components: { PaginationForm },

  data() {
    return {
      listData: [],
      page: 1,
      limit: 10,
      block: 5,
      pageNo: "",
      total: "",
      totalPurchaseAmount: 0,
    }
  },
  props: { usageGraphList: { type: Array }, memNick: { type: String } },
  computed: {
    ...mapState(["userInfo"]),
  },
  mounted() {
    this.fetchUserInfo(this.memNo)
  },

  beforeUpdate() {
    // await this.fetchUserSpendList(this.memNo)
    this.pagingMethod(this.page)
    this.totalPurchaseAmount = 0
    for (let i = 0; i < this.usageGraphList.length; i++) {
      this.totalPurchaseAmount += this.usageGraphList[i].totalAmount
    }
  },

  methods: {
    ...mapActions(["fetchUserInfo"]),
    pagingMethod(page) {
      this.listData = this.usageGraphList.slice(
        (page - 1) * this.limit,
        page * this.limit
      )
      this.page = page
      let total = this.usageGraphList.length
      this.pageDataSetting(total, this.limit, this.block, page)
    },
    pageDataSetting(total, limit, block, page) {
      total = this.usageGraphList.length
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
  filters: {
    yyyyMMdd(value) {
      if (value == "") return ""

      var js_date = new Date(value)

      var year = js_date.getFullYear()
      var month = js_date.getMonth() + 1
      var day = js_date.getDate()

      if (month < 10) {
        month = "0" + month
      }

      if (day < 10) {
        day = "0" + day
      }

      return year + "-" + month + "-" + day
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/admin/admin-member-list";

.space-between {
  @include flexbox(between);
}
</style>

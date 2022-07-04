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
              <h2>카페관리</h2>
            </header>
            <table class="member-list-table">
              <thead>
                <tr>
                  <th scope="col" align="center" style="width: 30px">No</th>
                  <th scope="col" align="center" style="width: 80px">카페명</th>
                  <th scope="col" align="center" style="width: 120px">주소</th>
                  <th scope="col" align="center" style="width: 40px">
                    영업시간
                  </th>
                  <th scope="col" align="center" style="width: 70px">연락처</th>
                  <th scope="col" align="center" style="width: 50px">
                    사업자 번호
                  </th>
                  <th scope="col" align="center" style="width: 40px">
                    평균 별점
                  </th>
                </tr>
              </thead>
              <tbody v-for="(cafe, index) in listData" :key="index">
                <tr>
                  <td align="center">{{ cafe.cafeNo }}</td>
                  <td align="center">{{ cafe.cafe_name }}</td>
                  <td align="center">{{ cafe.address }}</td>
                  <td align="center">{{ cafe.cafe_time }}</td>
                  <td align="center">{{ cafe.cafe_call }}</td>
                  <td align="center">{{ cafe.cafe_bis_no }}</td>
                  <td align="center">{{ cafe.starAver }}</td>
                  <!-- <td align="center">{{ cafe.name }}</td>
                  <td align="center">
                    <div v-if="!member.isblack">
                      <v-btn class="btn-red" @click="ToBlackList(index)">
                        <v-icon>mdi-account-remove </v-icon>BlackList
                      </v-btn>
                    </div>
                    <div v-else>
                      <v-btn class="btn-outlined" @click="TakeBack(index)">
                        <v-icon>mdi-account-convert </v-icon> take back
                      </v-btn>
                    </div>
                  </td> -->
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
    </div>
  </div>
</template>
<script>
import { mapActions, mapState } from "vuex"
import AdminSidebar from "@/components/Admin/AdminSidebar.vue"
import ImgBox from "@/components/ImgBox.vue"
import PaginationForm from "../PaginationForm.vue"

export default {
  name: "AdminMemberList",
  components: { ImgBox, AdminSidebar, PaginationForm },

  data() {
    return {
      listData: [],
      page: 1,
      limit: 10,
      block: 5,
      pageNo: "",
      total: "",
    }
  },
  computed: {
    ...mapState(["mainlist"]),
  },
  async mounted() {
    await this.fetchMainList()
    this.pagingMethod(this.page)
  },
  methods: {
    ...mapActions(["fetchMainList"]),
    pagingMethod(page) {
      this.listData = this.mainlist.slice(
        (page - 1) * this.limit,
        page * this.limit
      )
      this.page = page
      let total = this.mainlist.length
      this.pageDataSetting(total, this.limit, this.block, page)
    },
    pageDataSetting(total, limit, block, page) {
      total = this.mainlist.length
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

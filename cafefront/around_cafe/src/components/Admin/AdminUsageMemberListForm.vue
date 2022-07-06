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
              <h2>회원 이용 현황</h2>
            </header>

            <table class="member-list-table">
              <thead>
                <tr>
                  <th scope="col" align="center" style="width: 30px">No</th>
                  <th scope="col" align="center" style="width: 80px">아이디</th>
                  <th scope="col" align="center" style="width: 120px">
                    닉네임
                  </th>
                  <th scope="col" align="center" style="width: 40px">
                    생년월일
                  </th>
                  <th scope="col" align="center" style="width: 70px">연락처</th>
                  <th scope="col" align="center" style="width: 40px">권한</th>
                </tr>
              </thead>
              <tbody v-for="(member, index) in listData" :key="index">
                <tr v-if="member.name != ADMIN">
                  <td align="center">{{ member.memNo }}</td>
                  <td align="center">{{ member.memId }}</td>
                  <td align="center">
                    <router-link
                      :to="{
                        name: 'AdminUsageMemberPage',
                        params: {
                          memNo: member.memNo,
                          memNick: member.memNick,
                        },
                      }"
                    >
                      {{ member.memNick }}
                    </router-link>
                  </td>
                  <td align="center">{{ member.memBirth }}</td>
                  <td align="center">{{ member.phoneNum }}</td>
                  <td align="center">{{ member.name }}</td>
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
  name: "AdminUsageMemberListForm",
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
    ...mapState(["adminlist"]),
  },
  async mounted() {
    await this.fetchAdminMemberList()
    this.pagingMethod(this.page)
  },
  methods: {
    ...mapActions(["fetchAdminMemberList"]),
    pagingMethod(page) {
      this.listData = this.adminlist.slice(
        (page - 1) * this.limit,
        page * this.limit
      )
      this.page = page
      let total = this.adminlist.length
      this.pageDataSetting(total, this.limit, this.block, page)
    },
    pageDataSetting(total, limit, block, page) {
      total = this.adminlist.length
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

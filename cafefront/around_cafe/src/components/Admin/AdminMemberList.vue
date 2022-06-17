<template>
  <div class="cafe-register">
    <img-box class="mb-10" />
    <div class="container">
      <div class="row">
        <div class="col-sm-4 col-md-3 col-lg-3">
          <AdminSidebar />
        </div>
        <div class="col-sm-4 col-md-9 col-lg-9">
          <div class="member-list">
            <header class="member-list-header">
              <h2>회원관리</h2>
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
                  <th scope="col" align="center" style="width: 40px">
                    가입일자
                  </th>
                  <th scope="col" align="center" style="width: 40px">수정</th>
                  <th scope="col" align="center" style="width: 40px">삭제</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="member in memberList" :key="member.memberNo">
                  <td align="center">{{ member.memberNo }}</td>
                  <td align="center">{{ member.memId }}</td>
                  <td align="center">{{ member.memNick }}</td>
                  <td align="center">{{ member.memBirth }}</td>
                  <td align="center">{{ member.memPhoneNum }}</td>
                  <td align="center">{{ member.regDate }}</td>
                  <td align="center">
                    <v-btn
                      class="btn-outlined"
                      small
                      aria-label="회원 수정하기"
                    >
                      <v-icon>mdi-pencil </v-icon>수정
                    </v-btn>
                  </td>
                  <td align="center">
                    <v-btn class="btn-red" small aria-label="회원 삭제하기">
                      <v-icon>mdi-delete </v-icon>삭제
                    </v-btn>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import AdminSidebar from "@/components/Admin/AdminSidebar.vue"
import ImgBox from "@/components/ImgBox.vue"

export default {
  name: "AdminMemberList",
  components: { ImgBox, AdminSidebar },

  data() {
    return {
      memberList: [
        {
          memberNo: 1,
          memId: "bean32226@naver.com",
          memNick: "일이삼사오육칠팔구십일이삼사오",
          memBirth: "94-03-26",
          memPhoneNum: "010-0000-0000",
          regDate: "22-12-02",
        },
        {
          memberNo: 2,
          memId: "bean322226@naver.com",
          memNick: "일이삼사오육칠팔구십일이삼사오",
          memBirth: "94-03-26",
          memPhoneNum: "010-0000-0000",
          regDate: "22-12-02",
        },
        {
          memberNo: 3,
          memId: "bean326@naver.com",
          memNick: "일이삼사오육칠팔구십일이삼사오",
          memBirth: "94-03-26",
          memPhoneNum: "010-0000-0000",
          regDate: "22-12-02",
        },
        {
          memberNo: 4,
          memId: "bean3263@naver.com",
          memNick: "일이삼사오육칠팔구십일이삼사오",
          memBirth: "94-03-26",
          memPhoneNum: "010-0000-0000",
          regDate: "22-12-02",
        },
      ],
    }
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/admin/admin-member-list";
</style>

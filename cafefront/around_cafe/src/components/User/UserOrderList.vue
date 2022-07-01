<template>
  <div class="cafe-register">
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <ImgBox />
        </div>
        <div class="col-sm-4 col-md-3 col-lg-3">
          <UserSidebar />
        </div>
        <div class="col-sm-8 col-md-9 col-lg-9">
          <header>
            <h1>주문 현황</h1>
          </header>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import UserSidebar from "@/components/User/UserSidebar.vue"
import ImgBox from "@/components/ImgBox.vue"

export default {
  name: "MyOrderStausForm",
  components: { ImgBox, UserSidebar },

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

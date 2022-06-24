<template>
  <div class="mysidebar">
    <div class="mysidebar-content">
      <header>
        <h1>{{ infoOne }}</h1>
      </header>
      <ul>
        <li class="mysidebar-list" v-for="item in sidebarOne" :key="item.id">
          <router-link class="mysidebar-link" :to="item.link">
            <div class="mysidebar-test">
              <v-icon>{{ item.icon }}</v-icon>
              <p>{{ item.title }}</p>
            </div>
          </router-link>
        </li>
      </ul>
    </div>
    <div class="mysidebar-content">
      <header>
        <h1>{{ infoTwo }}</h1>
      </header>
      <ul>
        <li class="mysidebar-list" v-for="item in sidebarTwo" :key="item.id">
          <router-link class="mysidebar-link" :to="item.link">
            <div class="mysidebar-test">
              <v-icon>{{ item.icon }}</v-icon>
              <p>{{ item.title }}</p>
            </div>
          </router-link>
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
import { mapState, mapActions } from "vuex"

export default {
  name: "CafeSidebar",
  data() {
    return {
      sidebarOne: [
        {
          id: 1,
          icon: "mdi-home-outline",
          title: "카페 관리",
          link: { name: "CafeRegisterPage", params: { cafeNo: this.cafeNo } },
        },
        {
          id: 2,
          icon: "mdi-coffee-outline",
          title: "메뉴 관리",
          link: {
            name: "CafeRegisterMenuPage",
            params: { cafeNo: this.cafeNo },
          },
        },
        {
          id: 3,
          icon: "mdi-format-list-bulleted",
          title: "주문 리스트",
          link: "/",
        },
        {
          id: 4,
          icon: "mdi-cash",
          title: "매출",
          link: "/",
        },
        {
          id: 5,
          icon: "mdi-message-text-outline",
          title: "고객 리뷰",
          link: { name: "CafeReviewListPage", params: { cafeNo: this.cafeNo } },
        },
        {
          id: 6,
          icon: "mdi-wechat",
          title: "고객과 대화",
          link: "/",
        },
      ],
      sidebarTwo: [
        {
          id: 1,
          icon: "mdi-account-outline",
          title: "내 정보수정",
          link: { name: "CafeUserModifyPage", params: { cafeNo: this.cafeNo } },
        },
        {
          id: 2,
          icon: "mdi-lock",
          title: "비밀번호 변경",
          link: { name: "CafePasswordPage", params: { cafeNo: this.cafeNo } },
        },
        {
          id: 3,
          icon: "mdi-email-outline",
          title: "문의사항",
          link: "/",
        },
      ],
      infoOne: "카페 정보",
      infoTwo: "내 정보",
    }
  },
  computed: {
    ...mapState(["user", "cafeNo"]),
  },
  mounted() {
    this.fetchUser(), this.fetchcafeNo(this.user.memNo)
  },
  methods: {
    ...mapActions(["fetchUser"]),
    ...mapActions(["fetchcafeNo"]),
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/my-sidebar";
</style>

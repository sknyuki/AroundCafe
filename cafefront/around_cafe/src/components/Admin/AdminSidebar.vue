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
    <div class="mysidebar-content">
      <header>
        <h1>{{ infoTh }}</h1>
      </header>
      <ul>
        <li class="mysidebar-list" v-for="item in sidebarTh" :key="item.id">
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
export default {
  name: "AdminSidebar",
  data() {
    return {
      sidebarOne: [
        {
          id: 1,
          icon: "mdi-account-outline",
          title: "회원 리스트",
          link: "/admin/member",
        },
        {
          id: 2,
          icon: "mdi-chart-areaspline",
          title: "회원 이용 현황",
          link: "/admin/AdminUsageMemberListPage",
        },
        {
          id: 3,
          icon: "mdi-microphone",
          title: "공지사항",
          link: "/admin/noticelist",
        },
      ],
      sidebarTwo: [
        {
          id: 1,
          icon: "mdi-home-outline",
          title: "카페 리스트",
          link: "/admin/cafe",
        },
        {
          id: 2,
          icon: "mdi-chart-bar",
          title: "카페 매출",
          link: "/",
        },
      ],
      sidebarTh: [
        {
          id: 1,
          icon: "mdi-account-outline",
          title: "내 정보수정",
          link: "/admin/adminUserModifyPage",
        },
        {
          id: 2,
          icon: "mdi-lock",
          title: "비밀번호 변경",
          link: "/admin/pw",
        },
        {
          id: 3,
          icon: "mdi-email-outline",
          title: "문의사항",
          link: "/",
        },
      ],
      infoOne: "회원 관리",
      infoTwo: "카페 관리",
      infoTh: "내 정보",
    }
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/my-sidebar";
</style>

<template>
  <div class="mysidebar">
    <div class="mysidebar-content">
      <header>
        <h1>{{ infoOne }}</h1>
      </header>
      <ul>
        <li class="mysidebar-list" v-for="item in sidebarOne" :key="item.id">
          <template v-if="item.id == 6">
            <router-link class="mysidebar-link" :to="item.link" target="_blank">
              <div class="mysidebar-test">
                <v-icon aria-hidden="true">{{ item.icon }}</v-icon>
                <p>{{ item.title }}</p>
              </div>
            </router-link>
          </template>
          <template v-else>
            <router-link class="mysidebar-link" :to="item.link">
              <div class="mysidebar-test">
                <v-icon aria-hidden="true">{{ item.icon }}</v-icon>
                <p>{{ item.title }}</p>
              </div>
            </router-link>
          </template>
        </li>
      </ul>
    </div>
    <div class="mysidebar-content">
      <header>
        <h1>{{ infoTwo }}</h1>
      </header>
      <ul>
        <li class="mysidebar-list" v-for="item in sidebarTwo" :key="item.id">
          <template v-if="item.id == 3">
            <router-link class="mysidebar-link" :to="item.link" target="_blank">
              <div class="mysidebar-test">
                <v-icon aria-hidden="true">{{ item.icon }}</v-icon>
                <p>{{ item.title }}</p>
              </div>
            </router-link>
          </template>
          <template v-else>
            <router-link class="mysidebar-link" :to="item.link" target="_blank">
              <div class="mysidebar-test">
                <v-icon aria-hidden="true">{{ item.icon }}</v-icon>
                <p>{{ item.title }}</p>
              </div>
            </router-link>
          </template>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: "CafeSidebar",
  data() {
    return {
      sidebarOne: [
        {
          id: 1,
          icon: "mdi-home-outline",
          title: "카페 관리",
          link: "/cafe/register",
        },
        {
          id: 2,
          icon: "mdi-coffee-outline",
          title: "메뉴 관리",
          link: "/cafe/menu",
        },
        {
          id: 3,
          icon: "mdi-format-list-bulleted",
          title: "주문 리스트",
          link: "/cafe/order/list",
        },
        {
          id: 4,
          icon: "mdi-cash",
          title: "매출",
          link: "/cafe/sales",
        },
        {
          id: 5,
          icon: "mdi-message-text-outline",
          title: "고객 리뷰",
          link: "/cafe/review",
        },
        {
          id: 6,
          icon: "mdi-wechat",
          title: "고객과 대화",
          link: "/chat",
        },
      ],
      sidebarTwo: [
        {
          id: 1,
          icon: "mdi-account-outline",
          title: "내 정보수정",
          link: "/cafe/modify",
        },
        {
          id: 2,
          icon: "mdi-lock",
          title: "비밀번호 변경",
          link: "/cafe/pw",
        },
        {
          id: 3,
          icon: "mdi-email-outline",
          title: "문의사항",
          link: "/admin/chat",
        },
      ],
      infoOne: "카페 정보",
      infoTwo: "내 정보",
    }
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/my-sidebar";
</style>

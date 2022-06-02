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
export default {
  name: "MySidebar",
  data() {
    return {
      sidebarOne: [
        {
          id: 1,
          icon: "mdi-coffee-outline",
          title: "주문현황",
          link: "/",
        },
        {
          id: 2,
          icon: "mdi-format-list-bulleted",
          title: "주문내역",
          link: "/",
        },
        {
          id: 3,
          icon: "mdi-message-text-outline",
          title: "내리뷰",
          link: "/",
        },
        {
          id: 4,
          icon: "mdi-heart-outline",
          title: "좋아요",
          link: "/",
        },
      ],
      sidebarTwo: [
        {
          id: 1,
          icon: "mdi-account-outline",
          title: "내 정보수정",
          link: "/",
        },
        {
          id: 2,
          icon: "mdi-email-outline",
          title: "문의사항",
          link: "/",
        },
      ],
      infoOne: "나의 활동",
      infoTwo: "내 정보",
    }
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/my-sidebar";
</style>

<template>
  <div class="my-menu sm-hidden">
    <div class="my-menu-content">
      <ul class="my-menu-list">
        <!-- 로그인 안되어있을 때  -->
        <template>
          <li class="my-menu-item" v-for="item in menuModal" :key="item.id">
            <router-link :to="item.link">{{ item.title }}</router-link>
          </li>
        </template>
        <li class="my-menu-item" v-if="checkUser != null">
          <button @click="onLogout" type="submit" aria-label="로그아웃 ">
            로그아웃
          </button>
        </li>

        <!-- User 로그인일 시  -->
        <!-- <template>
          <li
            class="my-menu-item"
            v-for="item in myMenuUserLogin"
            :key="item.id"
          >
            <router-link :to="item.link">{{ item.title }}</router-link>
          </li>
        </template> -->

        <!-- Cafe 로그인일 시  -->
        <!-- 
        <template>
          <li
            class="my-menu-item"
            v-for="item in myMenuCafeLogin"
            :key="item.id"
          >
            <router-link :to="item.link">{{ item.title }}</router-link>
          </li>
        </template> -->

        <!-- Admin 로그인일 시  -->
        <!-- <template>
          <li
            class="my-menu-item"
            v-for="item in myMenuAdminLogin"
            :key="item.id"
          >
            <router-link :to="item.link">{{ item.title }}</router-link>
          </li>
        </template> -->
      </ul>
    </div>
  </div>
</template>
<script>
export default {
  name: "MyMenuModal",

  methods: {
    onLogout() {},
  },
  props: {
    menuModal: {
      type: Array,
      required: true,
    },
  },

  data() {
    return {
      checkUser: null,
      myMenuUserLogin: [
        {
          id: 1,
          title: "내 정보",
          link: "/sign",
        },
        {
          id: 2,
          title: "주문현황",
          link: "/login",
        },
        {
          id: 3,
          title: "주문내역",
          link: "/login",
        },
        {
          id: 4,
          title: "내리뷰",
          link: "/login",
        },
        {
          id: 5,
          title: "좋아요",
          link: "/login",
        },
      ],
      myMenuCafeLogin: [
        {
          id: 1,
          title: "내 정보",
          link: "/cafe/modify",
        },
        {
          id: 2,
          title: "카페 관리",
          link: "/cafe/register",
        },
        {
          id: 3,
          title: "메뉴 관리",
          link: "/cafe/menu",
        },
        {
          id: 4,
          title: "주문 리스트",
          link: "",
        },
        {
          id: 5,
          title: "매출",
          link: "",
        },
        {
          id: 6,
          title: "고객 리뷰",
          link: "/cafe/review",
        },
      ],
      myMenuAdminLogin: [
        {
          id: 1,
          title: "내 정보",
          link: "",
        },
        {
          id: 2,
          title: "회원 리스트",
          link: "",
        },
        {
          id: 3,
          title: "회원 이용현황",
          link: "",
        },
        {
          id: 4,
          title: "공지사항",
          link: "",
        },
        {
          id: 5,
          title: "카페 리스트",
          link: "",
        },
        {
          id: 6,
          title: "카페 매출",
          link: "",
        },
      ],
    }
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/my-menu-modal";
</style>

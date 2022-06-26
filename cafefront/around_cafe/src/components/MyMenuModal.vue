<template>
  <div class="my-menu sm-hidden">
    <div class="my-menu-content">
      <ul class="my-menu-list">
        <!-- 로그인 안되어있을 때  -->
        <template v-if="user.memNo == null">
          <li class="my-menu-item" v-for="item in menuModal" :key="item.id">
            <router-link :to="item.link">{{ item.title }}</router-link>
          </li>
        </template>

        <!-- User 로그인일 시  -->
        <template v-if="user.role == 'USER'">
          <li
            class="my-menu-item"
            v-for="item in myMenuUserLogin"
            :key="item.id"
          >
            <router-link :to="item.link">{{ item.title }}</router-link>
          </li>
        </template>

        <!-- Cafe 로그인일 시  -->

        <template v-if="user.role == 'CAFE'">
          <li
            class="my-menu-item"
            v-for="item in myMenuCafeLogin"
            :key="item.id"
          >
            <router-link :to="item.link">{{ item.title }}</router-link>
          </li>
        </template>

        <!-- Admin 로그인일 시  -->
        <template v-if="user.role == 'ADMIN'">
          <li
            class="my-menu-item"
            v-for="item in myMenuAdminLogin"
            :key="item.id"
          >
            <router-link :to="item.link">{{ item.title }}</router-link>
          </li>
        </template>
        <li class="my-menu-item" v-if="user.memNo != null">
          <button @click="onLogout" type="submit" aria-label="로그아웃 ">
            로그아웃
          </button>
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
import axios from "axios"
import userService from "@/services/userService"
import tokenService from "@/services/tokenService"

export default {
  name: "MyMenuModal",

  methods: {
    onLogout() {
      const url = "http://localhost:7777/auth/logout"
      const refreshToken = tokenService.getRefreshToken()
      const config = {
        headers: {
          refresh_token: `${refreshToken}`,
        },
      }
      axios
        .delete(url, config)
        .then(() => {
          userService.deleteUserInfo()
          tokenService.deleteTokens()
          alert("로그아웃되었습니다.")
          this.$router.push({ name: "MainPage" })
          this.$router.go()
        })

        .catch((err) => {
          alert(err)
        })
    },
  },
  props: {
    menuModal: {
      type: Array,
      required: true,
    },
    user: {
      type: Object,
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
          link: "/user/modify",
        },
        {
          id: 2,
          title: "주문내역",
          link: "/user/history",
        },
        {
          id: 3,
          title: "내리뷰",
          link: "/login",
        },
        {
          id: 4,
          title: "좋아요",
          link: "/user/like",
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

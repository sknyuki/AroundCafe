<template>
  <aside class="sidebar sm-only">
    <header>
      <h1 class="logo">
        <router-link :to="{ name: 'MainPage' }">
          <span>Around Cafe</span>
          <img src="" alt="" />
        </router-link>
      </h1>
    </header>
    <div class="sidebar-user">
      <a href="/">
        <div class="avatar-32">
          <img
            v-if="userInfo.memImg == null"
            src="@/assets/images/avatar.webp"
            alt="유저 기본이미지"
          />
          <img
            v-if="userInfo.memImg != null && userInfo.socialType == 'LOCAL'"
            v-bind:src="require(`@/assets/images/memberImg/${userInfo.memImg}`)"
          />
          <img
            v-if="userInfo.memImg != null && userInfo.socialType != 'LOCAL'"
            :src="userInfo.memImg"
          />
        </div>
        <strong v-if="userInfo.memImg == null" class="username"
          >로그인을 해주세요!</strong
        >
        <strong v-else class="username">{{ userInfo.memNick }}</strong>
      </a>
    </div>
    <nav class="sidebar-nav">
      <h2 class="visually-hidden">메뉴</h2>
      <div class="sidebar-user-menu">
        <ul class="user-menu-list">
          <li v-for="item in sidebarList" :key="item.id" class="user-menu-item">
            <router-link :to="item.link" @click.native="closeSidebar">
              {{ item.title }}
            </router-link>
          </li>
          <template v-if="user.role == null">
            <li class="user-menu-item">
              <button type="button" onclick="location.href='/login'">
                로그인
              </button>
            </li>
            <li class="user-menu-item">
              <button type="button" onclick="location.href='/sign'">
                회원가입
              </button>
            </li>
          </template>
          <template>
            <li class="user-menu-item" v-if="user.memNo != null">
              <button @click="onLogout">로그아웃</button>
            </li>
          </template>
        </ul>
      </div>
    </nav>
  </aside>
</template>

<script>
import axios from "axios"
import userService from "@/services/userService"
import tokenService from "@/services/tokenService"
import { mapState, mapActions } from "vuex"

export default {
  name: "TheSidebar",
  props: {
    isActive: {
      type: Boolean,
      require: true,
    },
    userInfo: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      sidebarList: [
        {
          id: 1,
          title: "카페",
          link: "/main",
        },
        {
          id: 2,
          title: "커피",
          link: "/main",
        },
        {
          id: 3,
          title: "베스트",
          link: "/main",
        },
        {
          id: 4,
          title: "뉴스",
          link: "/main",
        },
        {
          id: 5,
          title: "지도",
          link: "/map/main",
        },
        {
          id: 6,
          title: "고객센터",
          link: "/service",
        },
      ],
    }
  },
  computed: {
    ...mapState(["user"]),
  },
  methods: {
    ...mapActions(["fetchUser"]),
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
          window.location.href = "/"
        })

        .catch((err) => {
          alert(err)
        })
    },
    closeSidebar() {
      this.$emit("sumbit", { isActive: this.isActive })
    },
  },
}
</script>

<style lang="scss" scoped>
@import "~@/assets/scss/layouts/Sidebar";
</style>

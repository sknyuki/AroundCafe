<template>
  <header class="global-header" app="app">
    <div class="gnb">
      <div class="container">
        <div class="row">
          <div class="col-sm-12">
            <div class="gnb-wrapper">
              <div class="gnb-left">
                <h1 class="logo">
                  <router-link :to="{ name: 'MainPage' }"
                    ><img src="@/assets/images/Logo.png" alt="로고" />
                    <span class="sm-hidden">Around Cafe</span>
                  </router-link>
                </h1>
                <v-btn
                  class="gnb-icon-button is-menu sm-only"
                  icon
                  @click="drawer = true"
                  type="button"
                  aria-label="메뉴 열기 버튼"
                >
                  <i class="icMenu"></i>
                </v-btn>
              </div>

              <div class="gnb-right">
                <div class="gnb-search lg-only">
                  <div class="input-group">
                    <i class="icSearch" aria-hidden="true"></i>
                    <input
                      class="form-input input-40"
                      type="text"
                      placeholder="카페 검색"
                    />
                  </div>
                </div>

                <div class="button-group">
                  <v-btn
                    icon
                    class="gnb-icon-button is-search lg-hidden"
                    type="button"
                    aria-label="검색창 열기 버튼"
                  >
                    <i class="icSearch" aria-hidden="true"></i>
                  </v-btn>

                  <v-btn
                    icon
                    class="gnb-icon-button"
                    aria-label="좋아요 페이지로 이동"
                  >
                    <a>
                      <i class="icHeart"></i>
                      <strong class="badge">1</strong>
                    </a>
                  </v-btn>

                  <!-- 로그인을 하지 않은 경우 -->
                  <div class="gnb-login sm-hidden">
                    <div class="gnb-login-content">
                      <button class="gnb-login-btn" @click="toggleMyMenu">
                        <div>
                          <i class="icMenu"></i>
                        </div>

                        <div class="avatar-32">
                          <template v-if="userInfo.memImg == null">
                            <img src="@/assets/images/avatar.webp" alt="" />
                          </template>
                          <template
                            v-if="findKakao > 5 && userInfo.memImg != null"
                          >
                            <img :src="userInfo.memImg" alt="" />
                          </template>
                          <template
                            v-if="findKakao < 5 && userInfo.memImg != null"
                          >
                            <img
                              v-bind:src="
                                require(`@/assets/images/memberImg/${userInfo.memImg}`)
                              "
                              alt=""
                            />
                          </template>
                        </div>
                      </button>
                    </div>
                    <MyMenuModal
                      :menuModal="menuModal"
                      :class="{ active: isActive }"
                      :user="user"
                    />
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <v-navigation-drawer width="240" v-model="drawer" fixed temporary>
      <TheNavBarList />
    </v-navigation-drawer>
    <TheNavBar />
  </header>
</template>
<script>
import TheNavBar from "@/components/Layouts/TheNavBar.vue"
import TheNavBarList from "@/components/Layouts/TheNavBarList.vue"
import MyMenuModal from "@/components/MyMenuModal.vue"
import { mapState, mapActions } from "vuex"

export default {
  components: { TheNavBarList, TheNavBar, MyMenuModal },
  name: "TheHeader",

  data() {
    return {
      drawer: false,
      isActive: false,
      menuModal: [
        {
          id: 1,
          title: "회원가입",
          link: "/sign",
        },
        {
          id: 2,
          title: "로그인",
          link: "/login",
        },
      ],
      memImg: "",
      findKakao: "",
    }
  },
  computed: {
    ...mapState(["user", "userInfo"]),
  },
  mounted() {
    this.fetchUser()
    this.fetchUserInfo()
    setTimeout(this.change, 70)
  },
  methods: {
    ...mapActions(["fetchUser", "fetchUserInfo"]),
    toggleMyMenu() {
      this.isActive = !this.isActive
    },
    change() {
      this.memImg = this.userInfo.memImg
      this.findKakao = this.memImg.indexOf("k.kakaocdn.net")
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/header/global-header";
@import "~@/assets/scss/components/header/gnb-icon";
@import "~@/assets/scss/components/my-menu";
@import "~@/assets/scss/layouts/HeaderLayout";
@import "~@/assets/scss/layouts/NavLayout";

.v-navigation-drawer--temporary {
  z-index: 80;
}

.gnb-login {
  &-content {
    button {
      @include flexbox(start);
      border: 1px solid $border;
      padding: 5px 5px 5px 12px;
      border-radius: 21px;

      &:hover {
        box-shadow: 0 1px 2px rgb(0 0 0 / 12%), 0 4px 12px rgb(0 0 0 / 12%);
      }
    }

    .icMenu {
      font-size: 18px;
      margin-right: 8px;
    }
  }
}
</style>

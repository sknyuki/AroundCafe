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
                    ><img
                      src="@/assets/images/Logo.png"
                      alt="어라운드카페 로고"
                    />
                    <span class="sm-hidden">Around Cafe</span>
                  </router-link>
                </h1>
                <v-btn
                  class="gnb-icon-button is-menu sm-only"
                  icon
                  @click="toggleDrawerMenu"
                  type="button"
                  aria-label="메뉴 열기 버튼"
                >
                  <i class="icMenu" aria-hidden="true"></i>
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

                  <router-link
                    :to="{ name: 'UserLikePage' }"
                    style="text-decoration: none"
                  >
                    <v-btn
                      icon
                      class="gnb-icon-button"
                      aria-label="좋아요 페이지로 이동"
                    >
                      <i class="icHeart" aria-hidden="true"></i>
                      <strong class="badge">{{ myLikes.length }}</strong>
                    </v-btn>
                  </router-link>

                  <!-- 로그인을 하지 않은 경우 -->
                  <div class="gnb-login sm-hidden">
                    <div class="gnb-login-content">
                      <button
                        class="gnb-login-btn"
                        @click="toggleMyMenu"
                        type="button"
                        aria-label="로그인 메뉴"
                      >
                        <div>
                          <i class="icMenu" aria-hidden="true"></i>
                        </div>

                        <div class="avatar-32">
                          <template v-if="userInfo.memImg == null">
                            <img src="@/assets/images/avatar.webp" alt="" />
                          </template>
                          <template
                            v-if="
                              userInfo.socialType != 'LOCAL' &&
                              userInfo.memImg != null
                            "
                          >
                            <img :src="userInfo.memImg" alt="" />
                          </template>
                          <template
                            v-if="
                              userInfo.socialType == 'LOCAL' &&
                              userInfo.memImg != null
                            "
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
                      :class="toggleMyMenuModal"
                      :isActive="isActive"
                      @sumbit="closeDialog()"
                    />
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <v-navigation-drawer width="240" v-model="onDrawer" fixed temporary>
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
      onDrawer: false,
      isActive: false,
      showSearchResult: false,
    }
  },

  computed: {
    ...mapState(["user", "userInfo", "myLikes"]),
    toggleMyMenuModal() {
      return this.isActive ? "is-active" : null
    },
  },
  async mounted() {
    await this.fetchUserInfo()
    this.fetchMyLikesList(this.user.memNo)
  },
  methods: {
    ...mapActions(["fetchUser", "fetchUserInfo", "fetchMyLikesList"]),

    toggleDrawerMenu() {
      this.onDrawer = !this.onDrawer
    },
    toggleMyMenu() {
      this.isActive = !this.isActive
    },
    closeDialog(payload) {
      console.log(payload)
      this.isActive = false
    },
    onClick() {
      this.isActive = false
    },
    onClick1() {
      this.isActive = true
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
</style>

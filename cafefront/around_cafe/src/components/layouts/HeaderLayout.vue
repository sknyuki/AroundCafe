<template>
  <header class="global-header" app="app">
    <div class="gnb">
      <div class="container">
        <div class="row">
          <div class="col-sm-12">
            <div class="gnb-wrapper">
              <div class="gnb-left">
                <h1 class="logo">
                  <router-link :to="{ name: 'MainPage' }" alt="로고"
                    >로고</router-link
                  >
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
                      class="form-input"
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
                    aria-label="장바구니 페이지로 이동"
                  >
                    <a>
                      <i class="icCart"></i>
                      <strong class="badge">1</strong>
                    </a>
                  </v-btn>

                  <!-- 로그인을 하지 않은 경우 -->
                  <div class="gnb-login sm-hidden">
                    <router-link
                      :to="{ name: 'LoginPage' }"
                      class="gnb-login-item"
                      >로그인</router-link
                    >
                    <router-link
                      :to="{ name: 'SignUpPage' }"
                      class="gnb-login-item"
                      >회원가입</router-link
                    >
                    <a class="gnb-login-item lg-only" href="">고객센터</a>
                  </div>

                  <!-- 로그인을 한 경우  -->
                  <!-- <div class="my-menu sm-hidden">
                    <button
                      class="my-menu-button"
                      type="button"
                      aria-label="마이메뉴 열기 버튼"
                    >
                      <img src="@/assets/images/img-user-default.png" alt="" />
                    </button>
                  </div> -->
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <v-navigation-drawer width="240" v-model="drawer" fixed temporary>
      <navbar-list></navbar-list>
    </v-navigation-drawer>
    <NavLayout></NavLayout>
  </header>
</template>
<script>
import NavLayout from "@/components/layouts/NavLayout.vue"
import NavbarList from "@/components/layouts/NavbarList.vue"

export default {
  name: "HeaderLayout",
  components: { NavLayout, NavbarList },

  data() {
    return {
      drawer: false,
    }
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
</style>

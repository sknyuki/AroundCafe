import Vue from "vue"
import VueRouter from "vue-router"
import MainPage from "../views/MainPage.vue"

// Account 회원
import SignUpPage from "../views/account/SignUpPage.vue"
import LoginPage from "../views/account/LoginPage.vue"
import PasswordPage from "../views/account/PasswordPage.vue"

// Policy 정책
import UsePolicyPage from "../views/policy/UsePolicyPage.vue"

// MyPage 마이페이지
import MyPage from "../views/mypage/MyPage.vue"

// Admin 관리자페이지
import AdminPage from "../views/admin/AdminPage.vue"

// Cafe 카페페이지
import MyCafePage from "../views/mycafe/MyCafePage.vue"

//CafeMyPage
import CafeModifyView from "../views/CafeMyPageViews/CafeModifyView.vue"
import CafeMenuRegister from "../views/CafeMyPageViews/CafeMenuRegister.vue"

Vue.use(VueRouter)

const routes = [
  {
    path: "/main",
    name: "MainPage",
    component: MainPage,
  },
  {
    path: "/sign",
    name: "SignUpPage",
    component: SignUpPage,
  },
  {
    path: "/login",
    name: "LoginPage",
    component: LoginPage,
  },
  {
    path: "/password",
    name: "PasswordPage",
    component: PasswordPage,
  },
  {
    path: "/usepolicy",
    name: "UsePolicyPage",
    component: UsePolicyPage,
  },
  {
    path: "/my",
    name: "MyPage",
    component: MyPage,
  },
  {
    path: "/CafeModifyView",
    name: "CafeModifyView",
    components: { CafeModifyView },
    props: {
      default: true,
    },
  },
  {
    path: "/cafeMenuRegister",
    name: "CafeMenuRegister",
    component: CafeMenuRegister,
  },
  {
    path: "/admin",
    name: "AdminPage",
    component: AdminPage,
  },
  {
    path: "/mycafe",
    name: "MyCafePage",
    component: MyCafePage,
  },
]

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
})

export default router

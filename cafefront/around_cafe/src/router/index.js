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
import MyOrderStatusPage from "../views/mypage/MyOrderStatusPage.vue"

// Admin 관리자페이지
import AdminMemberListPage from "../views/admin/AdminMemberListPage.vue"

// Cafe 카페페이지
import MyCafePage from "../views/mycafe/MyCafePage.vue"
import CafeRegisterPage from "../views/mycafe/CafeRegisterPage.vue"
import CafeReviewPage from "../views/mycafe/CafeReviewPage.vue"
import CafeChangePwPage from "../views/mycafe/CafeChangePwPage.vue"

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
    path: "/myorder",
    name: "MyOrderStatusPage",
    component: MyOrderStatusPage,
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
    name: "AdminMemberListPage",
    component: AdminMemberListPage,
  },
  {
    path: "/mycafe",
    name: "MyCafePage",
    component: MyCafePage,
  },
  {
    path: "/caferegister",
    name: "CafeRegisterPage",
    component: CafeRegisterPage,
  },
  {
    path: "/cafereview",
    name: "CafeReviewPage",
    component: CafeReviewPage,
  },
  {
    path: "/cafepw",
    name: "CafeChangePwPage",
    component: CafeChangePwPage,
  },
]

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
})

export default router

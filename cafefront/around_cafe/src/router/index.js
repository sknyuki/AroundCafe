import Vue from "vue"
import VueRouter from "vue-router"
import MainPage from "../views/MainPage.vue"

// Account 회원
import AccountSignUpPage from "../views/Account/AccountSignUpPage.vue"
import AccountLoginPage from "../views/Account/AccountLoginPage.vue"
import AccountPasswordPage from "../views/Account/AccountPasswordPage.vue"

// Policy 정책
import PolicyPage from "../views/Policy/PolicyPage.vue"

// User 페이지
import UserOrderListPage from "../views/User/UserOrderListPage.vue"

// Admin 관리자페이지
import AdminMemberListPage from "../views/Admin/AdminMemberListPage.vue"

// Cafe 카페페이지
import CafeRegisterPage from "../views/Cafe/CafeRegisterPage.vue"
import CafeReviewListPage from "../views/Cafe/CafeReviewListPage.vue"
import CafePasswordPage from "../views/Cafe/CafePasswordPage.vue"
import CafeUserModifyPage from "../views/Cafe/CafeUserModifyPage.vue"

//CafeMyPage
import CafeModifyView from "../views/CafeMyPageViews/CafeModifyView.vue"
import CafeMenuRegister from "../views/CafeMyPageViews/CafeMenuRegister.vue"
import TestTest from "../views/CafeMyPageViews/TestTest.vue"
Vue.use(VueRouter)

const routes = [
  {
    path: "/main",
    name: "MainPage",
    component: MainPage,
  },
  {
    path: "/sign",
    name: "AccountSignUpPage",
    component: AccountSignUpPage,
  },
  {
    path: "/login",
    name: "AccountLoginPage",
    component: AccountLoginPage,
  },
  {
    path: "/password",
    name: "AccountPasswordPage",
    component: AccountPasswordPage,
  },
  {
    path: "/policy",
    name: "PolicyPage",
    component: PolicyPage,
  },
  {
    path: "/userorder",
    name: "UserOrderListPage",
    component: UserOrderListPage,
  },
  {
    path: "/CafeModifyView",
    name: "CafeModifyView",
    component: CafeModifyView,
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
    path: "/caferegister",
    name: "CafeRegisterPage",
    component: CafeRegisterPage,
  },
  {
    path: "/cafereview",
    name: "CafeReviewListPage",
    component: CafeReviewListPage,
  },
  {
    path: "/cafepw",
    name: "CafePasswordPage",
    component: CafePasswordPage,
  },
  {
    path: "/cafemodify",
    name: "CafeUserModifyPage",
    component: CafeUserModifyPage,
  },
  {
    path: "/test",
    name: "TestTest",
    component: TestTest,
  },
]

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
})

export default router

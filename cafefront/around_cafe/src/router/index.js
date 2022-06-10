import Vue from "vue"
import VueRouter from "vue-router"

// Policy 약관페이지
import PolicyPage from "../views/Policy/PolicyPage.vue"

// NotFoundPage 에러페이지
import NotFoundPage from "@/views/NotFoundPage.vue"

// Main 메인페이지
// import MainPage from "../views/MainPage.vue"

// Account 계정
// import AccountSignUpPage from "../views/Account/AccountSignUpPage.vue"
// import AccountLoginPage from "../views/Account/AccountLoginPage.vue"
// import AccountPasswordPage from "../views/Account/AccountPasswordPage.vue"

// User 회원페이지
// import UserOrderListPage from "../views/User/UserOrderListPage.vue"
// import UserPasswordPage from "../views/User/UserPasswordPage.vue"

// Admin 관리자페이지
// import AdminMemberListPage from "../views/Admin/AdminMemberListPage.vue"

// Cafe 카페페이지
// import CafeRegisterPage from "../views/Cafe/CafeRegisterPage.vue"
// import CafeReviewListPage from "../views/Cafe/CafeReviewListPage.vue"
// import CafePasswordPage from "../views/Cafe/CafePasswordPage.vue"
// import CafeUserModifyPage from "../views/Cafe/CafeUserModifyPage.vue"

//CafeMyPage
// import CafeModifyView from "../views/CafeMyPageViews/CafeModifyView.vue"
import CafeMenuRegister from "../views/CafeMyPageViews/CafeMenuRegister.vue"
import TestTest from "../views/CafeMyPageViews/TestTest.vue"
import CafeReviewRegisterPage from "../views/Cafe/CafeReviewRegisterPage.vue"


Vue.use(VueRouter)

const routes = [

  // Main 메인페이지
  {
    path: "/",
    redirect: "/main",
    component: () => import("@/views/MainPage.vue"),
  },
  {
    path: "/main",
    name: "MainPage",
    component: () => import("@/views/MainPage.vue"),
  },

  // Account 계정페이지
  {
    path: "/sign",
    name: "AccountSignUpPage",
    component: () => import("@/views/Account/AccountSignUpPage.vue"),
  },
  {
    path: "/login",
    name: "AccountLoginPage",
    component: () => import("@/views/Account/AccountLoginPage.vue"),
  },
  {
    path: "/password",
    name: "AccountPasswordPage",
    component: () => import("@/views/Account/AccountPasswordPage.vue"),
  },

  // Admin 관리자페이지 
  {
    path: "/admin/member",
    name: "AdminMemberListPage",
    component: () => import("@/views/Admin/AdminMemberListPage.vue"),
  },

  // Cafe 카페페이지
  {
    path: "/cafe/register",
    name: "CafeRegisterPage",
    component: () => import("@/views/Cafe/CafeRegisterPage.vue"),
  },
  {
    path: "/cafe/review",
    name: "CafeReviewListPage",
    component: () => import("@/views/Cafe/CafeReviewListPage.vue"),
  },
  {
    path: "/cafe/modify",
    name: "CafeUserModifyPage",
    component: () => import("@/views/Cafe/CafeUserModifyPage"),
  },
  {
    path: "/cafe/pw",
    name: "CafePasswordPage",
    component: () => import("@/views/Cafe/CafePasswordPage"),
  },

  //CafeSite
  {
    path: "/cafe/CafeSite",
    name: "CafeSiteList",
    component: () => import("@/views/CafeSiteViews/CafeSiteList"),
  },



  //Policy 이용약관페이지
  {
    path: "/policy",
    name: "PolicyPage",
    component: PolicyPage,
  },


  // User 회원 페이지
  {
    path: "/user/order",
    name: "UserOrderListPage",
    component: () => import("@/views/User/UserOrderListPage"),
  },
  {
    path: "/user/pw",
    name: "UserPasswordPage",
    component: () => import("@/views/User/UserPasswordPage"),
  },

//인지연이 만든 페이지 ~ test
  {
    path: "/cafeMenuRegister",
    name: "CafeMenuRegister",
    component: CafeMenuRegister,
  },
  {
    path: "/CafeModifyView",
    name: "CafeModifyView",
    component: () => import("@/views/CafeMyPageViews/CafeModifyView"),
  },

  {
    path: "/cafeQnAPage",
    name: "CafeQnAPage",
    component: () => import("@/views/CafeMyPageViews/CafeQnAPage"),
  },

  {
    path: "/cafeListPage",
    name: "CafeListPage_manager",
    component: () => import("@/views/CafeMyPageViews/CafeListPage_manager"),
  },
  {
    path: "/test",
    name: "TestTest",
    component: TestTest,
  },
  

  {
    path: "/cafe/review/register",
    name: "CafeReviewRegisterPage",
    components: {
      default :CafeReviewRegisterPage
    },
    props : {
      default: true
    }
  },

  // 에러처리 페이지 
  {
    path: '*',
    redirect: "/404"
  },
  {
    path: '/404',
    component: NotFoundPage,
  },
]
const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
})

export default router

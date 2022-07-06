import Vue from "vue"
import VueRouter from "vue-router"

// Policy 약관페이지
import PolicyPage from "../views/Policy/PolicyPage.vue"

// NotFoundPage 에러페이지
import NotFoundPage from "@/views/NotFoundPage.vue"

//관리자 시스템
import NoticeReadPage from "@/views/Admin/AdminNotice/NoticeReadPage"
import NoticeModifyPage from "@/views/Admin/AdminNotice/NoticeModifyPage"
//CafeMyPage
// import CafeModifyView from "../views/CafeMyPageViews/CafeModifyView.vue"
import CafeMenuRegister from "../views/CafeMyPageViews/CafeMenuRegister.vue"
import TestTest from "../views/CafeMyPageViews/TestTest.vue"
import CafeReviewRegisterPage from "../views/Cafe/CafeReviewRegisterPage.vue"
import CafeQnAPage from "../views/CafeMyPageViews/CafeQnAPage"

Vue.use(VueRouter)

const routes = [
  // Main 메인페이지
  {
    path: "/",
    redirect: "/main",
    component: () => import("@/views/MainPage.vue"),
    meta: {
      title: "Around Cafe",
    },
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
    path: "/login/callback",
    name: "AccountCallbackPage",
    component: () => import("@/views/Account/AccountCallbackPage"),
  },
  {
    path: "/password",
    name: "AccountPasswordPage",
    component: () => import("@/views/Account/AccountPasswordPage.vue"),
  },
  {
    path: "/password/modify",
    name: "AccountModifyPasswordPage",
    components: {
      default: () => import("@/views/Account/AccountModifyPasswordPage"),
    },
    props: {
      default: true,
    },
  },
  {
    path: "/account/test",
    name: "AccountTestPage",
    component: () => import("@/views/Account/AccountTestPage.vue"),
  },
  {
    path: "/account/callback/test",
    name: "AccountCallbackTest",
    component: () => import("@/views/Account/AccountCallbackTest.vue"),
  },

  // Admin 관리자페이지
  {
    path: "/admin/pw",
    name: "AdminPasswordPage",
    component: () => import("@/views/Admin/AdminPasswordPage.vue"),
  },
  {
    path: "/admin/AdminUserModifyPage",
    name: "AdminUserModifyPage",
    component: () => import("@/views/Admin/AdminUserModifyPage.vue"),
  },
  {
    path: "/admin/AdminUsageMemberListPage",
    name: "AdminUsageMemberListPage",
    component: () => import("@/views/Admin/AdminUsageMemberListPage.vue"),
  },
  {
    path: "/admin/",
    name: "AdminUsageMemberPage",
    components: {
      default: () => import("@/views/Admin/AdminUsageMemberPage.vue"),
    },
    props: {
      default: true,
    },
  },
  {
    path: "/admin/member",
    name: "AdminMemberListPage",
    component: () => import("@/views/Admin/AdminMemberListPage.vue"),
  },
  {
    path: "/admin/cafe",
    name: "AdminCafeListPage",
    component: () => import("@/views/Admin/AdminCafeListPage.vue"),
  },
  {
    path: "/admin/noticelist",
    name: "NoticelistPage",
    component: () => import("@/views/Admin/AdminNotice/NoticeListPage.vue"),
  },

  {
    path: "/admin/noticeread/:boardNo",
    name: "NoticeReadPage",
    components: {
      default: NoticeReadPage,
    },
    props: {
      default: true,
    },
  },
  {
    path: "/admin/NoticeModifyPage/:boardNo",
    name: "NoticeModifyPage",
    components: {
      default: NoticeModifyPage,
    },
    props: {
      default: true,
    },
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
  {
    path: "/cafe/menu",
    name: "CafeRegisterMenuPage",
    component: () => import("@/views/Cafe/CafeRegisterMenuPage"),
  },

  //CafeSite
  {
    path: "/cafe/CafeSite",
    name: "CafeSiteList",
    component: () => import("@/views/CafeSiteViews/CafeSiteList"),
  },
  {
    path: "/cafe/purchase",
    name: "CafePurchasePage",
    component: () => import("@/views/CafeSiteViews/CafePurchasePage"),
    props: true,
  },
  {
    path: "/cafe/purchase/complete",
    name: "CafePurchaseCompletePage",
    component: () => import("@/views/CafeSiteViews/CafePurchaseCompletePage"),
    props: true,
  },
  {
    path: "/cafe/purchase/popup",
    name: "CafePurchasePopUp",
    component: () => import("@/views/CafeSiteViews/CafePurchasePopUp"),
    props: true,
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
    path: "/user/history",
    name: "UserOrderHistoryPage",
    component: () => import("@/views/User/UserOrderHistoryPage"),
  },
  {
    path: "/user/history/detail/:paymentNo",
    name: "UserOrderHistoryDetailPage",
    components: {
      default: () => import("@/views/User/UserOrderHistoryDetailPage"),
    },
    props: {
      default: true,
    },
  },
  {
    path: "/user/pw",
    name: "UserPasswordPage",
    component: () => import("@/views/User/UserPasswordPage"),
  },
  {
    path: "/user/modify",
    name: "UserModifyPage",
    component: () => import("@/views/User/UserModifyPage"),
  },
  {
    path: "/user/like",
    name: "UserLikePage",
    component: () => import("@/views/User/UserLikePage"),
  },
  {
    path: "/user/review",
    name: "UserReviewListPage",
    component: () => import("@/views/User/UserReviewListPage.vue"),
  },

  //CafeDetail
  {
    path: "/detail/:cafeNo",
    name: "CafeDetailPage",
    components: {
      default: () => import("@/views/CafeDetail/CafeDetailPage"),
    },
    props: {
      default: true,
    },
  },
  {
    path: "/chat/",
    name: "ChatPage",
    component: () => import("@/views/Chat/ChatPage"),
  },
  {
    path: "/service",
    name: "ServiceCenterPage",
    component: () => import("@/views/Service/ServiceCenterPage"),
  },
  {
    path: "/service/refund",
    name: "ServiceCenterRefundPage",
    component: () => import("@/views/Service/ServiceCenterRefundPage"),
  },
  {
    path: "/service/account",
    name: "ServiceCenterAccountPage",
    component: () => import("@/views/Service/ServiceCenterAccountPage"),
  },
  {
    path: "/service/evidence",
    name: "ServiceCenterEvidencePage",
    component: () => import("@/views/Service/ServiceCenterEvidencePage"),
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
    components: {
      default: CafeQnAPage,
    },
    props: {
      default: true,
    },
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
      default: CafeReviewRegisterPage,
    },
    props: {
      default: true,
    },
  },
  {
    path: "/cafe/review/modify/:reviewNo",
    name: "CafeReviewModifyPage",
    component: () => import("@/views/Cafe/CafeReviewModifyPage"),
    props: {
      default: true,
    },
  },
  {
    path: "/cafe/review/list",
    name: "CafeReviewListPage",
    components: { default: () => import("@/views/Cafe/CafeReviewListPage") },
    props: {
      default: true,
    },
  },

  {
    path: "/map/main",
    name: "MapMain",
    components: { default: () => import("@/views/Map/MapMain") },
    props: {
      default: true,
    },
  },
  {
    path: "/cafe/order/list",
    name: "OrderCafeList",
    components: { default: () => import("@/views/Order/OrderCafeListPage") },
    props: {
      default: true,
    },
  },

  {
    path: "*",
    redirect: "/404",
  },
  {
    path: "/404",
    component: NotFoundPage,
  },
]
const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
})
/* eslint-disable */
router.afterEach((to, from) => {
  const title = to.meta.title === undefined ? "Around Cafe" : to.meta.title
  Vue.nextTick(() => {
    document.title = title
  })
})

export default router

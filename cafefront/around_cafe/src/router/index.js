import Vue from "vue"
import VueRouter from "vue-router"
import MainPage from "../views/MainPage.vue"

// Account 회원
import SignUpPage from "../views/account/SignUpPage.vue"
import LoginPage from "../views/account/LoginPage.vue"
import PasswordPage from "../views/account/PasswordPage.vue"

// Policy 정책
import UsePolicyPage from "../views/policy/UsePolicyPage.vue"

//CafeMyPage
import CafeModifyView from "../views/MyPageViews/CafeMyPageViews/CafeModifyView"

//Cafesite
import CafeSiteList from "../views/CafeSiteViews/CafeSiteList"

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
    path: "/CafeModifyView",
    name: "CafeModifyView",
    components:{CafeModifyView,
  },
  props:{
    default: true
  }
},
{
  path: "/CafeSiteList",
  name: "CafeSiteList",
  component: CafeSiteList,
},

]

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
})

export default router

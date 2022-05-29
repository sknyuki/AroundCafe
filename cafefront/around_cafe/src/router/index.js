import Vue from "vue"
import VueRouter from "vue-router"
import MainPage from "../views/MainPage.vue"

// Account 회원
import SignUpPage from "../views/account/SignUpPage.vue"
import LoginPage from "../views/account/LoginPage.vue"

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
]

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
})

export default router

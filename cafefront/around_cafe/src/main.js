import Vue from "vue"
import App from "./App.vue"
import router from "./router"
import store from "./store"
import vuetify from "./plugins/vuetify"
//import axios from "axios"
import axios from "@/plugins/axiosInterceptors"
import "@/utils/validation"
import VueChatScroll from "vue-chat-scroll"

import { yyyyMMdd, HHmm, pricePoint } from "@/utils/filters"

Vue.filter("pricePoint", pricePoint)
Vue.filter("yyyyMMdd", yyyyMMdd)
Vue.filter("HHmm", HHmm)
// Vue.filter("ratingPoint", ratingPoint)

Vue.config.productionTip = false

// 이미지 레이지로딩 디렉티브
const lazyLoad = {
  inserted: (el) => {
    function loadImage() {
      const isImg = el.nodeName === "IMG"
      // 이미지 태그만 url 로딩
      if (isImg) {
        el.src = el.dataset.url
      }
    }

    function createObserver() {
      const observer = new IntersectionObserver((entries, observer) => {
        entries.forEach((entry) => {
          if (entry.isIntersecting) {
            // 감지대상이 교차영역에 진입 할 경우
            loadImage() // 교차영역 들어올경우 이미지 로딩
            observer.unobserve(el) // 이미지 로딩 이후론 관찰할 필요 x
          }
        })
      })

      observer.observe(el)
    }

    // 지원하지 않는 브라우저 이미지 로딩을 시켜주도록 호환성 체크
    window["IntersectionObserver"] ? createObserver() : loadImage()
  },
}

Vue.directive("lazyload", lazyLoad)

Vue.use(VueChatScroll)

new Vue({
  router,
  store,
  vuetify,
  axios,
  render: (h) => h(App),
}).$mount("#app")

import Vue from "vue"
import App from "./App.vue"
import router from "./router"
import store from "./store"
import vuetify from "./plugins/vuetify"
//import axios from "axios"
import axios from "@/plugins/axiosInterceptors"
import "@/utils/validation"

import { yyyyMMdd, HHmm, pricePoint } from "@/utils/filters"

Vue.filter("pricePoint", pricePoint)
Vue.filter("yyyyMMdd", yyyyMMdd)
Vue.filter("HHmm", HHmm)
// Vue.filter("ratingPoint", ratingPoint)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  axios,
  render: (h) => h(App),
}).$mount("#app")

import Vue from "vue"
import App from "./App.vue"
import router from "./router"
import store from "./store"
import vuetify from "./plugins/vuetify"
//import axios from "axios"
import axios from "@/plugins/axiosInterceptors"
import "@/utils/validation"
import Dayjs from "vue-dayjs"

import { phoneNumber, pricePoint } from "@/utils/filters"

Vue.filter("phoneNumber", phoneNumber)
Vue.filter("pricePoint", pricePoint)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  axios,
  Dayjs,
  render: (h) => h(App),
}).$mount("#app")

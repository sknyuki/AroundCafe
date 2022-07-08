<template>
  <v-app>
    <TheHeader v-if="visibleHeader" />
    <v-main>
      <router-view />
    </v-main>
    <TheFooter v-if="visibleFooter" />
  </v-app>
</template>

<script>
import TheFooter from "@/components/Layouts/TheFooter.vue"
import TheHeader from "@/components/Layouts/TheHeader.vue"
import router from "./router"
export default {
  components: { TheHeader, TheFooter },
  name: "App",
  data() {
    return {
      visibleHeader: true,
      visibleFooter: true,
    }
  },
  /* eslint-disable */
  created() {
    let visibleHeader = false
    router.afterEach((to, from) => {
      if (
        (visibleHeader = to.path === "/login") ||
        (visibleHeader = to.path === "/sign") ||
        (visibleHeader = to.path === "/chat") ||
        (visibleHeader = to.path === "/login/callback") ||
        (visibleHeader = to.path === "/cafe/PurchasePopUp") ||
        (visibleHeader = to.path === "/map/main") ||
        (visibleHeader = to.path === "/cafe/purchase/popup")
      ) {
        this.visibleHeader = false
      } else {
        this.visibleHeader = true
      }
    })
    let visibleFooter = false
    router.afterEach((to, from) => {
      if (
        (visibleFooter = to.path === "/login") ||
        (visibleFooter = to.path === "/sign") ||
        (visibleFooter = to.path === "/chat") ||
        (visibleFooter = to.path === "/login/callback") ||
        (visibleFooter = to.path === "/cafe/PurchasePopUp") ||
        (visibleFooter = to.path === "/map/main") ||
        (visibleFooter = to.path === "/cafe/purchase/popup")
      ) {
        this.visibleFooter = false
      } else {
        this.visibleFooter = true
      }
    })
  },
}
</script>
<style lang="scss">
@import "~@/assets/scss/abstracts/";
</style>

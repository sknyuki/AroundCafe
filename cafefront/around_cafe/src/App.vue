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
        (visibleHeader = to.path === "/chat")
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
        (visibleFooter = to.path === "/chat")
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

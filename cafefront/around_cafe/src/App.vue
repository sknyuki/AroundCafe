<template>
  <v-app>
    <TheHeader v-if="visible" />
    <v-main>
      <v-container>
        <v-row>
          <v-col>
            <router-view />
          </v-col>
        </v-row>
      </v-container>
    </v-main>
    <TheFooter v-if="visible" />
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
      visible: false,
    }
  },
  /* eslint-disable */
  created() {
    let visible = false
    router.afterEach((to, from) => {
      if ((visible = to.path === "/sign") || (visible = to.path === "/login")) {
        this.visible = false
      } else {
        this.visible = true
      }
    })
  },
}
</script>
<style lang="scss">
@import "~@/assets/scss/abstracts/";
</style>

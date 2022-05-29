<template>
  <v-app>
    <HeaderLayout />
    <v-main>
      <v-container>
        <v-row>
          <v-col>
            <router-view />
          </v-col>
        </v-row>
      </v-container>
    </v-main>
    <FooterLayout />
  </v-app>
</template>

<script>
import FooterLayout from "./components/layouts/FooterLayout.vue"
import HeaderLayout from "./components/layouts/HeaderLayout.vue"

export default {
  name: "App",
  components: { HeaderLayout, FooterLayout },
}
</script>
<style lang="scss">
@import "~@/assets/scss/abstracts/";
</style>

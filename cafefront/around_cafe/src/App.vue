<template>
  <v-app>
    <TheHeader />
    <v-main>
      <v-container>
        <v-row>
          <v-col>
            <router-view />
          </v-col>
        </v-row>
      </v-container>
    </v-main>
    <TheFooter />
  </v-app>
</template>

<script>
import TheFooter from "./components/Layouts/TheFooter.vue"
import TheHeader from "./components/Layouts/TheHeader.vue"

export default {
  components: { TheHeader, TheFooter },
  name: "App",
}
</script>
<style lang="scss">
@import "~@/assets/scss/abstracts/";
</style>

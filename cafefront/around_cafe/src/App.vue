<template>
  <v-app>
    <HeaderLayout></HeaderLayout>
    <router-view></router-view>
    <FooterLayout></FooterLayout>
  </v-app>
</template>

<script>
import FooterLayout from './components/layouts/FooterLayout.vue';
import HeaderLayout from './components/layouts/HeaderLayout.vue';

export default {
  name: 'App',
  components: { HeaderLayout, FooterLayout },


};
</script>
<style lang="scss">
@import "~@/assets/scss/abstracts/";
</style>

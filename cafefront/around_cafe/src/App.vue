<template>
  <v-app>
    <HeaderLayout></HeaderLayout>
    <FooterLayout></FooterLayout>
  </v-app>
</template>

<script>
import FooterLayout from './components/layouts/FooterLayout.vue';
import HeaderLayout from './components/layouts/HeaderLayout.vue';

export default {
  components: { HeaderLayout, FooterLayout },
  name: 'App',


};
</script>
<style lang="scss">
@import "~@/assets/scss/abstracts/";
</style>

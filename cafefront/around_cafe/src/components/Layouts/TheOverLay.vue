<template>
  <div @click="offSidebar" class="overlay sm-only" aria-hidden="true"></div>
</template>

<script>
export default {
  name: "TheOverLay",

  methods: {
    offSidebar() {
      this.$emit("submit", { isActive: this.isActive })
    },
  },
}
</script>

<style lang="scss" scoped>
@import "~@/assets/scss/layouts/OverLay";
</style>

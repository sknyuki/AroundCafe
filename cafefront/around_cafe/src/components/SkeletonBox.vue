<template>
  <span :style="{ height, width: computedWidth }" class="SkeletonBox" />
</template>

<script>
export default {
  name: "SkeletonBox",
  props: {
    maxWidth: {
      default: 100,
      type: Number,
    },
    minWidth: {
      default: 80,
      type: Number,
    },
    height: {
      default: "1em",
      type: String,
    },
    width: {
      default: null,
      type: String,
    },
    computed: {
      computedWidth() {
        return (
          this.width ||
          `${Math.floor(
            0.9 * (this.maxWidth - this.minWidth) + this.minWidth
          )}%`
        )
      },
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/skeleton-box";
</style>

<template>
  <section>
    <div>
      <div class="test">
        <skeleton-box />
      </div>
      <skeleton-box />
      <skeleton-box />
      <skeleton-box />
    </div>
  </section>
</template>

<script>
import SkeletonBox from "@/components/SkeletonBox.vue"
export default {
  name: "SkeletonTest",
  components: {
    SkeletonBox,
  },

  data() {
    return {}
  },

  mounted() {},

  methods: {},
}
</script>

<style lang="scss" scoped>
.test {
  height: 200px;
}
</style>

<template>
  <div class="example" v-if="loading">
    <span class="material spinner" />
  </div>
</template>

<script>
export default {
  props: {
    loading: {
      type: Boolean,
      required: true,
    },
  },
}
</script>

<style lang="scss" scoped>
.example {
  display: block;
  height: 200px;
  position: relative;
  letter-spacing: 0.5px;
}

.spinner::after {
  content: "";
  box-sizing: border-box;
  width: 40px;
  height: 40px;
  position: absolute;
  top: calc(50% - 20px);
  left: calc(50% - 20px);
  border-radius: 50%;
}

.spinner.material::after {
  border-top: 4px solid white;
  border-left: 4px solid $indigo;
  border-bottom: 4px solid $indigo;
  border-right: 4px solid $indigo;
  animation: spinner 0.6s linear infinite;
}

@keyframes spinner {
  to {
    transform: rotate(360deg);
  }
}
</style>

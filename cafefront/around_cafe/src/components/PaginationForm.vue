<template>
  <div>
    <div class="pagination">
      <v-btn
        class="page-control page-prev"
        type="button"
        aria-label="이전페이지로 이동"
      >
        <i class="icChevron" aria-hidden></i>
      </v-btn>
      <ol>
        <li>{{}}</li>
      </ol>
      <v-btn
        class="page-control page-next"
        type="button"
        aria-label="다음페이지로 이동"
      >
        <i class="icChevron" aria-hidden></i>
      </v-btn>
    </div>
  </div>
</template>
<script>
export default {
  name: "PaginationForm",
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/modules/pagination";
</style>

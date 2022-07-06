<template>
  <div>
    <div v-if="pageSetting.list.length" class="pagination">
      <v-btn
        class="page-control page-prev"
        type="button"
        aria-label="이전페이지로 이동"
        v-if="pageSetting.first !== null"
        @click="pageSetting.first !== null ? sendPage(pageSetting.first) : ''"
      >
        <i class="icChevron" aria-hidden="true"></i>
      </v-btn>

      <ol class="page-list">
        <li
          class="page-item"
          :class="{ isactive: page === pageSetting.currentPage }"
          v-for="page in pageSetting.list"
          :key="page"
          @click="sendPage(page)"
        >
          <a> {{ page }} </a>
        </li>
      </ol>

      <v-btn
        class="page-control page-next"
        type="button"
        aria-label="다음페이지로 이동"
        v-if="pageSetting.end !== null"
        @click="pageSetting.end !== null ? sendPage(pageSetting.end) : ''"
      >
        <i class="icChevron" aria-hidden="true"></i>
      </v-btn>
    </div>
  </div>
</template>

<script>
export default {
  name: "PaginationForm",
  props: ["pageSetting"],

  methods: {
    sendPage(page) {
      this.$emit("paging", page)
    },
  },
}
</script>

<style lang="scss" scoped>
@import "~@/assets/scss/modules/pagination";
</style>

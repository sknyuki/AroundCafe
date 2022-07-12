<template>
  <aside class="search-modal lg-hidden">
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <header class="search-modal-header">
            <h1 class="visually-hidden">검색창</h1>

            <div class="search-modal-form">
              <div class="input-group">
                <i class="icSearch" aria-hidden></i>
                <input
                  class="form-input input-40"
                  type="text"
                  placeholder="스토어 검색"
                />
              </div>
              <button
                @click="offSearchModal"
                class="btn-ghost btn-40"
                type="button"
              >
                취소
              </button>
            </div>
          </header>

          <section class="search-history">
            <header class="search-history-header">
              <h2 class="title">최근 검색어</h2>
              <button type="button">전체 삭제</button>
            </header>

            <!-- 최근 검색어가 존재할 경우 -->
            <div class="search-history-content">
              <ol class="search-history-list">
                <li
                  v-for="item in searchHistory"
                  :key="item.id"
                  class="search-history-item"
                >
                  <button class="word-button" type="button">
                    {{ item.title }}
                  </button>
                  <button
                    class="delete-button"
                    type="button"
                    aria-label="검색어 삭제"
                  >
                    <i class="icClose" aria-hidden="true"></i>
                  </button>
                </li>
              </ol>
            </div>

            <!-- 최근 검색어가 존재하지 않을 경우 -->
            <!-- <div class="search-history-content">
                <p class="placeholder">최근 검색한 내역이 없습니다.</p>
              </div> -->
          </section>
        </div>
      </div>
    </div>
  </aside>
</template>

<script>
export default {
  name: "TheSearchModal",
  data() {
    return {
      searchHistory: [
        {
          id: 1,
          title: "끝났다",
        },
      ],
    }
  },
  methods: {
    offSearchModal() {
      this.$emit("submit", { isActive: this.isActive })
    },
  },
}
</script>

<style lang="scss" scoped>
@import "~@/assets/scss/layouts/Search-Modal";
</style>

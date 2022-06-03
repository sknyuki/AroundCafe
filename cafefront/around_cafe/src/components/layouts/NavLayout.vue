<template>
  <nav class="lnb" :class="{ 'nav-hidden': !showNavbar }">
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <h1 class="visually-hidden">서브 메뉴리스트</h1>
          <ul class="lnb-list">
            <li class="lnb-item is-active"><a href="">카페</a></li>
            <li class="lnb-item"><a href="">커피</a></li>
            <li class="lnb-item"><a href="">베스트</a></li>
            <li class="lnb-item"><a href="">뉴스</a></li>
            <li class="lnb-item"><a href="">고객센터</a></li>
          </ul>
        </div>
      </div>
    </div>
  </nav>
</template>
<script>
export default {
  name: "NavLayout",
  data() {
    return {
      showNavbar: true,
      lastScrollPostion: 0,
    }
  },
  mounted() {
    window.addEventListener("scroll", this.onScroll)
  },
  beforeDestroy() {
    window.removeEventListener("scroll", this.onScroll)
  },
  onScroll() {
    const currentScrollPosition =
      window.pageYOffset || document.documentElement.scrollTop
    if (currentScrollPosition < 0) {
      return
    }
    if (Math.abs(currentScrollPosition - this.lastScrollPosition) < 60) {
      return
    }
    this.showNavbar = currentScrollPosition < this.lastScrollPosition
    this.lastScrollPosition = currentScrollPosition
  },
  methods: {
    onScroll() {
      const currentScrollPosition =
        window.pageYOffset || document.documentElement.scrollTop
      if (currentScrollPosition < 0) {
        return
      }
      this.showNavbar = currentScrollPosition < this.lastScrollPosition
      this.lastScrollPosition = currentScrollPosition
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/layouts/NavLayout";
</style>

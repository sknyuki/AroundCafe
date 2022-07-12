<template>
  <nav class="lnb" :class="onNavBarList">
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <h1 class="visually-hidden">서브 메뉴리스트</h1>
          <ul class="lnb-list">
            <li v-for="item in lnbList" :key="item.id" class="lnb-item">
              <router-link :to="item.link">{{ item.title }}</router-link>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </nav>
</template>
<script>
export default {
  name: "TheNavBar",
  data() {
    return {
      showNavbar: true,
      lastScrollPostion: 0,
      lnbList: [
        {
          id: 1,
          title: "카페",
          link: "/main",
        },
        {
          id: 2,
          title: "공지사항",
          link: "/noticeBoard",
        },
        {
          id: 3,
          title: "베스트",
          link: "/main",
        },
        {
          id: 4,
          title: "뉴스",
          link: "/main",
        },
        {
          id: 5,
          title: "지도",
          link: "/map/main",
        },
        {
          id: 6,
          title: "고객센터",
          link: "/service",
        },
      ],
    }
  },
  computed: {
    onNavBarList() {
      return !this.showNavbar ? "nav-hidden" : null
    },
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

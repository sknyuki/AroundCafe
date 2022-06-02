<template>
  <div>
    <ImgBox />
    <DashboardSidebar
      :infoOne="infoOne"
      :infoTwo="infoTwo"
      :sidebarOne="sidebarOne"
      :sidebarTwo="sidebarTwo"
    />
  </div>
</template>
<script>
import ImgBox from "@/components/mypage/ImgBox.vue"
import DashboardSidebar from "@/components/DashboardSidebar.vue"
export default {
  components: { ImgBox, DashboardSidebar },
  name: "MyPage",

  data() {
    return {
      sidebarOne: [
        {
          id: 1,
          icon: "mdi-coffee-outline",
          title: "주문현황",
          link: "/",
        },
        {
          id: 2,
          icon: "mdi-format-list-bulleted",
          title: "주문내역",
          link: "/",
        },
        {
          id: 3,
          icon: "mdi-message-text-outline",
          title: "내리뷰",
          link: "/",
        },
        {
          id: 4,
          icon: "mdi-heart-outline",
          title: "좋아요",
          link: "/",
        },
      ],
      sidebarTwo: [
        {
          id: 1,
          icon: "mdi-account-outline",
          title: "내 정보수정",
          link: "/",
        },
        {
          id: 2,
          icon: "mdi-email-outline",
          title: "문의사항",
          link: "/",
        },
      ],
      infoOne: "나의 활동",
      infoTwo: "내 정보",
    }
  },
}
</script>
<style lang=""></style>

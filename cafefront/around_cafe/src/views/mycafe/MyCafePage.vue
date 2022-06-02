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
  name: "MyCafePage",

  data() {
    return {
      sidebarOne: [
        {
          id: 1,
          icon: "mdi-home-outline",
          title: "카페 관리",
          link: "/",
        },
        {
          id: 2,
          icon: "mdi-coffee-outline",
          title: "메뉴 관리",
          link: "/",
        },
        {
          id: 3,
          icon: "mdi-format-list-bulleted",
          title: "주문 리스트",
          link: "/",
        },
        {
          id: 4,
          icon: "mdi-cash",
          title: "매출",
          link: "/",
        },
        {
          id: 5,
          icon: "mdi-message-text-outline",
          title: "고객 리뷰",
          link: "/",
        },
        {
          id: 6,
          icon: "mdi-wechat",
          title: "고객과 대화",
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
      infoOne: "카페 정보",
      infoTwo: "내 정보",
    }
  },
}
</script>

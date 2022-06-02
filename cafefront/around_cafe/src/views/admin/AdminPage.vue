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
  name: "AdminPage",

  data() {
    return {
      sidebarOne: [
        {
          id: 1,
          icon: "mdi-account-outline",
          title: "회원 리스트",
          link: "/",
        },
        {
          id: 2,
          icon: "mdi-chart-areaspline",
          title: "회원 이용 현황",
          link: "/",
        },
        {
          id: 3,
          icon: "mdi-microphone",
          title: "공지사항",
          link: "/",
        },
      ],
      sidebarTwo: [
        {
          id: 1,
          icon: "mdi-home-outline",
          title: "카페 리스트",
          link: "/",
        },
        {
          id: 2,
          icon: "mdi-chart-bar",
          title: "카페 매출",
          link: "/",
        },
      ],
      infoOne: "회원 관리",
      infoTwo: "카페 관리",
    }
  },
}
</script>

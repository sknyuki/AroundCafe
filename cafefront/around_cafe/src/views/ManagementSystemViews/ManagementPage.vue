<template>
  <div class="container">
    <notice-list-page />

    <member-list-look-up />
  </div>
</template>
<script>
import NoticeListPage from "./noticeBoard/NoticeListPage.vue"
import MemberListLookUp from "@/components/ManagementSystem/MemberList/MemberListLookUp.vue"
export default {
  components: { NoticeListPage, MemberListLookUp },
  name: "ManagementPage",
}
</script>

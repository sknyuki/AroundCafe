<template>
  <AdminMemberList />
</template>
<script>
import AdminMemberList from "@/components/Admin/AdminMemberList.vue"
// import { mapActions, mapState } from "vuex"
export default {
  components: { AdminMemberList },
  name: "AdminMemberListPage",

  // computed: {
  //   ...mapState(["adminlist"]),
  // },
  // mounted() {
  //   this.fetchAdminMemberList()
  // },
  // methods: {
  //   ...mapActions(["fetchAdminMemberList"]),
  // },
}
</script>

<template>
  <AdminUsageMemberListForm />
</template>
<script>
import AdminUsageMemberListForm from "@/components/Admin/AdminUsageMemberListForm.vue"
// import { mapActions, mapState } from "vuex"
export default {
  components: { AdminUsageMemberListForm },
  name: "AdminUsageMemberListPage",

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

<template>
  <AdminMemberUsage />
</template>
<script>
import AdminMemberUsage from "@/components/Admin/AdminMemberUsage.vue"
// import { mapActions, mapState } from "vuex"
export default {
  components: { AdminMemberUsage },
  name: "AdminMemberUsagePage",

  //   computed: {
  //     ...mapState(["adminlist"]),
  //   },
  //   mounted() {
  //     this.fetchAdminMemberList()
  //   },
  //   methods: {
  //     ...mapActions(["fetchAdminMemberList"]),
  //   },
}
</script>

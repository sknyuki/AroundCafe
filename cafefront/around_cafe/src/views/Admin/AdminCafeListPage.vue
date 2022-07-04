<template>
  <AdminCafeList />
</template>
<script>
import AdminCafeList from "@/components/Admin/AdminCafeList.vue"
// import { mapActions, mapState } from "vuex"
export default {
  components: { AdminCafeList },
  name: "AdminCafeListPage",

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

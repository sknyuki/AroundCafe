<template>
  <AdminUsageGraphForm :memNo="memNo" :memNick="memNick" />
</template>
<script>
import AdminUsageGraphForm from "@/components/Admin/AdminUsageGraphForm.vue"

// import { mapActions, mapState } from "vuex"
export default {
  components: { AdminUsageGraphForm },
  name: "AdminUsageMemberPage",
  props: {
    memNo: { type: Number },
    memNick: { type: String },
  },
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

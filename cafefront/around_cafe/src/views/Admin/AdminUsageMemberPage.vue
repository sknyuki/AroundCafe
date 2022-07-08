<template>
  <AdminUsageMemberForm :memNo="memNo" :memNick="memNick" />
</template>
<script>
import AdminUsageMemberForm from "@/components/Admin/AdminUsageMemberForm.vue"

// import { mapActions, mapState } from "vuex"
export default {
  components: { AdminUsageMemberForm },
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

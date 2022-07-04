<template>
  <div>
    <AdminUserModify :user="user" />
  </div>
</template>
<script>
import AdminUserModify from "@/components/Admin/AdminUserModify"
import { mapState, mapActions } from "vuex"

export default {
  name: "AdminUserModifyPage",
  components: { AdminUserModify },
  computed: {
    ...mapState(["user"]),
  },
  mounted() {
    this.fetchUser()
  },
  methods: {
    ...mapActions(["fetchUser"]),
  },
}
</script>

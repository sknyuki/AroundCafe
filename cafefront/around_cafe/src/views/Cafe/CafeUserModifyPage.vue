<template>
  <div>
    <CafeUserModify :user="user" />
  </div>
</template>
<script>
import CafeUserModify from "@/components/Cafe/CafeUserModify.vue"
import { mapState, mapActions } from "vuex"

export default {
  name: "CafeModifyPage",
  components: { CafeUserModify },
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

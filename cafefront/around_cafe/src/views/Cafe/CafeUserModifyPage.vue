<template>
  <CafeUserModify />
</template>
<script>
import CafeUserModify from "@/components/Cafe/CafeUserModify.vue"
import { mapState, mapActions } from "vuex"

export default {
  components: { CafeUserModify },
  name: "CafeModifyPage",
  props: {
    cafeNo: {
      type: Number,
      required: true,
    },
    computed: {
      ...mapState(["user"]),
    },
    mounted() {
      this.fetchUser()
    },
    methods: {
      ...mapActions(["fetchUser"]),
    },
  },
}
</script>

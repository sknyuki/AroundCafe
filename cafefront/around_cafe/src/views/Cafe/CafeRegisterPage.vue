<template><CafeRegister /></template>
<script>
import CafeRegister from "@/components/Cafe/CafeRegister.vue"
import { mapState, mapActions } from "vuex"
export default {
  name: "CafeRegisterPage",
  components: { CafeRegister },
  computed: {
    ...mapState(["cafeBoard"]),
  },
  mounted() {
    this.fetchcafeBoard(2)
  },
  methods: {
    ...mapActions(["fetchcafeBoard"]),
  },
}
</script>

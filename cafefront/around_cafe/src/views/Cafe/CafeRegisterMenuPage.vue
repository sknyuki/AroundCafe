<template>
  <CafeMenuRegister @submit="onSubmitMenu" :menuLists="menuLists" />
</template>
<script>
import CafeMenuRegister from "@/components/Cafe/CafeRegisterMenu.vue"
import { mapState, mapActions } from "vuex"
export default {
  components: { CafeMenuRegister },
  name: "CafeRegisterMenuPage",
  computed: {
    ...mapState(["menuLists"]),
  },
  mounted() {
    this.fetchMenuLists(2)
  },
  methods: {
    ...mapActions(["fetchMenuLists"]),
    onSubmitMenu() {},
  },
}
</script>

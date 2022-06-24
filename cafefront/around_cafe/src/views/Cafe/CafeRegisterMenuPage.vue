<template>
  <CafeRegisterMenu :menuLists="menuLists" />
</template>
<script>
import CafeRegisterMenu from "@/components/Cafe/CafeRegisterMenu.vue"
import { mapState, mapActions } from "vuex"
export default {
  components: { CafeRegisterMenu },
  name: "CafeRegisterMenuPage",
  computed: {
    ...mapState(["menuLists"]),
  },
  mounted() {
    this.fetchMenuLists(2)
  },
  methods: {
    ...mapActions(["fetchMenuLists"]),
  },
}
</script>

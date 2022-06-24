<template>
  <CafeRegisterMenu :menuLists="menuLists" :cafeNo="cafeNo" />
</template>
<script>
import CafeRegisterMenu from "@/components/Cafe/CafeRegisterMenu.vue"
import { mapState, mapActions } from "vuex"
export default {
  components: { CafeRegisterMenu },
  name: "CafeRegisterMenuPage",
  props: {
    cafeNo: {
      type: Number,
      required: true,
    },
  },
  computed: {
    ...mapState(["user", "menuLists"]),
  },
  mounted() {
    this.fetchUser(), this.fetchMenuLists(this.user.memNo)
  },
  methods: {
    ...mapActions(["fetchUser"]),
    ...mapActions(["fetchMenuLists"]),
  },
}
</script>

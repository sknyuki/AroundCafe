<template>
  <AdminCafeSalesMenuList :cafeNo="cafeNo" />
</template>
<script>
import AdminCafeSalesMenuList from "@/components/Admin/AdminCafeSalesMenuList.vue"
export default {
  components: { AdminCafeSalesMenuList },
  name: "AdminCafeSalesMenuPage",
  props: {
    cafeNo: {
      type: String,
      required: true,
    },
  },
}
</script>
<style></style>

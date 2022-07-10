<template>
  <AdminCafeSalesDetailList :cafeNo="cafeNo" />
</template>
<script>
import AdminCafeSalesDetailList from "@/components/Admin/AdminCafeSalesDetailList.vue"
export default {
  name: "AdminCafeSalesDetailPage",
  components: { AdminCafeSalesDetailList },
  props: {
    cafeNo: {
      type: String,
      required: true,
    },
  },
}
</script>
<style lang=""></style>

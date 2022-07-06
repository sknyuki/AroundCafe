<template>
  <UserOrderHistoryDetail :paymentNo="paymentNo"/>
</template>

<script>
import UserOrderHistoryDetail from "@/components/User/UserOrderHistoryDetail";
export default {
  name: "UserOrderHistoryDetailPage",
  components: { UserOrderHistoryDetail },
  props : {
    paymentNo: { Type: Number, required: true}
  }
}
</script>

<style scoped>

</style>
<template>
  <OrderCafeSalesDetailList :findDate="findDate" />
</template>

<script>
import OrderCafeSalesDetailList from "@/components/Order/OrderCafeSalesDetailList.vue"
export default {
  components: { OrderCafeSalesDetailList },
  name: "OrderCafeSalesDetailPage",
  props: {
    findDate: {
      type: String,
      required: true,
    },
  },
}
</script>

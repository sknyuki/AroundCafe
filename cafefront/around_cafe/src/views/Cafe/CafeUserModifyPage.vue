<template>
  <div>
    <CafeUserModify />
  </div>
</template>
<script>
import CafeUserModify from "@/components/Cafe/CafeUserModify.vue"

export default {
  name: "CafeModifyPage",
  components: { CafeUserModify },
  data() {
    return {
      cafeNo: JSON.parse(localStorage.getItem("user")).cafeNo,
    }
  },
}
</script>

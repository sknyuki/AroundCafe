<template>
  <div>
    <CafeReviewList :reviews="reviews"/>
  </div>
</template>
<script>
import CafeReviewList from "@/components/Cafe/CafeReviewList.vue"


export default {
  components: { CafeReviewList },
  name: "CafeReviewListPage",
}
</script> 

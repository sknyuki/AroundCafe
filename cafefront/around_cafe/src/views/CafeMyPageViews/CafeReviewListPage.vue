<template>
  <div>
    <h2>카페 리뷰 리스트</h2>
    <CafeReviewListForm />
  </div>
</template>

<script>
import CafeReviewListForm from "@/components/MyPageComponents/CafeMypageComponents/CafeReviewListForm.vue"
import { mapState, mapActions } from "vue"
export default {
  components: { CafeReviewListForm },
  name: "CafeReviewListPage",
  computed: {
    ...mapState(["cafeReviewLists"]),
  },
  mounted() {
    this.fetchCafeReviewLists(2)
  },
  methods: {
    ...mapActions(["fetchCafeReviewLists"]),
  },
}
</script>

<style></style>

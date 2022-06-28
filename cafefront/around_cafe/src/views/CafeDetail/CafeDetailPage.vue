<template>
  <CafeDetailForm
    :cafeBoard="cafeBoard"
    :reviews="reviews"
    :myHelps="myHelps"
  />
</template>

<script>
import CafeDetailForm from "@/components/CafeDetail/CafeDetailForm.vue"
import { mapState, mapActions } from "vuex"

export default {
  components: { CafeDetailForm },
  name: "CafeDetailPage",
  props: {
    cafeNo: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      membNo: this.$store.state.user.memNo,
    }
  },
  computed: {
    ...mapState(["cafeBoard", "reviews", "myHelps"]),
  },
  mounted() {
    this.fetchcafeBoard(this.cafeNo)
    this.fetchReviewList(this.cafeNo)
    this.fetchMyHelpsList(this.membNo)
  },

  methods: {
    ...mapActions(["fetchcafeBoard"]),
    ...mapActions(["fetchReviewList", "fetchMyHelpsList"]),
  },
}
</script>

<template>
  <CafeDetailForm
    :cafeBoard="cafeBoard"
    :reviewList="reviewList"
    :cafeNo="cafeNo"
  />
</template>

<script>
import CafeDetailForm from "@/components/CafeDetail/CafeDetailForm.vue"
import { mapState, mapActions } from "vuex"
import axios from "axios"

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
      reviewList: [],
    }
  },
  computed: {
    ...mapState(["cafeBoard", "myHelps"]),
  },
  mounted() {
    this.fetchcafeBoard(this.cafeNo)
    this.fetchReviewList(this.cafeNo, this.membNo)
  },

  methods: {
    ...mapActions(["fetchcafeBoard"]),
    fetchReviewList(cafeNo, memNo) {
      if (memNo == null) {
        memNo = 0
      }
      axios
        .get(`http://localhost:7777/cafe/review/list/${cafeNo}/${memNo}`)
        .then((res) => {
          this.reviewList = res.data
        })
    },
  },
}
</script>

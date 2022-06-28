<template>
  <CafeCard :cafeBoards="cafeBoards" :myLikes="myLikes" />
</template>
<script>
import CafeCard from "@/components/Cafe/CafeCard.vue"
import { mapState, mapActions } from "vuex"

export default {
  components: { CafeCard },
  data() {
    return {
      membNo: JSON.parse(localStorage.getItem("user")).memNo,
    }
  },
  computed: {
    ...mapState(["cafeBoards", "myLikes"]),
  },
  mounted() {
    this.fetchcafeBoardList()
    this.fetchMyLikesList(this.membNo)
  },
  methods: {
    ...mapActions(["fetchcafeBoardList", "fetchMyLikesList"]),
  },
}
</script>

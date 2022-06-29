<template>
  <div v-if="!isLogin">
    <CafeCard :cafeBoards="cafeBoards" />
  </div>
  <div v-else>
    <CafeCard :cafeBoards="cafeBoards" :myLikes="myLikes" />
  </div>
</template>
<script>
import CafeCard from "@/components/Cafe/CafeCard.vue"
import { mapState, mapActions } from "vuex"

export default {
  components: { CafeCard },
  data() {
    return {
      membNo: JSON.parse(localStorage.getItem("user")).memNo,
      isLogin: false,
    }
  },
  computed: {
    ...mapState(["cafeBoards", "myLikes"]),
  },
  mounted() {
    this.fetchcafeBoardList()
    this.onLogin()
    //
  },
  methods: {
    ...mapActions(["fetchcafeBoardList", "fetchMyLikesList"]),
    onLogin() {
      if (this.membNo != null) {
        this.isLogin = true
        this.fetchMyLikesList(this.membNo)
      } else {
        this.onLogin = false
      }
    },
  },
}
</script>

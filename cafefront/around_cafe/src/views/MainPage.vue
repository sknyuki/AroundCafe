<template>
  <div v-if="!isLogin">
    <CafeCard :mainlist="mainlist" />
  </div>
  <div v-else>
    <CafeCard :mainlist="mainlist" :myLikes="myLikes" />
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
    ...mapState(["myLikes", "mainlist"]),
  },
  mounted() {
    // this.fetchcafeBoardList()
    this.fetchMainList()
    this.onLogin()

    //
  },
  methods: {
    ...mapActions(["fetchMyLikesList", "fetchMainList"]),
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

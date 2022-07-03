<template>
  <CafeCard :mainlist="mainlist" v-if="!isLogin" />

  <MainLoginPage v-else />
</template>
<script>
import CafeCard from "@/components/Cafe/CafeCard.vue"
import MainLoginPage from "@/views/MainLoginPage.vue"
import { mapState, mapActions } from "vuex"

export default {
  components: { CafeCard, MainLoginPage },
  data() {
    return {
      membNo: JSON.parse(localStorage.getItem("user")).memNo,
      isLogin: false,
    }
  },
  computed: {
    ...mapState(["mainlist"]),
  },
  mounted() {
    // this.fetchcafeBoardList()
    this.fetchMainList()
    this.onLogin()
    //
  },
  methods: {
    ...mapActions(["fetchMainList"]),
    onLogin() {
      if (this.membNo != null) {
        this.isLogin = true
        //  this.fetchMyLikesList(this.membNo)
      } else {
        this.isLogin = false
      }
    },
  },
}
</script>

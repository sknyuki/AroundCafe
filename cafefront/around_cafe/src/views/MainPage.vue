<template>
  <CafeCard />

  <!-- <MainLoginPage v-else /> -->
</template>
<script>
import CafeCard from "@/components/Cafe/CafeCard.vue"
// import MainLoginPage from "@/views/MainLoginPage.vue"

// import { mapState, mapActions } from "vuex"

export default {
  components: { CafeCard },
  name: "MainPage",

  // data() {
  //   return {
  //     membNo: JSON.parse(localStorage.getItem("user")).memNo,
  //     isLogin: false,
  //     isLoading: false,
  //   }
  // },

  // async mounted() {
  //   this.isLoading = true
  //   await this.fetchMainList()
  //   this.isLoading = false

  //   this.onLogin()
  //   //
  // },
  // methods: {
  //   ...mapActions(["fetchMainList"]),

  //   onLogin() {
  //     if (this.membNo != null) {
  //       this.isLogin = true
  //       //  this.fetchMyLikesList(this.membNo)
  //     } else {
  //       this.isLogin = false
  //     }
  //   },
  // },
}
</script>

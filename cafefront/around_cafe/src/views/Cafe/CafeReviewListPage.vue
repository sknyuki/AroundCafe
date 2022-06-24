<template>
  <div>
    <CafeReviewList :reviews="reviews" :myHelps="myHelps" />
  </div>
</template>
<script>
import CafeReviewList from "@/components/CafeReview/CafeReviewList.vue"
import axios from "axios"
import userService from "@/services/userService"
import tokenService from "@/services/tokenService"
import { mapActions, mapState } from "vuex"

export default {
  components: { CafeReviewList },
  name: "CafeReviewListPage",
  computed: {
    ...mapState(["reviews", "myHelps"]),
  },

  mounted() {
    this.fetchReviewList(1)
    this.fetchMyHelpsList(3)
  },

  methods: {
    ...mapActions(["fetchReviewList", "fetchMyHelpsList"]),

    onReviewDialog() {
      this.reviewDialog = true
    },

    closeDialog() {
      this.reviewDialog = false
    },
  },
  logout() {
    const url = "http://localhost:7777/auth/logout"
    const refreshToken = tokenService.getRefreshToken()
    const config = {
      headers: {
        refresh_token: `${refreshToken}`,
      },
    }
    axios
      .delete(url, config)
      .then(() => {
        userService.deleteUserInfo()
        tokenService.deleteTokens()
        alert("로그아웃")
      })

      .catch((err) => {
        alert(err)
      })
  },
}
</script>

<style lang="scss" scoped>
::v-deep .v-dialog {
  @include no-scrollbar;
  overflow-y: auto;
  overflow-x: hidden;
}
</style>

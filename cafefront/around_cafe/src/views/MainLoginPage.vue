<template>
  <CafeCardLogin :mainlist="mainlist" :myLikes="myLikes" />
</template>

<script>
import CafeCardLogin from "@/components/Cafe/CafeCardLogin.vue"
import { mapState, mapActions } from "vuex"

export default {
  components: { CafeCardLogin },
  data() {
    return {
      membNo: JSON.parse(localStorage.getItem("user")).memNo,
    }
  },
  computed: {
    ...mapState(["mainlist", "myLikes"]),
  },
  mounted() {
    this.fetchMainList()
    this.fetchMyLikesList(this.membNo)
  },
  methods: {
    ...mapActions(["fetchMainList", "fetchMyLikesList"]),
  },
}
</script>

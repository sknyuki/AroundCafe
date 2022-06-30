<template>
  <UserLike :cafeBoards="cafeBoards" :myLikes="myLikes" />
</template>
<script>
import UserLike from "@/components/User/UserLike.vue"
import { mapState, mapActions } from "vuex"
export default {
  components: { UserLike },
  name: "UserLikePage",

  data() {
    return {
      membNo: JSON.parse(localStorage.getItem("user")).memNo,
    }
  },

  computed: {
    ...mapState(["cafeBoards", "myLikes"]),
  },
  async mounted() {
    await this.fetchcafeBoardList()
    await this.fetchMyLikesList(this.membNo)
  },
  methods: {
    ...mapActions(["fetchcafeBoardList", "fetchMyLikesList"]),
  },
}
</script>

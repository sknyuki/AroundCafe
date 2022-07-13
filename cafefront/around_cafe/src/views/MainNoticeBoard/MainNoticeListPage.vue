<template>
  <div class="container">
    <v-row justify="center">
      <notice-board-list :noticeBoards="noticeBoards" />
      <!-- <div v-if="noticeBoards.length == 0"><p>등록된 공지사항이 없습니다.</p></div>
      <div v-else><notice-board-list: noticeBoards="noticeBoards" /></div>  -->
    </v-row>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex"
import NoticeBoardList from "@/components/MainNoticeBoard/NoticeBoardList.vue"
export default {
  name: "MainNoticeListPage",
  components: {
    NoticeBoardList,
  },

  computed: {
    ...mapState(["noticeBoards"]),
  },
  mounted() {
    this.fetchNoticeBoardList()
  },

  methods: {
    ...mapActions(["fetchNoticeBoardList"]),
  },
}
</script>
<style scoped>
.writeBtn {
  margin-top: 40px;
  left: 90%;
  height: 40px;
  width: 40px;
}
</style>

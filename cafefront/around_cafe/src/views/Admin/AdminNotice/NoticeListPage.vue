<template>
  <div>
    <v-row justify="center">
      <notice-board-list :noticeBoards="noticeBoards" @submit="onSubmit" />
      <!-- <div v-if="noticeBoards.length == 0"><p>공지사항을 등록해주세요</p></div>
      <div v-else><notice-board-list: noticeBoards="noticeBoards" /></div> -->
    </v-row>
  </div>
</template>

<script>
import axios from "axios"
import { mapActions, mapState } from "vuex"
import NoticeBoardList from "@/components/Admin/AdminNoticeComponents/NoticeBoardList.vue"
export default {
  name: "NoticeListPage",
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

    onSubmit(payload) {
      const { title, content, writer } = payload
      axios
        .post("http://localhost:7777/noticeBoard/register", {
          title,
          content,
          writer,
        })
        .then(() => {
          alert("Successfully submitted")
          this.$router.go()
        })

        .catch(() => {
          alert("오류 발생")
        })
    },
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

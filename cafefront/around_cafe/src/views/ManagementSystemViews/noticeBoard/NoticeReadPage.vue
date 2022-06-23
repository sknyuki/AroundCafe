<template>
  <v-container>
    <v-row>
      <notice-read-form v-if="noticeBoard" :noticeBoard="noticeBoard" />
    </v-row>
  </v-container>
</template>

<script>
import { mapActions, mapState } from "vuex"
import NoticeReadForm1 from "@/components/ManagementSystem/noticeBoard/NoticeReadForm.vue"
export default {
  name: "NoticeReadPage",
  components: {
    NoticeReadForm,
    NoticeReadForm1,
  },
  props: {
    boardNo: {
      type: String,
      required: true,
    },
  },
  computed: {
    ...mapState(["noticeBoard"]),
  },
  created() {
    this.fetchNoticeBoard(this.boardNo).catch(() => {
      alert("게시물 요청 실패")
      this.$router.push()
    })
  },
  methods: {
    ...mapActions(["fetchNoticeBoard"]),
  },
}
</script>

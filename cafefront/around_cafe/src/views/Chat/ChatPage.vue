<template>
  <div>
    <ChatForm :qnaLists="qnaLists" />
  </div>
</template>

<script>
import ChatForm from "@/components/Chat/ChatForm.vue"
import { mapActions, mapState } from "vuex"

export default {
  components: { ChatForm },
  name: "ChatPage",
  data() {
    return {
      membNo: JSON.parse(localStorage.getItem("user")).memNo,
      role: JSON.parse(localStorage.getItem("user")).role,
    }
  },
  computed: {
    ...mapState(["qnaLists"]),
  },
  mounted() {
    this.fetchQnALists(this.membNo)
  },
  methods: {
    ...mapActions(["fetchQnALists"]),
  },
}
</script>

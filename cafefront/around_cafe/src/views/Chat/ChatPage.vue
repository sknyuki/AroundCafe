<template>
  <ChatForm :qnaLists="qnaLists" />
</template>

<script>
import ChatForm from "@/components/Chat/ChatForm.vue"
import { mapActions, mapState } from "vuex"

export default {
  components: { ChatForm },
  name: "ChatPage",
  computed: {
    ...mapState(["qnaLists"]),
  },
  mounted() {
    this.fetchQnALists(1)
  },
  methods: {
    ...mapActions(["fetchQnALists"]),
  },
}
</script>

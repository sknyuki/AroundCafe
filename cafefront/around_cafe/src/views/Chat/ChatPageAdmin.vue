<template>
  <div>
    <ChatFormAdmin :adminQnaLists="adminQnaLists" />
  </div>
</template>

<script>
import ChatFormAdmin from "@/components/Chat/ChatFormAdmin.vue"
import { mapActions, mapState } from "vuex"

export default {
  components: { ChatFormAdmin },
  name: "ChatPageAdmin",
  data() {
    return {
      membNo: JSON.parse(localStorage.getItem("user")).memNo,
      role: JSON.parse(localStorage.getItem("user")).role,
    }
  },
  computed: {
    ...mapState(["adminQnaLists"]),
  },
  mounted() {
    this.fetchAdminQnALists(this.membNo)
  },
  methods: {
    ...mapActions(["fetchAdminQnALists"]),
  },
}
</script>

<template>
  <AccountPassword />
</template>
<script>
import AccountPassword from "@/components/Account/AccountPassword.vue"
export default {
  components: { AccountPassword },
  name: "AccountPasswordPage",
  methods: {
    onSubmit(payload) {
      const {email} = payload
    }
  }
}
</script>

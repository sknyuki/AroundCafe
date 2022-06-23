<template>
  <AccountModifyPassword :email="email" />
</template>

<script>
import AccountModifyPassword from "@/components/Account/AccountModifyPassword"

export default {
  components: { AccountModifyPassword },
  name: "AccountModifyPasswordPage",
  props: {
    email: { type: String, required: true },
  },
}
</script>

<style scoped></style>

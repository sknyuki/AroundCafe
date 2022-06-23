<template>
  <div class="modify-pw">
    <header class="sign-header">
      <h2>로고</h2>
    </header>

    <div class="sign-tab">
      <ValidationObserver v-slot="{ invalid }">
        <form @submit.prevent="onSubmit">
          <div class="sign-form">
            <div class="account-input">
              <ValidationProvider
                class="sign-validation"
                tag="div"
                name="password"
                type="password"
                v-slot="{ errors }"
                :rules="{ memCheckPw }"
              >
                <input
                  v-model="password"
                  class="form-input input-48"
                  type="password"
                  placeholder="새 비밀번호"
                  required
                />
                <div class="errmsg" aria-live="polite">
                  {{ errors[0] }}
                </div>
              </ValidationProvider>
            </div>
            <div class="account-input">
              <ValidationProvider
                class="sign-validation"
                tag="div"
                v-slot="{ errors }"
                name="memCheckPw"
                :rules="{ confirmed: 'password' }"
              >
                <input
                  v-model="memCheckPw"
                  class="form-input input-48"
                  type="password"
                  placeholder="새 비밀번호 확인"
                  required
                />
                <div class="errmsg" aria-live="polite">
                  {{ errors[0] }}
                </div>
              </ValidationProvider>
            </div>
          </div>
          <v-btn :disabled="invalid" class="sign-button-vuti" type="submit">
            비밀번호 변경하기
          </v-btn>
        </form>
      </ValidationObserver>
    </div>
  </div>
</template>
<script>
export default {
  name: "ModifyPassword",
  props: {
    email: { type: String, required: true },
  },
  data() {
    return {
      password: "",
      memCheckPw: "",
    }
  },
  methods: {
    onSubmit() {
      const data = {
        email: this.email,
        password: this.password,
      }
      this.$emit("submit", data)
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/account/sign";
@import "~@/assets/scss/components/account/modify-pw";
</style>

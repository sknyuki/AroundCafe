<template>
  <v-container>
    <v-form @submit.prevent="onSubmit">
      <table style="text-align: center; margin-right: 50px; margin-left: 50px">
        <div class="text-h4 font-weight-black mb-10">공지사항 등록</div>
        <div>
          <div>
            <tr>
              <td>
                <validation-provider
                  v-slot="{ errors }"
                  name="제목"
                  :rules="{ required: true }"
                >
                  <v-text-field
                    style="width: 700px"
                    v-model="title"
                    label="제목"
                    clearable
                    outlined
                    color="grey"
                    :error-messages="errors"
                  />
                </validation-provider>
              </td>
            </tr>

            <tr>
              <td>
                <v-text-field
                  style="width: 700px"
                  v-model="this.writer"
                  label="작성자"
                  clearable
                  disabled
                  outlined
                  color="grey"
                />
              </td>
            </tr>
          </div>
        </div>

        <tr>
          <td>
            <validation-provider
              v-slot="{ errors }"
              name="본문"
              :rules="{ required: true }"
            >
              <v-textarea
                style="width: 700px"
                v-model="content"
                label="본문"
                clearable
                auto-grow
                outlined
                color="grey"
                rows="10"
                :error-messages="errors"
              />
            </validation-provider>
          </td>
        </tr>
        <v-btn @click="cancel" class="cancleBtn" color="black" dark>
          돌아가기</v-btn
        >
        <v-btn type="submit" class="writeBtn" color="black" dark>submit</v-btn>
      </table>
    </v-form>
  </v-container>
</template>

<script>
export default {
  name: "NoticeRegisterForm",
  data() {
    return {
      title: "",
      content: "",
      writer: JSON.parse(localStorage.getItem("user")).nickname,
    }
  },

  methods: {
    onSubmit() {
      const { title, content, writer } = this
      this.$emit("submit", { title, content, writer })
      console.log(this.writer)
    },
    cancel() {
      this.$router.push("/ManagementPage")
    },
  },
}
</script>

<style scoped>
.label {
  font-size: 17pt;
  margin-right: 5%;
  text-align: center;
  font-family: "Poiret One", cursive;
}
table {
  padding: 5% 10% 5% 10%;
  margin-left: auto;
  margin-right: auto;
}

.cancleBtn {
  position: relative;
  margin-top: 1%;
}
.writeBtn {
  margin-top: 1%;
  margin-left: 210px;
}
</style>

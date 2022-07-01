<template>
  <div>
    <v-btn @click="registerQna">문의하기</v-btn>

    <form @submit.prevent="onSubmit">
      <v-dialog
        v-model="dialog"
        max-width="700"
        max-height="900"
        class="checkDialog"
      >
        <v-card>
          <v-card-title>문의사항 등록하기</v-card-title>
          <v-card-text
            >문의유형
            <v-select
              class="selectCake"
              v-model="findQna"
              :items="selectQna"
              label="===선택하기==="
              style="width: 200px"
            ></v-select>
          </v-card-text>
          <v-container>
            <v-row style="padding: 0 0 0 5%">
              <v-col cols="12" sm="6">
                <v-textarea
                  label="문의사항 내용 작성"
                  v-model="content"
                  style="width: 600px"
                />
              </v-col>
            </v-row>
            <v-row style="padding: 0 0 0 5%">
              <v-col cols="12" sm="6">
                <input
                  type="file"
                  id="files1"
                  ref="files1"
                  multiple
                  v-on:change="handleFileUpload()"
                />
              </v-col>
            </v-row>
            <v-row justify="center">
              <v-col cols="12" sm="2">
                <br />
                <v-btn @click="onSubmit">등록하기</v-btn>
              </v-col>
            </v-row>
          </v-container>
        </v-card>
      </v-dialog>
    </form>
  </div>
</template>
<script>
export default {
  name: "CafeQnARegister",
  data() {
    return {
      received_no: 2,
      content: "",
      files1: "",
      response: "",
      dialog: false,
      registerNo: "",
      selectQna: ["상품 문의", "픽업 관련", "환불 문의"],
      findQna: "",
    }
  },
  methods: {
    handleFileUpload() {
      this.files1 = this.$refs.files1.files
    },
    onSubmit() {
      const { content, findQna, files1 } = this
      this.$emit("submit", { content, findQna, files1 })
    },
    registerQna() {
      this.dialog = true
      this.registerNo = this.received_no
      //카페 사이트에서 문의하기를 클릭했을때 다이어로그가 나온다.
      //추가로 ui적으로 필요한 부분 -> 카페 이름 적기
    },
  },
}
</script>

<style scoped>
.registerForm {
  padding: 5%;
}

.checkDialog {
  max-height: 500;
}
</style>

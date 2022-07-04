<template>
  <div class="cafe-register">
    <img-box class="mb-10" />
    <div class="container">
      <div class="row">
        <div class="col-sm-4 col-md-3 col-lg-3">
          <AdminSidebar />
        </div>
        <div class="col-sm-4 col-md-9 col-lg-9">
          <div class="member-list">
            <header class="member-list-header"><h2>공지사항</h2></header>
            <v-form @submit.prevent="onSubmit">
              <table>
                <v-row justify="center">
                  <v-col cols="2" class="label mt-5 mr-3">Title</v-col>
                  <v-col>
                    <v-text-field color="grey" type="text" v-model="title" />
                  </v-col>
                </v-row>

                <v-row>
                  <v-col cols="12">
                    <v-textarea
                      cols="80"
                      rows="20"
                      outlined
                      color="grey"
                      style="white-space: pre-line"
                      class="mt-3"
                      v-model="content"
                    >
                    </v-textarea>
                  </v-col>
                </v-row>

                <v-row wrap>
                  <router-link
                    :to="{
                      name: 'NoticeReadPage',
                      params: { boardNo: noticeBoard.boardNo.toString() },
                    }"
                    style="text-decoration: none"
                    ><v-btn color="#c2c1c0" text>
                      <v-icon class="deleteIcon">
                        mdi-keyboard-backspace
                      </v-icon></v-btn
                    ></router-link
                  >
                  <v-btn type="submit" class="submitBtn" color="#4d71db">
                    <v-icon class="submitIcon" color="white">
                      mdi-file-check
                    </v-icon></v-btn
                  >
                </v-row>
              </table>
            </v-form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import AdminSidebar from "@/components/Admin/AdminSidebar.vue"
import ImgBox from "@/components/ImgBox.vue"
export default {
  name: "NoticeModifyForm",
  components: { ImgBox, AdminSidebar },
  props: {
    noticeBoard: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      title: "",
      content: "",
    }
  },
  methods: {
    onSubmit() {
      if (this.title == "" || this.content == "") {
        alert("제목과 게시글을 작성하여 주세요")
      } else {
        const { title, content } = this
        this.$emit("submit", { title, content })
      }
    },
  },
  created() {
    this.title = this.noticeBoard.title
    this.content = this.noticeBoard.content
  },
}
</script>

<style scoped>
.label {
  font-size: 20pt;
  margin-right: 5%;
  text-align: center;
}
table {
  padding: 5% 10% 5% 10%;
  margin-left: auto;
  margin-right: auto;
}

.v-text-field,
.v-textarea,
#files {
  font-family: "Noto Sans KR", sans-serif;
}
.submitBtn {
  margin-left: 250px;
}
</style>

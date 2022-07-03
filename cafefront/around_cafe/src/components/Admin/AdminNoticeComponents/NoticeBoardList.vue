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
            <v-card>
              <v-card-title>
                <v-dialog v-model="dialog" max-width="750" max-heigth="1000">
                  <template v-slot:activator="{ on }">
                    <v-btn v-on="on"> 작성하기</v-btn>
                  </template>
                  <v-card class="card">
                    <v-form class="table2" @submit.prevent="onSubmit">
                      <table class="table">
                        <div class="text-h4 font-weight-black mt-20">
                          공지사항 등록
                        </div>
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
                        <v-btn @click="cancel" class="btn-indigo">
                          돌아가기</v-btn
                        >
                        <v-btn
                          type="submit"
                          class="btn-indigo"
                          style="margin-top: 20px"
                          >submit</v-btn
                        >
                      </table>
                    </v-form>
                  </v-card>
                </v-dialog>
                <v-spacer></v-spacer>

                <v-text-field
                  v-model="search"
                  append-icon="mdi-magnify"
                  label="Search"
                  single-line
                  hide-details
                  class="shrink pr-15"
                  color="#e3c832"
                >
                </v-text-field>
              </v-card-title>

              <v-row>
                <v-col class="together">
                  <br />
                  <div class="total">
                    Total &nbsp;&nbsp;
                    <span class="count"> {{ noticeBoards.length }}</span>
                  </div>
                </v-col>
              </v-row>

              <v-data-table
                :headers="headers"
                :items="noticeBoards"
                :search="search"
                :key="noticeBoards.boardNo"
                class="elevation-1"
              >
                <template v-slot:[`item.title`]="{ item }">
                  <router-link
                    :to="{
                      name: 'NoticeReadPage',
                      params: { boardNo: item.boardNo.toString() },
                    }"
                  >
                    {{ item.title }}
                  </router-link>
                </template>

                >
              </v-data-table>
            </v-card>
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
  name: "NoticeBoardList",
  components: { ImgBox, AdminSidebar },
  props: {
    noticeBoards: {
      type: Array,
    },
  },

  data() {
    return {
      dialog: false,
      search: "",
      title: "",
      content: "",
      writer: JSON.parse(localStorage.getItem("user")).nickname,
      headers: [
        {
          text: " No",
          align: "center",
          value: "boardNo",
          width: "10%",
          class: "grey lighten-4",
        },
        {
          text: "제목",
          align: "center",
          value: "title",
          width: "45%",
          class: "grey lighten-4",
        },
        {
          text: "작성자",
          align: "center",
          value: "writer",
          width: "10%",
          class: "grey lighten-4",
        },
        {
          text: "조회수",
          align: "center",
          value: "viewCount",
          width: "15%",
          class: "grey lighten-4",
        },
        {
          text: "작성일",
          align: "center",
          value: "regDate",
          width: "20%",
          class: "grey lighten-4",
        },
      ],
      session: "",
      loginAuth: "",
    }
  },
  methods: {
    onSubmit() {
      const { title, content, writer } = this
      this.$emit("submit", { title, content, writer })
      console.log(this.writer)
    },
    cancel() {
      this.dialog = false
    },
  },
}
</script>

<style scoped>
/* @import url("https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Poiret+One&family=Sunflower:wght@300&display=swap"); */

.table {
  cursor: pointer;
  font-family: "Ubuntu", sans-serif;
}

.v-data-table::v-deep th {
  font-size: 15px !important;
  font-family: "Sunflower", sans-serif;
}

.total {
  font-size: 13pt;
}
.count {
  font-size: 13pt;
}
.together {
  margin-left: 700px;
  margin-bottom: 15px;
}
.label {
  font-size: 17pt;
  margin-right: 5%;
  text-align: center;
  font-family: "Poiret One";
}

.writeBtn {
  margin-left: 210px;
}

.table {
  margin-top: 20px;
  margin-bottom: 20px;
  text-align: center;
  margin-left: 15px;
}
.table2 {
  text-align: center;
  margin-left: 10px;
  min-height: 600px;
  display: flex;
}
</style>

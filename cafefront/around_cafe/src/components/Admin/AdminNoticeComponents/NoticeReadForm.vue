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
            <v-card class="table" style="width: 1500px">
              <v-row justify="center">
                <div style="display: flex">
                  <h2>{{ noticeBoard.title }}</h2>
                </div>
              </v-row>
              <br />
              <v-divider></v-divider>
              <div style="display: flex; height: 50px" class="mt-3 mb-3">
                <div>
                  <v-text-field
                    rounded
                    style="width: 150px"
                    readonly
                    :value="noticeBoard.writer"
                  />
                </div>

                <div>
                  <v-text-field
                    class="date"
                    rounded
                    readonly
                    :value="noticeBoard.regDate"
                  />
                </div>

                <div>
                  <div class="test mt-3">
                    <v-icon> mdi-account-eye</v-icon>
                    <span>{{ noticeBoard.viewCount }}</span>
                  </div>
                </div>

                <div>
                  <router-link
                    :to="{
                      name: 'NoticeModifyPage',
                      params: { boardNo: noticeBoard.boardNo.toString() },
                    }"
                    style="text-decoration: none"
                  >
                    <v-icon class="edit" color="#4d71db">
                      mdi-file-edit
                    </v-icon>
                  </router-link>
                </div>

                <div>
                  <v-dialog v-model="dialogDelete" width="460">
                    <template v-slot:activator="{ on, attrs }">
                      <v-btn
                        text
                        class="deleteBtn"
                        v-bind="attrs"
                        v-on="on"
                        color="red"
                      >
                        <v-icon class="deleteIcon">
                          mdi-trash-can-outline
                        </v-icon>
                      </v-btn>
                    </template>

                    <v-card>
                      <v-card-text class="pt-5">
                        <h3>Are you sure to delete your files?</h3>
                      </v-card-text>

                      <v-card-actions>
                        <v-spacer></v-spacer>

                        <v-btn @click="onDelete" text> Delete </v-btn>

                        <v-btn color="grey" text @click="dialogDelete = false"
                          >Cancle</v-btn
                        >
                      </v-card-actions>
                    </v-card>
                  </v-dialog>
                </div>
              </div>

              <v-row>
                <v-col cols="12">
                  <v-textarea
                    cols="80"
                    rows="14"
                    outlined
                    color="grey"
                    class="mt-3"
                    readonly
                    :value="noticeBoard.content"
                  >
                  </v-textarea>
                </v-col>
              </v-row>

              <v-row>
                <v-btn @click="goList" class="btn-indigo btn-48">
                  목록으로
                </v-btn>
              </v-row>
            </v-card>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import AdminSidebar from "@/components/Admin/AdminSidebar.vue"
import axios from "axios"
import ImgBox from "@/components/ImgBox.vue"
export default {
  name: "NoticeReadForm",
  components: { ImgBox, AdminSidebar },
  props: {
    noticeBoard: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      session: "",
      userId: "",
      dialogDelete: false,
    }
  },
  created() {
    if (this.$store.state.session != null) {
      this.loginAuth = this.$store.state.auth.auth
      this.userId = this.$store.state.session.userId
    }
  },
  methods: {
    goList() {
      this.$router.push("/admin/noticelist")
      this.$router.go()
    },
    onDelete() {
      const { boardNo } = this.noticeBoard
      axios
        .delete(`http://localhost:7777/noticeBoard/${boardNo}`)
        .then(() => {
          alert("Done! Files deleted successfully")
          this.$router.push("/admin/noticelist")
        })
        .catch(() => {
          alert("삭제 실패")
        })
    },
  },
}
</script>

<style lang="scss" scoped>
.label {
  font-family: "Ubuntu", sans-serif;
  font-size: 17pt;
  margin-right: 5%;
  text-align: center;
  padding-top: 25px;
}
.table {
  padding: 5% 10% 5% 10%;
  margin-left: auto;
  margin-right: auto;
}
.modifyBtn {
  color: rgba(214, 86, 103, 0.596);
}
.v-text-field,
.v-textarea {
  font-family: "Ubuntu", sans-serif;
}
.edit {
  margin-top: 15px;
  margin-left: 400px;
}
.deleteBtn {
  margin-top: 9px;
}
.listIcon {
  margin-bottom: 3px;
}
.listBtn {
  margin-bottom: 30px;
}
.test {
  position: relative;
  span {
    position: absolute;
    top: 2px;
    right: -15px;
  }
}
</style>

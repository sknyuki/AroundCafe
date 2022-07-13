<template>
  <v-container>
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
          <div class="test mt-4">
            <v-icon> mdi-account-eye</v-icon>
            <span>{{ noticeBoard.viewCount }}</span>
          </div>
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
        <v-btn @click="goList" class="btn-indigo btn-48" dark> 목록으로 </v-btn>
      </v-row>
    </v-card>
  </v-container>
</template>

<script>
export default {
  name: "NoticeReadForm",
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
  methods: {
    goList() {
      this.$router.go(-1)
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
  width: 100%;
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

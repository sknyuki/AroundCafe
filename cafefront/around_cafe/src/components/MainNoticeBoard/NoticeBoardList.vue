<template>
  <v-card class="cardStyle">
    <v-card-title>
      <h2>공지사항</h2>

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
            name: 'MainNoticeReadPage',
            params: { boardNo: item.boardNo.toString() },
          }"
        >
          {{ item.title }}
        </router-link>
      </template>
      ></v-data-table
    >
  </v-card>
</template>

<script>
export default {
  name: "NoticeBoardList",
  props: {
    noticeBoards: {
      type: Array,
    },
  },

  data() {
    return {
      search: "",
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
}
</script>

<style scoped>
/* @import url("https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Poiret+One&family=Sunflower:wght@300&display=swap"); */

.table {
  cursor: pointer;
  font-family: "Ubuntu", sans-serif;
}

.cardStyle {
  font-family: "Ubuntu", sans-serif;
  margin: 5% 5% 5% 5%;
  width: 1100px;
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
  margin-left: 920px;
  margin-bottom: 15px;
}
</style>

<template>
  <div class="container">
    <!--  <h3>날짜 지정</h3>

    <v-menu
      v-model="menu"
      ref="menu"
      :close-on-content-click="false"
      :return-value.sync="dates"
      transition="scale-transition"
      offset-y
      min-width="auto"
    >

       달력 date 설정타입
        <template v-slot:activator="{ on }">
        <v-text-field
          style="width: 250px"
          color="secondary"
          label="범위 설정"
          v-model="dates"
          v-on="on"
          append-icon="mdi-calendar"
        ></v-text-field>
      </template>
      <v-date-picker
        color="secondary"
        v-model="dates"
        range
        no-title
        scrollable
      >
        <v-btn text color="secondary" @click="menu = false"> Cancel </v-btn>
        <v-spacer></v-spacer>
        <v-btn text color="secondary" @click="getGraph(dates)"> OK </v-btn>
      </v-date-picker> 
    </v-menu>-->
    <v-row align="center">
      <v-col cols="12" sm="6">
        <v-subheader v-text="'범위를 선택해주세요'"></v-subheader>
      </v-col>

      <v-col cols="12" sm="6">
        <v-select
          v-model="dateRange"
          :items="states"
          label="Select"
          chips
          hint="범위를 선택해주세요"
        ></v-select>
      </v-col>
    </v-row>
    <v-row align="center" justify="center">
      <v-btn
        width="200"
        class="btn-indigo btn-32"
        style="color: white"
        @click="getGraph(dateRange)"
        >검색</v-btn
      >
    </v-row>
    <br />
    <v-divider></v-divider>
    <br />
  </div>
</template>

<script>
import axios from "axios"
export default {
  name: "AdminUsageGraphForm",
  props: { memNo: { type: Number }, memNick: { type: String } },
  data() {
    return {
      states: ["이번달이력", "세달간이력", "일년이력"],
      dateRange: [],
      usageGraphList: [],
      menu: false,
      date1: null,
      date2: null,
    }
  },
  methods: {
    getGraph(dateRange) {
      console.log(dateRange)
      var date = new Date()
      if (dateRange == "이번달이력") {
        this.date2 = new Date(date.getFullYear(), date.getMonth() + 1, 0)
        this.date1 = new Date(date.getFullYear(), date.getMonth(), 1)
      } else if (dateRange == "세달간이력") {
        this.date2 = new Date(date.getFullYear(), date.getMonth() + 1, 0)
        this.date1 = new Date(date.getFullYear(), date.getMonth() - 3, 1)
      } else {
        this.date2 = new Date(date.getFullYear(), date.getMonth() + 1, 0)
        this.date1 = new Date(date.getFullYear(), date.getMonth() - 12, 1)
      }
      // this.date1 = this.dates[0] + "T00:00:00.000+00:00"
      // this.date2 = this.dates[1] + "T00:00:00.000+00:00"
      this.date1 = this.date1 * 1
      this.date2 = this.date2 * 1
      const { memNo, date1, date2 } = this
      console.log(memNo)
      console.log(date1)
      console.log(date2)
      axios
        .post(`http://localhost:7777/payment/list/list`, {
          memNo,
          date1,
          date2,
        })
        .then((res) => {
          this.usageGraphList = res.data
          console.log(this.usageGraphList)
        })
      this.menu = false
    },
  },
}
</script>

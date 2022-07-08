<template>
  <div class="container">
    <h3>날짜 지정</h3>

    <v-menu
      v-model="menu"
      ref="menu"
      :close-on-content-click="false"
      :return-value.sync="dates"
      transition="scale-transition"
      offset-y
      min-width="auto"
    >
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
    </v-menu>
  </div>
</template>

<script>
import axios from "axios"
export default {
  name: "AdminUsageGraphForm",
  props: { memNo: { type: Number }, memNick: { type: String } },
  data() {
    return {
      dates: [],
      usageGraphList: [],
      menu: false,
      date1: null,
      date2: null,
    }
  },
  methods: {
    getGraph(dates) {
      console.log(dates)

      this.date1 = this.dates[0] + "T00:00:00.000+00:00"

      this.date2 = this.dates[1] + "T00:00:00.000+00:00"

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

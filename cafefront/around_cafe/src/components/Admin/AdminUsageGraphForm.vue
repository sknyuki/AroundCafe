<template>
  <div class="container">
    <div class="row">
      <div class="col-sm-12">
        <ImgBox />
      </div>
      <div class="col-sm-4 col-md-3 col-lg-3">
        <AdminSidebar />
      </div>
      <div class="col-sm-4 col-md-9 col-lg-9">
        <div class="member-list">
          <header class="member-list-header">
            <h2>회원 이용 현황</h2>
          </header>
          <br />
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
          <div>
            <!--차트가 그려질 부분-->
            <canvas id="myChart"></canvas>
          </div>
          <br />
          <AdminUsageMemberForm
            :usageGraphList="usageGraphList"
            :memNick="memNick"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import AdminSidebar from "@/components/Admin/AdminSidebar.vue"
import ImgBox from "@/components/ImgBox.vue"
import AdminUsageMemberForm from "@/components/Admin/AdminUsageMemberForm.vue"
import {
  Chart,
  BarElement,
  BarController,
  LinearScale,
  CategoryScale,
} from "chart.js"
Chart.register(
  BarElement,
  BarController,
  BarController,
  LinearScale,
  CategoryScale
)
import axios from "axios"
export default {
  name: "AdminUsageGraphForm",
  components: { ImgBox, AdminSidebar, AdminUsageMemberForm },
  props: { memNo: { type: Number }, memNick: { type: String } },
  data() {
    return {
      states: ["이번달이력", "세달간이력", "일년이력"],
      dateRange: [],
      usageGraphList: [],
      menu: false,
      date1: null,
      date2: null,
      myChart: null,
      dayRange1: "",
      dayRange2: "",
      dayRange3: "",
      dayRange4: "",
      dayRange5: "",
      dayRange6: "",
      dayRange7: "",
      dayRange8: "",
      dayRange9: "",
      dayRange10: "",
      dayRange11: "",
      dayRange12: "",
      value1: 0,
      value2: 0,
      value3: 0,
      value4: 0,
      value5: 0,
      value6: 0,
      value7: 0,
      value8: 0,
      value9: 0,
      value10: 0,
      value11: 0,
      value12: 0,
    }
  },
  methods: {
    yyyyMMdd(value) {
      if (value == "") return ""

      var js_date = new Date(value)

      var year = js_date.getFullYear()
      var month = js_date.getMonth() + 1
      var day = js_date.getDate()

      if (month < 10) {
        month = "0" + month
      }

      if (day < 10) {
        day = "0" + day
      }

      return year + "-" + month + "-" + day
    },
    getGraph(dateRange) {
      var date = new Date()
      if (dateRange == "이번달이력") {
        this.date2 = new Date(date.getFullYear(), date.getMonth() + 1, 0)
        this.date1 = new Date(date.getFullYear(), date.getMonth(), 1)
        this.dayRange1 = new Date(date.getFullYear(), date.getMonth(), 10)
        this.dayRange2 = new Date(date.getFullYear(), date.getMonth(), 20)
        this.dayRange3 = this.date2
      } else if (dateRange == "세달간이력") {
        this.date2 = new Date(date.getFullYear(), date.getMonth() + 1, 0)
        this.date1 = new Date(date.getFullYear(), date.getMonth() - 2, 1)
        this.dayRange1 = new Date(date.getFullYear(), date.getMonth() - 1, 1)
        this.dayRange2 = new Date(date.getFullYear(), date.getMonth(), 1)
        this.dayRange3 = this.date2
      } else {
        this.date2 = new Date(date.getFullYear(), date.getMonth() + 1, 0)
        this.date1 = new Date(date.getFullYear(), date.getMonth() - 11, 1)
        this.dayRange1 = new Date(date.getFullYear(), date.getMonth() - 10, 1)
        this.dayRange2 = new Date(date.getFullYear(), date.getMonth() - 9, 1)
        this.dayRange3 = new Date(date.getFullYear(), date.getMonth() - 8, 1)
        this.dayRange4 = new Date(date.getFullYear(), date.getMonth() - 7, 1)
        this.dayRange5 = new Date(date.getFullYear(), date.getMonth() - 6, 1)
        this.dayRange6 = new Date(date.getFullYear(), date.getMonth() - 5, 1)
        this.dayRange7 = new Date(date.getFullYear(), date.getMonth() - 4, 1)
        this.dayRange8 = new Date(date.getFullYear(), date.getMonth() - 3, 1)
        this.dayRange9 = new Date(date.getFullYear(), date.getMonth() - 2, 1)
        this.dayRange10 = new Date(date.getFullYear(), date.getMonth() - 1, 1)
        this.dayRange11 = new Date(date.getFullYear(), date.getMonth(), 1)
        this.dayRange12 = this.date2
      }
      // this.date1 = this.dates[0] + "T00:00:00.000+00:00"
      // this.date2 = this.dates[1] + "T00:00:00.000+00:00"
      // console.log(this.date1)
      // console.log(this.date2)
      this.date1 = this.date1 * 1
      this.date2 = this.date2 * 1

      const { memNo, date1, date2 } = this
      // console.log(memNo)
      // console.log(date1)
      // console.log(date2)
      axios
        .post(`http://localhost:7777/payment/list/list`, {
          memNo,
          date1,
          date2,
        })
        .then((res) => {
          this.usageGraphList = res.data
          // console.log(this.usageGraphList)
          ;(this.value1 = 0),
            (this.value2 = 0),
            (this.value3 = 0),
            (this.value4 = 0),
            (this.value5 = 0),
            (this.value6 = 0),
            (this.value7 = 0),
            (this.value8 = 0),
            (this.value9 = 0),
            (this.value10 = 0),
            (this.value11 = 0),
            (this.value12 = 0),
            this.setValue()
        })

      this.menu = false
    },
    setValue() {
      if (this.dateRange == "이번달이력" || this.dateRange == "세달간이력") {
        for (let i = 0; i < this.usageGraphList.length; i++) {
          var datePayment = new Date(this.usageGraphList[i].paymentDate)

          if (datePayment < this.dayRange1) {
            this.value1 += this.usageGraphList[i].totalAmount
          } else if (
            this.dayRange1 < datePayment &&
            datePayment < this.dayRange2
          ) {
            this.value2 += this.usageGraphList[i].totalAmount
          } else {
            this.value3 += this.usageGraphList[i].totalAmount
          }
        }
        this.fillData1()
      } else {
        for (let i = 0; i < this.usageGraphList.length; i++) {
          if (datePayment < this.dayRange1) {
            this.value1 += this.usageGraphList[i].totalAmount
          } else if (
            this.dayRange1 < datePayment &&
            datePayment < this.dayRange2
          ) {
            this.value2 += this.usageGraphList[i].totalAmount
          } else if (
            this.dayRange2 < datePayment &&
            datePayment < this.dayRange3
          ) {
            this.value3 += this.usageGraphList[i].totalAmount
          } else if (
            this.dayRange3 < datePayment &&
            datePayment < this.dayRange4
          ) {
            this.value4 += this.usageGraphList[i].totalAmount
          } else if (
            this.dayRange4 < datePayment &&
            datePayment < this.dayRange5
          ) {
            this.value5 += this.usageGraphList[i].totalAmount
          } else if (
            this.dayRange5 < datePayment &&
            datePayment < this.dayRange6
          ) {
            this.value6 += this.usageGraphList[i].totalAmount
          } else if (
            this.dayRange6 < datePayment &&
            datePayment < this.dayRange7
          ) {
            this.value7 += this.usageGraphList[i].totalAmount
          } else if (
            this.dayRange7 < datePayment &&
            datePayment < this.dayRange8
          ) {
            this.value8 += this.usageGraphList[i].totalAmount
          } else if (
            this.dayRange8 < datePayment &&
            datePayment < this.dayRange9
          ) {
            this.value9 += this.usageGraphList[i].totalAmount
          } else if (
            this.dayRange9 < datePayment &&
            datePayment < this.dayRange10
          ) {
            this.value10 += this.usageGraphList[i].totalAmount
          } else if (
            this.dayRange10 < datePayment &&
            datePayment < this.dayRange11
          ) {
            this.value11 += this.usageGraphList[i].totalAmount
          } else {
            this.value12 += this.usageGraphList[i].totalAmount
          }
        }
        this.fillData2()
      }
    },
    fillData1() {
      if (this.myChart !== null) {
        this.myChart.destroy()
      }
      const ctx = document.getElementById("myChart").getContext("2d")
      this.myChart = new Chart(ctx, {
        type: "bar",
        data: {
          labels: [
            this.yyyyMMdd(this.date1) + "~" + this.yyyyMMdd(this.dayRange1),
            this.yyyyMMdd(this.dayRange1) + "~" + this.yyyyMMdd(this.dayRange2),
            this.yyyyMMdd(this.dayRange2) + "~" + this.yyyyMMdd(this.dayRange3),
          ],
          datasets: [
            {
              label: "# of Votes",
              data: [this.value1, this.value2, this.value3],
              backgroundColor: [
                "rgba(255,99,132,0.2)",
                "rgba(54,162,235,0.2)",
                "rgba(255,206,86,0.2)",
              ],
              borderColor: [
                "rgba(255,99,132,1)",
                "rgba(54,162,235,1)",
                "rgba(255,206,86,1)",
              ],
              borderWidth: 1,
            },
          ],
        },
        options: {
          scales: {
            y: {
              beginAtZero: true,
            },
          },
        },
      })
    },
    fillData2() {
      if (this.myChart !== null) {
        this.myChart.destroy()
      }
      const ctx = document.getElementById("myChart").getContext("2d")
      this.myChart = new Chart(ctx, {
        type: "bar",
        data: {
          labels: [
            this.yyyyMMdd(this.date1) + "~" + this.yyyyMMdd(this.dayRange1),
            this.yyyyMMdd(this.dayRange1) + "~" + this.yyyyMMdd(this.dayRange2),
            this.yyyyMMdd(this.dayRange2) + "~" + this.yyyyMMdd(this.dayRange3),
            this.yyyyMMdd(this.dayRange3) + "~" + this.yyyyMMdd(this.dayRange4),
            this.yyyyMMdd(this.dayRange4) + "~" + this.yyyyMMdd(this.dayRange5),
            this.yyyyMMdd(this.dayRange5) + "~" + this.yyyyMMdd(this.dayRange6),
            this.yyyyMMdd(this.dayRange6) + "~" + this.yyyyMMdd(this.dayRange7),
            this.yyyyMMdd(this.dayRange7) + "~" + this.yyyyMMdd(this.dayRange8),
            this.yyyyMMdd(this.dayRange8) + "~" + this.yyyyMMdd(this.dayRange9),
            this.yyyyMMdd(this.dayRange9) +
              "~" +
              this.yyyyMMdd(this.dayRange10),
            this.yyyyMMdd(this.dayRange10) +
              "~" +
              this.yyyyMMdd(this.dayRange11),
            this.yyyyMMdd(this.dayRange11) +
              "~" +
              this.yyyyMMdd(this.dayRange12),
          ],
          datasets: [
            {
              label: "# of Votes",
              data: [
                this.value1,
                this.value2,
                this.value3,
                this.value4,
                this.value5,
                this.value6,
                this.value7,
                this.value8,
                this.value9,
                this.value10,
                this.value11,
                this.value12,
              ],
              backgroundColor: [
                "rgba(255,99,132,0.2)",
                "rgba(54,162,235,0.2)",
                "rgba(255,206,86,0.2)",
                "rgba(75,192,192,0.2)",
                "rgba(153,102,255,0.2)",
                "rgba(255,159,64,0.2)",
                "rgba(255,99,132,0.2)",
                "rgba(54,162,235,0.2)",
                "rgba(255,206,86,0.2)",
                "rgba(75,192,192,0.2)",
                "rgba(153,102,255,0.2)",
                "rgba(255,159,64,0.2)",
              ],
              borderColor: [
                "rgba(255,99,132,1)",
                "rgba(54,162,235,1)",
                "rgba(255,206,86,1)",
                "rgba(75,192,192,1)",
                "rgba(153,102,255,1)",
                "rgba(255,159,64,1)",
                "rgba(255,99,132,1)",
                "rgba(54,162,235,1)",
                "rgba(255,206,86,1)",
                "rgba(75,192,192,1)",
                "rgba(153,102,255,1)",
                "rgba(255,159,64,1)",
              ],
              borderWidth: 1,
            },
          ],
        },
        options: {
          scales: {
            y: {
              beginAtZero: true,
            },
          },
        },
      })
    },
    // resetCanvas() {
    //   $("#results-graph").remove() // this is my <canvas> element
    //   $("#graph-container").append('<canvas id="results-graph"><canvas>')
    //   canvas = document.querySelector("#results-graph")
    //   ctx = canvas.getContext("2d")
    //   ctx.canvas.width = $("#graph").width() // resize to parent width
    //   ctx.canvas.height = $("#graph").height() // resize to parent height
    //   var x = canvas.width / 2
    //   var y = canvas.height / 2
    //   ctx.font = "10pt Verdana"
    //   ctx.textAlign = "center"
    //   ctx.fillText("This text is centered on the canvas", x, y)
    // },
  },
}
</script>
<style scoped>
.myChart {
  width: 400px !important;
  height: 400px !important;
}
</style>

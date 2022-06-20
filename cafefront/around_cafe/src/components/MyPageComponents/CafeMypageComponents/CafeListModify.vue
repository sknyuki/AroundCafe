<template>
  <div>
    <v-btn @click="cafeModi">수정</v-btn>

    <v-dialog v-model="dialog" max-width="600">
      <v-card>
        <v-card-title>수정하기</v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col>
                <p>파일 다시 올리기</p>
                <input
                  type="file"
                  id="files2"
                  ref="files2"
                  multiple
                  v-on:change="handleFileUpload2()"
                />

                <v-text-field
                  label="카페 이름 수정"
                  v-model="mcafe_name"
                ></v-text-field>
                <v-text-field
                  label="사업자 번호 수정"
                  v-model="mcafe_bus_no"
                ></v-text-field>
                <v-text-field
                  label="영업 시간 수정"
                  v-model="mcafe_time"
                ></v-text-field>
                <v-text-field
                  label="카페 설명 수정"
                  v-model="mcafe_content"
                ></v-text-field>
                <v-text-field
                  label="연락처 수정"
                  v-model="mcafe_call"
                ></v-text-field>

                <td><h4>카페 주소:</h4></td>
                <td width="0">
                  <v-select
                    :items="states1"
                    label="시"
                    dense
                    style="width: 100px; font-size: 15px"
                    v-model="mcafe_adr1"
                  ></v-select>
                </td>

                <td width="0">
                  <v-select
                    :items="states2"
                    label="구"
                    dense
                    style="width: 100px; font-size: 15px"
                    v-model="mcafe_adr2"
                  ></v-select>
                </td>
                <td>
                  <v-text-field label="상세주소" v-model="mcafe_adr3" />
                </td>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="white" @click="dialog = false">돌아가기</v-btn>
          <v-btn color="white" @click="modifySubmit">수정하기</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
export default {
  name: "CafeListModify",
  props: {
    item: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      dialog: false,
      states1: ["서울시"],
      states2: [
        "강남구",
        "강동구",
        "강북구",
        "강서구",
        "관악구",
        "광진구",
        "구로구",
        "금천구",
        "노원구",
        "도봉구",
        "동대문구",
        "동작구",
        "마포구",
        "서대문구",
        "서초구",
        "성동구",
        "성북구",
        "송파구",
        "양천구",
        "영등포구",
        "용산구",
        "은평구",
        "종로구",
        "중구",
        "중랑구",
      ],
      mcafe_adr1: "",
      mcafe_adr2: "",
      mcafe_adr3: "",
      mcafe_name: "",
      mcafe_time: "",
      mcafe_content: "",
      mcafe_call: "",
      mcafe_bus_no: "",
      mcafe_no: "",
      files2: "",
      response: "",
    }
  },
  created() {
    this.mcafe_no = this.item.cafeNo
    this.mcafe_name = this.item.cafe_name
    this.mcafe_bus_no = this.item.cafe_bis_no
    this.mcafe_content = this.item.cafe_content
    this.mcafe_call = this.item.cafe_call
    this.mcafe_time = this.item.cafe_time
    this.mcafe_adr1 = this.item.cafe_adr1
    this.mcafe_adr2 = this.item.cafe_adr2
    this.mcafe_adr3 = this.item.cafe_adr3
  },
  methods: {
    cafeModi() {
      this.dialog = true
    },
    handleFileUpload2() {
      this.files2 = this.$refs.files2.files
    },
    modifySubmit() {
      var result = confirm("등록 하시겠습니까?")
      if (result) {
        const {
          mcafe_name,
          mcafe_bus_no,
          mcafe_no,
          mcafe_content,
          mcafe_call,
          mcafe_time,
          mcafe_adr1,
          mcafe_adr2,
          mcafe_adr3,
          files2,
        } = this
        this.$emit("submit", {
          mcafe_name,
          mcafe_bus_no,
          mcafe_no,
          mcafe_content,
          mcafe_call,
          mcafe_time,
          mcafe_adr1,
          mcafe_adr2,
          mcafe_adr3,
          files2,
        })
      }
    },
  },
}
</script>

<style></style>

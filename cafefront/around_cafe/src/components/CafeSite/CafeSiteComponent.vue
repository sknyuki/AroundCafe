<template>
  <div>
    <br />
    <v-divider></v-divider>
    <br />
    <v-row>
      <v-col cols="12" md="2">
        <v-img
          :src="`https://previews.123rf.com/images/illustrart/illustrart1512/illustrart151200002/49252377-%EC%BB%A4%ED%94%BC-%EA%B7%B8%EB%A6%BC.jpg`"
          aspect-ratio="1"
          max-height="200"
          max-width="200"
        ></v-img>
      </v-col>
      <v-col cols="12" md="10">
        <table class="table">
          <tr>
            <td>
              주소: {{ cafeBoard.cafe_adr1 }}{{ cafeBoard.cafe_adr2
              }}{{ cafeBoard.cafe_adr3 }}
            </td>
          </tr>
          <tr>
            <td>call: {{ cafeBoard.cafe_call }}</td>
          </tr>
          <tr>
            <td>
              영업 시간:
              {{ cafeBoard.cafe_time }}
            </td>
          </tr>
          <tr>
            <td>소개: {{ cafeBoard.cafe_content }}</td>
          </tr>
          <tr>
            <td>서비스 옵션: 매장 내 식사 · 테이크아웃</td>
          </tr>
        </table>
      </v-col>
    </v-row>
    <br />
    <v-divider></v-divider>
  </div>
</template>
<script>
export default {
  name: "CafeSiteComponent",
  data() {
    return {
      cafeBoard: {
        cafe_name: "벤투라",
        cafe_call: "031-777-7777",
        cafe_adr1: "서울특별시 ",
        cafe_adr2: "강남구 ",
        cafe_adr3: "강남대로 3번길",
        cafe_time: "11:00~23:00",
        cafe_content:
          "시그니처 로스팅, 가벼운 식사, 미국 시애틀 기반의 커피하우스 체인점입니다.",
      },
    }
  },
  props: {
    // cafeBoard:{
    //   type:Object,
    // },
    // cafeNo:String
  },
  methods: {},
}
</script>

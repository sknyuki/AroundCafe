<template>
  <div>
    <h2>{{ cafeBoard.cafe_name }}님에게 문의하기</h2>
    <span>보통 하루 이내에 응답</span> <br />
    <span>(여기 밑줄)</span><br />
    <span>어떤 것을 문의하고 싶으신가요?</span><br />
    <select>
      <option v-for="(item, index) in selectQna" :key="index" :value="item.id">
        {{ item.name }}
      </option></select
    ><br />
    <span>(여기 밑줄)</span><br />
    <h3>카페에게 메시지를 보내 문의하세요.</h3>
    <br />
    <textarea
      v-model="content"
      maxlength="200"
      cols="120"
      rows="32"
      placeholder="문의사항을 입력해주세요."
    ></textarea>
  </div>
</template>
<script>
export default {
  name: "CafeDetailQnaForm",
  props: {
    cafeNo: {
      type: String,
      required: true,
    },
    cafeBoard: {
      type: Object,
      required: true,
    },
    data() {
      return {
        selectQna: [
          { id: 1, name: "상품 문의" },
          { id: 2, name: "픽업 관련" },
          { id: 3, name: "환불 문의" },
          { id: 4, name: "기타" },
        ],
        content: "",
        findQna: "",
      }
    },
  },
}
</script>
<style lang=""></style>

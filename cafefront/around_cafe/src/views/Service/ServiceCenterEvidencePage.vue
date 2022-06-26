<template>
  <ServiceCenterForm :serviceItems="serviceItems" />
</template>

<script>
import ServiceCenterForm from "@/components/Service/ServiceCenterForm.vue"

export default {
  name: "ServiceCenterEvidencePage",
  components: {
    ServiceCenterForm,
  },

  data() {
    return {
      serviceItems: [
        {
          questions: "세금계산서를 받고 싶은데 어떻게 해야 하나요?",
          answers:
            "세금계산서 발급은 어려우며, 결제 시 지출증빙 또는 현금영수증 발행은 가능합니다.",
          isActive: false,
        },
        {
          questions: "주문 후 지출증빙/현금영수증 발행이 가능한가요?",
          answers: "주문 완료 후 지출증빙/현금영수증 발행은 불가능합니다. ",
          isActive: false,
        },
        {
          questions: "현금영수증 발급을 다른 사람으로 하고 싶습니다.?",
          answers:
            "타인의 명의로 현금영수증 발급을 원하실경우 상품 주문 시 발급 받으실 고객님의 정보(주민등록번호/현금영수증 카드번호/휴대폰번호)를 입력해주시면 됩니다.",
          isActive: false,
        },
        {
          questions: "주문건의 결제 금액을 나눠서 영수증 발행이 가능한가요?",
          answers:
            "주문하신 내역 중 일부 상품에 대해서만 분할로 영수증을 발급 받으실 수는 없습니다.",
          isActive: false,
        },
      ],
    }
  },
}
</script>

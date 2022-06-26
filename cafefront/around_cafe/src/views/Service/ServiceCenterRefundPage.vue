<template>
  <ServiceCenterForm :serviceItems="serviceItems" />
</template>

<script>
import ServiceCenterForm from "@/components/Service/ServiceCenterForm.vue"

export default {
  name: "ServiceCenterRefundPage",
  components: {
    ServiceCenterForm,
  },

  data() {
    return {
      serviceItems: [
        {
          questions: "주문 취소는 어떻게 하나요?",
          answers:
            "판매처에서 주문을 확인하기 전이라면 언제든 취소하실 수 있으며, [나의 쇼핑 > 주문조회]에서 직접 주문취소하실 수 있습니다.",
          isActive: false,
        },
        {
          questions: "취소 후 환불은 언제되나요?",
          answers:
            "신용카드 및 체크카드의 경우 카드사에서 확인 절차를 거치는 관계로 평균 3~7일 영업일 이내 환불처리가 완료됩니다. ",
          isActive: false,
        },
      ],
    }
  },
}
</script>

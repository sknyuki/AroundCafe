<template>
  <ServiceCenterForm :serviceItems="serviceItems" />
</template>

<script>
import ServiceCenterForm from "@/components/Service/ServiceCenterForm.vue"
export default {
  name: "ServiceCenterPage",
  components: {
    ServiceCenterForm,
  },

  data() {
    return {
      serviceItems: [
        {
          questions: "주문내역은 어떻게 확인할 수 있나요?",
          answers:
            "우측 상단 프로필 사진을 클릭 후 [나의쇼핑]을 통해 확인 가능합니다",
          isActive: false,
        },
        {
          questions: "결제 방법은 어떤 것이 있나요?",
          answers:
            "신용카드 및 체크카드, 무통장 입금, 휴대폰 소액결제, 카카페이를 이용해 결제 가능합니다.",
          isActive: false,
        },
        {
          questions: "비회원주문 및 전화주문이 가능한가요?",
          answers: "비회원 주문은 준비중에 있습니다.",
          isActive: false,
        },

        {
          questions: "주문 후 결제 방법을 변경하고 싶은데 어떻게 해야 하나요?",
          answers: "결제 후 결제 정보 변경은 불가능 합니다.",
          isActive: false,
        },
        {
          questions: "결제 시 에러 메시지가 나오는 경우 해야하나요?",
          answers:
            "우측 상단 프로필 사진을 클릭 후 [나의쇼핑 > 고객센터 > 1:1 카톡 상담하기]를 통해 문의 부탁드립니다.",
          isActive: false,
        },
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
        {
          questions:
            "카카오 계정으로 로그인 하면 '이미 카카오로 가입하신 이메일입니다' 라고 나오는데 어떻게 해야 하나요?",
          answers:
            "휴대전화 번호를 변경하셨거나 카카오톡 계정을 탈퇴하고 새로 가입하신 경우 이런 문제가 발생할 수 있습니다.이 경우 새로운 정보가 반영될 수 있도록 번거로우시더라도 고객센터로 문의 부탁드립니다.",
          isActive: false,
        },
        {
          questions:
            "어라운드카페에 가입한 적이 없는데 이미 가입된 이메일이라고 나와요. 어떻게 하나요?",
          answers:
            "이는 고객님의 정보가 노출된 것이 아니니 걱정하지 마시고, 번거로우시겠지만 고객센터로 문의 부탁드립니다. ",
          isActive: false,
        },
        {
          questions: "비밀번호 변경은 어떻게하나요?",
          answers:
            "우측 상단 프로필 사진을 클릭 후 [마이페이지 > 비밀번호 변경] 페이지에서 비밀번호 변경이 가능합니다. ",
          isActive: false,
        },
        {
          questions: "회원탈퇴 후 재가입이 가능한가요?",
          answers: "어라운드 카페 재가입은 언제든지 가능합니다. ",
          isActive: false,
        },
        {
          questions: "회원탈퇴는 어떻게 하나요?",
          answers:
            "PC 웹사이트: 우측 상단 프로필 사진 클릭 후 [마이페이지 > 회원정보수정] 상단의 탈퇴하기 버튼 클릭해주세요. ",
          isActive: false,
        },
        {
          questions:
            "카카오 계정으로 로그인 하면 '이미 카카오로 가입하신 이메일입니다' 라고 나오는데 어떻게 해야 하나요?",
          answers:
            "휴대전화 번호를 변경하셨거나 카카오톡 계정을 탈퇴하고 새로 가입하신 경우 이런 문제가 발생할 수 있습니다.이 경우 새로운 정보가 반영될 수 있도록 번거로우시더라도 고객센터로 문의 부탁드립니다.",
          isActive: false,
        },
        {
          questions:
            "어라운드카페에 가입한 적이 없는데 이미 가입된 이메일이라고 나와요. 어떻게 하나요?",
          answers:
            "이는 고객님의 정보가 노출된 것이 아니니 걱정하지 마시고, 번거로우시겠지만 고객센터로 문의 부탁드립니다. ",
          isActive: false,
        },
        {
          questions: "비밀번호 변경은 어떻게하나요?",
          answers:
            "우측 상단 프로필 사진을 클릭 후 [마이페이지 > 비밀번호 변경] 페이지에서 비밀번호 변경이 가능합니다. ",
          isActive: false,
        },
        {
          questions: "회원탈퇴 후 재가입이 가능한가요?",
          answers: "어라운드 카페 재가입은 언제든지 가능합니다. ",
          isActive: false,
        },
        {
          questions: "회원탈퇴는 어떻게 하나요?",
          answers:
            "PC 웹사이트: 우측 상단 프로필 사진 클릭 후 [마이페이지 > 회원정보수정] 상단의 탈퇴하기 버튼 클릭해주세요. ",
          isActive: false,
        },
      ],
    }
  },
}
</script>

<template>
  <ServiceCenterForm :serviceItems="serviceItems" />
</template>

<script>
import ServiceCenterForm from "@/components/Service/ServiceCenterForm.vue"
export default {
  name: "ServiceCenterEtcPage",
  components: {
    ServiceCenterForm,
  },

  data() {
    return {
      serviceItems: [
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

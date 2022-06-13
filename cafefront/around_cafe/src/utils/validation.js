import Vue from "vue"
import { extend, ValidationObserver, ValidationProvider } from "vee-validate"
import { required, email, confirmed } from "vee-validate/dist/rules"

// 비밀번호 최소~최대길이
extend("limit", (value, params) => {
  const [min, max] = params
  if ((value && value.length <= min) || value.length >= max) {
    return `비밀번호는 ${min}자 ~ ${max}자를 초과할 수 없습니다`
  }
  return true
})

// 비밀번호 유효성
extend("memCheckPw", (value) => {
  const regex = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,25}$/.test(value)
  if (!regex) {
    return `영문, 숫자를 포함하여 8자 이상.`
  }
  return true
})

// 최대길이 유효성
extend("max", (value, args) => {
  if (value.length > Number(args[0])) {
    return `${args}자 이하로 입력해 주세요.`
  }
  return true
})

// 최소길이 유효성
extend("min", (value, args) => {
  if (value.length < Number(args[0])) {
    return `${args}자 이상 입력해 주세요.`
  }
  return true
})

// 필수 권장
extend("required", {
  ...required,
  message: "필수 입력 항목입니다.",
})

// 이메일
extend("email", {
  ...email,
  message: "이메일 형식이 올바르지 않습니다.",
})

// 비밀번호 확인
// provider 에 :rules="{ confirmed: 'provider name 명을 적어주면 된다'}"
extend("confirmed", {
  ...confirmed,
  message: "비밀번호가 일치하지 않습니다",
})

Vue.component("ValidationObserver", ValidationObserver)
Vue.component("ValidationProvider", ValidationProvider)

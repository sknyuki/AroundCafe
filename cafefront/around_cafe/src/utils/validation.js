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

// 생년월일 유효성
extend("birthday", (value) => {
  const regex =
    /^(19[0-9][0-9]|20\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/.test(
      value
    )
  if (!regex) {
    return "유효한 생년월일을 입력 바랍니다. ex) YYYY-MM-DD"
  }
  return true
})

// 사업자 번호 유효성
extend("bisRegNum", (value) => {
  const regex = /^(\d{3})-(\d{2})-(\d{5})$/.test(value)
  if (!regex) {
    return "유효한 사업자 번호를 입력 바랍니다. ex) 110-11-11111"
  }
  return true
})

//휴대폰 번호 유효성
extend("phoneNum", (value) => {
  const regex = /(?:(010-\d{4})|(01[1|6|7|8|9]-\d{3,4}))-(\d{4})/.test(value)
  if (!regex) {
    return "유효한 휴대폰 번호를 입력 바랍니다. ex) 010-1234-5678"
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

//동일길이 유효성
extend("equals", (value, args) => {
  if (value.length !== Number(args[0])) {
    return `${args}자를 입력해 주세요.`
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

//export default

<template>
  <div class="sign">
    <header class="sign-header">
      <h2>회원가입</h2>
    </header>

    <section class="sign-section">
      <div>
        <div class="sign-tab">
          <div
            @click=";[(clickUse = true), (role = 'USER'), clearData()]"
            :class="[clickUse === true ? 'active' : '']"
            class="sign-tab-select"
          >
            <span>이용자</span>
          </div>
          <div
            @click=";[(clickUse = false), (role = 'CAFE'), clearData()]"
            :class="[clickUse === false ? 'active' : '']"
            class="sign-tab-select"
          >
            <span>사업자</span>
          </div>
        </div>
        <ValidationObserver v-slot="{ invalid }">
          <form @submit.prevent="onSubmit">
            <div class="sign-form bname" v-if="clickUse === false">
              <label class="sign-form-label" for="">상호명(법인명)</label>
              <div class="account-input">
                <ValidationProvider
                  class="sign-validation"
                  tag="div"
                  :rules="`${
                    clickUse === false ? 'required' : ''
                  }|min:2|max:15`"
                  v-slot="{ errors }"
                >
                  <input
                    v-model="username"
                    class="form-input input-48"
                    type="text"
                    placeholder="상호명(법인명)"
                    :disabled="usernameDisabled"
                  />
                  <div class="errmsg" role="alert" aria-live="polite">
                    {{ errors[0] }}
                  </div>
                  <div v-show="!usernameDisabled" class="sign-email btn">
                    <v-btn
                      @click="existByNickname(username)"
                      v-model="usernameDupChecked"
                      :disabled="username.length < 1 || errors.length !== 0"
                      class="sign-button-vuti"
                      :validate="(usernameDupChecked = true)"
                      aria-label="상호명(법인명) 중복확인"
                    >
                      상호명(법인명) 중복확인
                    </v-btn>
                  </div>
                </ValidationProvider>
              </div>
            </div>
            <div class="sign-form crn" v-if="clickUse === false">
              <label class="sign-form-label">사업자등록번호</label>
              <div class="account-input">
                <ValidationProvider
                  class="sign-validation"
                  tag="div"
                  :rules="`${clickUse === false ? 'required' : ''}|bisRegNum`"
                  v-slot="{ errors }"
                >
                  <input
                    v-model="cafeBisNo"
                    class="form-input input-48"
                    type="text"
                    placeholder="사업자등록번호"
                    maxlength="12"
                    required
                  />
                  <div class="errmsg" role="alert" aria-live="polite">
                    {{ errors[0] }}
                  </div>
                </ValidationProvider>
              </div>
            </div>
            <div class="sign-form id">
              <label class="sign-form-label">이메일</label>
              <div class="account-input">
                <ValidationProvider
                  class="sign-validation"
                  tag="div"
                  v-slot="{ errors }"
                >
                  <input
                    v-model="email"
                    class="form-input input-48"
                    type="email"
                    placeholder="이메일"
                    required
                    :disabled="emailDupChecked"
                  />
                  <div class="errmsg" role="alert" aria-live="polite">
                    {{ errors[0] }}
                  </div>
                  <div class="sign-email btn">
                    <v-btn
                      @click="existByEmail(email)"
                      :disabled="
                        email.length < 1 ||
                        errors.length !== 0 ||
                        emailDupChecked
                      "
                      class="sign-button-vuti"
                    >
                      이메일 인증하기
                    </v-btn>
                  </div>
                </ValidationProvider>
              </div>
            </div>
            <div class="sign-code-box codeVerify" v-show="emailVerifyUse">
              <div>이메일로 전송된 인증코드를 입력해주세요.</div>
              <ValidationProvider
                class="sign-validation"
                tag="div"
                v-slot="{ errors }"
                display="inline-block"
                :rules="{ equals: 6 }"
              >
                <div class="sign-code-input">
                  <input
                    v-model="emailCode"
                    class="form-input input-48"
                    type="text"
                    placeholder="인증코드 6자리 입력"
                    required
                  />
                  <span>{{ timeString }}</span>
                  <v-btn
                    :disabled="emailCode.length === 0 || errors.length !== 0"
                    class="sign-code-btn"
                    type="button"
                    @click="checkEmailCode()"
                  >
                    확인
                  </v-btn>
                </div>
                <div v-if="time <= 0">인증코드가 만료되었습니다.</div>
                <div class="errmsg" role="alert" aria-live="polite">
                  {{ errors[0] }}
                </div>
              </ValidationProvider>
              <div style="margin-top: 30px; margin-bottom: 10px">
                <div style="display: inline-block">
                  이메일을 받지 못하셨나요?
                </div>
                <a
                  style="
                    display: inline-block;
                    margin-left: 6px;
                    font-weight: bold;
                    text-decoration: underline;
                  "
                  @click="sendVerifyEmail(email)"
                >
                  이메일 재전송하기</a
                >
              </div>
            </div>
            <div class="sign-form pw">
              <label class="sign-form-label">비밀번호</label>
              <div class="sign-form-recommend">
                영문, 숫자를 포함한 8자 이상의 비밀번호를 입력해주세요.
              </div>
              <div class="account-input">
                <ValidationProvider
                  class="sign-validation"
                  tag="div"
                  name="password"
                  type="password"
                  v-slot="{ errors }"
                  :rules="{ memCheckPw }"
                >
                  <input
                    v-model="password"
                    class="form-input input-48"
                    type="password"
                    placeholder="비밀번호"
                    required
                  />
                  <div class="errmsg" role="alert" aria-live="polite">
                    {{ errors[0] }}
                  </div>
                </ValidationProvider>
              </div>
            </div>
            <div class="sign-form pwc">
              <label class="sign-form-label">비밀번호 확인</label>
              <div class="account-input">
                <ValidationProvider
                  class="sign-validation"
                  tag="div"
                  v-slot="{ errors }"
                  name="memCheckPw"
                  :rules="{ confirmed: 'password' }"
                >
                  <input
                    v-model="memCheckPw"
                    class="form-input input-48"
                    type="password"
                    placeholder="비밀번호 확인"
                    required
                  />
                  <div class="errmsg" role="alert" aria-live="polite">
                    {{ errors[0] }}
                  </div>
                </ValidationProvider>
              </div>
            </div>
            <div class="sign-form nick" v-if="clickUse === true">
              <label class="sign-form-label" for="">닉네임</label>
              <div class="sign-form-recommend">
                다른 유저와 겹치지 않는 별명을 입력해주세요. (2~15자)
              </div>
              <div class="account-input">
                <ValidationProvider
                  class="sign-validation"
                  tag="div"
                  name="name"
                  v-slot="{ errors }"
                  :rules="`${clickUse === true ? 'required' : ''}|min:2|max:15`"
                >
                  <input
                    v-model="username"
                    class="form-input input-48"
                    type="text"
                    placeholder="별명 (2~15자)"
                    maxlength="15"
                    :disabled="usernameDisabled"
                  />
                  <div class="errmsg" role="alert" aria-live="polite">
                    {{ errors[0] }}
                  </div>
                  <div v-show="!usernameDisabled" class="sign-email btn">
                    <v-btn
                      @click="existByNickname(username)"
                      :disabled="username.length < 1 || errors.length !== 0"
                      class="sign-button-vuti"
                    >
                      닉네임 중복확인
                    </v-btn>
                  </div>
                </ValidationProvider>
              </div>
            </div>
            <div class="sign-form bth">
              <label class="sign-form-label">생년월일</label>
              <div class="sign-form-recommend">
                생년월일 8자리 입력 ex) 1994-03-26
              </div>
              <div class="account-input">
                <ValidationProvider
                  class="sign-validation"
                  tag="div"
                  v-slot="{ errors }"
                  rules="birthday"
                >
                  <input
                    v-model="birth"
                    class="form-input input-48"
                    type="text"
                    placeholder="YYYY-MM-DD"
                    required
                  />
                  <div class="errmsg" role="alert" aria-live="polite">
                    {{ errors[0] }}
                  </div>
                </ValidationProvider>
              </div>
            </div>
            <div class="sign-form phone">
              <label class="sign-form-label" for="">휴대폰 번호</label>
              <div class="account-input">
                <ValidationProvider
                  class="sign-validation"
                  tag="div"
                  v-slot="{ errors }"
                  rules="phoneNum"
                >
                  <input
                    v-model="phoneNum"
                    class="form-input input-48"
                    type="text"
                    placeholder="휴대폰 번호"
                    required
                  />
                  <div class="errmsg" role="alert" aria-live="polite">
                    {{ errors[0] }}
                  </div>
                </ValidationProvider>
              </div>
            </div>
            <div class="sign-form-agr">
              <label class="sign-form-label" for="">약관동의</label>
              <div class="sign-form-agr-content">
                <div class="sign-form-agr-header">
                  <label class="sign-form-agr-label" for="">
                    <div class="sign-form-agr-input">
                      <input
                        type="checkbox"
                        v-model="isBoxAllChecked"
                        name="checkboxAll"
                        @click="checkAllBox"
                      />
                      <span>
                        <div>전체동의</div>
                      </span>
                    </div>
                  </label>
                </div>
                <label for="">
                  <div class="sign-form-agr-input">
                    <input
                      type="checkbox"
                      v-model="checkedBox.checkbox1"
                      name="checkbox1"
                      @click="checkSingleBox('1')"
                    />
                    <div>
                      만 14세 이상입니다.<span class="sign-form-agr-esn"
                        >(필수)</span
                      >
                    </div>
                  </div>
                </label>
                <label for="">
                  <div class="sign-form-agr-input">
                    <input
                      type="checkbox"
                      v-model="checkedBox.checkbox2"
                      name="checkbox2"
                      @click="checkSingleBox('2')"
                    />
                    <a href="" target="_blank">이용약관</a>
                    <span class="sign-form-agr-esn">(필수)</span>
                  </div>
                </label>
                <label for="">
                  <div class="sign-form-agr-input">
                    <input
                      type="checkbox"
                      v-model="checkedBox.checkbox3"
                      name="checkbox3"
                      @click="checkSingleBox('3')"
                    />
                    <a href="" target="_blank">개인정보수집 및 이용동의</a>
                    <span class="sign-form-agr-esn">(필수)</span>
                  </div>
                </label>
              </div>
            </div>
            <v-btn
              :disabled="
                invalid ||
                !isBoxAllChecked ||
                !usernameDupChecked ||
                !emailVerify
              "
              class="sign-button-vuti"
              type="submit"
            >
              회원가입하기
            </v-btn>
          </form>
        </ValidationObserver>
        <p>
          이미 아이디가 있으신가요?
          <router-link :to="{ name: 'AccountLoginPage' }">로그인</router-link>
        </p>
      </div>
    </section>
  </div>
</template>
<script>
import axios from "axios"
import TimerBeforeHour from "@/utils/timer"

export default {
  name: "AccountSignUp",
  data() {
    return {
      email: "",
      password: "",
      memCheckPw: "",
      username: "",
      birth: "",
      socialType: "LOCAL",
      role: "USER",
      cafeBisNo: "",
      phoneNum: "",
      emailCode: "",
      emailCodeFromServer: "",
      clickUse: true,
      emailDupChecked: false,
      emailVerifyUse: false,
      emailVerify: false,
      usernameDupChecked: false,
      usernameDisabled: false,
      emailVerifyDisabled: false,
      checkedBox: {
        checkbox1: false,
        checkbox2: false,
        checkbox3: false,
      },
      isBoxAllChecked: false,
      time: 0,
    }
  },
  computed: {
    timeString: {
      get() {
        if (this.time > 0) {
          return TimerBeforeHour.timeToString(this.time)
        } else {
          clearInterval(this.timerId)
          return "00:00"
        }
      },
    },
  },
  methods: {
    timerStart() {
      this.timerId = setInterval(() => {
        this.time--
        //this.timeString = TimerBeforeHour.timeToString(time)
      }, 1000)
    },
    checkSingleBox(box) {
      if (box === "1") {
        this.checkedBox.checkbox1 = !this.checkedBox.checkbox1
      } else if (box === "2") {
        this.checkedBox.checkbox2 = !this.checkedBox.checkbox2
      } else if (box === "3") {
        this.checkedBox.checkbox3 = !this.checkedBox.checkbox3
      }
      this.isBoxAllChecked =
        this.checkedBox.checkbox1 === true &&
        this.checkedBox.checkbox2 === true &&
        this.checkedBox.checkbox3 === true
    },
    checkAllBox() {
      if (
        this.checkedBox.checkbox1 === true &&
        this.checkedBox.checkbox2 === true &&
        this.checkedBox.checkbox3 === true
      ) {
        this.checkedBox.checkbox1 = false
        this.checkedBox.checkbox2 = false
        this.checkedBox.checkbox3 = false
        this.isBoxAllChecked = false
      } else {
        this.checkedBox.checkbox1 = true
        this.checkedBox.checkbox2 = true
        this.checkedBox.checkbox3 = true
        this.isBoxAllChecked = true
      }
    },
    clearData() {
      // 기본정보 초기화
      this.email = ""
      this.password = ""
      this.memCheckPw = ""
      this.username = ""
      this.birth = ""
      this.socialType = "LOCAL"
      this.cafeBisNo = ""
      this.phoneNum = ""
      // Email 중복검사 초기화
      this.emailDupChecked = false
      this.emailVerifyUse = false
      this.emailVerify = false
      this.emailVerifyDisabled = false
      // Username 중복검사 초기화
      this.usernameDupChecked = false
      this.usernameDisabled = false
      //체크박스 초기화
      this.isBoxAllChecked = false
      this.checkedBox.checkbox1 = false
      this.checkedBox.checkbox2 = false
      this.checkedBox.checkbox3 = false
    },
    checkEmailCode() {
      if (this.emailCode === this.emailCodeFromServer) {
        this.emailVerify = true
        alert("Email이 인증되셨습니다.")
        this.emailVerifyUse = false
      } else {
        alert("Email Code가 다릅니다. 다시 입력 바랍니다.")
      }
    },
    async sendVerifyEmail(email) {
      clearInterval(this.timerId)
      const url = "http://localhost:5000/mail/verifyEmail"
      const data = { email: email }
      const config = {
        headers: {
          "Content-Type": "Application/json",
        },
      }
      this.time = 180
      alert("인증 메일이 발송되었습니다.")
      this.timerStart()
      this.emailVerifyDisabled = true
      this.emailVerifyUse = true
      const code = await axios.post(url, JSON.stringify(data), config)
      // 유저가 확인할 수 없는 장소가 어디일까요?
      // 확인요망 -- 애매하면 그냥 redis 서버에 저장
      this.emailCodeFromServer = code.data["code"]
    },
    existByNickname(username) {
      const url = "http://localhost:7777/auth/isExists"
      const data = {
        memNick: username,
      }
      const config = {
        headers: {
          "Content-Type": "Application/json",
        },
      }
      axios.post(url, JSON.stringify(data), config).then((res) => {
        if (res.data === false) {
          alert("사용 가능한 닉네임입니다.")
          this.usernameDupChecked = true
          this.usernameDisabled = true
        } else {
          alert("이미 사용하고 있는 닉네임입니다. 다른 닉네임을 사용해주세요")
          this.usernameDupChecked = false
          this.usernameDisabled = false
        }
      })
    },
    existByEmail(email) {
      const url = "http://localhost:7777/auth/isExists"
      const data = {
        memId: email,
      }
      const config = {
        headers: {
          "Content-Type": "Application/json",
        },
      }
      axios.post(url, JSON.stringify(data), config).then((res) => {
        if (res.data === false) {
          this.emailDupChecked = true
          this.sendVerifyEmail(email)
        } else {
          alert("이미 사용하고 있는 이메일입니다. 다른 이메일을 사용해주세요")
          this.emailDupChecked = false
        }
      })
    },
    onSubmit() {
      const data = {
        email: this.email,
        password: this.password,
        username: this.username,
        birth: this.birth,
        socialType: this.socialType,
        role: this.role,
        cafeBisNo: this.cafeBisNo,
        phoneNum: this.phoneNum,
      }
      this.$emit("submit", data)
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/account/sign";
</style>

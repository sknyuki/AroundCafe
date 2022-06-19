<template>
  <div class="sign">
    <header class="sign-header">
      <h2>회원가입</h2>
    </header>

    <section class="sign-section">
      <div>
        <div class="sign-tab">
          <div
            @click="[clickUse = true, role = 'USER', clearData()]"
            :class="[clickUse === true ? 'active' : '']"
            class="sign-tab-select"
          >
            <span>이용자</span>
          </div>
          <div
            @click="[clickUse = false, role = 'CAFE', clearData()]"
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
                  :rules="`${clickUse === false? 'required' : ''}`"
                  v-slot="{ errors }"
                >
                  <input
                    v-model="username"
                    class="form-input input-48"
                    type="text"
                    placeholder="상호명(법인명)"
                  />
                  <div class="errmsg" aria-live="polite">
                    {{ errors[0] }}
                  </div>
                </ValidationProvider>
              </div>
            </div>
            <div class="sign-form crn" v-if="clickUse === false">
              <label class="sign-form-label" for="">사업자등록번호</label>
              <div class="account-input">
                <ValidationProvider
                  class="sign-validation"
                  tag="div"
                  :rules="`${clickUse === false? 'required' : ''}`"
                  v-slot="{ errors }"
                >
                  <input
                    v-model="cafeBisNo"
                    class="form-input input-48"
                    type="text"
                    placeholder="사업자등록번호"
                    required
                  />
                  <div class="errmsg" aria-live="polite">
                    {{ errors[0] }}
                  </div>
                </ValidationProvider>
              </div>
            </div>
            <div class="sign-form id">
              <label class="sign-form-label" for="">이메일</label>
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
                    :disabled="emailDisabled"
                  />
                  <div class="errmsg" aria-live="polite">
                    {{ errors[0] }}
                  </div>
                </ValidationProvider>
              </div>
            </div>
            <div @click="existByEmail(email)"
                 class="sign-email btn">
              <!-- disable 옵션 수정해야 함 -->
              <v-btn :disabled="email < 1" v-if="!emailDupChecked" class="sign-button-vuti">이메일 중복확인</v-btn>
            </div>
            <div @click="sendVerifyEmail(email)"
                 class="sign-email btn">
              <!-- disable 옵션 수정해야 함 -->
              <v-btn v-if="emailDupChecked && !emailVerifyDisabled" class="sign-button-vuti">이메일 인증하기</v-btn>
            </div>
            <div class="sign-form emailVerify" v-if="emailVerifyUse">
              <ValidationProvider
                  class="sign-validation"
                  tag="div"
                  v-slot="{ errors }"
              >
                <input
                    v-model="emailCode"
                    class="form-input input-48"
                    type="text"
                    placeholder="이메일 코드"
                    required
                />
                <div class="errmsg" aria-live="polite">
                  {{ errors[0] }}
                </div>
              </ValidationProvider>
            </div>
            <div class="sign-button-vuti" v-if="emailVerifyUse"
                @click="checkEmailCode()">
              <v-btn class="sign-button-vuti">이메일 코드 확인하기</v-btn>
            </div>
            <div class="sign-form pw">
              <label class="sign-form-label" for="">비밀번호</label>
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
                  <div class="errmsg" aria-live="polite">
                    {{ errors[0] }}
                  </div>
                </ValidationProvider>
              </div>
            </div>
            <div class="sign-form pwc">
              <label class="sign-form-label" for="">비밀번호 확인</label>
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
                  <div class="errmsg" aria-live="polite">
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
                  :rules="`${clickUse === true? 'required' : ''}|min:2|max:15`"
                >
                  <input
                    v-model="username"
                    class="form-input input-48"
                    type="text"
                    placeholder="별명 (2~15자)"
                    maxlength="15"
                    :disabled="usernameDisabled"
                  />
                  <div class="errmsg" aria-live="polite">
                    {{ errors[0] }}
                  </div>
                </ValidationProvider>
              </div>
            </div>
            <!-- disable 조건 변경 필요 -->
            <div @click="existByNickname(username)"
                 v-if="!usernameDupChecked"
                 class="sign-email btn">
              <v-btn :disabled="username < 1" class="sign-button-vuti">닉네임 중복확인</v-btn>
            </div>
            <div class="sign-form bth">
              <label class="sign-form-label" for="">생년월일</label>
              <div class="sign-form-recommend">
                생년월일 8자리 입력 ex) 1994-03-26
              </div>
              <div class="account-input">
                <ValidationProvider
                  class="sign-validation"
                  tag="div"
                  v-slot="{ errors }"
                >
                  <input
                    v-model="birth"
                    class="form-input input-48"
                    type="text"
                    placeholder="YYYY-MM-DD"
                    required
                  />
                  <div class="errmsg" aria-live="polite">
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
                >
                  <input
                    v-model="phoneNum"
                    class="form-input input-48"
                    type="text"
                    placeholder="휴대폰 번호"
                    required
                  />
                  <div class="errmsg" aria-live="polite">
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
                      <input type="checkbox" v-model="isBoxAllChecked" value="" @click="checkAllBox"/>
                      <span>
                        <div>전체동의</div>
                      </span>
                    </div>
                  </label>
                </div>
                <label for="">
                  <div class="sign-form-agr-input">
                    <input type="checkbox" v-model="checkedBox.checkbox1" value="1" @click="checkSingleBox('1')"/>
                    <div>
                      만 14세 이상입니다.<span class="sign-form-agr-esn"
                        >(필수)</span
                      >
                    </div>
                  </div>
                </label>
                <label for="">
                  <div class="sign-form-agr-input">
                    <input type="checkbox" v-model="checkedBox.checkbox2" value="2" @click="checkSingleBox('2')"/>
                    <a href="" target="_blank">이용약관</a>
                    <span class="sign-form-agr-esn">(필수)</span>
                  </div>
                </label>
                <label for="">
                  <div class="sign-form-agr-input">
                    <input type="checkbox" v-model="checkedBox.checkbox3" value="3" @click="checkSingleBox('3')"/>
                    <a href="" target="_blank">개인정보수집 및 이용동의</a>
                    <span class="sign-form-agr-esn">(필수)</span>
                  </div>
                </label>
              </div>
            </div>
            <v-btn :disabled="invalid" class="sign-button-vuti" type="submit">
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
import axios from "axios";


export default {
  name: "AccountSignUp",
  computed: {
  },
  methods: {
    checkSingleBox(box){
      if(box === "1") {
        this.checkedBox.checkbox1 = !this.checkedBox.checkbox1
      } else if(box === "2"){
        this.checkedBox.checkbox2 = !this.checkedBox.checkbox2
      } else if(box === "3") {
        this.checkedBox.checkbox3 = !this.checkedBox.checkbox3
      }
      this.isBoxAllChecked = this.checkedBox.checkbox1 === true && this.checkedBox.checkbox2 === true && this.checkedBox.checkbox3 === true;
    },
    checkAllBox() {
      if(this.checkedBox.checkbox1 === true && this.checkedBox.checkbox2 === true && this.checkedBox.checkbox3 === true) {
        this.checkedBox.checkbox1 = false
        this.checkedBox.checkbox2 = false
        this.checkedBox.checkbox3 = false
        this.isBoxAllChecked = false
      }
      else{
        this.checkedBox.checkbox1 = true
        this.checkedBox.checkbox2 = true
        this.checkedBox.checkbox3 = true
        this.isBoxAllChecked = true
      }
    },
    clearData() {
          this.email= "";
          this.password= "";
          this.memCheckPw= "";
          this.username= "";
          this.birth= "";
          this.socialType= "LOCAL";
          this.cafeBisNo= "";
          this.phoneNum= "";
          this.isBoxAllChecked = false;
          this.checkedBox.checkbox1 = false;
          this.checkedBox.checkbox2 = false;
          this.checkedBox.checkbox3 = false;
    },
    checkEmailCode() {
      if(this.emailCode === this.emailCodeFromServer){
        this.emailVerify = true
        alert("Email이 인증되셨습니다.")
        this.emailVerifyUse = false
      } else {
        alert("Email Code가 다릅니다. 다시 입력 바랍니다.")
      }
    },
    async sendVerifyEmail(email) {
      const url = "http://localhost:5000/mail/verifyEmail"
      const data = {'email': email}
      const config = {
        headers: {
          'Content-Type': 'Application/json'
        }
      }
      alert("인증 메일이 발송되었습니다. 이메일이 도착하는데 시간이 걸릴 수 있습니다.")
      const code = await axios.post(url,JSON.stringify(data), config)
      // 유저가 확인할 수 없는 장소가 어디일까요?
      // 확인요망 -- 애매하면 그냥 redis 서버에 저장
      this.emailCodeFromServer = code.data['code']
      this.emailVerifyDisabled = true
      this.emailVerifyUse = true
    },
    existByNickname(username) {
      const url = 'http://localhost:7777/auth/isDuplicated'
      const data = {
        'memNick': username
      }
      const config = {
        headers: {
          'Content-Type': 'Application/json'
        }
      }
      axios.post(url,JSON.stringify(data),config).then(res => {
        if(res.data === false){
          alert("사용 가능한 닉네임입니다.")
          this.usernameDupChecked = true
          this.usernameDisabled = true
        } else{
          alert("이미 사용하고 있는 닉네임입니다. 다른 닉네임을 사용해주세요")
          this.usernameDupChecked = false
        }
        console.log(this.checkedBox)
      })
    },
    existByEmail(email) {
      const url = 'http://localhost:7777/auth/isDuplicated'
      const data = {
        'memId': email
      }
      const config = {
        headers: {
          'Content-Type': 'Application/json'
        }
      }
      axios.post(url,JSON.stringify(data),config).then(res => {
        if(res.data === false){
          alert("사용 가능한 이메일입니다.")
          this.emailDupChecked = true
          this.emailDisabled = true
        } else{
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
      this.$emit('submit', data)
    },
  },
  data() {
    return {
      clickUse: true,
      emailDupChecked: false,
      emailVerifyUse: false,
      emailVerify: false,
      emailCode: "",
      emailCodeFromServer: "",
      email: "",
      password: "",
      memCheckPw: "",
      username: "",
      usernameDupChecked: false,
      birth: "",
      socialType: "LOCAL",
      role: "USER",
      cafeBisNo: "",
      phoneNum: "",
      emailDisabled: false,
      usernameDisabled: false,
      emailVerifyDisabled: false,
      checkedBox: {
        checkbox1 : false,
        checkbox2 : false,
        checkbox3 : false,
      },
      isBoxAllChecked: false,
      required: false,
    }
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/account/sign";
</style>

<!--
사업자 번호 정규식

function checkCorporateRegiNumber(number){
	var numberMap = number.replace(/-/gi, '').split('').map(function (d){
		return parseInt(d, 10);
	});

	if(numberMap.length == 10){
		var keyArr = [1, 3, 7, 1, 3, 7, 1, 3, 5];
		var chk = 0;

		keyArr.forEach(function(d, i){
			chk += d * numberMap[i];
		});

		chk += parseInt((keyArr[8] * numberMap[8])/ 10, 10);
		console.log(chk);
		return Math.floor(numberMap[9]) === ( (10 - (chk % 10) ) % 10);
	}

	return false;
}
-->
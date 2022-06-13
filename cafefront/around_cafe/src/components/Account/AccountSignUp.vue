<template>
  <div class="sign">
    <header class="sign-header">
      <h2>회원가입</h2>
    </header>

    <section class="sign-section">
      <div>
        <div class="sign-tab">
          <div
            @click="clickUse = true"
            :class="[clickUse === true ? 'active' : '']"
            class="sign-tab-select"
          >
            <span>이용자</span>
          </div>
          <div
            @click="clickUse = false"
            :class="[clickUse === false ? 'active' : '']"
            class="sign-tab-select"
          >
            <span>사업자</span>
          </div>
        </div>
        <ValidationObserver v-slot="{ invalid }">
          <form v-show="clickUse === true">
            <div class="sign-form id">
              <label class="sign-form-label" for="">이메일</label>
              <div class="account-input">
                <ValidationProvider
                  class="sign-validation"
                  tag="div"
                  v-slot="{ errors }"
                >
                  <input
                    v-model="memId"
                    class="form-input input-48"
                    type="email"
                    placeholder="이메일"
                    required
                  />
                  <div class="errmsg" aria-live="polite">
                    {{ errors[0] }}
                  </div>
                </ValidationProvider>
              </div>
            </div>
            <div class="sign-email btn">
              <v-btn class="sign-button-vuti">이메일 인증하기</v-btn>
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
                    v-model="memPw"
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
            <div class="sign-form nick">
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
                  :rules="{ min: 2, max: 15 }"
                >
                  <input
                    v-model="memNick"
                    class="form-input input-48"
                    type="text"
                    placeholder="별명 (2~15자)"
                    required
                    maxlength="15"
                  />
                  <div class="errmsg" aria-live="polite">
                    {{ errors[0] }}
                  </div>
                </ValidationProvider>
              </div>
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
                    v-model="memBirth"
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
                      <input type="checkbox" />
                      <span>
                        <div>전체동의</div>
                      </span>
                    </div>
                  </label>
                </div>
                <label for="">
                  <div class="sign-form-agr-input">
                    <input type="checkbox" />
                    <div>
                      만 14세 이상입니다.<span class="sign-form-agr-esn"
                        >(필수)</span
                      >
                    </div>
                  </div>
                </label>
                <label for="">
                  <div class="sign-form-agr-input">
                    <input type="checkbox" />
                    <a href="" target="_blank">이용약관</a>
                    <span class="sign-form-agr-esn">(필수)</span>
                  </div>
                </label>
                <label for="">
                  <div class="sign-form-agr-input">
                    <input type="checkbox" />
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
        <ValidationObserver v-slot="{ invalid }">
          <form v-show="clickUse === false">
            <div class="sign-form bname">
              <label class="sign-form-label" for="">상호명(법인명)</label>
              <div class="account-input">
                <ValidationProvider
                  class="sign-validation"
                  tag="div"
                  v-slot="{ errors }"
                >
                  <input
                    v-model="memNick"
                    class="form-input input-48"
                    type="text"
                    placeholder="상호명(법인명)"
                    required
                  />
                  <div class="errmsg" aria-live="polite">
                    {{ errors[0] }}
                  </div>
                </ValidationProvider>
              </div>
            </div>
            <div class="sign-form crn">
              <label class="sign-form-label" for="">사업자등록번호</label>
              <div class="account-input">
                <ValidationProvider
                  class="sign-validation"
                  tag="div"
                  v-slot="{ errors }"
                >
                  <input
                    v-model="memCrn"
                    class="form-input input-48"
                    type="email"
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
                    v-model="memId"
                    class="form-input input-48"
                    type="email"
                    placeholder="이메일"
                    required
                  />
                  <div class="errmsg" aria-live="polite">
                    {{ errors[0] }}
                  </div>
                </ValidationProvider>
              </div>
            </div>
            <div class="sign-email-btn">
              <v-btn class="sign-button-vuti">이메일 인증하기</v-btn>
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
                    v-model="memPw"
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
                    v-model="memBirth"
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
                      <input type="checkbox" />
                      <span>
                        <div>전체동의</div>
                      </span>
                    </div>
                  </label>
                </div>

                <label for="">
                  <div class="sign-form-agr-input">
                    <input type="checkbox" />
                    <a href="" target="_blank">이용약관</a>
                    <span class="sign-form-agr-esn">(필수)</span>
                  </div>
                </label>
                <label for="">
                  <div class="sign-form-agr-input">
                    <input type="checkbox" />
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
export default {
  name: "AccountSignUp",

  data() {
    return {
      clickUse: true,
      memId: "",
      memPw: "",
      memCheckPw: "",
      memNick: "",
      memBirth: "",
      memCrn: "",
      phoneNum: "",
    }
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/account/sign";
</style>

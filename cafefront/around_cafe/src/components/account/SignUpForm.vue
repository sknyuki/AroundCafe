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
            <span>사업자(법인/개인)</span>
          </div>
        </div>
        <form v-show="clickUse === true" class="sign-form">
          <div class="sign-form-id">
            <label class="sign-form-label" for="">이메일</label>
            <div class="account-input">
              <span>
                <input
                  class="form-input"
                  type="email"
                  placeholder="이메일"
                  value
                />
              </span>
            </div>
          </div>
          <div class="sign-email-btn">
            <v-btn class="sign-button-vuti">이메일 인증하기</v-btn>
          </div>
          <div class="sign-form-pw">
            <label class="sign-form-label" for="">비밀번호</label>
            <div class="sign-form-recommend">
              영문, 숫자를 포함한 8자 이상의 비밀번호를 입력해주세요.
            </div>
            <div class="account-input">
              <span>
                <input
                  class="form-input"
                  type="password"
                  placeholder="비밀번호"
                  value
                />
              </span>
            </div>
          </div>
          <div class="sign-form-pwc">
            <label class="sign-form-label" for="">비밀번호 확인</label>
            <div class="account-input">
              <span>
                <input
                  class="form-input"
                  type="password"
                  placeholder="비밀번호 확인"
                  value
                />
              </span>
            </div>
          </div>
          <div class="sign-form-nick">
            <label class="sign-form-label" for="">닉네임</label>
            <div class="sign-form-recommend">
              다른 유저와 겹치지 않는 별명을 입력해주세요. (2~15자)
            </div>
            <div class="account-input">
              <span>
                <input
                  class="form-input"
                  type="text"
                  placeholder="별명 (2~15자)"
                  value
                />
              </span>
            </div>
          </div>
          <div class="sign-form-bth">
            <label class="sign-form-label" for="">생년월일</label>
            <div class="sign-form-recommend">
              생년월일 8자리 입력 ex) 1994-03-26
            </div>
            <div class="account-input">
              <span>
                <input
                  class="form-input"
                  type="text"
                  placeholder="YYYY-MM-DD"
                  value
                />
              </span>
            </div>
          </div>
          <div class="sign-form-phone">
            <label class="sign-form-label" for="">휴대폰 번호</label>
            <div class="account-input">
              <span>
                <input
                  class="form-input"
                  type="text"
                  placeholder="휴대폰 번호"
                  value
                />
              </span>
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
                  <span>
                    <div>
                      만 14세 이상입니다.<span class="sign-form-agr-esn"
                        >(필수)</span
                      >
                    </div>
                  </span>
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

          <v-btn class="sign-button-vuti" type="submit"> 회원가입하기 </v-btn>
        </form>
        <form v-show="clickUse === false">
          <div>test</div>
          <div>test</div>
          <div>test</div>
        </form>
        <p>
          이미 아이디가 있으신가요?
          <router-link :to="{ name: 'LoginPage' }">로그인</router-link>
        </p>
      </div>
    </section>
  </div>
</template>
<script>
export default {
  name: "SignUpForm",
  data() {
    return {
      clickUse: true,
    }
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/account/sign";
</style>

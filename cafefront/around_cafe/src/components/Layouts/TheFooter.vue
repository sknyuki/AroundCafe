<template>
  <footer class="global-footer">
    <v-container>
      <v-row>
        <v-col>
          <div class="global-footer-top">
            <address class="global-footer-layout">
              <h3 class="global-footer-title">
                고객센터
                <v-icon>mdi-chevron-right</v-icon>
              </h3>
              <a class="global-footer-contact" href="tel:1611-0828"
                >1611-0828</a
              >
              <p class="global-footer-time">
                평일 09:00 ~ 18:00 (주말 &amp; 공휴일 제외)
              </p>
            </address>

            <div class="global-footer-outbound">
              <a href="">
                <v-btn
                  fab
                  dark
                  x-small
                  color="indigo"
                  aria-label="애플스토어 이동"
                >
                  <v-icon aria-hidden="true">mdi-apple</v-icon>
                </v-btn>
              </a>
              <a href="">
                <v-btn
                  fab
                  dark
                  x-small
                  color="indigo"
                  aria-label="구글플레이 이동"
                >
                  <v-icon aria-hidden="true">mdi-google-play</v-icon>
                </v-btn>
              </a>
              <a href="">
                <v-btn
                  fab
                  dark
                  x-small
                  color="indigo"
                  aria-label="페이스북 이동"
                >
                  <v-icon aria-hidden="true">mdi-facebook</v-icon>
                </v-btn>
              </a>
              <a href="">
                <v-btn fab dark x-small color="indigo" aria-label="유튜브 이동">
                  <v-icon aria-hidden="true">mdi-youtube</v-icon>
                </v-btn>
              </a>
              <a href="">
                <v-btn fab dark x-small color="indigo" aria-label="트위터 이동">
                  <v-icon aria-hidden="true">mdi-twitter</v-icon>
                </v-btn>
              </a>
            </div>
          </div>
          <ul class="global-footer-list">
            <li>
              <a class="global-footer-item" href="#" target="_blank"
                >브랜드 스토리</a
              >
            </li>
            <li>
              <a class="global-footer-item" href="#" target="_blank"
                >회사소개</a
              >
            </li>
            <li>
              <a class="global-footer-item" href="#" target="_blank"
                >채용정보</a
              >
            </li>
            <li>
              <a class="global-footer-item" href="#" target="_blank"
                >이용약관</a
              >
            </li>
            <li>
              <a class="global-footer-item" href="#" target="_blank"
                >개인정보처리방침</a
              >
            </li>
            <li>
              <a class="global-footer-item" href="#" target="_blank"
                >공지사항</a
              >
            </li>
            <li>
              <a class="global-footer-item" href="#" target="_blank"
                >고객센터</a
              >
            </li>
            <li>
              <a class="global-footer-item" href="#" target="_blank"
                >고객의 소리</a
              >
            </li>
            <li>
              <a class="global-footer-item" href="#" target="_blank"
                >전문가 등록</a
              >
            </li>
            <li>
              <a class="global-footer-item" href="#" target="_blank"
                >사업자 구매회원</a
              >
            </li>
            <li>
              <a class="global-footer-item" href="#" target="_blank"
                >제휴/광고 문의</a
              >
            </li>
            <li>
              <a class="global-footer-item" href="#" target="_blank"
                >입점신청 문의</a
              >
            </li>
          </ul>

          <address class="global-footer-wrap">
            <dl class="global-footer-info">
              <div class="global-footer-info-item">
                <dt>상호명</dt>
                <dd>(주)Arount Cafe</dd>
              </div>
              <div class="global-footer-info-item">
                <dt>이메일</dt>
                <dd>
                  <address>
                    <a href="mailto:around@gmail.com">around@gmail.com</a>
                  </address>
                  (제휴문의)
                </dd>
              </div>
              <div class="global-footer-info-item">
                <dt>대표이사</dt>
                <dd>인지연</dd>
              </div>
              <div class="global-footer-info-item">
                <dt>주소</dt>
                <dd>
                  <address>
                    서울 서초구 서초대로74길 4 삼성생명서초타워 25, 27층
                  </address>
                </dd>
              </div>
            </dl>
            <span class="global-footer-info">
              <span
                >(주)어라운드카페는 통신판매중개자로 거래 당사자가 아니므로,
                판매자가 등록한 상품정보 및 거래 등에 대해 책임을 지지 않습니다.
                <br />
                단, (주)어라운드카페가 판매자로 등록 판매한 상품은 판매자로서
                책임을 부담합니다.
              </span>
            </span>
          </address>
          <p class="global-footer-copyright">
            Copyright 2022, AroundCafe, Co., Lted. All rights reserved
          </p>
        </v-col>
      </v-row>
    </v-container>
  </footer>
</template>
<script>
export default {
  name: "TheFooter",
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/layouts/FooterLayout";
</style>

<template>
  <footer class="global-footer">
    <v-container>
      <v-row>
        <v-col>
          <div class="global-footer-top">
            <address class="global-footer-layout">
              <router-link
                :to="{ name: 'ServiceCenterPage' }"
                class="global-footer-title"
                target="_blank"
              >
                고객센터
                <v-icon aria-hidden="true">mdi-chevron-right</v-icon>
              </router-link>
              <div>
                <a class="global-footer-contact" href="tel:1611-0828"
                  >1611-0828</a
                >
              </div>
              <p class="global-footer-time">
                평일 09:00 ~ 18:00 (주말 &amp; 공휴일 제외)
              </p>
            </address>

            <div class="global-footer-outbound">
              <v-btn
                v-for="item in socialBtn"
                :key="item.id"
                fab
                dark
                x-small
                color="indigo"
                :aria-label="item.aria"
              >
                <v-icon aria-hidden="true">{{ item.icon }}</v-icon>
              </v-btn>
            </div>
          </div>
          <ul class="global-footer-list">
            <li v-for="item in footerList" :key="item.id">
              <a class="global-footer-item" href="#" target="_blank">{{
                item.title
              }}</a>
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
  data() {
    return {
      socialBtn: [
        {
          id: 1,
          icon: "mdi-apple",
          aria: "애플스토어 이동",
        },
        {
          id: 2,
          icon: "mdi-google-play",
          aria: "구글플레이 이동",
        },
        {
          id: 3,
          icon: "mdi-facebook",
          aria: "페이스북 이동",
        },
        {
          id: 4,
          icon: "mdi-youtube",
          aria: "유튜브 이동",
        },
        {
          id: 5,
          icon: "mdi-twitter",
          aria: "트위터 이동",
        },
      ],
      footerList: [
        {
          id: 1,
          title: "회사소개",
        },
        {
          id: 2,
          title: "채용정보",
        },
        {
          id: 3,
          title: "이용약관",
        },
        {
          id: 4,
          title: "개인정보처리방침",
        },
        {
          id: 5,
          title: "공지사항",
        },
        {
          id: 6,
          title: "고객센터",
        },
        {
          id: 7,
          title: "고객의 소리",
        },
        {
          id: 8,
          title: "전문가 등록",
        },
        {
          id: 9,
          title: "사업자 구매회원",
        },
        {
          id: 10,
          title: "제휴/광고 문의",
        },
        {
          id: 11,
          title: "입점신청 문의",
        },
      ],
    }
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/layouts/FooterLayout";
</style>

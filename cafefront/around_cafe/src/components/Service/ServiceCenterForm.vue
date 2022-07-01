<template>
  <div class="service">
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <section class="service">
            <div>
              <div class="service-wrapper">
                <div class="service-content">
                  <h2 class="service-header">무엇을 도와드릴까요?</h2>
                  <ul>
                    <li class="service-list">
                      <a><span>Q</span>주문 취소는 어떻게 하나요?</a>
                    </li>
                    <li class="service-list">
                      <a><span>Q</span>매장의 자세한 정보를 알고 싶어요.</a>
                    </li>
                    <li class="service-list">
                      <a
                        ><span>Q</span>카카오 계정으로 로그인하면 이미
                        가입되었다고 합니다.</a
                      >
                    </li>
                  </ul>
                </div>
                <div class="service-content tel">
                  <h2>고객센터 <span class="time"> 09:00 ~ 18:00</span></h2>
                  <p class="telephone lg-only">
                    <v-icon>mdi-phone-outline</v-icon>
                    <strong>1611-0828</strong>
                  </p>
                  <div class="btn-group">
                    <v-btn class="btn-indigo btn-40">1:1 카톡 상담하기</v-btn>
                    <v-btn class="btn-outlined btn-40" href="">
                      이메일 문의하기
                    </v-btn>
                    <v-btn class="btn-outlined btn-40" href="">
                      전화 문의하기
                    </v-btn>
                  </div>
                </div>
              </div>
              <div class="service-chip">
                <nav>
                  <router-link
                    :to="serviceNav.link"
                    v-for="(serviceNav, index) in serviceNavs"
                    :key="index"
                    @click.native="onBtnColor(index)"
                  >
                    <label
                      v-if="serviceNav.link"
                      :class="{ 'is-active': serviceNav.isActive }"
                    >
                      <input type="button" />
                      <span>{{ serviceNav.title }}</span>
                    </label>
                  </router-link>
                </nav>
              </div>
              <section class="service-section">
                <ul>
                  <li
                    v-for="(serviceItem, index) in serviceItems"
                    :key="index"
                    class="service-content-list"
                  >
                    <h3 :class="{ 'is-active': serviceItem.isActive }">
                      <button
                        @click="serviceItem.isActive = !serviceItem.isActive"
                        class="service-content-button"
                        type="button"
                      >
                        <span class="icon">Q</span
                        ><span>{{ serviceItem.questions }}</span
                        ><span
                          ><i
                            :class="{ 'is-active': serviceItem.isActive }"
                            class="icChevron"
                          ></i
                        ></span>
                      </button>
                    </h3>
                    <div
                      class="service-content-info"
                      :class="{ 'is-active': serviceItem.isActive }"
                    >
                      <p>
                        {{ serviceItem.answers }}
                      </p>
                    </div>
                  </li>
                </ul>
              </section>
            </div>
          </section>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "ServiceCenterForm",

  props: {
    serviceItems: {
      type: Array,
      required: true,
    },
  },

  data() {
    return {
      serviceNavs: [
        {
          link: "/service",
          title: "전체",
          isActive: false,
        },
        {
          link: "/service",
          title: "주문/결제",
          isActive: false,
        },
        {
          link: "/service/refund",
          title: "취소/환불",
          isActive: false,
        },
        {
          link: "/service",
          title: "카페 등록",
          isActive: false,
        },
        {
          link: "/service/evidence",
          title: "증빙서류발급",
          isActive: false,
        },
        {
          link: "/service/account",
          title: "로그인/회원정보",
          isActive: false,
        },
        {
          link: "/service",
          title: "서비스/기타",
          isActive: false,
        },
      ],
    }
  },
  methods: {
    onBtnColor(index) {
      for (let i = 0; i < this.serviceNavs.length; i++) {
        if (this.serviceNavs[i].isActive == true) {
          this.serviceNavs[i].isActive = false
        } else {
          this.serviceNavs[index].isActive = true
        }
      }
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/service/service";
</style>

<template>
  <div class="container">
    <div class="row">
      <div class="col-sm-12">
        <div class="cafe-gallery-content">
          <ul class="cafe-list">
            <li
              v-for="(cafeItem, index) in mainlist"
              :key="index"
              class="cafe-item"
              @mouseover="swiperBtn = index"
              @mouseleave="swiperBtn = null"
            >
              <!-- 스켈레톤 UI -->
              <template v-if="isLoading">
                <div>
                  <SkeletonBox class="skeleton-box" />
                </div>
                <div>
                  <SkeletonBox class="skeleton-title" />
                </div>
                <div>
                  <SkeletonBox class="skeleton-location" />
                </div>
                <div>
                  <SkeletonBox class="skeleton-time" />
                </div>
                <div>
                  <SkeletonBox class="skeleton-star" />
                </div>
              </template>

              <!-- 로그인 했을때 CafeCard -->
              <template v-else>
                <CafeLike
                  v-if="onLogin"
                  class="cafe-item-btn"
                  :cafeItem="cafeItem"
                />
                <router-link
                  :to="{
                    name: 'CafeDetailPage',
                    params: { cafeNo: cafeItem.cafeNo.toString() },
                  }"
                  @focus.native="swiperBtn = index"
                >
                  <!-- 스와이퍼 -->
                  <swiper class="swiper" :options="swiperOption">
                    <template v-if="cafeItem.cafeImgs.length == 0">
                      <swiper-slide class="cafe-gallery-image">
                        <picture>
                          <img
                            v-lazyload
                            :data-url="
                              require(`@/assets/cafe/cafeMypage/beforeReady.jpg`)
                            "
                            alt="등록하지 않은 카페 이미지"
                          />
                        </picture>
                      </swiper-slide>
                    </template>
                    <template v-else>
                      <swiper-slide
                        v-for="img in cafeItem.cafeImgs"
                        :key="img.cafeImgNo"
                        class="cafe-gallery-image"
                      >
                        <picture>
                          <source type="image/webp" />
                          <img
                            v-lazyload
                            :data-url="
                              require(`@/assets/cafe/cafeMypage/${img.cafe_img}`)
                            "
                            :alt="`image${img.cafeImgNo}`"
                          />
                        </picture>
                      </swiper-slide>
                      <v-btn
                        v-show="swiperBtn === index"
                        slot="button-prev"
                        class="swiper-button-prev is-prev"
                        type="button"
                        fab
                        x-small
                        aria-label="이전 이미지 버튼"
                      >
                        <i class="icChevron" aria-hidden="true"></i>
                      </v-btn>
                      <v-btn
                        v-show="swiperBtn === index"
                        slot="button-next"
                        class="swiper-button-next is-next"
                        type="button"
                        fab
                        x-small
                        aria-label="다음 이미지 버튼"
                      >
                        <i class="icChevron" aria-hidden="true"></i>
                      </v-btn>
                      <div class="swiper-pagination" slot="pagination"></div>
                    </template>
                  </swiper>

                  <h1 class="cafe-list-title">{{ cafeItem.cafe_name }}</h1>

                  <div class="cafe-list-location">
                    <span>{{ cafeItem.address }}</span>
                  </div>

                  <div class="cafe-list-time">
                    <span>{{ cafeItem.cafe_time }}</span>
                  </div>

                  <dl class="cafe-list-rating">
                    <div class="rating">
                      <dt>
                        <i class="icStar" aria-hidden="true"></i>
                        <span class="visually-hidden">평점</span>
                      </dt>
                      <dd>{{ cafeItem.starAver | ratingPoint }}</dd>
                    </div>
                  </dl>
                </router-link>
              </template>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { swiper, swiperSlide } from "vue-awesome-swiper"
import SkeletonBox from "@/components/SkeletonBox.vue"
import CafeLike from "@/components/Cafe/CafeLike"
import "swiper/dist/css/swiper.min.css"
import { mapState, mapActions } from "vuex"

export default {
  name: "CafeCard",

  components: {
    swiper,
    swiperSlide,
    SkeletonBox,
    CafeLike,
  },

  data() {
    return {
      isLoading: false,
      // onHoverIndex: null,
      swiperBtn: null,
      // Swiper 데이터
      swiperOption: {
        touchEventsTarget: "wrapper",
        slidesPerView: 1,
        loop: false,
        speed: 500,
        pagination: {
          el: ".swiper-pagination",
          dynamicBullets: true,
          dynamicMainBullets: 3,
        },
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev",
        },
        a11y: {
          prevSlideMessage: "이전 이미지 버튼",
          nextSlideMessage: "다음 이미지 버튼",
        },
      },
      cafeNo: "",
    }
  },
  computed: {
    ...mapState(["mainlist", "user"]),
  },

  async mounted() {
    this.isLoading = true
    await this.fetchMainList()
    setTimeout(() => {
      this.isLoading = false
    }, 1500)
    this.fetchMyLikesList(this.user.memNo)

    this.onLogin()
  },

  methods: {
    ...mapActions(["fetchMainList", "fetchMyLikesList", "fetchUser"]),

    onLogin() {
      if (this.membNo != "") {
        this.onLogin = true
      } else {
        this.onLogin = false
      }
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/gallery/cafe-recommendation";
@import "~@/assets/scss/components/gallery/cafe-gallery";
@import "~@/assets/scss/components/gallery/cafe-list";
@import "~@/assets/scss/components/gallery/swiper";
@import "~@/assets/scss/components/gallery/cafe-skeleton";
</style>

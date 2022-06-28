<template>
  <div class="container">
    <div class="row">
      <div class="col-sm-12 col-md-12 col-lg-12">
        <div class="cafe-gallery-content">
          <ul class="cafe-list">
            <li
              v-for="(cafeItem, index) in cafeBoards"
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

              <template v-else>
                <router-link
                  :to="{
                    name: 'CafeDetailPage',
                    params: { cafeNo: cafeItem.cafeNo.toString() },
                  }"
                  @focus.tab="swiperBtn = index"
                >
                  <!-- 좋아요 토글 -->
                  <button class="cafe-item-btn" type="button">
                    <i class="icHeart"></i>
                  </button>

                  <!-- 스와이퍼 -->
                  <swiper class="swiper" :options="swiperOption">
                    <swiper-slide
                      v-if="cafeItem.cafeImgs.length == 0"
                      class="cafe-gallery-image"
                    >
                      <picture>
                        <img
                          loading="lazy"
                          v-bind:src="
                            require(`@/assets/cafe/cafeMypage/beforeReady.jpg`)
                          "
                          alt="카페이미지"
                        />
                      </picture>
                    </swiper-slide>
                    <swiper-slide
                      v-else
                      v-for="img in cafeItem.cafeImgs"
                      :key="img.cafeImgNo"
                      class="cafe-gallery-image"
                    >
                      <picture>
                        <img
                          loading="lazy"
                          v-bind:src="
                            require(`@/assets/cafe/cafeMypage/${img.cafe_img}`)
                          "
                          alt="카페이미지"
                        />
                      </picture>
                    </swiper-slide>
                    <v-btn
                      fab
                      x-small
                      class="swiper-button-prev is-prev"
                      slot="button-prev"
                      aria-label="이전 이미지"
                      type="button"
                      v-show="swiperBtn === index"
                    >
                      <i class="icChevron" aria-hidden></i>
                    </v-btn>
                    <v-btn
                      fab
                      x-small
                      class="swiper-button-next is-next"
                      slot="button-next"
                      aria-label="다음 이미지"
                      type="button"
                      v-show="swiperBtn === index"
                    >
                      <i class="icChevron" aria-hidden></i>
                    </v-btn>
                    <div class="swiper-pagination" slot="pagination"></div>
                  </swiper>

                  <h1 class="cafe-list-title">{{ cafeItem.cafe_name }}</h1>

                  <div class="cafe-list-location">
                    <span
                      >{{ cafeItem.cafe_adr1 }} {{ cafeItem.cafe_adr2 }}
                      {{ cafeItem.cafe_adr3 }}</span
                    >
                  </div>

                  <div class="cafe-list-time">
                    <span>{{ cafeItem.cafe_time }}</span>
                  </div>

                  <dl class="cafe-list-rating">
                    <div class="rating">
                      <dt>
                        <i class="icStar"></i>
                        <span class="visually-hidden">평점</span>
                      </dt>
                      <dd>{{ cafeItem.star }}</dd>
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
import "swiper/dist/css/swiper.min.css"

export default {
  name: "CafeCard",

  components: {
    swiper,
    swiperSlide,
    SkeletonBox,
  },

  props: {
    cafeBoards: {
      type: Array,
      required: true,
    },
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
          prevSlideMessage: "이전 슬라이드",
          nextSlideMessage: "다음 슬라이드",
        },
      },
    }
  },
  // mounted() {
  //   setTimeout(() => this.show(), 2000)
  // },

  methods: {
    // show: function () {
    //   this.isLoading = !this.isLoading
    //   setTimeout(() => this.show(), 3500)
    // },
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

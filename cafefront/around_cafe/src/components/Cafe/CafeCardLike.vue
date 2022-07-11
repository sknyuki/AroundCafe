<template>
  <div class="cafe-gallery-content">
    <ul class="cafe-list">
      <li
        v-for="(cafeItem, index) in cafeMatchBoards"
        :key="index"
        class="cafe-item"
        @mouseover="swiperBtn = index"
        @mouseleave="swiperBtn = null"
      >
        <template>
          <router-link
            :to="{
              name: 'CafeDetailPage',
              params: { cafeNo: cafeItem.cafeNo.toString() },
            }"
            @focus.tab="swiperBtn = index"
            :aria-label="cafeItem.cafe_name"
          >
            <CafeLike
              :cafeItem="cafeItem"
              :myLikes="myLikes"
              class="cafe-item-btn"
            />

            <!-- 스와이퍼 -->
            <swiper class="swiper" :options="swiperOption">
              <swiper-slide
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
                <i class="icChevron" aria-hidden="true"></i>
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
                <i class="icChevron" aria-hidden="true"></i>
              </v-btn>
              <div class="swiper-pagination" slot="pagination"></div>
            </swiper>

            <h1 class="cafe-list-title">{{ cafeItem.cafe_name }}</h1>

            <div class="cafe-list-location">
              <span>{{ cafeItem.address }} {{ cafeItem.post }}</span>
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
                <dd>{{ cafeItem.star }}</dd>
              </div>
            </dl>
          </router-link>
        </template>
      </li>
    </ul>
  </div>
</template>
<script>
import { swiper, swiperSlide } from "vue-awesome-swiper"
import CafeLike from "@/components/Cafe/CafeLike"
import "swiper/dist/css/swiper.min.css"

export default {
  name: "CafeCardLike",

  components: {
    swiper,
    swiperSlide,
    CafeLike,
  },

  props: {
    cafeMatchBoards: {
      type: Array,
      required: true,
    },
    myLikes: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
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
      membNo: JSON.parse(localStorage.getItem("user")).memNo,
      cafeNo: "",
    }
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

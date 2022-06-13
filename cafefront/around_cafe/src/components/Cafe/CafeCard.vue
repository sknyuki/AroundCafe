<template>
  <div class="cafe-gallery-content">
    <ul class="cafe-list">
      <li
        v-for="cafeItem in cafeItems"
        :key="cafeItem.id"
        class="cafe-item"
        @mouseover="btnHover = true"
        @mouseleave="btnHover = false"
      >
        <button class="cafe-item-btn" type="button">
          <i class="icHeart"></i>
        </button>
        <a href="">
          <swiper class="swiper" :options="swiperOption">
            <swiper-slide
              v-for="cafeItem in cafeItem.img"
              :key="cafeItem.id"
              class="cafe-gallery-image"
            >
              <picture>
                <img :src="cafeItem.image" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <v-btn
              fab
              x-small
              :class="['prev-prev', { show: btnHover }]"
              class="swiper-button-prev is-prev"
              slot="button-prev"
              aria-label="이전 이미지"
              type="button"
            >
              <i class="icChevron" aria-hidden></i>
            </v-btn>
            <v-btn
              :class="['prev-next', { show: btnHover }]"
              fab
              x-small
              class="swiper-button-next is-next"
              slot="button-next"
              aria-label="다음 이미지"
              type="button"
            >
              <i class="icChevron" aria-hidden></i>
            </v-btn>
            <div class="swiper-pagination" slot="pagination"></div>
          </swiper>

          <h1 class="cafe-list-title">{{ cafeItem.title }}</h1>

          <div class="cafe-list-location">
            <span>{{ cafeItem.location }}</span>
          </div>
          <div class="cafe-list-time">
            <span>{{ cafeItem.time }}</span>
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
        </a>
      </li>
    </ul>
  </div>
</template>
<script>
import { swiper, swiperSlide } from "vue-awesome-swiper"
import "swiper/dist/css/swiper.min.css"

export default {
  name: "CafeCard",

  components: {
    swiper,
    swiperSlide,
  },
  props: {
    cafeItems: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      btnHover: false,
      swiperOption: {
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
      },
    }
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/gallery/cafe-recommendation";
@import "~@/assets/scss/components/gallery/cafe-gallery";
@import "~@/assets/scss/components/gallery/cafe-list";
@import "~@/assets/scss/components/gallery/swiper";

.prev-next,
.prev-prev {
  opacity: 0;
}
.show {
  opacity: 1;
}
</style>

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
        <button v-if="!isLoading" class="cafe-item-btn" type="button">
          <i class="icHeart"></i>
        </button>
        <template v-if="isLoading">
          <div>
            <SkeletonBox class="sk-box" />
          </div>
          <div>
            <SkeletonBox class="sk-title" />
          </div>
          <div>
            <SkeletonBox class="sk-location" />
          </div>
          <div>
            <SkeletonBox class="sk-time" />
          </div>
          <div>
            <SkeletonBox class="sk-star" />
          </div>
        </template>

        <template v-else>
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
        </template>
      </li>
    </ul>
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
    cafeItems: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      btnHover: false,

      // Swiper 데이터
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
      // Skeleton UI 데이터
      isLoading: true,
    }
  },
  methods: {
    show: function () {
      this.isLoading = !this.isLoading
      setTimeout(() => this.show(), 3500)
    },
  },
  mounted() {
    setTimeout(() => this.show(), 2000)
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

.sk {
  &-title,
  &-location,
  &-time,
  &-star {
    height: 18px;
  }

  &-title {
    width: 320px;
  }
  &-location {
    width: 240px;
  }
  &-time {
    width: 200px;
  }
  &-star {
    width: 80px;
  }

  &-box {
    margin-top: 24px;
    width: 100%;
    height: 0;
    padding-bottom: 100%;
    overflow: hidden;
  }

  @include responsive(T) {
    &-title {
      width: 240px;
    }
    &-location {
      width: 160px;
    }
    &-time {
      width: 120px;
    }
    &-star {
      width: 80px;
    }
  }
}
</style>

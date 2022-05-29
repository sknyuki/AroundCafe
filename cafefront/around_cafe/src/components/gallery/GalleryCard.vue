<template>
  <div class="cafe-gallery-content">
    <ul class="cafe-list">
      <li
        class="cafe-item"
        @mouseover="btnHover = true"
        @mouseleave="btnHover = false"
      >
        <button class="cafe-item-btn" type="button">
          <i class="icHeart"></i>
        </button>
        <a href="">
          <swiper class="swiper" :options="swiperOption">
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-01.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-02.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-03.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-04.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-05.jpg" alt="카페이미지" />
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

          <h1 class="cafe-list-title">벤투라(카페이름)</h1>

          <div class="cafe-list-location">
            <span>서울</span>
          </div>
          <div class="cafe-list-time">
            <span>11:00 ~ 23:00</span>
          </div>
          <dl class="cafe-list-rating">
            <div class="rating">
              <dt>
                <i class="icStar"></i>
                <span class="visually-hidden">평점</span>
              </dt>
              <dd>4.8</dd>
            </div>
          </dl>
        </a>
      </li>
      <li
        class="cafe-item"
        @mouseover="btnHover = true"
        @mouseleave="btnHover = false"
      >
        <button class="cafe-item-btn" type="button">
          <i class="icHeart"></i>
        </button>
        <a href="">
          <swiper class="swiper" :options="swiperOption">
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-01.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-02.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-03.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-04.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-05.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>

            <div class="swiper-pagination" slot="pagination"></div>

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
          </swiper>

          <h1 class="cafe-list-title">벤투라(카페이름)</h1>

          <div class="cafe-list-location">
            <span>서울</span>
          </div>
          <div class="cafe-list-time">
            <span>11:00 ~ 23:00</span>
          </div>
          <dl class="cafe-list-rating">
            <div class="rating">
              <dt>
                <i class="icStar"></i>
                <span class="visually-hidden">평점</span>
              </dt>
              <dd>4.8</dd>
            </div>
          </dl>
        </a>
      </li>
      <li
        class="cafe-item"
        @mouseover="btnHover = true"
        @mouseleave="btnHover = false"
      >
        <button class="cafe-item-btn" type="button">
          <i class="icHeart"></i>
        </button>
        <a href="">
          <swiper class="swiper" :options="swiperOption">
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-01.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-02.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-03.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-04.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-05.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>

            <div class="swiper-pagination" slot="pagination"></div>

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
          </swiper>

          <h1 class="cafe-list-title">벤투라(카페이름)</h1>

          <div class="cafe-list-location">
            <span>서울</span>
          </div>
          <div class="cafe-list-time">
            <span>11:00 ~ 23:00</span>
          </div>
          <dl class="cafe-list-rating">
            <div class="rating">
              <dt>
                <i class="icStar"></i>
                <span class="visually-hidden">평점</span>
              </dt>
              <dd>4.8</dd>
            </div>
          </dl>
        </a>
      </li>
      <li
        class="cafe-item"
        @mouseover="btnHover = true"
        @mouseleave="btnHover = false"
      >
        <button class="cafe-item-btn" type="button">
          <i class="icHeart"></i>
        </button>
        <a href="">
          <swiper class="swiper" :options="swiperOption">
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-01.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-02.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-03.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-04.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-05.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>

            <div class="swiper-pagination" slot="pagination"></div>

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
          </swiper>

          <h1 class="cafe-list-title">벤투라(카페이름)</h1>

          <div class="cafe-list-location">
            <span>서울</span>
          </div>
          <div class="cafe-list-time">
            <span>11:00 ~ 23:00</span>
          </div>
          <dl class="cafe-list-rating">
            <div class="rating">
              <dt>
                <i class="icStar"></i>
                <span class="visually-hidden">평점</span>
              </dt>
              <dd>4.8</dd>
            </div>
          </dl>
        </a>
      </li>
      <li
        class="cafe-item"
        @mouseover="btnHover = true"
        @mouseleave="btnHover = false"
      >
        <button class="cafe-item-btn" type="button">
          <i class="icHeart"></i>
        </button>
        <a href="">
          <swiper class="swiper" :options="swiperOption">
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-01.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-02.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-03.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-04.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-05.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>

            <div class="swiper-pagination" slot="pagination"></div>

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
          </swiper>

          <h1 class="cafe-list-title">벤투라(카페이름)</h1>

          <div class="cafe-list-location">
            <span>서울</span>
          </div>
          <div class="cafe-list-time">
            <span>11:00 ~ 23:00</span>
          </div>
          <dl class="cafe-list-rating">
            <div class="rating">
              <dt>
                <i class="icStar"></i>
                <span class="visually-hidden">평점</span>
              </dt>
              <dd>4.8</dd>
            </div>
          </dl>
        </a>
      </li>
      <li
        class="cafe-item"
        @mouseover="btnHover = true"
        @mouseleave="btnHover = false"
      >
        <button class="cafe-item-btn" type="button">
          <i class="icHeart"></i>
        </button>
        <a href="">
          <swiper class="swiper" :options="swiperOption">
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-01.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-02.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-03.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-04.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-05.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>

            <div class="swiper-pagination" slot="pagination"></div>

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
          </swiper>

          <h1 class="cafe-list-title">벤투라(카페이름)</h1>

          <div class="cafe-list-location">
            <span>서울</span>
          </div>
          <div class="cafe-list-time">
            <span>11:00 ~ 23:00</span>
          </div>
          <dl class="cafe-list-rating">
            <div class="rating">
              <dt>
                <i class="icStar"></i>
                <span class="visually-hidden">평점</span>
              </dt>
              <dd>4.8</dd>
            </div>
          </dl>
        </a>
      </li>
      <li
        class="cafe-item"
        @mouseover="btnHover = true"
        @mouseleave="btnHover = false"
      >
        <button class="cafe-item-btn" type="button">
          <i class="icHeart"></i>
        </button>
        <a href="">
          <swiper class="swiper" :options="swiperOption">
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-01.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-02.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-03.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-04.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-05.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>

            <div class="swiper-pagination" slot="pagination"></div>

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
          </swiper>

          <h1 class="cafe-list-title">벤투라(카페이름)</h1>

          <div class="cafe-list-location">
            <span>서울</span>
          </div>
          <div class="cafe-list-time">
            <span>11:00 ~ 23:00</span>
          </div>
          <dl class="cafe-list-rating">
            <div class="rating">
              <dt>
                <i class="icStar"></i>
                <span class="visually-hidden">평점</span>
              </dt>
              <dd>4.8</dd>
            </div>
          </dl>
        </a>
      </li>
      <li
        class="cafe-item"
        @mouseover="btnHover = true"
        @mouseleave="btnHover = false"
      >
        <button class="cafe-item-btn" type="button">
          <i class="icHeart"></i>
        </button>
        <a href="">
          <swiper class="swiper" :options="swiperOption">
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-01.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-02.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-03.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-04.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>
            <swiper-slide class="cafe-gallery-image">
              <picture>
                <img src="@/assets/images/img-review-05.jpg" alt="카페이미지" />
              </picture>
            </swiper-slide>

            <div class="swiper-pagination" slot="pagination"></div>

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
          </swiper>

          <h1 class="cafe-list-title">벤투라(카페이름)</h1>

          <div class="cafe-list-location">
            <span>서울</span>
          </div>
          <div class="cafe-list-time">
            <span>11:00 ~ 23:00</span>
          </div>
          <dl class="cafe-list-rating">
            <div class="rating">
              <dt>
                <i class="icStar"></i>
                <span class="visually-hidden">평점</span>
              </dt>
              <dd>4.8</dd>
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
  name: "GalleryCard",

  components: {
    swiper,
    swiperSlide,
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

<template>
  <div>
    <div v-for="item in cafeBoards" :key="item.cafeNo"> 
      <p>이름 : {{item.cafe_name}}</p>
      <p>주소 : {{item.cafe_adr1}} {{item.cafe_adr2}} {{item.cafe_adr3}}</p>
      <p>영업시간 : {{item.cafe_time}}</p>
      <p>연락처 : {{item.cafe_call}}</p>
      <p>소개 : {{item.cafe_content}}</p>

      <ul class="cafe-list">
      <li
        class="cafe-item"
        @mouseover="btnHover = true"
        @mouseleave="btnHover = false"
      >

      <swiper class="swiper" :options="swiperOption">
        <swiper-slide v-for="img in item.cafeImgs" :key="img.cafeImgNo">
          <picture>
            <img v-bind:src="require(`@/assets/cafe/cafeMypage/${img.cafe_img}`)" width="200px;"><br>
          </picture>
        </swiper-slide>
      </swiper>
      </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { swiper, swiperSlide } from "vue-awesome-swiper"
import "swiper/dist/css/swiper.min.css"
import { mapActions, mapState} from 'vuex'

export default {
  name: 'TestTest',
  components: {
    swiper,
    swiperSlide,
  },
  data () {
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
  computed : {
    ...mapState(['cafeBoards'])
  },
  mounted() {
    this.fetchcafeBoardList()
  },
  methods: {
    ...mapActions(['fetchcafeBoardList'])
  }
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

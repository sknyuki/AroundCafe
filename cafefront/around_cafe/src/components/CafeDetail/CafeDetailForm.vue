<template>
  <div class="detail">
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <!-- 카페이름  -->
          <div class="detail-container">
            <header>
              <h1>{{ cafeBoard.cafe_name }}</h1>
            </header>
            <div class="image-grid">
              <picture
                v-for="(img, index) in cafeBoard.cafeImgs.slice(0, 5)"
                :key="index"
                :class="{ row: index === 0 }"
              >
                <source />
                <template v-if="index < 5">
                  <img
                    :src="require(`@/assets/cafe/cafeMypage/${img.cafe_img}`)"
                    alt=""
                  />
                </template>
              </picture>
            </div>

            <div class="detail-image">
              <div class="detail-image-one">
                <button>
                  <picture>
                    <source />
                    <img src="" alt="" />
                  </picture>
                </button>
              </div>
            </div>
            <div class="contaianer">
              <div class="row">
                <div class="col-sm-12 col-md-12 col-lg-8">
                  <div>
                    <div class="detail-content">
                      <div class="detail-info">
                        <div>
                          <v-icon>mdi-phone</v-icon>
                        </div>
                        <div>{{ cafeBoard.cafe_call }}</div>
                      </div>
                      <div class="detail-info">
                        <div>
                          <v-icon>mdi-map-marker-outline</v-icon>
                        </div>
                        <div>
                          <span
                            >{{ cafeBoard.cafe_adr1 }}
                            {{ cafeBoard.cafe_adr2 }}
                            {{ cafeBoard.cafe_adr3 }}</span
                          >
                        </div>
                      </div>
                      <div class="detail-info">
                        <div>
                          <span>
                            <v-icon>mdi-av-timer</v-icon>
                          </span>
                        </div>
                        <div>{{ cafeBoard.cafe_time }}</div>
                      </div>
                    </div>
                    <div class="detail-content">
                      <h3 class="detail-int-header">카페소개</h3>
                      <div class="detail-int-text">
                        <span>{{ cafeBoard.cafe_content }} </span>
                      </div>
                      <div class="detail-int-btn">
                        <button>
                          <span>
                            <span class="view">더 보기</span>
                            <i class="icChevron"></i>
                          </span>
                        </button>
                      </div>
                    </div>
                    <div class="detail-content">
                      <h3 class="detail-int-header">메뉴</h3>
                      <CafeSiteMenuList
                        @submitCheckBasketList="submitCheckBasketList"
                        :cafeNo="cafeNo"
                      />
                    </div>
                    <div class="detail-content">
                      <section class="detail-section">
                        <header class="detail-section-header">
                          <h1 class="review-title">리뷰</h1>
                          <strong class="badge" aria-label="400개">{{
                            reviews.length
                          }}</strong>
                          <a class="text-btn">리뷰쓰기</a>
                        </header>

                        <div class="review-scoreboard">
                          <div class="score-summary">
                            <strong class="average-scroe">
                              <StarRating
                                :inline="true"
                                :read-only="true"
                                :star-size="32"
                                :incremane="0.1"
                                active-color="#fee500"
                                :star-points="[
                                  23, 2, 14, 17, 0, 19, 10, 34, 7, 50, 23, 43,
                                  38, 50, 36, 34, 46, 19, 31, 17,
                                ]"
                              ></StarRating>
                            </strong>
                          </div>
                          <div class="score-detail">
                            <dl class="score-stats-list">
                              <div class="score-stats-item is-active">
                                <dt>5점</dt>
                                <dd>
                                  <div class="bar-graph" aria-hidden>
                                    <div
                                      class="active-bar"
                                      style="width: 82.5088339223%"
                                    ></div>
                                  </div>
                                  <strong class="count" aria-label="467명"
                                    >467</strong
                                  >
                                </dd>
                              </div>

                              <div class="score-stats-item">
                                <dt>4점</dt>
                                <dd>
                                  <div class="bar-graph" aria-hidden>
                                    <div
                                      class="active-bar"
                                      style="width: 15.371024735%"
                                    ></div>
                                  </div>
                                  <strong class="count" aria-label="87명"
                                    >87</strong
                                  >
                                </dd>
                              </div>

                              <div class="score-stats-item">
                                <dt>3점</dt>
                                <dd>
                                  <div class="bar-graph" aria-hidden>
                                    <div
                                      class="active-bar"
                                      style="width: 2.296819788%"
                                    ></div>
                                  </div>
                                  <strong class="count" aria-label="13명"
                                    >13</strong
                                  >
                                </dd>
                              </div>

                              <div class="score-stats-item">
                                <dt>2점</dt>
                                <dd>
                                  <div class="bar-graph" aria-hidden>
                                    <div class="active-bar"></div>
                                  </div>
                                  <strong class="count" aria-label="0명"
                                    >0</strong
                                  >
                                </dd>
                              </div>

                              <div class="score-stats-item">
                                <dt>1점</dt>
                                <dd>
                                  <div class="bar-graph" aria-hidden>
                                    <div class="active-bar"></div>
                                  </div>
                                  <strong class="count" aria-label="0명"
                                    >0</strong
                                  >
                                </dd>
                              </div>
                            </dl>
                          </div>
                        </div>

                        <CafeReviewForm
                          :reviews="reviews"
                          :myHelps="myHelps"
                          :cafeNo="cafeNo"
                        />
                      </section>
                    </div>
                  </div>
                </div>
                <div class="col-lg-4 lg-only">
                  <CafeDetailSidebar :basketList="basketList" />
                </div>
                <div class="detail-content">
                  지도
                  지도지도지도지도지도지도지도지도지도지도지도지도지도지도지도지도지도지도지도지도지도지도지도지도지도지도지도지도지도지도지도지도지도지도지도지도지도지도지도
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import StarRating from "vue-star-rating"
import CafeReviewForm from "@/components/CafeReview/CafeReviewForm.vue"
import CafeDetailSidebar from "@/components/CafeDetail/CafeDetailSidebar.vue"
import CafeSiteMenuList from "../CafeSite/CafeSiteMenuList.vue"
import { mapState, mapActions } from "vuex"

export default {
  name: "CafeDetailForm",
  components: {
    StarRating,
    CafeReviewForm,
    CafeDetailSidebar,
    CafeSiteMenuList,
  },
  props: {
    reviews: {
      type: Array,
      required: true,
    },
    myHelps: {
      type: Array,
      required: true,
    },
    cafeBoard: {
      type: Object,
      required: true,
    },
    cafeNo: {
      type: String,
      required: true,
    },
  },
  computed: {
    ...mapState(["menuLists"]),
  },
  mounted() {
    this.fetchMenuLists(this.cafeNo)
  },

  data() {
    return {
      reviewNo: "",
      basketList: [],
      page: "",
    }
  },

  methods: {
    ...mapActions(["fetchMenuLists"]),
    submitCheckBasketList(CheckBasketList) {
      this.basketList = CheckBasketList
    },
  },
}
</script>

<style lang="scss" scoped>
@import "~@/assets/scss/components/detail/detail";
@import "~@/assets/scss/components/detail/detail-content";
@import "~@/assets/scss/components/review/review-scoreboard";
@import "~@/assets/scss/components/review/score-stats";
.image-grid {
  --gap: 8px;
  --num-cols: 4;
  --row-height: 200px;
  display: grid;
  padding: var(--gap);
  grid-template-columns: repeat(var(--num-cols), 1fr);
  grid-auto-rows: var(--row-height);
  gap: var(--gap);

  img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    border-radius: 12px !important;
  }
}
.row {
  grid-column: span 2 !important;
  grid-row: span 2 !important;
}
/* Anything udner 1024px */
@media screen and (max-width: 1024p) {
  .image-grid {
    --num-cols: 2;
    --row-height: 120px;
  }
}
</style>

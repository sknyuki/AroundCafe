<template>
  <div class="detail">
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <!-- 카페이름  -->
          <div class="detail-container">
            <header>
              <h1>벤투라</h1>
            </header>

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
                        <div>1599-1599</div>
                      </div>
                      <div class="detail-info">
                        <div>
                          <v-icon>mdi-map-marker-outline</v-icon>
                        </div>
                        <div>경기 의정부시 평화로 525</div>
                      </div>
                      <div class="detail-info">
                        <div>
                          <span>
                            <v-icon>mdi-av-timer</v-icon>
                          </span>
                        </div>
                        <div>09:00 - 23:00</div>
                      </div>
                    </div>
                    <div class="detail-content">
                      <h3 class="detail-int-header">카페소개</h3>
                      <div class="detail-int-text">
                        <span
                          >카페를 소개하는 글인데 얼마나 써야할까 나도 항상
                          이런건 고민이다 내 자기소개도 못쓰는데 없는 카페를
                          소개시킨다는 것은 잔인한다.카페를 소개하는 글인데
                          얼마나 써야할까 나도 항상 이런건 고민이다 내
                          자기소개도 못쓰는데 없는 카페를 소개시킨다는 것은
                          잔인한다. 카페를 소개하는 글인데 얼마나 써야할까 나도
                          항상 이런건 고민이다 내 자기소개도 못쓰는데 없는
                          카페를 소개시킨다는 것은 잔인한다. 카페를 소개하는
                          글인데 얼마나 써야할까 나도 항상 이런건 고민이다 내
                          자기소개도 못쓰는데 없는 카페를 소개시킨다는 것은
                          잔인한다. 카페를 소개하는 글인데 얼마나 써야할까 나도
                          항상 이런건 고민이다 내 자기소개도 못쓰는데 없는
                          카페를 소개시킨다는 것은 잔인한다.카페를 소개하는
                          글인데 얼마나 써야할까 나도 항상 이런건 고민이다 내
                          자기소개도 못쓰는데 없는 카페를 소개시킨다는 것은
                          잔인한다. 카페를 소개하는 글인데 얼마나 써야할까 나도
                          항상 이런건 고민이다 내 자기소개도 못쓰는데 없는
                          카페를 소개시킨다는 것은 잔인한다. 카페를 소개하는
                          글인데 얼마나 써야할까 나도 항상 이런건 고민이다 내
                          자기소개도 못쓰는데 없는 카페를 소개시킨다는 것은
                          잔인한다.
                        </span>
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
                      />
                    </div>
                    <div class="detail-content">
                      <section class="detail-section">
                        <header class="detail-section-header">
                          <h1 class="review-title">리뷰</h1>
                          <strong class="badge" aria-label="400개">400</strong>
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

                        <CafeReviewForm :reviews="reviews" :myHelps="myHelps" />
                      </section>
                    </div>
                  </div>
                </div>
                <div class="col-lg-4 lg-only">
                  <CafeDetailSidebar :baskeList="baskeList" />
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
  },

  data() {
    return {
      reviewNo: "",
      basketList: [],
    }
  },
  methods: {
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
</style>

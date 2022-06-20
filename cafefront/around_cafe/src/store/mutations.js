import {
  FETCH_CAFE_BOARD_LIST,
  FETCH_CAFE_BOARD,
  FETCH_MENU_LISTS,
  FETCH_CAFE_IMG_LISTS,
  FETCH_REVIEW_LIST,
  FETCH_REVIEW,
  FETCH_LIKES_LIST,
  FETCH_QNA_LIST,
  FETCH_QNA_LISTS,
  FETCH_CAFE_REVIEW_LISTS,
} from "./mutation-types"

export default {
  [FETCH_CAFE_BOARD_LIST](state, cafeBoards) {
    state.cafeBoards = cafeBoards
  },

  [FETCH_CAFE_BOARD](state, cafeBoard) {
    state.cafeBoard = cafeBoard
  },

  [FETCH_MENU_LISTS](state, menuLists) {
    state.menuLists = menuLists
  },

  [FETCH_CAFE_IMG_LISTS](state, cafeImgLists) {
    state.cafeImgLists = cafeImgLists
  },

  [FETCH_REVIEW_LIST](state, reviews) {
    state.reviews = reviews
  },

  [FETCH_REVIEW](state, review) {
    state.review = review
  },
  [FETCH_LIKES_LIST](state, likes) {
    state.likes = likes
  },

  [FETCH_QNA_LIST](state, qnaList) {
    state.qnaList = qnaList
  },

  [FETCH_QNA_LISTS](state, qnaLists) {
    state.qnaLists = qnaLists
  },

  [FETCH_CAFE_REVIEW_LISTS](state, cafeReviewLists) {
    state.cafeReviewLists = cafeReviewLists
  },
}

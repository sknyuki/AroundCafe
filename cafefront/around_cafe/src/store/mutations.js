import {
  FETCH_CAFE_BOARD_LIST,
  FETCH_CAFE_BOARD,
  FETCH_MENU_LISTS,
  FETCH_CAFE_IMG_LISTS,
  FETCH_REVIEW_LIST,
  FETCH_REVIEW,
  FETCH_HELPS_LIST,
  FETCH_QNA_LIST,
  FETCH_QNA_LISTS,
  FETCH_USER,
  FETCH_HELP,
  FETCH_MY_HELPS_LIST,
} from "./mutation-types"

export default {
  [FETCH_USER](state, user) {
    state.user = user
  },

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
  [FETCH_HELPS_LIST](state, helps) {
    state.helps = helps
  },

  [FETCH_HELP](state, help) {
    state.help = help
  },
  [FETCH_MY_HELPS_LIST](state, myHelps) {
    state.myHelps = myHelps
  },

  [FETCH_QNA_LIST](state, qnaList) {
    state.qnaList = qnaList
  },

  [FETCH_QNA_LISTS](state, qnaLists) {
    state.qnaLists = qnaLists
  },
}

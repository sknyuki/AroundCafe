import {
  FETCH_CAFE_BOARD_LIST,
  FETCH_CAFE_BOARD,
  FETCH_ORDER_CAFE_LIST,
  FETCH_MENU_LISTS,
  FETCH_CAFE_IMG_LISTS,
  FETCH_REVIEW_LIST,
  FETCH_USER_REVIEW_LIST,
  FETCH_CAFE_REVIEW_LIST,
  FETCH_REVIEW,
  FETCH_HELPS_LIST,
  FETCH_QNA_LIST,
  FETCH_QNA_LISTS,
  FETCH_USER,
  FETCH_USER_INFO,
  FETCH_HELP,
  FETCH_MY_HELPS_LIST,

  //공지사항
  FETCH_NOTICE_BOARD_LIST,
  FETCH_NOTICE_BOARD,
  FETCH_LIKE,
  FETCH_LIKES_LIST,
  FETCH_MY_LIKES_LIST,
  FETCH_LIKES_ID_LISTS,
  FETCH_MAIN_LIST,
  FETCH_ADMIN_MEMBER_LIST,
} from "./mutation-types"

export default {
  [FETCH_USER](state, user) {
    state.user = user
  },

  [FETCH_USER_INFO](state, userInfo) {
    state.userInfo = userInfo
  },

  [FETCH_CAFE_BOARD_LIST](state, cafeBoards) {
    state.cafeBoards = cafeBoards
  },

  [FETCH_CAFE_BOARD](state, cafeBoard) {
    state.cafeBoard = cafeBoard
  },
  [FETCH_ORDER_CAFE_LIST](state, orderCafeLists) {
    state.orderCafeLists = orderCafeLists
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
  [FETCH_USER_REVIEW_LIST](state, userReviews) {
    state.userReviews = userReviews
  },
  [FETCH_CAFE_REVIEW_LIST](state, cafeReviews) {
    state.cafeReviews = cafeReviews
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

  //공지사항
  [FETCH_NOTICE_BOARD_LIST](state, noticeBoards) {
    state.noticeBoards = noticeBoards
  },

  [FETCH_NOTICE_BOARD](state, noticeBoard) {
    state.noticeBoard = noticeBoard
  },

  [FETCH_LIKES_LIST](state, likes) {
    state.likes = likes
  },

  [FETCH_LIKE](state, like) {
    state.like = like
  },
  [FETCH_MY_LIKES_LIST](state, myLikes) {
    state.myLikes = myLikes
  },
  [FETCH_LIKES_ID_LISTS](state, likesIdLists) {
    state.likesIdLists = likesIdLists
  },

  [FETCH_MAIN_LIST](state, mainlist) {
    state.mainlist = mainlist
  },
  [FETCH_ADMIN_MEMBER_LIST](state, adminlist) {
    state.adminlist = adminlist
  },
}

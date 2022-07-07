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

  //공지사항
  FETCH_NOTICE_BOARD_LIST,
  FETCH_NOTICE_BOARD,
  FETCH_HELP,
  FETCH_MY_HELPS_LIST,
  FETCH_USER,
  FETCH_USER_INFO,
  FETCH_LIKE,
  FETCH_LIKES_LIST,
  FETCH_MY_LIKES_LIST,
  FETCH_MAIN_LIST,
  FETCH_PAYMENT_DETAIL,
  FETCH_USER_SPEND_LIST,
} from "./mutation-types"

import axios from "axios"
import UserService from "@/services/userService.js"

export default {
  fetchUser({ commit }) {
    return commit(FETCH_USER, UserService.getUserInfo())
  },
  fetchUserInfo({ commit }) {
    return axios.get("http://localhost:7777/members").then((res) => {
      commit(FETCH_USER_INFO, res.data)
    })
  },

  fetchPaymentDetail({ commit }, paymentNo) {
    return axios
      .get(`http://localhost:7777/payment/${paymentNo}`)
      .then((res) => {
        commit(FETCH_PAYMENT_DETAIL, res.data)
      })
  },

  fetchcafeBoardList({ commit }) {
    return axios.get("http://localhost:7777/cafe/list").then((res) => {
      commit(FETCH_CAFE_BOARD_LIST, res.data)
      // console.log("값이 넘어왔는지 체크")
      // console.log(res.data)
    })
  },
  fetchcafeBoard({ commit }, cafeNo) {
    return axios
      .get(`http://localhost:7777/cafe/mypage/read/${cafeNo}`)
      .then((res) => {
        commit(FETCH_CAFE_BOARD, res.data)
      })
  },
  fetchOrderCafeList({ commit }, cafeNo) {
    return axios
      .get(`http://localhost:7777/payment/cafe/${cafeNo}`)
      .then((res) => {
        commit(FETCH_ORDER_CAFE_LIST, res.data)
      })
  },

  fetchMenuLists({ commit }, cafeNo) {
    return axios
      .get(`http://localhost:7777/menu/list/${cafeNo}`)
      .then((res) => {
        commit(FETCH_MENU_LISTS, res.data)
      })
  },

  fetchCafeImgLists({ commit }, memNo) {
    return axios
      .get(`http://localhost:7777/cafe/mypageImg/${memNo}`)
      .then((res) => {
        commit(FETCH_CAFE_IMG_LISTS, res.data)
      })
  },

  fetchReviewList({ commit }, { cafeNo, memNo }) {
    return axios
      .get(`http://localhost:7777/cafe/review/list/${cafeNo}/${memNo}`)
      .then((res) => {
        commit(FETCH_REVIEW_LIST, res.data)
      })
  },
  fetchUserReviewList({ commit }, memNo) {
    return axios
      .get(`http://localhost:7777/cafe/review/user/list/${memNo}`)
      .then((res) => {
        commit(FETCH_USER_REVIEW_LIST, res.data)
      })
  },
  fetchReview({ commit }, reviewNo) {
    return axios
      .get(`http://localhost:7777/cafe/review/${reviewNo}`)
      .then((res) => {
        commit(FETCH_REVIEW, res.data)
      })
  },
  fetchCafeReviewList({ commit }, cafeNo) {
    return axios
      .get(`http://localhost:7777/cafe/likes/list/${cafeNo}`)
      .then((res) => {
        commit(FETCH_CAFE_REVIEW_LIST, res.data)
      })
  },
  fetchHelpsList({ commit }, reviewNo) {
    return axios
      .get(`http://localhost:7777/cafe/review/list/${reviewNo}`)
      .then((res) => {
        commit(FETCH_HELPS_LIST, res.data)
      })
  },
  fetchQnAList({ commit }, qnaNo) {
    return axios
      .get(`http://localhost:7777/qna/memberRead/${qnaNo}`)
      .then((res) => {
        commit(FETCH_QNA_LIST, res.data)
      })
  },

  fetchQnALists({ commit }, membNo) {
    return axios
      .get(`http://localhost:7777/qna/memberList/${membNo}`)
      .then((res) => {
        commit(FETCH_QNA_LISTS, res.data)
      })
  },

  //공지사항

  fetchNoticeBoardList({ commit }) {
    return axios.get(`http://localhost:7777/noticeBoard/list/`).then((res) => {
      commit(FETCH_NOTICE_BOARD_LIST, res.data)
    })
  },
  fetchNoticeBoard({ commit }, boardNo) {
    return axios
      .get(`http://localhost:7777/noticeBoard/${boardNo}`)
      .then((res) => {
        commit(FETCH_NOTICE_BOARD, res.data)
      })
  },
  fetchHelp({ commit }, { reviewNo, membNo }) {
    return axios
      .get(`http://localhost:7777/cafe/like/${reviewNo}/${membNo}`)
      .then((res) => {
        commit(FETCH_HELP, res.data)
      })
  },
  fetchMyHelpsList({ commit }, membNo) {
    return axios
      .get(`http://localhost:7777/cafe/like/list/my/${membNo}`)
      .then((res) => {
        commit(FETCH_MY_HELPS_LIST, res.data)
      })
  },
  fetchLikesList({ commit }, cafeNo) {
    return axios
      .get(`http://localhost:7777/cafe/likes/list/${cafeNo}`)
      .then((res) => {
        commit(FETCH_LIKES_LIST, res.data)
      })
  },
  fetchLike({ commit }, { cafeNo, membNo }) {
    return axios
      .get(`http://localhost:7777/cafe/likes/${cafeNo}/${membNo}`)
      .then((res) => {
        commit(FETCH_LIKE, res.data)
      })
  },
  fetchMyLikesList({ commit }, membNo) {
    return axios
      .get(`http://localhost:7777/cafe/likes/list/my/${membNo}`)
      .then((res) => {
        commit(FETCH_MY_LIKES_LIST, res.data)
      })
  },
  fetchMainList({ commit }) {
    return axios.get(`http://localhost:7777/cafe/main/list`).then((res) => {
      commit(FETCH_MAIN_LIST, res.data)
      // console.log("main list결과값 확인")
      // console.log(res.data)
    })
  },

  fetchUserSpendList({ commit }, memNo) {
    return axios
      .get(`http://localhost:7777/payment/list/${memNo}`)
      .then((res) => {
        commit(FETCH_USER_SPEND_LIST, res.data)
        // console.log("멤버 소비 리스트 출력")
        // console.log(res.data)
      })
  },
}

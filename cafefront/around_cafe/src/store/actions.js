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
} from "./mutation-types"

import axios from "axios"

export default {
  fetchcafeBoardList({ commit }) {
    return axios.get("http://localhost:7777/cafe/list").then((res) => {
      commit(FETCH_CAFE_BOARD_LIST, res.data)
      //console.log(res)
    })
  },
  fetchcafeBoard({ commit }) {
    return axios.get("http://localhost:7777/cafe/mypage/read").then((res) => {
      commit(FETCH_CAFE_BOARD, res.data)
    })
  },

  fetchMenuLists({ commit }) {
    return axios.get("http://localhost:7777/menu/list").then((res) => {
      commit(FETCH_MENU_LISTS, res.data)
    })
  },

  fetchCafeImgLists({ commit }, cafeNo) {
    return axios
      .get(`http://localhost:7777/cafe/mypageImg/${cafeNo}`)
      .then((res) => {
        commit(FETCH_CAFE_IMG_LISTS, res.data)
      })
  },

  fetchReviewList({ commit }, membNo) {
    return axios
      .get(`http://localhost:7777/cafe/review/list/${membNo}`)
      .then((res) => {
        commit(FETCH_REVIEW_LIST, res.data)
      })
  },
  fetchReview({ commit }, reviewNo) {
    return axios
      .get(`http://localhost:7777/cafe/review/${reviewNo}`)
      .then((res) => {
        commit(FETCH_REVIEW, res.data)
      })
  },
  fetchLikesList({ commit }, reviewNo) {
    return axios
      .get(`http://localhost:7777/cafe/review/${reviewNo}/like`)
      .then((res) => {
        commit(FETCH_LIKES_LIST, res.data)
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
}

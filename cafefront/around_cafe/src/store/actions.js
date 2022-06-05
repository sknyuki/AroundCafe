import{
    FETCH_CAFE_BOARD_LIST,
    FETCH_CAFE_BOARD,
    FETCH_MENU_LISTS,
    FETCH_CAFE_IMG_LISTS

}from './mutation-types'

import axios from 'axios'

export default {
  fetchcafeBoardList ({ commit }) {
        return axios.get('http://localhost:7777/Cafe/list')
            .then((res) => {
                commit(FETCH_CAFE_BOARD_LIST, res.data)
               //console.log(res)
            })
    },
    fetchcafeBoard ({ commit }) {
        return axios.get('http://localhost:7777/cafe/mypage/read')
            .then((res) => {
                commit(FETCH_CAFE_BOARD, res.data)
            })
    },

    fetchMenuLists ({ commit }) {
        return axios.get('http://localhost:7777/menu/list')
            .then((res) => {
                commit(FETCH_MENU_LISTS, res.data)
            })
    },

    fetchCafeImgLists ({ commit },cafeNo) {
        return axios.get(`http://localhost:7777/cafe/mypageImg/${cafeNo}`)
            .then((res) => {
                commit(FETCH_CAFE_IMG_LISTS, res.data)
            })
    },
}
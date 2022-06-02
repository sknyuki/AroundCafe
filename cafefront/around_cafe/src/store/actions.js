import{
/*CafeMyPage  List작업시 열어주세요
    FETCH_CAFE_BOARD_LIST,
    FETCH_CAFE_BOARD,*/
    FETCH_MENU_LISTS,
    FETCH_MENU_SIG_LISTS,
    FETCH_MENU_SOLD_OUT_LISTS

}from './mutation-types'

import axios from 'axios'

export default {
  /*  List작업시 열어주세요
  fetchcafeBoardList ({ commit }) {
        return axios.get('http://localhost:7777/Cafe/list')
            .then((res) => {
                commit(FETCH_CAFE_BOARD_LIST, res.data)
               //console.log(res)
            })
    },
    fetchcafeBoard ({ commit }, membNo) {
        return axios.get(`http://localhost:7777/Cafe/${membNo}`)
            .then((res) => {
                commit(FETCH_CAFE_BOARD, res.data)
            })
    },*/

    fetchMenuLists ({ commit }) {
        return axios.get('http://localhost:7777/menu/list')
            .then((res) => {
                commit(FETCH_MENU_LISTS, res.data)
            })
    },
    fetchMenuSigLists ({ commit }) {
        return axios.get('http://localhost:7777/menu/signatureList')
            .then((res) => {
                commit(FETCH_MENU_SIG_LISTS, res.data)
            })
    },
    fetchMenuSoldOutLists ({ commit }) {
        return axios.get('http://localhost:7777/menu/soldOutList')
            .then((res) => {
                commit(FETCH_MENU_SOLD_OUT_LISTS, res.data)
            })
    },
}
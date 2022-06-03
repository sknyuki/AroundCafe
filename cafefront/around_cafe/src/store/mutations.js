import {
    //CafeMyPage 작업시 열어주세요
  /*  FETCH_CAFE_BOARD_LIST,
    FETCH_CAFE_BOARD*/
    FETCH_MENU_LISTS,
    FETCH_MENU_SIG_LISTS,
    FETCH_MENU_SOLD_OUT_LISTS


} from './mutation-types'

export default {

    //CafeMyPage

    /*[FETCH_CAFE_BOARD_LIST] (state, cafeBoards) {
        state.cafeBoards = cafeBoards
   },

    [FETCH_CAFE_BOARD ] (state, cafeBoard) {
        state.cafeBoard = cafeBoard
   },*/

    [FETCH_MENU_LISTS] (state, menuLists) {
    state.menuLists = menuLists
    },
    [FETCH_MENU_SIG_LISTS] (state, menuSigLists) {
        state.menuSigLists = menuSigLists
    },
    [FETCH_MENU_SOLD_OUT_LISTS] (state, menuSoldOutLists) {
        state.menuSoldOutLists = menuSoldOutLists
    },
}
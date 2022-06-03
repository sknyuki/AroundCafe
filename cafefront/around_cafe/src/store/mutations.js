import {
    //CafeMyPage 작업시 열어주세요
  /*  FETCH_CAFE_BOARD_LIST,
    FETCH_CAFE_BOARD*/
    FETCH_MENU_LISTS,


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
}
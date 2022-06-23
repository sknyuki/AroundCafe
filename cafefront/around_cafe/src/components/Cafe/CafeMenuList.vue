<template>
  <div class="menu-list-content">
    <ul class="menu-list">
      <div v-if="this.fileterArray.length == 0">
        <li v-for="item in listData" :key="item.menu_no" class="menu-item">
          <a href="">
            <div class="menu-card">
              <div class="menu-card-image">
                <v-img
                  v-if="item.menu_img == null"
                  v-bind:src="require(`@/assets/cafe/cafeMenu/imgNull.png`)"
                />
                <v-img
                  v-if="item.menu_img != null"
                  v-bind:src="
                    require(`@/assets/cafe/cafeMenu/${item.menu_img}`)
                  "
                />
                <div class="test">
                  <span>Signature</span>
                </div>
              </div>

              <div class="menu-card-info">
                <div class="menu-card-title">{{ item.menu_name }}</div>
                <div class="menu-card-detail">
                  <p>
                    {{ item.menu_content }}
                  </p>
                </div>
                <div class="menu-card-price">
                  {{ item.menu_price | pricePoint }}원
                </div>
              </div>
            </div>
          </a>

          <div class="menu-item-btn">
            <button class="modify-button" aria-label="해당 메뉴 수정하기">
              <i class="icBell"></i>
            </button>
            <button
              @click="onDeleteMenu"
              class="delete-button"
              aria-label="해당 메뉴 삭제하기"
              type="button"
            >
              <i class="icClose"></i>
            </button>
          </div>
        </li>
      </div>
      <div v-else>
        <li v-for="item in fileterArray" :key="item.menu_no" class="menu-item">
          <a href="">
            <div class="menu-card">
              <div class="menu-card-image">
                <v-img
                  v-if="item.menu_img == null"
                  v-bind:src="require(`@/assets/cafe/cafeMenu/imgNull.png`)"
                />
                <v-img
                  v-if="item.menu_img != null"
                  v-bind:src="
                    require(`@/assets/cafe/cafeMenu/${item.menu_img}`)
                  "
                />
                <div class="test">
                  <span>Signature</span>
                </div>
              </div>

              <div class="menu-card-info">
                <div class="menu-card-title">{{ item.menu_name }}</div>
                <div class="menu-card-detail">
                  <p>
                    {{ item.menu_content }}
                  </p>
                </div>
                <div class="menu-card-price">
                  {{ item.menu_price | pricePoint }}원
                </div>
              </div>
            </div>
          </a>

          <div class="menu-item-btn">
            <button class="modify-button" aria-label="해당 메뉴 수정하기">
              <i class="icBell"></i>
            </button>
            <button
              @click="onDeleteMenu"
              class="delete-button"
              aria-label="해당 메뉴 삭제하기"
              type="button"
            >
              <i class="icClose"></i>
            </button>
          </div>
        </li>
      </div>
    </ul>
    <PaginationForm
      :pageSetting="pageDataSetting(total, limit, block, this.page)"
      @paging="pagingMethod"
    />
  </div>
</template>
<script>
import axios from "axios"
import PaginationForm from "@/components/PaginationForm.vue"

export default {
  name: "CafeMenuList",
  components: {
    PaginationForm,
  },
  props: {
    fileterArray: {
      type: Array,
      required: true,
    },
    menuLists: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      signatureNo: "",
      modi_name: "",
      listData: [],
      page: 1,
      limit: 5,
      block: 5,
      pageNo: "",
      total: "",
    }
  },
  mounted() {
    this.pagingMethod(this.page)
  },

  methods: {
    onSignatureMenu(item) {
      this.signatureNo = item.menu_no
    },
    onModifyMenu() {},
    onDeleteMenu() {
      this.menuNo = this.deleteNo
      axios
        .delete(`http://localhost:7777/menu/delete/${this.menuNo}`)
        .then(() => {
          alert("삭제가 완료되었습니다!")
          this.$router.go()
        })
        .catch(() => {
          alert("삭제실패!")
        })
    },
    pagingMethod(page) {
      this.listData = this.menuLists.slice(
        (page - 1) * this.limit,
        page * this.limit
      )
      this.page = page
      let total = this.menuLists.length
      this.pageDataSetting(total, this.limit, this.block, page)
    },
    pageDataSetting(total, limit, block, page) {
      total = this.menuLists.length
      const totalPage = Math.ceil(total / limit)
      let currentPage = page
      const first =
        currentPage > 1 ? parseInt(currentPage, 10) - parseInt(1, 10) : null
      const end =
        totalPage !== currentPage
          ? parseInt(currentPage, 10) + parseInt(1, 10)
          : null

      let startIndex = (Math.ceil(currentPage / block) - 1) * block + 1
      let endIndex =
        startIndex + block > totalPage ? totalPage : startIndex + block - 1
      let list = []
      for (let index = startIndex; index <= endIndex; index++) {
        list.push(index)
      }
      return { first, end, list, currentPage }
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/cafe/cafe-menu-list";
.test {
  @include text-style(14, $white);
  position: absolute;
  z-index: 3;
  top: 0%;
  left: 52%;
  width: 48%;
  height: 14%;
  background-color: $red;

  span {
    margin-left: 6px;
    padding: 1px;
  }
}
</style>

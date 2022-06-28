<template>
  <div class="menu-list-content">
    <ul class="menu-list">
      <li v-for="item in listData" :key="item.menu_no" class="menu-item">
        <a href="">
          <div class="menu-card">
            <div class="menu-card-image">
              <template>
                <img
                  position="relative"
                  v-if="item.menu_img != null"
                  v-bind:src="
                    require(`@/assets/cafe/cafeMenu/${item.menu_img}`)
                  "
                />
              </template>
              <template v-if="item.sold_out == true">
                <img
                  position="absolute"
                  v-bind:src="require(`@/assets/images/sold_out2.webp`)"
                />
              </template>
              <template>
                <img
                  v-if="item.menu_img == null"
                  v-bind:src="require(`@/assets/cafe/cafeMenu/imgNull.png`)"
                />
              </template>

              <div v-if="item.signature == true" class="test">
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
          <button @click="addToBasket(item)">
            <i class="icCart"></i>
          </button>
        </div>
      </li>
    </ul>
    <PaginationForm
      :pageSetting="pageDataSetting(total, limit, block, this.page)"
      @paging="pagingMethod"
    />
  </div>
</template>
<script>
import PaginationForm from "@/components/PaginationForm.vue"
import { mapState, mapActions } from "vuex"

export default {
  name: "CafeMenuList",
  components: { PaginationForm },
  props: {
    cafeNo: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      signatureNo: "",
      CheckbasketList: [],
      listData: [],
      page: 1,
      limit: 3,
      block: 5,
      pageNo: "",
      total: "",
    }
  },
  computed: {
    ...mapState(["menuLists"]),
  },
  mounted() {
    this.fetchMenuLists(this.cafeNo)
    this.pagingMethod(this.page)
  },
  methods: {
    ...mapActions(["fetchMenuLists"]),
    addToBasket(item) {
      for (let i = 0; i <= 3; i++)
        if (this.CheckBasketList.includes(item)) {
          console.log(JSON.stringify(this.CheckBasketList))
          console.log(item)
          alert("해당제품은 이미 장바구니에 담으셨습니다!")
        } else {
          this.CheckBasketList.push(item)
          console.log("값을 정상적으로 저장하였습니다.")

          this.$emit("submitCheckBasketList", this.CheckBasketList)
        }
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
</style>

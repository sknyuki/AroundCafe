<template>
  <div class="menu-list-content">
    <ul class="menu-list">
      <li v-for="item in menuLists" :key="item.menu_no" class="menu-item">
        <a href="">
          <div class="menu-card">
            <div class="menu-card-image">
              <img src="@/assets/images/cold.jpg" alt="" />
              <div class="menu-mark">
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
  </div>
</template>
<script>
import axios from "axios"

export default {
  name: "CafeMenuList",
  props: {
    listData: {
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
      CheckbasketList: [],
    }
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
    sendPage(page) {
      this.$emit("paging", page)
    },
    addToBasket(item) {
      for (let i = 0; i <= this.CheckBasketList.length; i++)
        console.log(this.CheckBasketList.length)
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
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/cafe/cafe-menu-list";
</style>

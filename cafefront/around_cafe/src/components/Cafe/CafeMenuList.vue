<template>
  <div class="menu-list-content">
    <ul class="menu-list">
      <li v-for="item in menuLists" :key="item.menu_no" class="menu-item">
        <a href="">
          <div class="menu-card">
            <div class="menu-card-image">
              <img src="@/assets/images/cold.jpg" alt="" />
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
    </ul>
  </div>
</template>
<script>
import axios from "axios"

export default {
  name: "CafeMenuList",
  props: {
    menuLists: {
      type: Array,
    },
  },
  data() {
    return {
      signatureNo: "",
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

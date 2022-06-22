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
          <v-btn class="to-basket" icon @click="tobasket(item)">
            <v-icon>mdi-cart</v-icon>
          </v-btn>
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
  },
  data() {
    return {
      signatureNo: "",
      Checkbasketlist: [],
      menuLists: [
        {
          menu_no: 0,
          sold_out: true,
          menu_name: "아메리카노",
          menu_price: 4900,
          PerMenuPrices: 4900,
          number: 1,
          menu_content:
            "카페 모카와 유사한 음료로는 모카치노가 있다. 모카치노는 카푸치노에 초콜릿 시럽을 넣은 것이다.",
        },
        {
          menu_no: 1,
          sold_out: true,
          menu_name: "카페모카",
          menu_price: 6700,
          PerMenuPrices: 6700,
          number: 1,
          menu_content: "마시써요",
        },
        {
          menu_no: 2,
          sold_out: true,
          menu_name: "마끼아또",
          menu_price: 7200,
          PerMenuPrices: 7200,
          number: 1,
          menu_content: "마시써요",
        },
        {
          menu_no: 3,
          sold_out: true,
          menu_name: "프라프치노",
          menu_price: 7200,
          PerMenuPrices: 7200,
          number: 1,
          menu_content: "마시써요",
        },
        {
          menu_no: 4,
          sold_out: true,
          menu_name: "카페모카",
          menu_price: 6700,
          PerMenuPrices: 6700,
          number: 1,
          menu_content: "마시써요",
        },
        {
          menu_no: 5,
          sold_out: true,
          menu_name: "망고스무디",
          menu_price: 6700,
          PerMenuPrices: 6700,
          number: 1,
          menu_content: "마시써요",
        },
        {
          menu_no: 6,
          sold_out: true,
          menu_name: "딸기스무디",
          menu_price: 6700,
          PerMenuPrices: 6700,
          number: 1,
          menu_content: "마시써요",
        },
        {
          menu_no: 7,
          sold_out: true,
          menu_name: "꺄라멜마끼아또",
          menu_price: 6700,
          PerMenuPrices: 6700,
          number: 1,
          menu_content: "마시써요",
        },
        {
          menu_no: 8,
          sold_out: true,
          menu_name: "핫초코",
          menu_price: 6700,
          PerMenuPrices: 6700,
          number: 1,
          menu_content: "마시써요",
        },
      ],
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
    tobasket(item) {
      // console.log(item)
      // console.log(this.Checkbasketlist.length)
      // console.log(JSON.stringify(this.Checkbasketlist))
      for (let i = 0; i <= this.Checkbasketlist.length; i++)
        console.log(this.Checkbasketlist.length)
      if (this.Checkbasketlist.includes(item)) {
        console.log(JSON.stringify(this.Checkbasketlist))
        console.log(item)
        alert("해당제품은 이미 장바구니에 담으셨습니다!")
      } else {
        this.Checkbasketlist.push(item)
        console.log("값을 정상적으로 저장하였습니다.")

        this.$emit("submitCheckbasketlist", this.Checkbasketlist)
        // console.log("넘어가는 파일:")
        // console.log(this.Checkbasketlist)
      }
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

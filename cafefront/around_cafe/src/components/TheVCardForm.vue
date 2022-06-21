<template>
  <div>
    <br />
    <h4>메뉴 일람</h4>
    <br>
    <v-divider></v-divider>
    <br>
    <v-card class="pa-3" flat>
      <v-row>
        <v-col
          v-for="menuList in menuLists"
          :key="menuList.menu_no"
          cols="12"
          sm="6"
          md="4"
          lg="3"
        >
          <div v-if="menuList.sold_out">
            <v-hover v-slot="{ hover }" close-delay="50">
              <v-card
                :elevation="hover ? 16 : 2"
                class="{ 'on-hover': hover }"
                @click="handleClick(menuLists)"
              >
                <v-list-item>
                  <v-list-item-title>
                    {{ menuList.menu_name }}
                  </v-list-item-title>
                </v-list-item>

                <v-divider></v-divider>
                <img :src="showFile()" width="100%" />

                <v-row>
                  <v-card-text class="caption">
                    가격: {{ menuList.menu_price }}
                    <br />
                    설명: {{ menuList.menu_content }}
                  </v-card-text>
                  <div class="버튼">
                    <v-row justify="center">
                      <v-col cols="3">
                        <v-btn
                          text
                          color="black"
                          rounded
                          x-large
                          style="padding: 10px; width: 150px"
                          @click="SelectdeMenuSubmit()"
                          >장바구니에 담기
                        </v-btn>
                      </v-col>
                    </v-row>
                  </div>
                </v-row>
              </v-card>
            </v-hover>
          </div>
        </v-col>
      </v-row>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "TheVcardForm",
  data() {
    return {
      menuLists: [
        {
          menu_no: 0,
          sold_out: true,
          menu_name: "아메리카노",
          menu_price: 4900,
          PerMenuPrices: 0,
          number: 0,
          menu_content:
            "카페 모카와 유사한 음료로는 모카치노가 있다. 모카치노는 카푸치노에 초콜릿 시럽을 넣은 것이다.",
        },
        {
          menu_no: 1,
          sold_out: true,
          menu_name: "카페모카",
          menu_price: 6700,
          PerMenuPrices: 0,
          number: 0,
          menu_content: "마시써요",
        },
        {
          menu_no: 2,
          sold_out: true,
          menu_name: "마끼아또",
          menu_price: 7200,
          PerMenuPrices: 0,
          number: 0,
          menu_content: "마시써요",
        },
        {
          menu_no: 3,
          sold_out: true,
          menu_name: "프라프치노",
          menu_price: 7200,
          PerMenuPrices: 0,
          number: 0,
          menu_content: "마시써요",
        },
      ],
    }
  },
  props: {
    // menuLists: {
    //     type: Array,
    //     required: true
    // },
    // cafeNo:{
    //     type: String,
    //  },
  },
  methods: {
    showFile() {
      //   try {
      //     return require(`@/assets/cafe/cafeMenu/${menuLists.menu_img}`)
      // } catch (e) {
      //             return require(`@/assets/logo.png`)
      //             }
      try {
        return require(`@/assets/moka.jpg`)
      } catch (e) {
        return require(`@/assets/moka.jpg`)
      }
    },
    handleClick(menuLists) {
      this.$router.push({
        name: "CafeSiteMenuSelectPage",
        params: { menuNo: menuLists.menuNo.toString() },
      })
    },
  },
}
</script>

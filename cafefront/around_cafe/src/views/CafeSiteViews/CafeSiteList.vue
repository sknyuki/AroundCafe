<template>
  <div>
    <cafe-image></cafe-image>
    <div class="container">
      <div class="row">
        <div class="col-sm-8 col-md-9 col-lg-9">
          <cafe-site-component :cafeBoard="cafeBoard" />
          <br />
          <cafe-site-menu-list @submitCheckbasketlist="submitCheckbasketlist" />

          <!--<the-v-card-form />-->

          <!--<the-v-card-form :menuList='menuList'/>-->
        </div>
        <div class="col-sm-4 col-md-3 col-lg-3">
          <cafe-sticky-sidebar
            :basketlist="basketlist"
            @SelectdeMenuSubmit="SelectdeMenuSubmit"
          />
        </div>
      </div>
      <header>
        <h1>리뷰</h1>
      </header>
      <CafeReviewForm :reviews="reviews" />
    </div>
  </div>
</template>
<script>
import CafeStickySidebar from "@/components/CafeSite/CafeStickySidebar.vue"
import CafeReviewForm from "@/components/CafeReview/CafeReviewForm.vue"
import CafeSiteComponent from "@/components/CafeSite/CafeSiteComponent.vue"
import CafeImage from "../../components/CafeSite/CafeImage.vue"
import CafeSiteMenuList from "@/components/CafeSite/CafeSiteMenuList.vue"
//import TheVCardForm from "@/components/TheVCardForm.vue"
/* register작업 완료 후 store 열어준 후 열어주세요
import axios from 'axios'
import { mapState } from 'vuex'*/
export default {
  components: {
    CafeStickySidebar,
    CafeReviewForm,
    CafeImage,
    CafeSiteComponent,
    CafeSiteMenuList,
  },
  name: "CafeSiteList",

  //components: { CafeSiteComponent },
  //components: { TheVCardForm },
  props: {
    // Checkbasketlist: {
    //   type: Array,
    // },
    cafeNo: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      basketlist: [],
    }
  },

  methods: {
    submitCheckbasketlist(Checkbasketlist) {
      this.basketlist = Checkbasketlist
      // console.log("받아낸값")
      //console.log(this.basketlist)
    },
  },
  computed: {
    /*      ...mapState(['cafeBoard'],['menuLists'],["reviews"])
        },
        mounted() {
            this.fetchcafeBoard(),
            this.fetchMenuLists(),
            this.fetchReviewList(1)

        },
        methods : {
            주문 
            ...mapActions(['fetchbasket']),
            리뷰
            ...mapActions(["fetchReviewList"]),
            
            onReviewDialog() {
            this.reviewDialog = true
            },

            closeDialog() {
            this.reviewDialog = false
            },
            SelectdeMenuSubmit(payload) {
                  const { totalPrice, baskets.PerMenuPrices, baskets.Price, baskets.menu, baskets.number} = payload

                let formData = new FormData()

                let fileInfo = {
                    cafe_name: cafe_name,
                    cafe_time: cafe_time,
                    cafe_content: cafe_content,
                    cafe_call: cafe_call,
                    cafe_adr1:cafe_adr1,
                    cafe_adr2:cafe_adr2,
                    cafe_adr3:cafe_adr3,
                    cafe_bis_no:this.cafeBoard.cafe_bis_no


                }

                formData.append(
                    "info", new Blob([JSON.stringify(fileInfo)], {type:"application/json"})
                )

                if(cafeImgs.length > 0) {
                    for(let idx = 0; idx <1; idx++) {
                        formData.append('fileList', cafeImgs[idx])
                    }
                }

                console.log(fileInfo)
                axios.put(`http://localhost:7777/cafe/modify/${this.membNo}`, formData)
                        .then(() => {
                            alert('수정되었습니다!')
                            this.$router.go()
                        })
                        .catch(() => {
                            alert('수정 실패!')
                        })
            }*/
  },
}
</script>
<style lang="scss" scoped>
::v-deep .v-dialog {
  @include no-scrollbar;
  overflow-y: auto;
  overflow-x: hidden;
}
</style>

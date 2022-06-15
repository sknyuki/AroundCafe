<template>
  <form class="cafe-review">
    <div v-for="review in reviews" :key="review.reviewNo" class="cafe-review-content">
      <ol class="cafe-review-list">
        <li class="cafe-review-item">
          <article class="cafe-review-card">
            <header class="cafe-review-header">
              <h3 class="visually-hidden">{{ review.reviewNo }}</h3>

              <a class="avatar-24">
                <img src="@/assets/images/img-user-default.png" sralt="" />
              </a>

              <router-link :to="{name:'CafeReviewModifyPage', params:{ reviewNo: review.reviewNo.toString()}}" style="text-decoration:none">
                <v-btn class="modifyBtn" @click="onReviewDialog()">Modify</v-btn>
                <v-dialog max-width="750" v-model="reviewDialog">
                </v-dialog>
              </router-link>

              <div class="inf">
                <a class="username" href="">
                  <strong> cafe.no {{ review.cafeNum }}</strong>
                </a>

                <div class="detail">
                  <div class="star-rating-13">
                    <StarRating
                      :inline="true"
                      :read-only="true"
                      :star-size="10"
                      :show-rating="false"
                      v-model="review.star_score"
                      :incremane="0.1"
                      active-color="#fee500"
                      :star-points="[
                        23, 2, 14, 17, 0, 19, 10, 34, 7, 50, 23, 43, 38, 50, 36,
                        34, 46, 19, 31, 17,
                      ]"
                    />
                  </div>
                  <div class="misc">
                    <time>{{ review.regDate }} </time>
                    <span>cafe.no{{ review.cafeNum }}</span>
                  </div>
                </div>
              </div>
            </header>
            <div class="cafe-review-body">
              
              <div class="review-image">
                <img v-if="review.fileName !== null && review.fileName !== 'null'" class="addImg" 
                :src="require(`@/assets/review/${review.fileName}`)">
              </div>

              <p>{{ review.review_content }}</p>

            </div>

            <div class="cafe-review-footer">
              <v-btn @click="like_count()" aria-label="좋아요 버튼" >
                <i class="icCheck"></i> 도움됨
              </v-btn>

              <p>
                <strong> <span> {{ review.likeCnt }}</span>명</strong>에게 도움이 되었습니다.
              </p>

            </div>
            <button class="delete-button" aria-label="해당 리뷰 삭제하기" type="button" >
              <i class="icClose"></i>
            </button>
              <button
              @click="onReviewDialog"
              class="delete-button"
              aria-label="다이얼로그 등장"
              type="button"
            >
              <i class="icHeart"></i>
            </button>
            <v-dialog max-width="750" v-model="reviewDialog">
            <cafe-review-dialog @close="closeDialog"/>
            </v-dialog>


          </article>
        </li>
      </ol>
    </div>
  </form>
</template>
<script>
import StarRating from "vue-star-rating"

import axios from "axios"
import CafeReviewDialog from './Cafe/CafeReviewDialog.vue'


export default {
  name: "ReviewForm",
  components: {
    StarRating,
    CafeReviewDialog,
  
  },
  props: {
    reviews
    : {
     type: Array
    },
    rating:{
      type: Object
    },
     /*
    reviewNo: {
      type: String,
      required: true
    },
    */
    review:{
      type: Object,
      required: true
    }
 
  },
  data () {
    return {
    reviewNo: this.review.reviewNo,
    reviewDialog: false,
    


    //fileName: this.review.fileName,
    
    }
  },
  /*
   mounted () {
        this.like_count = false
        for(let i=0; i<this.myLikes.length; i++) {
            if (this.myLikes[i].review.reviewNo==this.review.reviewNo) {
                this.like_count = true
            } 
        }
    },*/


  methods:{

    
/*
    movetomodify(event, idx){
     
      console.log("글 번호: " + idx.review.boardNo)
      this.$router.push({name: 'CafeReviewModifyPage', params: {boardNo: String(idx.review.reviewNo)}})

    },
    */
   like_count() {
 
      const { reviewNo } = this.review.reviewNo
      //const { writer } = this
      //console.log(reviewNo,writer)
      console.log(reviewNo)
      if (this.count == true ){
          alert("이미 했음 ")
      } else {
      axios.post(`http://localhost:7777/cafe/review/${this.reviewNo}/like`)
        .then((res) => {
          if (res.data == false) {
          alert("이미 좋아요 하셨습니다!")
      } else {
          alert("좋아요")
          history.go(0)
      }
      })
      .catch(() => {
          alert ('좋아요 실패 문제발생 !')
      })
      }
  },
  onReviewDialog() {
      this.reviewDialog = true
      console.log(this.reviewDialog)
    },
    closeDialog() {
      this.reviewDialog = false
    },
  
  } 

}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/cafe/cafe-review";
</style>

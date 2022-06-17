<!--
<template>
    <v-container class="writeForm">
        <v-row>
            <cafe-review-list v-if="review" :review="review" />
            <CafeReviewModify @submit="onModify"/>
          
        </v-row>
    </v-container>
</template>

<script>
import axios from 'axios'
import { mapActions,mapState } from 'vuex'
import CafeReviewModify from "@/components/Cafe/CafeReviewModify"
import CafeReviewList from "@/components/Cafe/CafeReviewList"

export default {
    name: 'CafeReviewModifyPage',
    components: { 
        CafeReviewModify,
        CafeReviewList
    
    },
     props: {
        reviewNo: {
            type: String,
            required: true
        }
    },
    
    data() {
        return {
            reviewDialog: false,
        }
  },
  computed: {
        ...mapState(['review'])
    },
    
    methods: {
         ...mapActions(['fetchReview']),
        onModify (payload) {
            const { star_score, review_content, cafeNum,file} = payload
            let formData = new FormData()
            if (file != null )
            {formData.append('file', file)}
            formData.append('star_score',star_score)
            formData.append('review_content', review_content)
            formData.append('cafeNum', cafeNum)
            
           
            axios.post(`http://localhost:7777/cafe/review/${this.reviewNo}`,formData, { headers: {
                    'Content-Type': 'multipart/form-data'
                }})
                    .then(() => {
                        alert("Successfully submitted")
                        this.$router.push({
                            name: 'CafeReviewListPage'
                        })
                    })
                    .catch(() => {
                        alert('문제 발생!')
                    })
        },
    onReviewDialog() {
      this.reviewDialog = true
    },
    closeDialog() {
      this.reviewDialog = false
    },
    created() {
        this.fetchReview(this.reviewNo)
        .catch(() => {
            alert('게시물 DB조회 실패!')
        })
    }
  },
}

</script>

<style lang="scss" scoped>
::v-deep .v-dialog {
  @include no-scrollbar;
  overflow-y: auto;
  overflow-x: hidden;
}
</style>e>-->
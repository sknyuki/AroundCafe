<template>
    <v-container>
        <cafe-modify-component  v-if="cafeBoard" :cafeBoard="cafeBoard" 
          @submitContents="contentsSubmit" />
    </v-container>
</template>

<script>
import CafeModifyComponent from '@/components/MyPageComponents/CafeMypageComponents/CafeModifyComponent.vue'
/* list작업 완료 후 store 열어준 후 열어주세요
import axios from 'axios'
import { mapState } from 'vuex'*/
export default {
    name:'CafeModifyView',
    components: { CafeModifyComponent },
     props:{
        cafeNo: {
            type: String,
            required: true
         },
     data () {
    return {
      fileList:[],
    }
},
/*
computed:{
            ...mapState(['cafeBoard'])
        },
        mounted() {
            this.fetchcafeBoard()
        },
        methods : {
            ...mapActions(['fetchcafeBoard']),
            contentsSubmit(payload) {
                  const { cafe_name, cafe_time, cafe_content, cafe_call, cafe_adr1, cafe_adr2, cafe_adr3, cafeImgs} = payload

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
            }
        }*/
    }
}
</script>
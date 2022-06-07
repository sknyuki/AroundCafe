<template>
    <v-container>
      <!-- <cafe-site-component :cafeBoard='cafeBoard'/>--> 
        <the-v-card-form :menuList='menuList'/>
    </v-container>
</template>
<script>
//import CafeSiteComponent from '@/components/CafeSite/CafeSiteComponent.vue'
import TheVCardForm from'@/components/TheVCardForm.vue'
/* register작업 완료 후 store 열어준 후 열어주세요
import axios from 'axios'
import { mapState } from 'vuex'*/
export default {
    name:'CafeSiteList',
    //components: { CafeSiteComponent },
    components: { TheVCardForm },
     props:{
        cafeNo: {
            type: String,
            required: true
         },
     data () {
    return {
     
    }
},
/*
computed:{
            ...mapState(['cafeBoard'],['menuLists'])
        },
        mounted() {
            this.fetchcafeBoard(),
            this.fetchMenuLists()

        },
        methods : {
            ...mapActions(['fetchMenuLists']),
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
<template>
    <div style="padding: 5%;">
        <div v-for="item in cafeBoards" :key="item.cafeNo">
            <v-container>
                <v-row>
                    <v-col>
                        <div v-for="img, index in item.cafeImgs" :key="index">
                            <img v-if="img.cafe_img != null && index == 0" v-bind:src="require(`@/assets/cafe/cafeMypage/${img.cafe_img}`)" width="200px;">
                        </div>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col class="col-12 col-sm-2">
                        이름 : {{item.cafe_name}}
                    </v-col>
                    <v-col class="col-12 col-sm-6">
                        사업자 번호 : {{item.cafe_bis_no}}
                    </v-col >
                    <v-col class="col-12 col-sm-1">
                        <CafeListModify :item="item" @submit="modifySubmit"/>
                    </v-col>
                    <v-col class="col-12 col-sm-1">
                        <v-btn @click="cafeDelete(item)">삭제</v-btn>
                    </v-col >
                </v-row>
                 <v-row>
                    <v-col class="col-12 col-sm-6">
                        설명 : {{item.cafe_content}}
                    </v-col>
                </v-row>
                <v-row>
                    <v-col class="col-12 col-sm-6">
                        주소 : {{item.cafe_adr1}} {{item.cafe_adr2}} {{item.cafe_adr3}}
                    </v-col>
                </v-row>
                <v-row>
                    <v-col v-col class="col-12 col-sm-3">
                        연락처 : {{item.cafe_call}}
                    </v-col>
                    <v-col v-col class="col-12 col-sm-3">
                        영업시간 : {{item.cafe_time}}
                    </v-col>
                </v-row>
            </v-container>
            <br><hr><br>
        </div>
        
    </div>
</template>

<script>
import axios from 'axios'
import CafeListModify from './CafeListModify.vue'
export default {
  components: { CafeListModify },
    name:'CafeList',
    props:{
        cafeBoards:{
            type: Array,
            required: true
        }
    },
    data(){
        return {
            
        }
    },
    methods:{
        cafeDelete(item) {
            let cafeNo = item.cafeNo
            var result = confirm('삭제 하시겠습니까?')

            if(result) {
            axios.delete(`http://localhost:7777/cafe/delete/${cafeNo}`)
                    .then(() => {
                            alert('삭제가 완료되었습니다!')
                            this.$router.go()
                        })
                        .catch(() => {
                            alert('삭제실패!')
                        })
            }
        },
        modifySubmit(payload) {
            const { mcafe_name, mcafe_bus_no, mcafe_no, mcafe_content, mcafe_call, mcafe_time, mcafe_adr1, mcafe_adr2, mcafe_adr3, files} = payload

            let formData = new FormData()

            let fileInfo = {
                cafeNo : mcafe_no,
                cafe_name: mcafe_name,
                cafe_time: mcafe_time,
                cafe_content: mcafe_content,
                cafe_call: mcafe_call,
                cafe_adr1:mcafe_adr1,
                cafe_adr2:mcafe_adr2,
                cafe_adr3:mcafe_adr3,
                cafe_bis_no:mcafe_bus_no


            }

            formData.append(
                "info", new Blob([JSON.stringify(fileInfo)], {type:"application/json"})
            )

            if(files.length > 0) {
                for(let idx = 0; idx <files.length; idx++) {
                    formData.append('fileList', files[idx])
                }
            }

            console.log(fileInfo)
            let membNo = 1;
            axios.put(`http://localhost:7777/cafe/modify/${membNo}`, formData)
                    .then(() => {
                        alert('수정되었습니다!')
                        this.$router.go()
                    })
                    .catch(() => {
                        alert('수정 실패!')
                    })
        
        }
    }
}
</script>
<style lang="">
    
</style>
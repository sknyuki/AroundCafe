<template>
    <div>
        <div v-for="item in cafeBoards" :key="item.cafeNo">
            <v-container>
                <v-row>
                    <v-col v-for="img in item.cafeImgs" :key="img.CafeImgNo">
                        <img v-if="img != null" v-bind:src="require(`@/assets/cafe/cafeMypage/${item.cafe_img}`)" width="200px;">      
                    </v-col>
                </v-row>
                <v-row>
                    <v-col>
                        이름 : {{item.cafe_name}}
                    </v-col>
                    <v-col>
                        사업자 번호 : {{item.cafe_bis_no}}
                    </v-col>
                </v-row>
                <v-row>
                    <v-col>
                        주소 : {{item.cafe_adr1}} {{item.cafe_adr2}} {{item.cafe_adr3}}
                    </v-col>
                    
                    <v-col>
                        연락처 : {{item.cafe_call}}
                    </v-col>
                    <v-col>
                        영업시간 : {{item.cafe_time}}
                    </v-col>
                </v-row>
            </v-container>

        </div>
    </div>
</template>

<script>
export default {
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
    }
}
</script>
<style lang="">
    
</style>
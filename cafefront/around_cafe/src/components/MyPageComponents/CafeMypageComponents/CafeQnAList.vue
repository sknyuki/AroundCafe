<template >
    <div>
        <h4>등록한 문의사항 리스트</h4>
        <!-- <v-card>
        <v-card-title>
        <v-text-field
            v-model="search"
            append-icon="mdi-magnify"
            label="검색"
            single-line
            hide-details
        ></v-text-field>
        </v-card-title>
        <v-data-table :headers="headerTitle1" :items="qnaLists" :search="search"  class="elevation-0">
            <template v-slot:[`item.received_no`] = "{ item }" >
                <router-link :to="{ name: 'CafeQnAPage',
                                    params: { qnaNo: item.qna_no.toString() } }"
                                    style="color:black; float:left;">
                    {{item.received_no}}님의 주문서입니다. [{{item.comments.length}}] 
                </router-link>
            </template>
        </v-data-table>
        </v-card> -->
        <div v-if="!qnaLists || (Array.isArray(qnaLists) && qnaLists.length === 0)">
            <p>등록된 리스트가 없습니다.</p>
        </div>
        <div v-else v-for="item,index in qnaLists" :key="index">
            <v-container style="padding: 3%;">
                <v-row>
                    <v-col cols="12" sm="6">
                        넘버 : {{item.qna_no}}
                    </v-col>
                </v-row>
                 <v-row>
                    <v-col cols="12" sm="8">
                        {{item.type}}
                    </v-col>
                    <v-col cols="12" sm="3">
                        {{item.regDate}}
                    </v-col>
                </v-row>
                 <v-row>
                    <v-col cols="12" sm="6">
                        문의 : {{item.cafe_name}}
                    </v-col>
                </v-row>
                <v-row>
                    <v-col cols="12" sm="6" v-if="item.serverCheck == true" style="padding:0 0 0 15%">
                        문의사항 전송이 완료 되었습니다. (클릭)
                    </v-col>
                    <v-col cols="12" sm="6" v-else style="padding:0 0 0 15%">
                        읽지 않은 답변이 있습니다. (클릭)
                    </v-col>
                </v-row>
            </v-container><br>
        </div>
    </div>
</template>

<script>
export default {
    name:'CafeQnAList',
    props: {
        qnaLists : {
            type: Array,
            required: true
        },
        cafeBoards: {
            type: Array,
            required: true
        }
    },
    data(){
        return {
            search:'',
            headerTitle1 : [
                {text: 'no', value: 'qna_no', width: '20px'},
                {text: '제목', vlaue: 'received_no', width: '200px' },
                {text: 'date', value: 'regDate', width:'100px'}
            ],
            cafeLists:[]
            
        }
    },
}
</script>

<style scoped>

</style>
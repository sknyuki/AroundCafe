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
        <div v-else v-for="item in qnaLists" :key="item.qna_no">
            <a @click="sendQnaNo(item)">
            <v-container style="padding: 3%;">
                <v-row>
                    <v-col cols="12" sm="3">
                        넘버 : {{item.qna_no}}
                    </v-col>
                </v-row>
                 <v-row style="font-size:13.8px;">
                    <v-col cols="12" sm="3">
                        [{{item.type}}]
                    </v-col>
                </v-row>
                 <v-row style="font-size:17px;">
                     <v-col cols="12" sm="3" >
                        {{item.received_name}}
                    </v-col>
                </v-row>
                <v-row>
                    <v-col cols="12" sm="3" v-if="item.writer == idNo">
                        {{item.content}}
                    </v-col>
                    <v-col cols="12" sm="3" v-else>
                        <v-icon style="color:red;">mdi-twitch</v-icon>
                        {{item.content}}
                    </v-col>
                </v-row>
                <v-row style="font-size:12px;">
                    <v-col cols="12" sm="3">
                        {{item.regYear}} {{item.regTime}}
                    </v-col>
                </v-row>
            </v-container>
            </a>
            <br>
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
            cafeLists:[],
            sendQna_no:'',
            idNo:1
            
        }
    },
    methods: {
        sendQnaNo(item){
            this.sendQna_no = item.qna_no;
            this.$emit('submit', parseInt(this.sendQna_no))
            
        }
    }
}
</script>

<style scoped>

</style>
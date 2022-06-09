<template>
    <div class="registerForm">
        <form @submit.prevent="onSubmit">
            <v-container>
                <v-row justify="center">
                    <v-col cols="12" sm="6">
                        <h4>문의사항 등록하기</h4>
                    </v-col>
                </v-row>
                <v-row justify="center">
                    <v-col cols="12" sm="6">
                        <v-text-field label="문의사항 내용 작성" v-model="content"/>
                    </v-col>
                </v-row>
                <v-row justify="center">
                    <v-col cols="12" sm="6">
                        <input type="file" id="files1" ref="files1" 
                        multiple v-on:change="handleFileUpload()"/>
                    </v-col>
                </v-row>
                <v-row justify="center">
                    <v-col cols="12" sm="2">
                        <br>
                        <v-btn @click="onSubmit">등록하기</v-btn>
                    </v-col>
                </v-row>
            </v-container>
        </form>

        <p>문의 내용</p>
        <!-- <div v-for="item in qnaList" :key="item.qna_no">
            <div v-for="con in item" :key="con.qna_comment_no">
                <p>{{con.content}} {{con.regDate}}</p> 
                <img v-if="con.img !=null" :src="require(`@/assets/qna/${con.img}`)"/><br>
                
            </div>
        </div> -->
        <div v-for="item in qnaList" :key="item.qna_comment_no">
            <span>{{item.content}}</span> 
            <span style="font-color: gray;"> {{item.regDate}}</span>
        </div>
    </div>
</template>
<script>
export default {
    name:'CafeQnARegister',
    data() {
        return {
            received_no:1,
            content: '',
            files1:'',
            response:''
        }
    },
    props: {
        qnaList: {
            type: Array,
            required: true
        }
    },
    methods: {
        handleFileUpload() {
            this.files1 = this.$refs.files1.files
        },
        onSubmit() {
            const {received_no, content, files1} = this
            this.$emit('submit', {received_no, content, files1})
        }
    }
}
</script>

<style scoped>
.registerForm{
    padding: 5%;
} 
</style>
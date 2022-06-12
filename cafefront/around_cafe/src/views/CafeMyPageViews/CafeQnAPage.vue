<template>
    <div>
        <CafeQnARegister @submit="onSubmit" :qnaList="qnaList"/>
        <CafeQnAList :qnaLists="qnaLists" @submit="sendQnaNo"  class="left_menu"/>
        <CafeQnAComment :qnaList="qnaList" @submit="sumbitMsg" class="right_content"/>
        
    </div>
</template>

<script>
import CafeQnARegister from '@/components/MyPageComponents/CafeMypageComponents/CafeQnARegister.vue'
import axios from 'axios'
import { mapActions, mapState } from 'vuex'
import CafeQnAComment from '@/components/MyPageComponents/CafeMypageComponents/CafeQnAComment.vue'
import CafeQnAList from '@/components/MyPageComponents/CafeMypageComponents/CafeQnAList.vue'

export default {
    name:'CafeQnAPage',
    components: { 
        CafeQnARegister, 
        CafeQnAComment,
        CafeQnAList 
    },
    data() {
        return{
            existingQnaNo:1,
            checkQnaNo:'',
            
        }
    },
    computed: {
        ...mapState(['qnaList', 'qnaLists'])
    },
    mounted() {
        this.fetchQnAList(this.checkQnaNo)
        this.fetchQnALists(1)
    },
    methods: {
        ...mapActions(['fetchQnAList','fetchQnALists']),
        onSubmit(payload) {
            const {registerNo,content,findQna, files1} = payload

            let formData = new FormData()

            let fileInfo = {
                received_no : registerNo,
                type : findQna,
                content : content
            }

            formData.append(
                "info", new Blob([JSON.stringify(fileInfo)], {type:"application/json"})
            );

            if(files1.length > 0) {
                for(let idx= 0; idx < files1.length; idx ++) {
                    formData.append('fileList',files1[idx])
                }
            }

            let membNo = 1; //문의사항 등록은 1번 멤버가 계속쓰기 때문에 이건 일단 그대로 둔다.
            axios.post(`http://localhost:7777/qna/register/${membNo}`, formData)
                    .then(res => {
                        alert(res.data)
                        this.$router.go()
                    })
                    .catch(() => {
                        alert("문의사항 등록에 실패하였습니다.")
                    })
        },
        sumbitMsg(payload) {
            const {qnaNo, chatting} = payload

            let formData = new FormData()

            let fileInfo = {
                qnaNo : qnaNo,
                chatting : chatting
            }

            formData.append(
                "info", new Blob([JSON.stringify(fileInfo)], {type:"application/json"})
            );

            let membNo = 1;
            axios.post(`http://localhost:7777/qnaComment/register/${membNo}`, formData)
                    .then(() => {
                        alert("등록 성공")
                    })
                    .catch(() => {
                        alert("문의사항 등록에 실패하였습니다.")
                    })
        },
        sendQnaNo(payload){
            this.checkQnaNo = payload
            this.fetchQnAList(this.checkQnaNo)
        }
    }
}
</script>

<style scoped>
.left_menu {
    width: 500px;
    height: 500px;
    padding: 3%;
}

.right_content {
    margin: 5% 0 0 25%;
}

</style>
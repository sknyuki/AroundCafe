<template>
    <div>
        <CafeQnARegister @submit="onSubmit" :qnaList="qnaList"/>
        <CafeQnAComment :qnaList="qnaList" @submit="sumbitMsg"/>
        <CafeQnAList :qnaLists="qnaLists" :cafeBoards="cafeBoards"/>
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

        }
    },
    computed: {
        ...mapState(['qnaList', 'qnaLists'])
    },
    mounted() {
        this.fetchQnAList(1)
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

            let membNo = 1;
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
                        this.$router.go()
                    })
                    .catch(() => {
                        alert("문의사항 등록에 실패하였습니다.")
                    })
        },
        
    }
}
</script>

<style lang="">
    
</style>
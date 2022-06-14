<template>
    <div>
        <CafeQnARegister @submit="onSubmit" :qnaList="qnaList"/>
        <CafeQnAList :qnaLists="qnaLists" />
        
    </div>
</template>

<script>
import CafeQnARegister from '@/components/MyPageComponents/CafeMypageComponents/CafeQnARegister.vue'
import axios from 'axios'
import { mapActions, mapState } from 'vuex'
import CafeQnAList from '@/components/MyPageComponents/CafeMypageComponents/CafeQnAList.vue'
// import CafeQnAComment from '@/components/MyPageComponents/CafeMypageComponents/CafeQnAComment.vue'

export default {
    name:'CafeQnAPage',
    components: { 
        CafeQnARegister, 
        CafeQnAList,
        //CafeQnAComment 
    },
    data() {
        return{
            existingQnaNo:1,
            checkQnaNo:'',
            qnaList:{
                type: Array
            }
            
        }
    },
    computed: {
        ...mapState(['qnaLists'])
    },
    mounted() {
        this.fetchQnALists(1)
    },
    methods: {
        ...mapActions(['fetchQnALists']),
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
    
    }
}
</script>

<style scoped>
</style>
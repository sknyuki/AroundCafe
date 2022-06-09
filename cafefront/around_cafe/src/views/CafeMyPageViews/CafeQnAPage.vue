<template>
    <div>
        <cafe-qn-a-register @submit="onSubmit"/>
    </div>
</template>

<script>
import CafeQnARegister from '@/components/MyPageComponents/CafeMypageComponents/CafeQnARegister.vue'
import axios from 'axios'

export default {
    name:'CafeQnAPage',
    components: { CafeQnARegister },
    data() {
        return{

        }
    },
    methods: {
        onSubmit(payload) {
            const {received_no,content, files1} = payload

            let formData = new FormData()

            let fileInfo = {
                received_no : received_no,
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
        }
    }
}
</script>

<style lang="">
    
</style>
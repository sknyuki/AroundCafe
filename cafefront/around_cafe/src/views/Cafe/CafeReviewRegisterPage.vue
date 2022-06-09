<template>
    <v-container class="writeForm">
        <v-row>
            <CafeReviewRegister @submit="onSubmit"/>
        </v-row>
    </v-container>
</template>

<script>
import axios from 'axios'
import CafeReviewRegister from "@/components/Cafe/CafeReviewRegister.vue"
export default {
    name: 'CafeReviewRegisterPage',
    components: { 
        CafeReviewRegister
    },
    
    methods: {
        onSubmit (payload) {
            const { star_score, review_content,file,cafeNum} = payload
            let formData = new FormData()
            if (file != null )
            {formData.append('file', file)}
            formData.append('star_score',star_score)
            formData.append('review_content', review_content)
            formData.append('cafeNum', cafeNum)
           
            axios.post(`http://localhost:7777/cafe/review/register`,formData, { headers: {
                    'Content-Type': 'multipart/form-data'
                }})
                    .then(() => {
                        alert("Successfully submitted")
                        this.$router.push({
                            name: 'CafeReviewListPage'
                        })
                    })
                    .catch(() => {
                        alert('문제 발생!')
                    })
        }
    }
}
</script>

<style scoped>
.writeForm{
    margin-top:2.5%;
    margin-bottom: 5%;
}
</style>
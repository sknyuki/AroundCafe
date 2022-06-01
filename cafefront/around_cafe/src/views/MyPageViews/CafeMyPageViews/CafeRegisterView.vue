<template>
    <v-container>
        <cafe-register-component @submitContents="contentsSubmit"
         @submitFiles="filesSubmit"/>
    </v-container>
</template>

<script>
import CafeRegisterComponent from '@/components/MyPageComponents/CafeMypageComponents/CafeRegisterComponent.vue'
import axios from 'axios'
import { mapState } from 'vuex'
export default {
    name:'CafeRegisterVeiw',
    components: { CafeRegisterComponent },
     data () {
    return {
      boardNo:null,
      files:[],
    }
},
computed: {
    ...mapState([ 'userInfo' ])
  },
     methods: {
        contentsSubmit (payload) {
            const id=this.userInfo.id
            const { cafe_name, cafe_time, cafe_content, cafe_call, cafe_adr1, cafe_adr2, cafe_adr3 ,cafeImgs} = payload
            console.log('contents의 값이 넘어왔습니다.'+payload)
            axios.post('http://localhost:7777/cafe/register', { id, type, name, size, price, brandrank, accessories, status, soldCheck})
                    .then(res => {
                        // alert('등록 성공! - ' + res)
                        this.boardNo=res.data.boardNo
                       
                       // this.$router.push({
                         //   name: 'BoardListPage',
                           // params: { boardNo: res.data.boardNo.toString() }
                        //})
                    })
                },
                filesSubmit(files){
                this.files=files
                console.log('값이 넘어왔습니다'+files)
                setTimeout(()=>{
                    const formData=new FormData()
                    for (let i=0; i<this.files.length; i++){
                        formData.append('fileList',this.files[i])
                    }

                    formData.append('boardNo',this.boardNo)
                   // formData.append('id', this.userInfo.id)
                    

                    axios.post('http://localhost:7777/cafe/register',formData,{
                        headers:{ 'Content-Type': 'multipart/form-data'}
                        })
                        .then(res=>{
                             console.log(res.data)
                             alert('등록 성공! - ' + res)
                                 this.$router.push({
                                     name:'K2ProductView'
                                 })
                        }).catch(err=>{
                             console.log(err)
                        })
                },1000)
                }
     }
}
        
</script>
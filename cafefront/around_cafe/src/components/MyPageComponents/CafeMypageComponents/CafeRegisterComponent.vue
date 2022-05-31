<template>
<form @submit.prevent="onSubmit">
   <div class="app">
            <h3 style="padding:30px 0px 30px 0px; font-weight:bold" align="center">게시글 작성</h3>
  <v-sheet class="mt-5 mb-10">
    <v-row>
      <v-col cols="12" md="8">
        <upload-file-1 @selectFile="selectFile"></upload-file-1>
      </v-col>
      <v-col cols="12" md="4">
      
        <v-card align="center" class="mt-5 pa-2">
                  <h3>상세 목록</h3>
           <table class="table">
            <tr>
                <td><h4>카페명:</h4></td>
                <td>
                    <input type="text" v-model="cafe_name"/>
                </td>
            </tr>
             <tr>
                <td><h4>영업 시간:</h4></td>
                <td>
                    <input type="text" v-model="cafe_time"/>
                </td>
            </tr>
            <tr>
                <td><h4>카페 설명:</h4></td>
                <td>
                    <input type="text" v-model="cafe_content"/>
                </td>
            </tr>
            <tr>
                <td><h4>전화 번호:</h4></td>
                <td>
                    <input type="text" v-model="cafe_call"/>
                </td>
            </tr>
            
            <tr>
                <td><h4>카페 주소:</h4></td>
                <td width="0"> <v-select
            :items="states1"
            label="시"
            dense
            style="width:100px; font-size:15px; padding:0px"
             v-model="cafe_adr1"
          ></v-select>
                </td>
                <td width="0">
                    <v-select
            :items="states2"
            label="구"
            dense
            style="width:100px; font-size:15px;"
             v-model="cafe_adr2"
             ></v-select>
                </td>
                <td>
                    <input type="text" style="width:100px; font-size:15px;" v-model="cafe_adr3"/>
                </td>
            </tr>
          </table>


         <div class="버튼">
           <v-row>
             <v-col>
                <v-btn text color="black" rounded x-large
                    style="padding: 10px; width: 30px;" type="submit" >등록하기
                </v-btn>
             </v-col>
                <br>
              <v-col>
                <router-link style="text-decoration: none; color:black" :to="{ name: 'main' }">
                    <v-btn text color="black" rounded x-large
                        style="padding: 10px; width: 30px;" type="submit">목록으로
                    </v-btn>
                </router-link>
              </v-col>
           </v-row>
            </div>
        </v-card>
      </v-col>
    </v-row>
  </v-sheet>
   </div>
</form>
</template>

<script>
import UploadFile1 from '@/components/uploadfile1.vue'

export default {
    name:'ProductBoardRegister',
    components:{
       UploadFile1,
    },
     data () {
        return {
            cafe_adr1: '',
            cafe_adr2: '',
            cafe_adr3:'',
            cafe_name: '',
            cafe_time: '',
            cafe_content: '',
            cafe_call: '',
            files:[],
            states1:['서울시'],
            states2: [
        '강남구', '강동구', '강북구', '강서구',
        '관악구', '광진구', '구로구', '금천구',
        '노원구', '도봉구', '동대문구',
        '동작구', '마포구', '서대문구', '서초구', '성동구',
        '성북구', '송파구', '양천구', '영등포구', '용산구','은평구','종로구','중구','중랑구'
      ],
        }
    },
      methods: {
        selectFile(files){
            this.files=files
        },
         onSubmit () {
            var result = confirm('등록 하시겠습니까?')
            if(result) {
                const { cafe_adr1, cafe_adr2, scafe_adr3ize, cafe_name, cafe_time, cafe_content, cafe_call} = this
                this.$emit('submitContents', {  cafe_adr1, cafe_adr2, scafe_adr3ize, cafe_name, cafe_time, cafe_content, cafe_call})
                console.log(cafe_adr1, cafe_adr2, scafe_adr3ize, cafe_name, cafe_time, cafe_content, cafe_call)

                this.$emit("submitFiles",this.files)
                console.log(this.files)
            }
         }
      }
}
</script>
<style scoped>
.table{
border-collapse: separate;
border-spacing: 0 10px;
}
</style>
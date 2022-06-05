<template>
<form @submit.prevent="onSubmit">
   <div class="app">
            <h3 style="padding:30px 0px 30px 0px; font-weight:bold" align="center">게시글 작성</h3>
  <v-sheet class="mt-5 mb-10">
    <v-row>
      <v-col cols="12" md="2">
        <upload-file-1 @selectFile="selectFile"></upload-file-1>
      </v-col>
      <v-col cols="12" md="8">
      
        <v-card align="center" class="mt-5 pa-2">
                  <h3>상세 목록</h3>
           <table class="table">
            <tr>
                <td><h4>카페명:</h4></td>
                <td>
                    <input type="text" :value="mcafe_name"/>
                </td>
            </tr>
            <tr>
                <td><h4>사업자 번호:</h4></td>
                <td>
                    <input type="text" :value="cafeBoard.cafe_bis_no" readonly/>
                </td>
            </tr>
             <tr>
                <td><h4>영업 시간:</h4></td>
                <td>
                    <input type="text" v-model="mcafe_time"/>
                </td>
            </tr>
            <tr>
                <td><h4>카페 설명:</h4></td>
                <td>
                    <input type="text" v-model="mcafe_content"/>
                </td>
            </tr>
            <tr>
                <td><h4>전화 번호:</h4></td>
                <td>
                    <input type="text" v-model="mcafe_call"/>
                </td>
            </tr>
            
            <tr>
                <td><h4>카페 주소:</h4></td>
                <td width="0"> 
                    <v-select
                        :items="states1"
                        label="시"
                        dense
                        style="width:100px; font-size:15px;"
                        v-model="mcafe_adr1"
                    ></v-select>
                </td>

                <td width="0">
                    <v-select
                        :items="states2"
                        label="구"
                        dense
                        style="width:100px; font-size:15px;"
                        v-model="mcafe_adr2"
                    ></v-select>
                </td>
                <td>상세주소:
                    <input type="text" style="width:100px; font-size:15px;" v-model="mcafe_adr3"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="file" id="files1" ref="files1" 
                        multiple v-on:change="handleFileUpload()"/>
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

  <div>
      <v-btn>
          <router-link :to="{name: 'CafeImgManage',
                params: { cafeNo: this.cafeBoard.cafeNo}} ">
              이미지 관리
          </router-link>
      </v-btn>
  </div>

  <div v-for="item,idx in cafeImgLists" :key="idx">
      <img v-bind:src="require(`@/assets/cafe/cafeMypage/${item.cafe_img}`)" width="200px;">      
  </div>
   </div>
</form>

</template>

<script>
import UploadFile1 from '@/components/uploadfile1.vue'

export default {
    name:'CafeModifyComponent',
    components:{
       UploadFile1
    },
     props: {
        cafeBoard: {
            type: Object,
            required: true
        },
        cafeImgLists : {
            type: Array,
            required: true
        }
     },
     data() {
         return{
            cafeNo:'',
            mcafe_adr1:'',
            mcafe_adr2:'',
            mcafe_adr3:'',
            mcafe_name:'',
            mcafe_time:'',
            mcafe_content: '',
            mcafe_call: '',
            files:[],
            states1:['서울시'],
            states2: [
                '강남구', '강동구', '강북구', '강서구',
                '관악구', '광진구', '구로구', '금천구',
                '노원구', '도봉구', '동대문구',
                '동작구', '마포구', '서대문구', '서초구', '성동구',
                '성북구', '송파구', '양천구', '영등포구', '용산구','은평구','종로구','중구','중랑구'
            ],
            cafeImgs:[],
            files1: '',
            response: ''
        }
    },
    created() {
        this.mcafe_name = this.cafeBoard.cafe_name
        this.mcafe_content = this.cafeBoard.cafe_content
        this.mcafe_call = this.cafeBoard.cafe_call
        this.mcafe_time = this.cafeBoard.cafe_time
        this.mcafe_adr1 = this.cafeBoard.cafe_adr1
        this.mcafe_adr2 = this.cafeBoard.cafe_adr2
        this.mcafe_adr3 = this.cafeBoard.cafe_adr3
    },
      methods: {
        selectFile(files){
            this.cafeImgs=files
        },
        handleFileUpload () {
            this.files1 = this.$refs.files1.files
        },
         onSubmit () {
            var result = confirm('등록 하시겠습니까?')
            if(result) {
                const { mcafe_name, mcafe_content, mcafe_call, mcafe_time, mcafe_adr1, mcafe_adr2, mcafe_adr3, files1} = this
                this.$emit('submitContents', {mcafe_name, mcafe_content, mcafe_call, mcafe_time, mcafe_adr1, mcafe_adr2, mcafe_adr3, files1})
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
.input{
    border: 1px solid black;
}
</style>
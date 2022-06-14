<template>
    <div >
        <div v-if="!qnaList || (Array.isArray(qnaList) && qnaList.length === 0)">
            <h4>채팅할 상대를 선택해주세요.</h4>
        </div>
        <div v-else>
            <div  v-for="item in qnaList" :key="item.qna_no">
                <ul v-if="item.writer == memberNo" >
                    <li class="showBox" v-if="item.content != null" style="float: right;"> {{(item.regTime)}} 나 : {{item.content}} <br></li>
                    <li class="showBox" v-if="item.img != null">
                        <img v-bind:src="require(`@/assets/qna/${item.img}`)" style="width :200px; display: block;">
                    </li>
                </ul><br>
                <ul v-if="item.writer != memberNo" style="float: left;">
                    <li class="showBoxOther">남 : {{item.content}} {{(item.regTime)}}</li>
                    <li class="showBoxOther" v-if="item.img != null">
                        <img v-bind:src="require(`@/assets/qna/${item.img}`)" style="width :200px; display: block;">
                    </li>
                </ul><br>
            </div>
            <div>
                <textarea type="text" v-model="chatting" style="width: 500px; height: 100px"/>
                <v-icon @click="handleFileUpload()" id="files1" ref="files1" multiple>mdi-panorama-variant </v-icon>
                <v-icon @click="sumbitMsg()"> mdi-arrow-up-circle</v-icon>
            </div>
        </div>
        
        
        
        
        <!-- <div v-else>
            <v-card
                class="mx-auto"
                max-width="400"
                >
                <v-card-title class="white--text blue darken-4">
                    문의사항
                <v-spacer></v-spacer>  </v-card-title>

                <v-card-text class="pt-4">
                    1:1 채팅을 통해 궁금한 것을 물어보세요!
                </v-card-text>
                <v-divider></v-divider>
                <v-divider></v-divider>
                <v-virtual-scroll
                    :item-height="55"
                    height="350"
                    style="margin: 5%;">
                    <v-list-item-title></v-list-item-title>
                </v-virtual-scroll>
                <v-virtual-scroll
                    :items="qnaList"
                    :item-height="55"
                    height="350"
                    style="margin: 5%;"
                >
                
                    <template v-slot = "{ item }" >
                        <v-list-item-content v-if="item.writer == 1" >
                            <v-list-item-title class="showBox" v-if="item.content != null"> <p style="float: right;"> {{(item.regTime)}} 나 : {{item.content}}</p></v-list-item-title>
                            <v-list-item-title class="showBox" v-if="item.img != null">
                                <img v-bind:src="require(`@/assets/qna/${item.img}`)" style="width :200px; display: block;">
                            </v-list-item-title>
                        </v-list-item-content>
                        <v-list-item-content v-if="item.writer != 1" style="float: left;">
                            <v-list-item-title class="showBoxOther">남 : {{item.content}} {{(item.regTime)}}</v-list-item-title>
                            <v-list-item-title class="showBoxOther" v-if="item.img != null">
                                <img v-bind:src="require(`@/assets/qna/${item.img}`)" style="width :200px; display: block;">
                            </v-list-item-title>
                        </v-list-item-content>
                       
                    </template>

                </v-virtual-scroll>

                <v-card
                class="mx-auto"
                max-width="400px"
                max-height="400px"
                >
                <v-card-title>
                    <textarea type="text" v-model="chatting" style="width: 500px; height: 100px"/>
                    <v-icon @click="handleFileUpload()" id="files1" ref="files1" multiple>mdi-panorama-variant </v-icon>
                    <v-icon @click="sumbitMsg()"> mdi-arrow-up-circle</v-icon>
                <v-spacer></v-spacer>  </v-card-title></v-card>
                
            </v-card>
        </div> -->
    
    </div>
</template>
<script>
import axios from 'axios'
export default {
    name : 'CafeQnAComment',
    data() {
        return {
            chatting:'',
            qnaNo:1,
            memberNo:1
        }
    },
    props : {
        qnaList: {
            type: Array,
            required: true
        }
    },
    methods: {
        handleFileUpload() {
            let vue = this
            let elem = document.createElement('input')
            // 이미지 파일 업로드 / 동시에 여러 파일 업로드
            elem.id = 'image'
            elem.type = 'file'
            elem.accept = 'image/*'
            elem.multiple = true
            // 클릭
            elem.click();
            elem.onchange = function() {
                let formData = new FormData()
                for (let index = 0; index < this.files.length; index++) {
                    formData.append('fileList', this.files[index])
                }
                let qnaNo = 1
                axios.post(`http://localhost:7777/qnaComment/registerImg/${qnaNo}`, formData)
                .then(response => {
                    vue.response = response.data
                }).catch(error => {
                    vue.response = error.message
                })
            }
        },
        sumbitMsg() {
            const { qnaNo, chatting } = this
            this.$emit('input',{ qnaNo, chatting})
            this.chatting = "";
        },
    }
}
</script>
<style scoped>
.tokk {
    width: 400px;
    height: 600px;
    border: 1px solid black;
}

.inputSomething{
    position: absolute;
    clear:left;
    width: 400px;
    height: 50px;
    bottom: 0px;
    border: 1px solid black;
}

input{
    width: 350px;
}

.virtualScroll {
    background-color: rgb(66, 123, 228);
    border: 1px solid;
    padding: 5%;
}


.showBox {
  background-color: #eef3fd;
  border: #7689fd solid 1px;
  border-radius: 5px;
  color: #000000;
  font-size: 12px;
  font-weight: 500;
  height: auto;
  letter-spacing: -0.25px;
  margin-top: 6.8px;
  padding: 5px 11px;
  position: relative;
  width: fit-content;
  z-index: 100;
  max-height: 200;
  max-width: 300;
}

.showBox::after {
  border-color: #eef3fd transparent;
  border-style: solid;
  border-width: 0 6px 8px 6.5px;
  content: '';
  display: block;
  left: 75px;
  position: absolute;
  top: -7px;
  width: 0;
  z-index: 1;
}

.showBox::before {
  border-color: #7689fd transparent;
  border-style: solid;
  border-width: 0 6px 8px 6.5px;
  content: '';
  display: block;
  left: 75px;
  position: absolute;
  top: -8px;
  width: 0;
  z-index: 0;
}

.showBoxOther {
  background-color: #fcfcf4;
  border: #fcfcf4 solid 1px;
  border-radius: 5px;
  color: #000000;
  font-size: 12px;
  font-weight: 500;
  height: auto;
  letter-spacing: -0.25px;
  margin-top: 6.8px;
  padding: 5px 11px;
  position: relative;
  width: fit-content;
  z-index: 100;
}

.showBoxOther::after {
  border-color: #fcfcf4 transparent;
  border-style: solid;
  border-width: 0 6px 8px 6.5px;
  content: '';
  display: block;
  left: 75px;
  position: absolute;
  top: -7px;
  width: 0;
  z-index: 1;
}

.showBoxOther::before {
  border-color: #fcfcf4 transparent;
  border-style: solid;
  border-width: 0 6px 8px 6.5px;
  content: '';
  display: block;
  left: 75px;
  position: absolute;
  top: -8px;
  width: 0;
  z-index: 0;
}


</style>
<template>
    <div>
        <!-- <div class="tokk">
            <h3 style="text-align:center">문의사항</h3>
            <hr>
            <div v-for="item in qnaList" :key="item.qna_comment_no" >
                <v-container v-if="item.writer == 1" style="float: right; padding: 5% 5% 0 0;">
                    <v-row >
                        <v-col>
                           <p style="float:right;">나 : {{item.content}}</p> 
                        </v-col><br><br>
                    </v-row >
                    <v-row v-if="item.writer == 2 || item.writer == 0" style="float: left;">
                        <v-col>
                            남 : {{item.content}}
                        </v-col><br><br>
                    </v-row>
                    <v-row v-if="item.writer == null || item.img != null" style="float:right;">
                        <v-col>
                            <img v-bind:src="require(`@/assets/qna/${item.img}`)" style="float:right; width :250px;"> 
                        </v-col><br><br>
                    </v-row>
                    <v-row v-if="item.writer == 1" style="float:right;">
                        <v-col>
                            <img v-bind:src="require(`@/assets/qna/${item.img}`)" style=" width :250px;"> 
                        </v-col><br><br>
                    </v-row>
                    <v-row style="float: right;"> 
                        <v-col>
                            {{item.regDate}}
                        </v-col>
                    </v-row><br>
                </v-container> 

                <v-container>
                    <v-row v-if="item.writer == 1" style="float: right; padding: 5% 5% 0 0;">
                        <v-col>
                           <p style="float:right;">나 : {{item.content}}</p> 
                        </v-col><br><br>
                    </v-row >
                    <v-row v-if="item.writer == 2 || item.writer == 0" style="float: left;">
                        <v-col>
                            남 : {{item.content}}
                        </v-col><br><br>
                    </v-row>
                    <v-row v-if="item.writer == null || item.img != null" style="float:right;">
                        <v-col>
                            <img v-bind:src="require(`@/assets/qna/${item.img}`)" style="float:right; width :250px;"> 
                        </v-col><br><br>
                    </v-row>
                    <v-row v-if="item.writer == 1" style="float:right;">
                        <v-col>
                            <img v-bind:src="require(`@/assets/qna/${item.img}`)" style=" width :250px;"> 
                        </v-col><br><br>
                    </v-row>
                    <v-row style="float: right;"> 
                        <v-col>
                            {{item.regDate}}
                        </v-col>
                    </v-row><br>
                </v-container> 

                
            </div>
            
            <div class="inputSomething">
                <input type="text" v-model="chatting"/>
                <v-icon @click="handleFileUpload()" id="files1" ref="files1" multiple>mdi-panorama-variant </v-icon>
                <v-icon @click="sumbitMsg"> mdi-arrow-up-circle</v-icon>
            </div>

            <div>
                <v-virtual-scroll
                :items="qnaList"
                height="600" width="400" item-height="30" 
                style="left: 10%;top: 10%;"
                class="virtualScroll">

                <template v-slot="{ item, index }">
                    <v-list-item :key="index">
                        <v-list-item-action>
                            <input type="radio" @change="confirmCake(item)" v-model="checkIndex" :value="index" name="checkOwnCake"/> 
                        </v-list-item-action>

                        <v-list-item-content style="width:310px; height:310px;">
                            <v-img v-bind:src="require(`@/assets/uploadImg/${item.linkInfo}`)" contain />
                        </v-list-item-content>

                        <v-list-item-action>
                        </v-list-item-action>
                        
                        <v-list-item-content>
                            <v-list-item-title>
                                design : {{item.design}} <br> 
                                size : {{item.size}} <br> 
                                price: {{item.price}}
                            </v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                    <v-divider></v-divider>
                </template>
            </v-virtual-scroll>
            </div>

            
      
        </div> -->

        <div>
                <v-card
                    class="mx-auto"
                    max-width="400"
                    >
                    <v-card-title class="white--text orange darken-4">
                        문의사항
                    <v-spacer></v-spacer>  </v-card-title>

                    <v-card-text class="pt-4">
                        1:1 채팅을 통해 궁금한 것을 물어보세요!
                    </v-card-text>
                    <v-divider></v-divider>
                    
                    <v-card-title>
                    <v-text-field
                        v-model="search"
                        append-icon="mdi-magnify"
                        label="내용 검색"
                        single-line
                        hide-details
                        @change="findContent"
                    ></v-text-field>
                    </v-card-title>


                    <v-virtual-scroll
                        :items="qnaList"
                        :item-height="55"
                        :search="search"
                        height="350"
                        style="margin: 5%;"
                    >
                        <template v-slot="{ item }">
                            <v-list-item-content v-if="item.writer == 1" style="float: right;">
                                <v-list-item-title> <p style="float: right;">나 : {{item.content}}</p></v-list-item-title>
                                <v-list-item-title v-if="item.img != null">
                                    <img v-bind:src="require(`@/assets/qna/${item.img}`)" style="width :200px; display: block;">
                                </v-list-item-title>
                            </v-list-item-content>
                            <v-list-item-content v-else style="float: left;">
                                <v-list-item-title>남 : {{item.content}}</v-list-item-title>
                            </v-list-item-content>
                        </template>
                       
                    </v-virtual-scroll>

                     <v-card
                    class="mx-auto"
                    max-width="400px"
                    max-height="400px"
                    >
                    <v-card-title>
                        <input type="text" v-model="chatting" style="max-width:300px; max-height: 200px;"/>
                        <v-icon @click="handleFileUpload()" id="files1" ref="files1" multiple>mdi-panorama-variant </v-icon>
                        <v-icon @click="sumbitMsg"> mdi-arrow-up-circle</v-icon>
                    <v-spacer></v-spacer>  </v-card-title></v-card>
                    
                </v-card>
            </div>
        
    </div>
</template>
<script>
import axios from 'axios'
export default {
    name : 'CafeQnAComment',
    data() {
        return {
            qnaNo: 1,
            chatting:'',
            files1: '',
            img: '',
            check:'',
            search:''
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
                let qnaNo = 1;
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
            this.$emit('submit',{ qnaNo, chatting})
        },
        findContent(){
            return this.qnaList.filter(() => 
            this.qnaList.toLowerCase().includes(this.search.value.toLowerCase()));
        }
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
    background-color: rgb(228, 66, 66);
    border: 1px solid;
    padding: 5%;
}

</style>
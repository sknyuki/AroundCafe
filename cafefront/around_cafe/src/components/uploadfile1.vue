<template>
    <div>
        
            <!-- <h3 align="left"> 업로드할 이미지 : </h3>
             <v-file-input class="input" type="file" v-model="files"
             counter show-size label="이미지 제출"
             outlined dense multiple prepend-icon="mdi-camera"
              @change="onInputImage"/>
           <v-card class="mx-10" flat>
      <v-img class="ml-10" :src="url" width="100%"/>
             </v-card>-->

        
            <br>
             <v-row  wrap justify="center">
                        <p v-if="this.files.length == 0" class="beforeAttach" >
                                카페 이미지를 첨부해주세요.
                        </p>
                        <v-carousel hide-delimiters height="auto" v-else > 
                            <div class="delect" style="margin: auto;">
                            <!--이미지 값을 포함 하고있는 블럭 delect-->
                            <v-carousel-item
                            v-for="(file, index) in files" :key="index" >
                            <v-img :src=file.preview class="preview"
                             max-height="700" max-width="700"
                             ><!--직접적으로 hover를 입히고 싶은 btn-->
                                <v-btn class="show-btns" icon
                                 @click="imgDelect(file)">
                                   <v-icon>
                                    mdi-minus-box
                                    </v-icon>
                                </v-btn>

                           
                            </v-img>
                            </v-carousel-item>
                             </div>
                            </v-carousel>
                  
                    </v-row>
                    <v-row justify="center">
                        <v-col cols="3">
                                 
                            <input type="file" id="files" ref="files" dense style="width:300px"
                                    multiple v-on:change="FileUpload()"/>
                        </v-col>
                    </v-row>
        

             <!--<v-row  wrap justify="center">
                    <p v-if="this.files.length == 0" class="beforeAttach" >
                                카페 이미지를 첨부해주세요.
                    </p>
                        <v-carousel hide-delimiters height="auto" v-else > 
                        <div class="delect" style="margin: auto;">
                            <v-carousel-item
                                v-for="(file, index) in files" :key="index" >
                                <v-img :src=file.preview class="preview"
                                 max-height="700" max-width="700">
                                    <v-btn class="show-btns" icon
                                        @click="imgDelect(file)">
                                        <v-icon>
                                         mdi-minus-box
                                        </v-icon>
                                    </v-btn>
                                </v-img>
                            </v-carousel-item>
                        </div>
                    </v-carousel>
                    </v-row>
                    <v-row justify="center">
                        <v-col cols="3">
         <v-file-input class="input" type="file" v-model="files"
                        counter show-size label="이미지 제출"
                        outlined dense multiple prepend-icon="mdi-camera" 
                        v-on:change="FileUpload()"/> 
                        </v-col>
                    </v-row>-->    
        <div class="버튼">
           <v-row justify="center">
             <v-col cols="3">
                <v-btn text color="black" rounded x-large
                    style="padding: 10px; width: 30px;" @click="PotoSubmit()" >사진 확정하기
                </v-btn>
             </v-col>    
             </v-row>
        </div>   
    </div>
</template>

<script>


export default {
    name: 'UploadFile1',
    data () {
        return {
            files: [],
            uploadFiles:[],
           previewImg:[],
          
        }
    },
    methods: {
       
       /* onInputImage () {
         // console.log(this.files) 반영 확인 완
             try {
                  for (let i = 0; i < this.$refs.files.files.length; i++){
                      this.url = URL.createObjectURL(this.files[i])
                  }
        
      }catch(e) {
        this.url = null
      }
           this.$emit('selectFile',this.files)
        },*/
         FileUpload () {
            this.uploadFiles=this.$refs.files.files
            console.log(this.uploadFiles)

          try {
            for (let i = 0; i < this.$refs.files.files.length; i++){
                this.files = [
                    ...this.files,
                    {   
                       file: this.$refs.files.files[i],
                        //file: i,
                        preview: URL.createObjectURL(this.$refs.files.files[i])
                    
                    }
                ] 
            }
            console.log("this.file의 타입")
                console.log(this.files[0].file.name)
          }catch(e){
              this.preview=null
          }  
            },
           
        imgDelect(file){
           console.log("삭제되는 파일")
           console.log(file.file)
          
            for(let i=0; this.uploadFiles.length; i++){
                if(this.uploadFiles[i]==file.file){
                   console.log("삭제시킬파일 재확인")
                   console.log(this.uploadFiles[i])
                    //fileList=>Array변환 후 요소삭제
                   this.uploadFiles=[...this.uploadFiles]
                    this.uploadFiles.splice(i,1); 
                    this.files.splice(i,1);
                    //Array=>fileList로 다시 변경
                   // this.uploadFiles=this.uploadFiles[0].toObject();
                   break;
                }
            }  
           console.log("삭제처리후")
           console.log(this.uploadFiles)
        },
        PotoSubmit(){
            this.$emit('selectFile',this.uploadFiles)  
            console.log("넘어가는 파일:")
            console.log(this.uploadFiles)
        }
        },
    }


</script>
<style scoped>
.delect{
position: relative;
overflow: hidden;
}
.delect:hover::after{
opacity: 1;
}
.show-btns{
position: absolute;
bottom:150%;
z-index: 1;
transition:all .35s;
}
.delect:hover .show-btns{
top:0%;
}
</style>
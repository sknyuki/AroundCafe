<template>
    <v-container>
        <v-form @submit.prevent="onSubmit">
            <table>
                <v-row justify="center">
                    <v-col cols="1" class="label mt-5 ml-4" >Star</v-col>
                    <v-col>
                        <v-text-field color="#e3c832" type="text" v-model="star_score"/>
                    </v-col>
                </v-row>
                <v-row justify="center">
                    <v-col cols="1" class="label mt-5 ml-4" >cafe_num</v-col>
                    <v-col>
                        <v-text-field color="#e3c832" type="text" v-model="cafeNum"/>
                    </v-col>
                </v-row>
                
                <v-row>
                    <v-col cols="12">
                        <v-textarea cols="80" rows="20" outlined color="grey"
                        style="white-space:pre-line" placeholder="content" class="mt-3"
                        v-model="review_content">
                        </v-textarea>
                    </v-col>
                </v-row>

                <v-row  wrap justify="center">

                    <v-col cols="1" class="label" > Images </v-col>

                    <v-col>
                        <input type="file" id="files" ref="files" style="width:150px" clearable 
                            multiple="multiple" v-on:change="handleFileUpload()"/>
                        <v-btn @click="fileDeleteButton()" class="imageDeleteBtn">delete Image</v-btn>
                    </v-col>

                    <v-col cols="10">
                    <v-img :src="image" class="preview" alt=""/>
                    </v-col>
                </v-row>

                <v-row wrap>
                    <v-btn @click="cancel" class="cancleBtn" color="grey" text>
                        <v-icon class="listIcon" justify="center"> mdi-keyboard-backspace</v-icon></v-btn>
                    <v-btn type="submit" class="writeBtn" color="black" dark>submit</v-btn>
                </v-row>
            </table>
        </v-form>
    </v-container>
</template>

<script>
export default {
    name:'CafeReviewRegister',
    
    data() {
        return {
            star_score:'',
            review_content:'',
            files: '',
            image :'',
            cafeNum:''
            
        }
    },
    //mounted () {
       // this.writer = this.$store.state.session.userId
    //},
    methods: {
        handleFileUpload () {
            console.log('add Image')
            var image = this.$refs['files'].files[0]
            const url = URL.createObjectURL(image)
            this.image = url
            this.files = this.$refs.files.files[0]
            
        },
        onSubmit () {
            const { star_score, review_content,cafeNum } = this
            const file =  this.$refs.files.files[0]
            this.$emit('submit', { star_score, review_content,file,cafeNum })
            console.log(star_score,review_content,file,cafeNum)
        },
        cancel (){
            this.$router.push('/main')
        },
        
        fileDeleteButton () {
            this.files = '',
            this.image = ''
        },       
    }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poiret+One&display=swap');
.label{
    font-size: 17pt;
    margin-right:5%;
    text-align: center;
    font-family: 'Poiret One', cursive;
}
table{ 
    background-color: #f3f3f3;;
    padding: 5% 10% 5% 10%;
    margin-left:auto;
    margin-right:auto;
      
}
.preview {
    position: relative;
    margin-left: auto;
    margin-right:auto;
    max-width: 400px;
    max-height: 600px;
}
.cancleBtn {
    position: relative;
    margin-top:1%;   
}
.writeBtn {
     margin-top:1%; 
      margin-left:33%;      
}
.imageDeleteBtn{
    zoom:0.8;
    margin-left:3%;
    
}
</style>
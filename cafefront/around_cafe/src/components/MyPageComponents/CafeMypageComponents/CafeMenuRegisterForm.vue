<template>
    <div class="registerForm">
        <form @submit.prevent="onSubmit">
            <v-container>
                <v-row justify="center">
                    <v-col cols="12" sm="6">
                        <h4>메뉴 등록하기</h4>
                    </v-col>
                </v-row>
                <v-row justify="center">
                    <v-col cols="12" sm="3">
                        <v-text-field label="메뉴 이름 작성" v-model="menu_name"/>
                    </v-col>
                    <v-col cols="12" sm="3">
                        <v-text-field label="메뉴 가격 작성" v-model="menu_price"/>
                    </v-col>
                </v-row>
                <v-row justify="center">
                    <v-col cols="12" sm="6">
                        <v-text-field label="메뉴 설명 작성하기" v-model="menu_content"/>
                    </v-col>
                </v-row>
                <v-row justify="center">
                    <v-col cols="12" sm="6">
                        <input type="file" id="files1" ref="files1"
                        multiple v-on:change="handleFileUpliad()"/>
                    </v-col>
                </v-row>
                <v-row justify="center">
                    <v-col cols="12" sm="2">
                        <br>
                        <v-btn type="submit">등록하기</v-btn>
                    </v-col>
                </v-row>
            </v-container>
        </form>
    </div>
</template>

<script>
    export default { 
        name:'CafeMenuRegisterForm',
        data() {
            return {
                menu_name: '',
                menu_price:'',
                menu_content:'',
                files1: '',
                response: ''
            }
        },
        methods: {
            handleFileUpliad() {
                this.files1 = this.$refs.files1.files
            },
            onSubmit() {
                if(this.files1.length > 0) {
                    const {menu_name, menu_price, menu_content, files1} = this
                    this.$emit('submit',{menu_name, menu_price, menu_content, files1})
                }else {
                    this.files1 = 0
                    
                    const {menu_name, menu_price, menu_content, files1} = this
                    this.$emit('submit',{menu_name, menu_price, menu_content, files1})
                }
            }
                
        }
    }
</script>

<style scoped>
.inputContent {
    border: 2px solid rgb(37, 26, 26);
    width:700px;
    height: 100px;
    color:black;
}

.registerForm{
    padding: 5%;
}
</style>
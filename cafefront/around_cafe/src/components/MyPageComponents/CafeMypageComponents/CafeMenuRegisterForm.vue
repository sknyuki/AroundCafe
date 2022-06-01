<template>
    <div class="registerForm">
        <form @submit.prevent="onSubmit">
            <v-container>
                <v-row><h4>메뉴 등록</h4></v-row>
                <v-row justify="center">
                    <v-col cols="12" sm="3">
                        <v-text-field label="메뉴 이름 작성" v-model="menu_name"/>
                    </v-col>
                    <v-col cols="12" sm="3">
                        <v-text-field label="메뉴 가격 작성" v-model="menu_price"/>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col>
                        <textarea type="text" v-model="menu_content" placeholder="메뉴 설명 작성" class="inputContent"/>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col>
                        <input type="file" id="files1" ref="files1"
                        multiple v-on:change="handleFileUpliad()"/>
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
            }
        }
    }
</script>

<style>
.inputContent {
    border: 2px solid rgb(37, 26, 26);
    width:700px;
    height: 100px;
    color:black;
}

.registerForm{
    padding: 20%;
}
</style>
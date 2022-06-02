<template>
    <div>
        <div>
            <h4>시그니처 메뉴 확인</h4>
            <v-card>
            <v-card-title>
            </v-card-title>
            <v-data-table :headers="headerTitle2" :items="signatureArr" class="elevation-0" >
                    
                    <template v-slot:[`item.menu_img`]="{ item }" >
                        <v-img v-if="item.menu_img != null" v-bind:src="require(`@/assets/cafe/cafeMenu/${item.menu_img}`)" height="50px"/>
                        <v-img v-if="item.menu_img == null" src="@/assets/cafe/cafeMenu/imgNull.png" height="50px"/>
                    </template>   
                    <template v-slot:[`item.actions`] ="{ item }" >
                    <v-icon
                        small
                        class="mr-2"
                        @click="editItem(item)"
                    >
                        mdi-pencil
                    </v-icon>
                    <v-icon
                        small
                        @click="deleteItem(item)"
                    >
                        mdi-delete
                    </v-icon>
                    </template>
            </v-data-table>
            </v-card>
            
        </div>
        <br>
        <div>
            <h4>등록한 메뉴 확인</h4>
            <v-card>
            <v-card-title>
            <v-text-field
                v-model="search"
                append-icon="mdi-magnify"
                label="메뉴 찾기"
                single-line
                hide-details
            ></v-text-field>
            </v-card-title>
            <v-data-table :headers="headerTitle1" :items="menuLists" :search="search"  class="elevation-0" >
                    
                    <template v-slot:[`item.linkInfo`]="{ item }" >
                        <v-img v-if="item.menu_img != null" v-bind:src="require(`@/assets/cafe/cafeMenu/${item.menu_img}`)" height="50px"/>
                        <v-img v-if="item.menu_img != null" v-bind:src="require(`@/assets/cafe/cafeMenu/imgNull.png`)" height="50px"/>
                    </template>   

                    <template v-slot:[`item.actions`] ="{ item }" >
                    <v-icon
                        small
                        class="mr-2"
                        @click="editItem(item)"
                    >
                        mdi-pencil
                    </v-icon>
                    <v-icon
                        small
                        @click="deleteItem(item)"
                    >
                        mdi-delete
                    </v-icon>
                    </template>
            </v-data-table>
            </v-card>

            <form @submit.prevent="modifySubmit">
                <v-dialog v-model="dialog" >
                    <v-card >
                        <v-card-title >수정하기</v-card-title>
                        <v-card-text>
                            <v-container>
                                <v-row>
                                    <v-col>
                                        <p>파일 다시 올리기</p>
                                        <input type="file" id="files2" ref="files2"
                                            multiple v-on:change="handleFileUpload2()"/>
                                    </v-col>
                                    <v-col>
                                        <v-text-field label="메뉴 이름 수정" v-model="modify_name"></v-text-field>
                                        <v-text-field label="메뉴 가격 수정" v-model="modify_price"></v-text-field>
                                        <v-text-field label="메뉴 설명 수정" v-model="modify_content"></v-text-field>
                                    </v-col>
                                </v-row>
                            </v-container>
                        </v-card-text>
                        <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="white" @click="dialog = false">돌아가기</v-btn>
                        <v-btn color="white" @click="modifySubmit">수정하기</v-btn>
                        </v-card-actions>
                    </v-card>   
                </v-dialog>
            </form>
                
            <v-dialog v-model="deleteDialog">
                <v-card>
                    <v-card-title class="headline">정말 삭제하시겠습니까?</v-card-title>
                    <v-card-text>삭제하게 되면 내용은 다시 볼 수 없게 됩니다.</v-card-text>
                    <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="white" @click="deleteDialog = false">돌아가기</v-btn>
                    <v-btn color="white" type="button" @click="deleteReview">삭제합니다</v-btn>
                    </v-card-actions>
                </v-card>   
            </v-dialog> 
        </div>
    </div>
</template>

<script>
import axios from 'axios'
    export default {
        name: 'CafeMenuListForm',
        data () {
            return {
                headerTitle1 : [
                    {text: 'no', value: 'menu_no', width: '20px'},
                    {text: 'image', vlaue: 'menu_img', width: '70px' },
                    {text: 'name', value: 'menu_name', width:'70px'},
                    {text: 'price', value: 'menu_price', width:'70px'},
                    {text: 'content', value: 'menu_content', width:'100px'},
                    { text: 'Actions', value: 'actions', sortable: false ,  width: "70px" },
                    { text: 'Signature', value: 'signature', sortable: false ,  width: "40px" },
                    { text: 'SoldOut', value: 'sold_out', sortable: false ,  width: "40px" }
                ],
                headerTitle2 : [
                    {text: 'no', value: 'menu_no', width: '20px'},
                    {text: 'image', vlaue: 'menu_img', width: '70px' },
                    {text: 'name', value: 'menu_name', width:'70px'},
                    {text: 'price', value: 'menu_price', width:'70px'},
                    {text: 'content', value: 'menu_content', width:'100px'},
                    { text: 'Signature', value: 'signature', sortable: false ,  width: "70px" }
                ],
                search: '',
                dialog:false,
                deleteDialog:false,
                modifyNo:'',
                deleteNo:'',
                files2:'',
                signatureArr:[],
                modify_name :'',
                modify_price : '',
                modify_content :''

            }
        },
        props: {
            menuLists: {
                type: Array,
                required: true
            }
        },
        mounted() {
            for(let idx=0; idx < this.menuLists.length ; idx++) {
                let count = 0

                if(this.menuLists[idx].signature == true) {
                    this.signatureArr[count]
                    count ++ 
                }
            }
        },
        methods: {
            editItem(item){
                this.dialog = true, 
                this.modifyNo = item.menu_no
                this.modify_name = item.menu_name
                this.modify_price = item.menu_price
                this.modify_content = item.menu_content
            },
            deleteItem(item){
                this.deleteDialog = true
                this.deleteNo = item.menu_no
            },
            handleFileUpload2() {
                this.files2 = this.$refs.files2.files
            },
            modifySubmit() {
                const {modifyNo, modify_name, modify_price, modify_content, files2} = this
                this.$emit('submit',{modifyNo, modify_name, modify_price, modify_content, files2} )
            },
            deleteReview() {
                
                this.menuNo = this.deleteNo

                axios.delete(`http://localhost:7777/menu/delete/${this.menuNo}` )
                        .then(() => {
                            alert('삭제가 완료되었습니다!')
                            this.$router.go()
                        })
                        .catch(() => {
                            alert('삭제실패!')
                        })
            }
        }
    }
</script>

<style scoped>

</style>
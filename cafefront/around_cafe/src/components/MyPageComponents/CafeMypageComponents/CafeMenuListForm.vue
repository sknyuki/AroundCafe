<template>
    <div>
        <v-text-field label="메뉴 이름 검색" v-model="modi_name"/>
        <div v-for="item in filterMenuLists" :key="item.menu_no">
            <v-container>
                <v-row>
                    <v-col class="col-12 col-sm-2">
                        <img v-if ="item.menu_img == null" v-bind:src="require(`@/assets/cafe/cafeMenu/imgNull.png`)" width="200px;">      
                        <img v-if ="item.menu_img != null" v-bind:src="require(`@/assets/cafe/cafeMenu/${item.menu_img}`)" width="200px;"><br>
                        {{item.menu_name}} <br> 
                        {{item.menu_price}}<br>
                    </v-col>
                    <v-col class="col-12 col-sm-10" style="padding: 0 0 0 3%;">
                        {{item.menu_content}}
                    </v-col>
                </v-row>
            </v-container>
            <br><br>
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
                    
                    <template v-slot:[`item.menu_img`] = "{ item }" >
                        <v-img v-if ="item.menu_img == null" v-bind:src="require(`@/assets/cafe/cafeMenu/imgNull.png`)" />
                        <v-img v-if ="item.menu_img != null" v-bind:src="require(`@/assets/cafe/cafeMenu/${item.menu_img}`)" />
                    </template>

                    <template v-slot:[`item.reviewFile`]="{ item }" >
                        <img v-if="item.reviewFile != null" v-bind:src="require(`@/assets/review/${item.reviewFile}`)" height="230px"/>
                        <img v-if="item.reviewFile == null" v-bind:src="require(`@/assets/review/nullImg.png`)" height="230px"/>
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

                    <template v-slot:[`item.signature`]="{ item }"  >
                        
                        <v-icon
                            v-if="item.signature == false"
                            small
                            class="mr-2"
                            @click="checkSignature(item)"
                        >
                            mdi-cards-heart-outline
                        </v-icon>

                        <v-icon
                            v-if="item.signature == true"
                            small
                            class="mr-2"
                            @click="deleteSignature(item)"
                        >
                            mdi-cards-heart
                        </v-icon>
                    </template> 

                    <template v-slot:[`item.sold_out`]="{ item }" >
                        <v-icon
                            v-if="item.sold_out == false"
                            small
                            class="mr-2"
                            @click="checkSoldOut(item)"
                        >
                            mdi-coffee-outline
                        </v-icon>
                         

                        <v-icon
                            v-if="item.sold_out == true"
                            small
                            class="mr-2"
                            @click="deleteSoldOut(item)"
                        >
                            mdi-coffee-off
                        </v-icon>
                    </template> 
            </v-data-table>
            </v-card>

            <form @submit.prevent="modifySubmit">
                <v-dialog v-model="dialog"  max-width="600">
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
                
            <v-dialog v-model="deleteDialog" max-width="600">
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

            <v-dialog v-model="signatureDialog" max-width="600">
                <v-card>
                    <v-card-title class="headline">해당 아이템을 시그니처로 등록하시겠습니까?</v-card-title>
                    <v-card-title >시그니처 등록은 최대 두개만 가능합니다.</v-card-title>
                    <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="white" @click="signatureDialog = false">돌아가기</v-btn>
                    <v-btn color="white" type="button" @click="changeSignature">등록합니다</v-btn>
                    </v-card-actions>
                </v-card>   
            </v-dialog> 
            <v-dialog v-model="deleteSigDialog" max-width="600">
                <v-card>
                    <v-card-title class="headline">해당 시그니처를 삭제하시겠습니까?</v-card-title>
                    <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="white" @click="deleteSigDialog = false">돌아가기</v-btn>
                    <v-btn color="white" type="button" @click="deleteSig">삭제합니다</v-btn>
                    </v-card-actions>
                </v-card>   
            </v-dialog> 
            <v-dialog v-model="soldOutDialog" max-width="600">
                <v-card>
                    <v-card-title class="headline">해당 아이템을 솔드아웃 처리하시겠습니까?</v-card-title>
                    <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="white" @click="soldOutDialog = false">돌아가기</v-btn>
                    <v-btn color="white" type="button" @click="changeSoldOut">솔드아웃</v-btn>
                    </v-card-actions>
                </v-card>   
            </v-dialog> 
            <v-dialog v-model="deleteSolDialog" max-width="600">
                <v-card>
                    <v-card-title class="headline">솔드아웃을 취소하겠습니까??</v-card-title>
                    <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="white" @click="deleteSolDialog = false">돌아가기</v-btn>
                    <v-btn color="white" type="button" @click="deleteSold">취소하기</v-btn>
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
                    {text: 'menu_img', vlaue: 'menu_img', width: '120px' },
                    {text: 'name', value: 'menu_name', width:'100px'},
                    {text: 'price', value: 'menu_price', width:'100px'},
                    {text: 'content', value: 'menu_content', width:'150px'},
                    { text: 'Actions', value: 'actions', sortable: false ,  width: "40px" },
                    { text: 'Signature', value: 'signature', sortable: false ,  width: "10px" },
                    { text: 'SoldOut', value: 'sold_out', sortable: false ,  width: "10px" }
                ],
                search: '',
                dialog:false,
                deleteDialog:false,
                signatureDialog: false,
                deleteSigDialog: false,
                soldOutDialog:false,
                deleteSolDialog: false,
                modifyNo:'',
                deleteNo:'',
                files2:'',
                modify_name :'',
                modify_price : '',
                modify_content :'',
                soldOutNo:'',
                signatureNo:'',
                deleteSigNo:'',
                deleteSoldNo:'',
                modi_name:''
            }
        },
        props: {
            menuLists: {
                type: Array,
                required: true
            }
        },
        computed: {
        filterMenuLists() {
            if(this.modi_name) {
                return this.menuLists.filter((item) => {
                return this.modi_name.toLowerCase().split(' ').every(v => item.menu_name.toLowerCase().includes(v));
            })
            }else{
                return this.menuLists;
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
            },
            checkSignature(item){
                this.signatureDialog = true
                this.signatureNo = item.menu_no
            },
            changeSignature(){
                this.menuNo = this.signatureNo

                axios.post(`http://localhost:7777/menu/changeSignature/${this.menuNo}`)
                        .then((res) => {
                            alert(res.data)
                            this.$router.go()
                        })
                        .catch(() => {
                            alert('등록실패!')
                        })
            },
            deleteSignature(item) {
                this.deleteSigDialog = true
                this.deleteSigNo = item.menu_no
            },
            deleteSig() {
                this.menuNo = this.deleteSigNo

                axios.post(`http://localhost:7777/menu/delSignature/${this.menuNo}`)
                        .then((res) => {
                            alert(res.data)
                            this.$router.go()
                        })
                        .catch(() => {
                            alert('삭제 실패!')
                        })
            },
            checkSoldOut(item){
                this.soldOutDialog = true
                this.soldOutNo = item.menu_no

            },
            changeSoldOut() {
                this.menuNo = this.soldOutNo

                axios.post(`http://localhost:7777/menu/changeSoldOut/${this.menuNo}`)
                        .then((res) => {
                            alert(res.data)
                            this.$router.go()
                        })
                        .catch(() => {
                            alert('삭제 실패!')
                        })
            },
            deleteSoldOut(item) {
                this.deleteSolDialog = true
                this.deleteSoldNo = item.menu_no
            },
            deleteSold() {
                this.menuNo = this.deleteSoldNo

                axios.post(`http://localhost:7777/menu/delSoldOut/${this.menuNo}`)
                        .then((res) => {
                            alert(res.data)
                            this.$router.go()
                        })
                        .catch(() => {
                            alert('변경 실패!')
                        })
            }
        }
    }

</script>

<style scoped>
</style>
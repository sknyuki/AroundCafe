<template>
    <div>
        <div>
            <h4>시그니처 메뉴 확인</h4>
            <v-card>
            <v-card-title>
            <v-text-field
                v-model="search"
                append-icon="mdi-magnify"
                label="Search"
                single-line
                hide-details
            ></v-text-field>
            </v-card-title>
            <v-data-table :headers="headerTitle" :items="cakeLists" :search="search"  class="elevation-0" >
                    
                    <template v-slot:[`item.linkInfo`]="{ item }" >
                        <img v-if="item.linkInfo != null" v-bind:src="require(`@/assets/cafe/cafeMenu/${item.menu_img}`)" height="230px"/>
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
                                        <v-select class="selectCake" v-model="design" :items="selectCake" label="디자인 선택"/>
                                        <v-select class="selectSize" v-model="size" :items="selectSize" label="사이즈 선택"/>
                                        <v-text-field label="가격 작성" v-model="price"></v-text-field>
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
        <br>
        <div>
            <h4>등록한 메뉴 확인</h4>
            <v-card>
            <v-card-title>
            <v-text-field
                v-model="search"
                append-icon="mdi-magnify"
                label="Search"
                single-line
                hide-details
            ></v-text-field>
            </v-card-title>
            <v-data-table :headers="headerTitle" :items="cakeLists" :search="search"  class="elevation-0" >
                    
                    <template v-slot:[`item.linkInfo`]="{ item }" >
                        <img v-if="item.linkInfo != null" v-bind:src="require(`@/assets/cafe/cafeMenu/${item.menu_img}`)" height="230px"/>
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
                                        <v-select class="selectCake" v-model="design" :items="selectCake" label="디자인 선택"/>
                                        <v-select class="selectSize" v-model="size" :items="selectSize" label="사이즈 선택"/>
                                        <v-text-field label="가격 작성" v-model="price"></v-text-field>
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
    export default {
        name: 'CafeMenuListForm',
        data () {
            return {
                heatherTitle : [
                    {text: 'no', value: 'menu_no', width: '20px'},
                    {text: 'image', vlaue: 'menu_img', width: '70px' },
                    {text: 'name', value: 'menu_name', width:'70px'},
                    {text: 'price', value: 'menu_price', width:'70px'},
                    {text: 'content', value: 'menu_content', width:'100px'},
                    { text: 'Actions', value: 'actions', sortable: false ,  width: "70px" }
                ]
            }
        }
    }
</script>

<style scoped>

</style>
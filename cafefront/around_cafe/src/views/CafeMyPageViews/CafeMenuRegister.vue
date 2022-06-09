<template>
    <div>
       <cafe-menu-register-form @submit="onSubmit"/>
       <cafe-menu-list-form :menuLists="menuLists" @submit="modifySubmit"/>
    </div>
</template>

<script>
import CafeMenuRegisterForm from '@/components/MyPageComponents/CafeMypageComponents/CafeMenuRegisterForm.vue'
import axios from 'axios'
import CafeMenuListForm from '@/components/MyPageComponents/CafeMypageComponents/CafeMenuListForm.vue'
import { mapState, mapActions } from 'vuex'
    
    export default {
        name: 'CafeMenuRegister',
        components: {
            CafeMenuRegisterForm,
            CafeMenuListForm,
                
        },
        data() {
            return {
                signatureArr:[]
            }
        },
        computed: {
            ...mapState(['menuLists']),
            
        },
        mounted(){
            this.fetchMenuLists()
        },
        methods: {
            ...mapActions(['fetchMenuLists']),
            onSubmit(payload) {
                const {menu_name, menu_price, menu_content, files1} = payload

                let formData = new FormData()

                let fileInfo = {
                    cafe_no : 1,
                    menu_name,
                    menu_price,
                    menu_content
                }

                formData.append(
                    "info", new Blob([JSON.stringify(fileInfo)], {type:"application/json"})
                );

                if(files1.length > 0) {
                    for(let idx= 0; idx < files1.length; idx ++) {
                        formData.append('fileList',files1[idx])
                    }
                }

                console.log(fileInfo)
                console.log(files1)
                axios.post('http://localhost:7777/menu/register', formData)
                        .then(res => {
                            alert(res.data)
                            this.$router.go()
                        })
                        .catch(() => {
                            alert("메뉴 등록에 실패하였습니다.")
                        })
            },
            modifySubmit(payload) {
                const { modifyNo, modify_name, modify_price, modify_content, files2 } = payload
                
                let formData = new FormData()

                let fileInfo = {
                    menu_no : modifyNo,
                    menu_name : modify_name,
                    menu_price : modify_price,
                    menu_content : modify_content
                }

                formData.append(
                    "info", new Blob([JSON.stringify(fileInfo)], {type:"application/json"})
                )

                if(files2.length > 0) {
                    for(let idx = 0; idx < files2.length; idx++) {
                        formData.append('fileList', files2[idx])
                    }
                }

                console.log(fileInfo)
                 let cafeNo = 1;
                 axios.put(`http://localhost:7777/menu/modify/${cafeNo}`, formData)
                        .then(() => {
                            alert('수정되었습니다!')
                            this.$router.go()
                        })
                        .catch(() => {
                            alert('수정 실패!')
                        })

            }
        }

    }
</script>

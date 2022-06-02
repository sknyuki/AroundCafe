<template>
    <div>
       <cafe-menu-register-form @submit="onSubmit"/>
       <cafe-menu-list-form :menuLists="menuLists"/>
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
            CafeMenuListForm
        },
        data() {
            return {

            }
        },
        computed: {
            ...mapState(['menuLists'])
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
                    menu_name,
                    menu_price,
                    menu_content
                }

                formData.append(
                    "info", new Blob([JSON.stringify(fileInfo)], {type:"application/json"})
                )

                if(files1 != 0) {
                    for(let idx= 0; idx < 1; idx ++) {
                        formData.append('fileList',files1[idx])
                    }
                }

                console.log(fileInfo)
                axios.post(`http://localhost:7777/menu/register`, formData)
                        .then(res => {
                            alert(res.data)
                            this.$router.go()
                        })
                        .catch(() => {
                            alert("메뉴 등록에 실패하였습니다.")
                        })
            }
        }

    }
</script>

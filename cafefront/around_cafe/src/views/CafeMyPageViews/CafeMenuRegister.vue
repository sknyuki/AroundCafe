<template>
    <div>
       <cafe-menu-register-form @submit="onSubmit"/>
    </div>
</template>

<script>
import CafeMenuRegisterForm from '@/components/MyPageComponents/CafeMypageComponents/CafeMenuRegisterForm.vue'
import axios from 'axios'
    
    export default {
        name: 'CafeMenuRegister',
        components: {
            CafeMenuRegisterForm
        },
        data() {
            return {

            }
        },
        methods: {
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

                if(this.files1 != null) {
                    for(let idx= 0; idx < 1; idx ++) {
                        formData.append('fileList',files1[idx])
                    }
                }

                console.log(fileInfo)
                axios.post('http://localhost:7777/menu/register', formData)
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

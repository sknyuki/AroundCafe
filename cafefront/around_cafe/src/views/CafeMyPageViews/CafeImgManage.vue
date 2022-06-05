<template>
    <div>
        <CafeImgManageForm :cafeImgLists="cafeImgLists"/>
    </div>
</template>

<script>
import CafeImgManageForm from '@/components/MyPageComponents/CafeMypageComponents/CafeImgManageForm.vue'
//import axios from 'axios'
import {mapActions, mapState} from 'vuex'

export default {
  components: { CafeImgManageForm },
    name:'CafeImgManage',
    data() {
        return {

        }
    },
    props: {
        cafeNo: {
            type: String,
            required: true
        }
    },
    computed: {
        ...mapState(['cafeImgLists'])
    },
    mounted() {
        this.fetchCafeImgLists(this.cafeNo)
    },
    methods : {
        ...mapActions(['fetchCafeImgLists'])
    }
}
</script>
<style lang="">
    
</style>
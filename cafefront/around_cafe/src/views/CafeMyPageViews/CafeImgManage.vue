<template>
    <div>
        
    </div>
</template>

<script>
//import axios from 'axios'
import {mapActions, mapState} from 'vuex'

export default {
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
        this.fetchCafeImgLists()
    },
    methods : {
        ...mapActions(['cafeImgLists'])
    }
}
</script>
<style lang="">
    
</style>
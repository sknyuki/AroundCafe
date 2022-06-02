<template>
    <v-container>
        <cafe-site-component/>
    </v-container>
</template>
<script>
import CafeSiteComponent from '@/components/CafeSite/CafeSiteComponent.vue'
export default {
    name:'CafeSiteList',
    components: { CafeSiteComponent },
     props:{
        cafeNo: {
            type: String,
            required: true
         },
     data () {
    return {
      fileList:[],
    }
},
    

    
     }
}
</script>

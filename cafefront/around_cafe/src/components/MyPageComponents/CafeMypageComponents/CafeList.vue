<template>
    <div>
        <div v-for="item in cafeBoards" :key="item.cafeNo">
            

        </div>
    </div>
</template>

<script>
export default {
    name:'Manager_cafeList',
    props:{
        cafeBoards:{
            type: Array,
            required: true
        }
    },
    data(){
        return {
            
        }
    }
}
</script>
<style lang="">
    
</style>
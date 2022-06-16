<template>
    <div>
        <CafeList :cafeBoards="cafeBoards"/>
    </div>
</template>
<script>
import CafeList from '@/components/MyPageComponents/CafeMypageComponents/CafeList.vue'
import { mapState, mapActions} from 'vuex'

export default {
  components: { CafeList },
    name: 'CafeListPage_manager',
    computed:{
        ...mapState(['cafeBoards'])
    },
    mounted(){
        this.fetchcafeBoardList()
    },
    methods: {
        ...mapActions(['fetchcafeBoardList'])
    }
}
</script>
<style lang="">
    
</style>
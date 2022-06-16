<template>
  <div style="padding:5%">
    <div v-for="item,index in this.listData" :key="index">
      <p>{{index}} = {{item}}</p>
    </div>

    <PaginationForm
      :pageSetting="pageDataSetting(total, limit, block, this.page)"
      @paging="pagingMethod"
    />
  </div>
</template>

<script>
import PaginationForm from '@/components/PaginationForm.vue'
 
  export default {
  components: { PaginationForm },
    data() {
      return {
        listData: [],
        paymentInfo:["1","2","3","4","5","6","7","8","9","11","12","13","15","12","13","11","11","11","1","2","3","4","5","6","7","8","9","11","12","13","15","12","13","11","11","11","13","15","12","13","11","11","11","1","2","3","4","5","6","7","8","9","11","12","13","15","12","13","11","11","11","13","15","12","13","11","11","11","1","2","3","4","5","6","7","8","9","11","12","13","15","12","13","11","11","11","13","15","12","13","11","11","11","1","2","3","4","5","6","7","8","9","11","12","13","15","12","13","11","11","11","13","15","12","13","11","11","11","1","2","3","4","5","6","7","8","9","11","12","13","15","12","13","11","11","11","13","15","12","13","11","11","11","1","2","3","4","5","6","7","8","9","11","12","13","15","12","13","11","11","11","13","15","12","13","11","11","11","1","2","3","4","5","6","7","8","9","11","12","13","15","12","13","11","11","11"],
        page: 1,
        limit: 5,
        block: 10,
        pageNo:'',
        total: ''
      }
    },
    mounted() {
      this.pagingMethod(this.page)
    },
    methods: {
      pagingMethod(page) {
        this.listData = this.paymentInfo.slice(
          (page - 1) * this.limit,
          page * this.limit
        )
        this.page = page
        let total = this.paymentInfo.length
        this.pageDataSetting(total, this.limit, this.block, page)
      },
      pageDataSetting(total, limit, block, page) {
        total = this.paymentInfo.length
        const totalPage = Math.ceil(total / limit)
        let currentPage = page
        const first =
          currentPage > 1 ? parseInt(currentPage, 10) - parseInt(1, 10) : null
        const end =
          totalPage !== currentPage
            ? parseInt(currentPage, 10) + parseInt(1, 10)
            : null
 
        let startIndex = (Math.ceil(currentPage / block) - 1) * block + 1
        let endIndex =
          startIndex + block > totalPage ? totalPage : startIndex + block - 1
        let list = []
        for (let index = startIndex; index <= endIndex; index++) {
          list.push(index)
        }
        return { first, end, list, currentPage }
      }
    }
  }
</script>
<template>
  <div class="menu-register">
    <ImgBox class="mb-10" />
    <div class="container">
      <div class="row">
        <div class="col-sm-4 col-md-3 col-lg-3">
          <CafeSidebar />
        </div>
        <div class="col-sm-8 col-md-9 col-lg-9">
          <CafeRegisterDialog />
          <CafeMenuList :listData="listData" />
          <PaginationForm
            :pageSetting="pageDataSetting(total, limit, block, this.page)"
            @paging="pagingMethod"
          />
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import ImgBox from "@/components/ImgBox.vue"
import CafeSidebar from "@/components/Cafe/CafeSidebar.vue"
import CafeRegisterDialog from "@/components/Cafe/CafeRegisterDialog.vue"
import CafeMenuList from "@/components/Cafe/CafeMenuList.vue"
import PaginationForm from "@/components/PaginationForm.vue"
export default {
  components: {
    ImgBox,
    CafeSidebar,
    CafeRegisterDialog,
    CafeMenuList,
    PaginationForm,
  },
  name: "CafeRegisterMenu",

  data() {
    return {
      menuLists: [
        {
          menuNo: 1,
          menu_name: "콜드브루1",
          menu_content:
            "콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루",
          menu_price: "5000",
        },
        {
          menuNo: 2,
          menu_name: "아아",
          menu_content:
            "콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루",
          menu_price: "5000",
        },
        {
          menuNo: 3,
          menu_name: "아아아",
          menu_content: "스타벅스 바리스타의 정성으로 탄생한 콜드 브루",
          menu_price: "5000",
        },
        {
          menuNo: 4,
          menu_name: "아아아아",
          menu_content:
            "콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루",
          menu_price: "5000",
        },
        {
          menuNo: 1,
          menu_name: "아아아아",
          menu_content:
            "콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루",
          menu_price: "5000",
        },
        {
          menuNo: 2,
          menu_name: "아아아아아",
          menu_content:
            "콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루",
          menu_price: "5000",
        },
        {
          menuNo: 3,
          menu_name: "아아아아아아",
          menu_content: "스타벅스 바리스타의 정성으로 탄생한 콜드 브루",
          menu_price: "5000",
        },
        {
          menuNo: 4,
          menu_name: "아아",
          menu_content:
            "콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루",
          menu_price: "5000",
        },
        {
          menuNo: 1,
          menu_name: "천재가",
          menu_content:
            "콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루",
          menu_price: "5000",
        },
        {
          menuNo: 2,
          menu_name: "저는",
          menu_content:
            "콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루",
          menu_price: "5000",
        },
        {
          menuNo: 3,
          menu_name: "해요",
          menu_content: "스타벅스 바리스타의 정성으로 탄생한 콜드 브루",
          menu_price: "5000",
        },
        {
          menuNo: 4,
          menu_name: "이해를",
          menu_content:
            "콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루",
          menu_price: "5000",
        },
        {
          menuNo: 1,
          menu_name: "백퍼",
          menu_content:
            "콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루",
          menu_price: "5000",
        },
        {
          menuNo: 2,
          menu_name: "어떻게",
          menu_content:
            "콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루",
          menu_price: "5000",
        },
        {
          menuNo: 3,
          menu_name: "제가",
          menu_content: "스타벅스 바리스타의 정성으로 탄생한 콜드 브루",
          menu_price: "5000",
        },
        {
          menuNo: 4,
          menu_name: "그럼요",
          menu_content:
            "콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루",
          menu_price: "5000",
        },
      ],
      listData: [],
      page: 1,
      limit: 5,
      block: 5,
      pageNo:'',
      total: ''
    }
  },
  mounted() {
      this.pagingMethod(this.page)
    },
    methods: {
      pagingMethod(page) {
        this.listData = this.menuLists.slice(
          (page - 1) * this.limit,
          page * this.limit
        )
        this.page = page
        let total = this.menuLists.length
        this.pageDataSetting(total, this.limit, this.block, page)
      },
      pageDataSetting(total, limit, block, page) {
        total = this.menuLists.length
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

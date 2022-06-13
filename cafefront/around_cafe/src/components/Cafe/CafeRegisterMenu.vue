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
          <CafeMenuList :menuLists="menuLists" />
          <PaginationForm />
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
          menu_name: "콜드브루",
          menu_content:
            "콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루",
          menu_price: "5000",
        },
        {
          menuNo: 2,
          menu_name: "콜드브루",
          menu_content:
            "콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루",
          menu_price: "5000",
        },
        {
          menuNo: 3,
          menu_name: "콜드브루",
          menu_content: "스타벅스 바리스타의 정성으로 탄생한 콜드 브루",
          menu_price: "5000",
        },
        {
          menuNo: 4,
          menu_name: "콜드브루",
          menu_content:
            "콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루콜드브루",
          menu_price: "5000",
        },
      ],
    }
  },
}
</script>

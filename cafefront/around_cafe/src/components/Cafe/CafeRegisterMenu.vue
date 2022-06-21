<template>
  <div class="menu-register">
    <ImgBox class="mb-10" />
    <div class="container">
      <div class="row">
        <div class="col-sm-4 col-md-3 col-lg-3">
          <CafeSidebar />
        </div>
        <div class="col-sm-8 col-md-9 col-lg-9">
          <CafeRegisterDialog @submit="onSubmitMenu" />
          <input
            class="form-input input-40"
            type="text"
            placeholder="메뉴 검색"
            v-model="modi_name"
          />
          <CafeMenuList :filterMenuLists="filterMenuLists" />
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
import axios from "axios"

export default {
  components: {
    ImgBox,
    CafeSidebar,
    CafeRegisterDialog,
    CafeMenuList,
  },
  name: "CafeRegisterMenu",
  props: {
    menuLists: {
      type: Array,
      required: true,
    },
  },

  data() {
    return {
      listData: [],
      page: 1,
      limit: 5,
      block: 5,
      pageNo: "",
      total: "",
      modi_name: "",
      fileterArray: [],
    }
  },
  computed: {
    filterMenuLists() {
      if (this.modi_name) {
        return this.menuLists.filter((item) => {
          return this.modi_name
            .toLowerCase()
            .split(" ")
            .every((v) => item.menu_name.toLowerCase().includes(v))
        })
      } else {
        return this.menuLists
      }
    },
  },
  mounted() {
    this.pagingMethod(this.page)
  },
  methods: {
    onSubmitMenu(payload) {
      const { menu_name, menu_price, menu_content, file } = payload

      let formData = new FormData()

      let fileInfo = {
        cafe_no: 1,
        menu_name,
        menu_price,
        menu_content,
      }

      formData.append(
        "info",
        new Blob([JSON.stringify(fileInfo)], { type: "application/json" })
      )

      if (file != null) {
        for (let idx = 0; idx < 1; idx++) {
          formData.append("fileList", file[idx])
        }
      }

      console.log(fileInfo)
      console.log(file)
      axios
        .post("http://localhost:7777/menu/register", formData)
        .then((res) => {
          alert(res.data)
          this.$router.go()
        })
        .catch(() => {
          alert("메뉴 등록에 실패하였습니다.")
        })
    },
  },
}
</script>

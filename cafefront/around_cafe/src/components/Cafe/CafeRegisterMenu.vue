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
          <div class="input-group">
            <i class="icSearch" aria-hidden="true"></i>
            <input
              class="form-input input-40"
              style="width: 350px"
              type="text"
              placeholder="메뉴 검색"
              v-model="modi_name"
              @keydown.enter.prevent="findList"
              @change="isSearchempty(modi_name)"
            />
            <button @click="resetList">X</button>
            <v-btn @click="findList">검색</v-btn>
          </div>
          <br />
          <div v-if="menuLists.length == 0"><p>메뉴를 등록해주세요!</p></div>
          <div v-else>
            <CafeMenuList :fileterArray="fileterArray" :user="user" />
          </div>
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
    user: {
      type: Object,
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

  methods: {
    onSubmitMenu(payload) {
      const { menu_name, menu_price, menu_content, file } = payload

      let formData = new FormData()

      let fileInfo = {
        memNo: this.user.memNo,
        menu_name,
        menu_price,
        menu_content,
      }

      formData.append(
        "info",
        new Blob([JSON.stringify(fileInfo)], { type: "application/json" })
      )

      if (file != null) {
        formData.append("fileList", file)
      }

      console.log(fileInfo)
      console.log(formData)
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
    findList() {
      let cafe_no = this.cafeNo
      let cafe_name = this.modi_name

      axios
        .get(`http://localhost:7777/menu/findMenu/${cafe_no}/${cafe_name}`)
        .then((res) => {
          if (res.data.length > 0) {
            this.fileterArray = res.data
          } else {
            alert("찾는 메뉴가 없습니다.")
          }
        })
        .catch(() => {
          alert("찾는 메뉴가 없습니다.")
        })
    },
    resetList() {
      this.fileterArray = ""
      this.modi_name = ""
    },
    isSearchempty(modi_name) {
      if (modi_name.length === 0) {
        this.fileterArray = ""
      }
    },
  },
}
</script>
<style lang="scss" scoped>
.input {
  position: relative;
}
button {
  position: absolute;
  top: 7px;
  left: 320px;
  width: 13px;
  height: 24px;
}
.v-btn {
  position: absolute;
  top: 0px;
  left: 360px;
}
</style>

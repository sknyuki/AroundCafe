<template>
  <div class="edit">
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <ImgBox />
        </div>
        <div class="col-sm-4 col-md-3 col-lg-3">
          <UserSidebar />
        </div>
        <div class="col-sm-8 col-md-9 col-lg-9">
          <header>
            <h1>좋아요</h1>
          </header>
          <div>
            <template v-if="loading">
              <LoadingSpinner :loading="loading" />
            </template>
            <template v-else>
              <CafeCardLike
                v-if="checkLike"
                :cafeMatchBoards="cafeMatchBoards"
                :myLikes="myLikes"
              />
            </template>
          </div>
          <div class="cafe-review-pag">
            <PaginationForm />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ImgBox from "@/components/ImgBox.vue"
import UserSidebar from "@/components/User/UserSidebar.vue"
import CafeCardLike from "@/components/Cafe/CafeCardLike.vue"
import PaginationForm from "@/components/PaginationForm.vue"
import { mapState, mapActions } from "vuex"
import LoadingSpinner from "../LoadingSpinner.vue"

export default {
  name: "UserLike",
  props: {
    cafeBoards: {
      type: Array,
      required: true,
    },
  },

  components: {
    UserSidebar,
    ImgBox,
    PaginationForm,
    CafeCardLike,
    LoadingSpinner,
  },

  data() {
    return {
      loading: false,
      membNo: JSON.parse(localStorage.getItem("user")).memNo,
      cafeMatchBoards: [],
    }
  },
  computed: {
    //...mapState(["cafeBoards", "myLikes"]),
    ...mapState(["myLikes"]),
  },

  async mounted() {
    //await this.fetchcafeBoardList()
    this.loading = true
    await this.fetchMyLikesList(this.membNo)
    setTimeout(() => {
      this.loading = false
    }, 1500)
    // await Promise.all((resolve) => {
    //   resolve(this.fetchcafeBoardList(), this.fetchMyLikesList(this.membNo))
    // })

    this.checkLike()
  },
  methods: {
    ...mapActions(["fetchMyLikesList"]),

    checkLike() {
      for (let i = 0; i < this.cafeBoards.length; i++) {
        for (let j = 0; j < this.myLikes.length; j++) {
          if (this.myLikes[j] == this.cafeBoards[i].cafeNo) {
            this.cafeMatchBoards.push(this.cafeBoards[i])

            console.log(this.cafeMatchBoards)
            break
          }
        }
      }
    },
  },
}
</script>
<style lang="scss" scoped>
.cafe-review {
  &-pag {
    @include flexbox(end);
    margin-bottom: 16px;
  }
}
</style>

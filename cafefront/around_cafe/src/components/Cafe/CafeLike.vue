<template>
  <div>
    <div v-if="onLike">
      <button class="cafe-like-btn" type="button" @click="likeOn()">
        <i class="icHeartFilled"></i>
      </button>
    </div>

    <div v-else>
      <button class="cafe-like-btn" type="button" @click="likeOn()">
        <i class="icHeart"></i>
      </button>
    </div>
  </div>
</template>

<script>
import axios from "axios"
import { mapState, mapActions } from "vuex"
export default {
  name: "CafeLike",

  computed: {
    ...mapState(["likes", "myLikes"]),
  },

  props: {
    index: {
      type: Number,
      require: true,
    },
    cafeItem: {
      type: Object,
      require: true,
    },
  },
  data() {
    return {
      onLike: "",
      membNo: JSON.parse(localStorage.getItem("user")).memNo,
      cafeNo: this.cafeItem.cafeNo,
      onLogin: false,
    }
  },

  mounted() {
    this.fetchLikesList(this.cafeNo)
    this.likeList()
    setTimeout(this.matching, 0.5)
  },

  methods: {
    ...mapActions(["fetchLikesList"]),

    matching() {
      console.log(this.myLikes)
      for (let j = 0; j < this.myLikes.length; j++) {
        if (this.myLikes[j] == this.cafeItem.cafeNo) {
          this.onLike = true
          break
        } else {
          this.onLike = false
        }
      }
    },

    likeList() {
      axios
        .get(`http://localhost:7777/cafe/likes/${this.cafeNo}/${this.membNo}`)
        .then((res) => {
          this.$store.state.like = res.data
        })
        .catch(() => {})
    },

    likeOn() {
      axios
        .post(
          `http://localhost:7777/cafe/likes/${this.cafeNo}/${this.membNo}`,
          {},
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        )
        .then(() => {
          history.go(0)
        })
        .catch(() => {
          alert("로그인해주세요!")
        })
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/gallery/cafe-like";
</style>

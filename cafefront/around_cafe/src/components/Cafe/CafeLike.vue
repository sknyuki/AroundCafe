<template>
  <div>
    <div v-if="onLike">
      <button
        class="cafe-like-btn"
        type="button"
        @click="likeOn()"
        aria-label="카페가 좋아요됨"
      >
        <i class="icHeartFilled" aria-hidden="true"></i>
      </button>
    </div>

    <div v-else>
      <button
        class="cafe-like-btn"
        type="button"
        @click="likeOn()"
        aria-label="카페가 좋아요 버튼"
      >
        <i class="icHeart" aria-hidden="true"></i>
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

  async mounted() {
    await this.fetchLikesList(this.cafeNo)
    await this.likeList()
    await this.matching()
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

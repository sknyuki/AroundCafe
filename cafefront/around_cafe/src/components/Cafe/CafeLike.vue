<template>
  <div v-if="onLike">
    <button
      class="cafe-like-btn"
      type="button"
      @click="likeOn()"
      aria-label="카페가 좋아요됨"
    >
      <svg
        viewBox="0 0 32 32"
        xmlns="http://www.w3.org/2000/svg"
        aria-hidden="true"
        role="presentation"
        focusable="false"
        style="
          display: block;
          height: 24px;
          width: 24px;
          stroke: #ffffff;
          stroke-width: 2;
          overflow: visible;
          fill: #ef5350;
        "
      >
        <path
          d="m16 28c7-4.733 14-10 14-17 0-1.792-.683-3.583-2.05-4.95-1.367-1.366-3.158-2.05-4.95-2.05-1.791 0-3.583.684-4.949 2.05l-2.051 2.051-2.05-2.051c-1.367-1.366-3.158-2.05-4.95-2.05-1.791 0-3.583.684-4.949 2.05-1.367 1.367-2.051 3.158-2.051 4.95 0 7 7 12.267 14 17z"
        ></path>
      </svg>
    </button>
  </div>

  <div v-else>
    <button
      class="cafe-like-btn"
      type="button"
      @click="likeOn()"
      aria-label="카페가 좋아요 버튼"
    >
      <svg
        viewBox="0 0 32 32"
        xmlns="http://www.w3.org/2000/svg"
        aria-hidden="true"
        role="presentation"
        focusable="false"
        style="
          display: block;
          height: 24px;
          width: 24px;
          fill: rgba(0, 0, 0, 0.5);

          overflow: visible;
          stroke: #ffffff;
          stroke-width: 2;
        "
      >
        <path
          d="m16 28c7-4.733 14-10 14-17 0-1.792-.683-3.583-2.05-4.95-1.367-1.366-3.158-2.05-4.95-2.05-1.791 0-3.583.684-4.949 2.05l-2.051 2.051-2.05-2.051c-1.367-1.366-3.158-2.05-4.95-2.05-1.791 0-3.583.684-4.949 2.05-1.367 1.367-2.051 3.158-2.051 4.95 0 7 7 12.267 14 17z"
        ></path>
      </svg>
    </button>
  </div>
</template>

<script>
import axios from "axios"
import { mapState, mapActions } from "vuex"
export default {
  name: "CafeLike",

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
  computed: {
    ...mapState(["likes", "myLikes"]),
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

<template>
  <div>
    <div v-if="onHelp">
      <button class="cafe-item-btn" type="button" @click="onLikes()">
        <i class="icHeart" color="red"></i>
      </button>
    </div>

    <div v-else>
      <button class="cafe-item-btn" type="button" @click="onLikes()">
        <i class="icHeart" color="grey"></i>
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
    ...mapState(["like", "likes", "mylikes"]),
  },

  props: {
    cafeBoard: {
      type: Object,
      require: true,
    },
  },
  data() {
    return {
      onLike: "",
      loginInfo: JSON.parse("3"),
    }
  },

  mounted() {
    this.fetchLikesList(this.cafeBoard.cafeNo)
    this.fetchLike({ cafeNo: this.cafeBoard.cafeNo, membNo: this.loginInfo })
    this.fetchMyLikesList(3)

    setTimeout(this.change, 0.5)
  },

  methods: {
    ...mapActions(["fetchLike", "fetchLikesList", "fetchMyLikesList"]),
    change() {
      for (let i = 0; i < this.myLikes.length; i++) {
        if (this.myLikes[i] == this.cafeBoard.cafeNo) {
          this.onLike = true
          break
        } else {
          this.onLike = false
        }
      }
    },

    likeOn() {
      axios
        .post(
          `http://localhost:7777/cafe/like/${this.cafeBoard.cafeNo}/${this.loginInfo}`,
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
          alert("문제 발생!")
        })
    },
  },
}
</script>

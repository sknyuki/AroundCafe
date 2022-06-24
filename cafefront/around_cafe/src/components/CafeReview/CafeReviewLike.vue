<template>
  <div>
    <div class="cafe-review-footer" v-if="onHelp">
      <v-btn @click="onLikes()" aria-label="좋아요 버튼"> 도움이 돼요 </v-btn>
      <p>
        <strong>
          <span> {{ review.reviewLike.length }}</span
          >명</strong
        >에게 도움이 되었습니다.
      </p>
    </div>

    <div class="cafe-review-footer" v-else>
      <v-btn @click="onLikes()" aria-label="좋아요 버튼" color="#3f51b5">
        <i class="icCheck"></i> 도움됨
      </v-btn>

      <p>
        <strong>
          <span> {{ review.reviewLike.length }}</span
          >명</strong
        >에게 도움이 되었습니다.
      </p>
    </div>
  </div>
</template>

<script>
import axios from "axios"
import { mapState, mapActions } from "vuex"
export default {
  name: "CafeReviewLike",

  computed: {
    ...mapState(["help", "helps", "myHelps"]),
  },

  props: {
    review: {
      type: Object,
      require: true,
    },
    index: {
      type: Number,
      require: true,
    },
  },
  data() {
    return {
      onHelp: "",
      loginInfo: JSON.parse("3"),
    }
  },

  mounted() {
    this.fetchHelpsList(this.review.reviewNo)
    this.fetchHelp({ reviewNo: this.review.reviewNo, membNo: this.loginInfo })
    this.fetchMyHelpsList(3)

    setTimeout(this.change, 0.1)
  },

  methods: {
    ...mapActions(["fetchHelp", "fetchHelpsList", "fetchMyHelpsList"]),
    change() {
      for (let i = 0; i < this.myHelps.length; i++) {
        if (this.myHelps[i] == this.review.reviewNo) {
          this.onHelp = true
          break
        } else {
          this.onHelp = false
        }
      }
    },

    onLikes() {
      axios
        .post(
          `http://localhost:7777/cafe/like/${this.review.reviewNo}/${this.loginInfo}`,
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

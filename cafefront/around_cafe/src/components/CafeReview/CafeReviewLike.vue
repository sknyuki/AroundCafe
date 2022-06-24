<template>
  <div>
    <div class="cafe-review-footer" v-if="!onHelp[index]">
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
import { mapActions, mapState } from "vuex"
export default {
  name: "CafeReviewLike",

  computed: {
    ...mapState(["help", "helps"]),
  },

  props: {
    review: {
      type: Object,
      require: true,
    },
    myHelps: {
      type: Array,
      require: true,
    },
    index: {
      type: Number,
      require: true,
    },
  },
  data() {
    return {
      onHelp: [],
      loginInfo: JSON.parse("2"),
    }
  },

  mounted() {
    this.fetchHelpsList(this.review.reviewNo)
    this.fetchHelp({ reviewNo: this.review.reviewNo, membNo: this.loginInfo })

    //

    for (let i = 0; i < this.myHelps.length; i++) {
      let count = 0

      if (this.myHelps[i] == this.review.reviewNo) {
        this.onHelp[count] = true
        count++
      } else {
        this.onHelp[count] = false
      }
    }
  },

  methods: {
    ...mapActions(["fetchHelp", "fetchHelpsList"]),

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

<template>
  <div>
    <v-btn @click="findPostalcode()" class="btn-indigo">주소 찾기</v-btn>
  </div>
</template>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
import { EventBus } from "@/utils/eventBus"
export default {
  data() {
    return {
      valid: true,
    }
  },
  props: [],
  mounted() {
    let recaptchaScript = document.createElement("script")
    recaptchaScript.setAttribute(
      "src",
      "//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"
    )
    document.head.appendChild(recaptchaScript)
  },
  methods: {
    findPostalcode() {
      new daum.Postcode({
        oncomplete: (data) => {
          // const post = data.zonecode
          // const cafeAdr3 = data.address

          // EventBus.$emit("findPostalcode", { post, cafeAdr3 })
          const post = data.zonecode
          const address = data.address

          EventBus.$emit("findPostalcode", { post, address })
        },
      }).open()
    },
  },
}
</script>

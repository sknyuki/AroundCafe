<template>
  <CafeCard :cafeItems="cafeItems" />
</template>
<script>
import CafeCard from "@/components/Cafe/CafeCard.vue"
export default {
  components: { CafeCard },
  data() {
    return {
      cafeItems: [
        {
          id: 1,
          title: "벤투라",
          location: "서울",
          time: "09:00 - 23:00",
          star: "5",
          img: [
            { image: require("@/assets/images/img-review-01.jpg") },
            { image: require("@/assets/images/img-review-02.jpg") },
            { image: require("@/assets/images/img-review-03.jpg") },
            { image: require("@/assets/images/img-review-04.jpg") },
            { image: require("@/assets/images/img-review-05.jpg") },
          ],
        },
        {
          id: 2,
          title: "스타벅스",
          location: "부산",
          time: "08:00 - 23:00",
          star: "4",
          img: [
            { image: require("@/assets/images/img-review-01.jpg") },
            { image: require("@/assets/images/img-review-02.jpg") },
            { image: require("@/assets/images/img-review-03.jpg") },
            { image: require("@/assets/images/img-review-04.jpg") },
            { image: require("@/assets/images/img-review-05.jpg") },
          ],
        },
        {
          id: 3,
          title: "투썸플레이스",
          location: "부산",
          time: "08:00 - 23:00",
          star: "4.5",
          img: [
            { image: require("@/assets/images/img-review-01.jpg") },
            { image: require("@/assets/images/img-review-02.jpg") },
            { image: require("@/assets/images/img-review-03.jpg") },
            { image: require("@/assets/images/img-review-04.jpg") },
            { image: require("@/assets/images/img-review-05.jpg") },
          ],
        },
        {
          id: 4,
          title: "투썸플레이스",
          location: "부산",
          time: "08:00 - 23:00",
          star: "4.5",
          img: [
            { image: require("@/assets/images/img-review-01.jpg") },
            { image: require("@/assets/images/img-review-02.jpg") },
            { image: require("@/assets/images/img-review-03.jpg") },
            { image: require("@/assets/images/img-review-04.jpg") },
            { image: require("@/assets/images/img-review-05.jpg") },
          ],
        },
        {
          id: 5,
          title: "투썸플레이스",
          location: "부산",
          time: "08:00 - 23:00",
          star: "4.5",
          img: [
            { image: require("@/assets/images/img-review-01.jpg") },
            { image: require("@/assets/images/img-review-02.jpg") },
            { image: require("@/assets/images/img-review-03.jpg") },
            { image: require("@/assets/images/img-review-04.jpg") },
            { image: require("@/assets/images/img-review-05.jpg") },
          ],
        },
        {
          id: 6,
          title: "투썸플레이스",
          location: "부산",
          time: "08:00 - 23:00",
          star: "4.5",
          img: [
            { image: require("@/assets/images/img-review-01.jpg") },
            { image: require("@/assets/images/img-review-02.jpg") },
            { image: require("@/assets/images/img-review-03.jpg") },
            { image: require("@/assets/images/img-review-04.jpg") },
            { image: require("@/assets/images/img-review-05.jpg") },
          ],
        },
        {
          id: 7,
          title: "투썸플레이스",
          location: "부산",
          time: "08:00 - 23:00",
          star: "4.5",
          img: [
            { image: require("@/assets/images/img-review-01.jpg") },
            { image: require("@/assets/images/img-review-02.jpg") },
            { image: require("@/assets/images/img-review-03.jpg") },
            { image: require("@/assets/images/img-review-04.jpg") },
            { image: require("@/assets/images/img-review-05.jpg") },
          ],
        },
        {
          id: 8,
          title: "투썸플레이스",
          location: "부산",
          time: "08:00 - 23:00",
          star: "4.5",
          img: [
            { image: require("@/assets/images/img-review-01.jpg") },
            { image: require("@/assets/images/img-review-02.jpg") },
            { image: require("@/assets/images/img-review-03.jpg") },
            { image: require("@/assets/images/img-review-04.jpg") },
            { image: require("@/assets/images/img-review-05.jpg") },
          ],
        },
      ],
    }
  },
}
</script>

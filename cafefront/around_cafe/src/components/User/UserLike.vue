<template>
  <div class="edit">
    <ImgBox class="mb-10" />
    <div class="container">
      <div class="row">
        <div class="col-sm-4 col-md-3 col-lg-3">
          <UserSidebar />
        </div>
        <div class="col-sm-8 col-md-9 col-lg-9">
          <header>
            <h1>좋아요</h1>
          </header>
          <CafeCard :cafeItems="cafeItems" />

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
import CafeCard from "@/components/Cafe/CafeCard.vue"
import PaginationForm from "@/components/PaginationForm.vue"
export default {
  name: "UserLike",

  components: { UserSidebar, ImgBox, CafeCard, PaginationForm },
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
<style lang="scss" scoped>
.cafe-review {
  &-pag {
    @include flexbox(end);
    margin-bottom: 16px;
  }
}
</style>

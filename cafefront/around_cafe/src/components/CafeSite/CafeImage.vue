<template>
  <div>
    <br />
    <h2>Cafeboard.name</h2>

    <v-carousel hide-delimiters height="auto">
      <div class="showimg" style="margin: auto">
        <v-carousel-item v-for="file in files" :key="file.file_no">
          <picture>
            <v-img
              :src="file.showimg"
              max-height="700"
              max-width="700"
              style="border-radius: 20px"
            >
            </v-img>
          </picture>
        </v-carousel-item>
      </div>
    </v-carousel>
    <!-- <v-row>
      <v-col
        cols="12"
        md="6"
      >
        <v-img
          :src="file.showimg1"
          max-height="700"
          max-width="700"
          aspect-ratio="1"
        >
          <v-col cols="12" md="6"> </v-col>
          <template v-slot:placeholder>
            <v-row class="fill-height ma-0" align="center" justify="center">
              <v-progress-circular
                indeterminate
                color="grey lighten-5"
              ></v-progress-circular>
            </v-row>
          </template>
        </v-img>
      </v-col>
    </v-row> -->
  </div>
</template>
<script>
export default {
  props: {},
  data() {
    return {
      files: [
        { file_no: 0, showimg: require("@/assets/images/img-review-01.jpg") },
        { file_no: 1, showimg: require("@/assets/images/img-review-02.jpg") },
        { file_no: 2, showimg: require("@/assets/images/img-review-03.jpg") },
        { file_no: 3, showimg: require("@/assets/images/img-review-04.jpg") },
      ],
    }
  },
}
</script>

<style scoped></style>

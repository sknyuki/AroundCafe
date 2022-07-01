<template>
  <div>
    <div id="map" style="width: 100%; height: 650px"></div>
  </div>
</template>

<script>
export default {
  name: "MapKakaoFind",
  props: {
    cafeBoard: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      map: null,
      geocoder: null,
    }
  },
  methods: {
    initMap() {
      const container = document.getElementById("map")
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3,
      }

      const map = new kakao.maps.Map(container, options)
      this.geocoder = new kakao.maps.services.Geocoder()
      console.log(this.geocoder)
      console.log("init" + this.cafeBoard)

      var imageSrc = require(`@/assets/images/Logo.png`),
        imageOption = { offset: new kakao.maps.Point(29, 29) },
        imageSize = new kakao.maps.Size(70, 70)

      var markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption
      )

      const cafeBoard = this.cafeBoard
      this.geocoder.addressSearch(cafeBoard.address, function (result, status) {
        console.log(kakao.maps.services.Status.OK)
        if (status === kakao.maps.services.Status.OK) {
          const coords = new kakao.maps.LatLng(result[0].y, result[0].x)
          console.log(coords)

          const marker = new kakao.maps.Marker({
            map,
            position: coords,
            image: markerImage,
          })

          const infowindow = new kakao.maps.InfoWindow({})
          infowindow.open(map, marker)

          map.setCenter(coords)
        }
      })
    },

    kakao() {
      if (!window.kakao || !window.kakao.maps) {
        const script = document.createElement("script")
        /* global kakao */
        script.onload = () => kakao.maps.load(this.initMap)
        script.src =
          "https://dapi.kakao.com/v2/maps/sdk.js?appkey=ec61fc18851964c845de3db938cfd080&libraries=services&autoload=false"
        document.head.appendChild(script)
      } else {
        this.initMap()
      }
      return
    },
  },
  created() {
    this.$watch("cafeBoard", function () {
      console.log("watch")
      this.kakao()
      return
    })
    console.log("new")
    this.kakao()
  },
}
</script>

<style lang="scss"></style>

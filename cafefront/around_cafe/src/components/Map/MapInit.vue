<template>
  <div id="map"></div>
</template>

<script>
//let kakao = window.kakao
export default {
  name: "MapInit",
  props: ["options"],
  data() {
    return {
      map: null,
    }
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap()
    } else {
      const script = document.createElement("script")
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap)
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=ec61fc18851964c845de3db938cfd080&libraries=services&autoload=false"
      document.head.appendChild(script)
    }
  },
  methods: {
    initMap() {
      var container = document.getElementById("map") // 지도를 표시할 div
      var options = {
        center: new kakao.maps.LatLng(37.56832, 126.97976), // 지도의 중심좌표
        level: 8,
        // mapTypeId : kakao.maps.MapTypeId.ROADMAP
      }

      var map = new kakao.maps.Map(container, options)
      console.log(map)
    },
  },
}
</script>

<style scoped>
.kmap {
  width: 99%;
  height: 100%;
}
</style>

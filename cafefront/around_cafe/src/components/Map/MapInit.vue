<template>
  <div id="map"></div>
</template>

<script>
//let kakao = window.kakao
export default {
  name: "MapInit",
  data() {
    return {
      map: null,
    }
  },
  props: ["options"],
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
      var container = document.getElementById("map")
      var options = {
        center: new kakao.maps.LatLng(37.56832, 126.97976),
        level: 6,
      }

      let map = new kakao.maps.Map(container, options)
      this.map = map
      let mapTypeControl = new kakao.maps.MapTypeControl()
      this.map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT)
      let zoomControl = new kakao.maps.ZoomControl()
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT)

      kakao.maps.event.addListener(this.map, "dragend", () => {
        var bounds = this.map.getBounds()

        this.swLatlng = bounds.getSouthWest()

        this.neLatlng = bounds.getNorthEast()

        // var message =
        //   "<p>영역좌표는 남서쪽 위도, 경도는  " +
        //   this.swLatlng.toString() +
        //   "이고 <br>"
        // message +=
        //   "북동쪽 위도, 경도는  " + this.neLatlng.toString() + "입니다 </p>"

        // this.matcharea()
        // this.showstation()
        // this.showdeallist()

        // var resultDiv = document.getElementById("result")
        // resultDiv.innerHTML = message
      })
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

<!--

<template>
  <div id="map"></div>
</template>

<script>
export default {
  name: "MapInit",

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
      /* global kakao */ // eslint-disable-line no-unused-vars
      script.onload = () => kakao.maps.load(this.initMap)
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=ec61fc18851964c845de3db938cfd080&libraries=services&autoload=false"
      document.head.appendChild(script)
    }
  },
  methods: {
    initMap() {
      const container = document.getElementById("map")
      const options = {
        center: new kakao.maps.LatLng(37.56832, 126.97976),
        level: 6,
      }

      let map = new kakao.maps.Map(container, options)
      this.map = map
      let mapTypeControl = new kakao.maps.MapTypeControl()
      this.map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT)
      let zoomControl = new kakao.maps.ZoomControl()
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT)

      kakao.maps.event.addListener(this.map, "dragend", () => {
        let bounds = this.map.getBounds()

        this.swLatlng = bounds.getSouthWest()

        this.neLatlng = bounds.getNorthEast()

        // let message =
        //   "<p>영역좌표는 남서쪽 위도, 경도는  " +
        //   this.swLatlng.toString() +
        //   "이고 <br>"
        // message +=
        //   "북동쪽 위도, 경도는  " + this.neLatlng.toString() + "입니다 </p>"

        // // this.matcharea()
        // // this.showstation()
        // // this.showdeallist()

        // var resultDiv = document.getElementById("result")
        // resultDiv.innerHTML = message
      })
      let mapCon = map
      this.$store.state.mapCon = mapCon
    },
  },
}
</script>

<style scoped>
.kmap {
  width: 99%;
  height: 100%;
}
#map {
  width: 100%;
  height: 100%;
  position: relative;
  overflow: hidden;
}
</style>
-->

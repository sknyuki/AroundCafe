<template>
  <div class="map_wrap">
    <!-- <MapInit :options="options" ref="map" id="map"/>  -->
    <div id="map"></div>
    <v-container id="map_side">
      <form @submit.prevent="searchPlace()">
        <h1 class="map_text-center">Search</h1>
        <v-row class="map_searchIn">
          <v-col cols="10" class="ml-6">
            <div class="input-group">
              <i class="icSearch" aria-hidden="true"></i>
              <input
                class="form-input input-40"
                type="text"
                placeholder="지역 검색"
                id="keyword"
              />
            </div>
          </v-col>
          <v-btn @click="getCurrentBtn()">현재 위치</v-btn>
        </v-row>

        <v-row class="map_searchResult">
          <v-col cols="11">
            <div class="map_llabel">Total &nbsp;{{ results.length }}</div>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="11" class="map_label">
            <div class="map_place" v-for="rs in results" :key="rs.cafe_no">
              <!-- <div display: flex justify-content: space-between> -->
              <!-- <span> -->

              <div @click="showInfo(rs)">{{ rs.cafe_name }}</div>

              <div class="map_cafeInfo">{{ rs.address }}</div>
              <img
                class="map_cafeInfoImg"
                :src="
                  require(`@/assets/cafe/cafeMypage/${rs.cafeImgs[0].cafe_img}`)
                "
              />
              <img
                class="map_cafeInfoImg"
                :src="
                  require(`@/assets/cafe/cafeMypage/${rs.cafeImgs[1].cafe_img}`)
                "
              />
              <img
                class="map_cafeInfoImg"
                :src="
                  require(`@/assets/cafe/cafeMypage/${rs.cafeImgs[2].cafe_img}`)
                "
              />

              <!-- </span> -->

              <!-- </div> -->
            </div>
          </v-col>
        </v-row>
      </form>

      <div id="pagination"></div>
    </v-container>
  </div>
</template>

<script>
//import MapInit from "@/components/Map/MapInit.vue"
import { mapState, mapActions } from "vuex"
//import axios from "axios"
//var kakao = window.kakao
export default {
  // components: {
  //     MapInit
  //   },
  name: "MapMain",
  data: () => ({
    //cafeBoards:cafeBoards,
    keyword: null,
    results: [],
    marker: [],
    map: null,
    geocoder: null,
    options: {
      center: {
        lat: 37.56832,
        lng: 126.97976,
      },
      level: 6,
    },
    infowindows: [],
    customOverlay: null,
    bounds: null,
    positions: [],
    area: {},
  }),
  computed: {
    ...mapState(["cafeBoards", "trans", "mapCon"]),
  },
  mounted() {
    this.fetchcafeBoardList()
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
    this.$store.state.trans = this.coords
    console.log(this.$store.state.trans)
  },
  methods: {
    ...mapActions(["fetchcafeBoardList"]),
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
      })
      let mapCon = map
      this.$store.state.mapCon = mapCon
    },
    searchPlace() {
      mapTmp = ""
      this.marker = ""

      this.results = []
      let trans = []

      var keyword = document.getElementById("keyword").value

      if (!keyword.replace(/^\s+|\s+$/g, "")) {
        alert("지역을 입력해주세요")
        return false
      }

      let mapTmp = this.$store.state.mapCon

      var imageSrc = require(`@/assets/images/Logo.png`),
        imageOption = { offset: new kakao.maps.Point(29, 29) },
        imageSize = new kakao.maps.Size(70, 70)

      var markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption
      )

      this.geocoder = new kakao.maps.services.Geocoder()

      this.cafeBoards.forEach((item) => {
        if (item.address.includes(keyword)) {
          this.geocoder.addressSearch(item.address, function (result, status) {
            if (status === kakao.maps.services.Status.OK) {
              var coords = new kakao.maps.LatLng(result[0].y, result[0].x)

              trans.push(coords)

              var marker = new kakao.maps.Marker({
                map: mapTmp,
                position: coords,
                image: markerImage,
                clickable: true,
              })

              var bounds = new kakao.maps.LatLngBounds()
              for (var i = 0; i < trans.length; i++) {
                new kakao.maps.Marker(trans[i])
                bounds.extend(new kakao.maps.LatLng(trans[i].Ma, trans[i].La))
              }
              mapTmp.setBounds(bounds)

              marker.setMap(mapTmp)
            }
          })
          this.results.push(item)
        } else {
          return false
        }
      })
    },
    showInfo(rs) {
      this.marker = null
      this.map = null

      const container = document.getElementById("map")
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 2,
      }

      const map = new kakao.maps.Map(container, options)

      this.geocoder = new kakao.maps.services.Geocoder()

      var imageSrc = require(`@/assets/images/Logo.png`),
        imageOption = { offset: new kakao.maps.Point(29, 29) },
        imageSize = new kakao.maps.Size(70, 70)

      var markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption
      )

      this.geocoder.addressSearch(rs.address, function (result, status) {
        console.log(kakao.maps.services.Status.OK)
        if (status === kakao.maps.services.Status.OK) {
          const coords = new kakao.maps.LatLng(result[0].y, result[0].x)
          console.log(coords)

          const marker = new kakao.maps.Marker({
            map,
            position: coords,
            image: markerImage,
          })

          marker.setMap(map)

          const iwContent =
            '<div class="map_customoverlay">' +
            `<a class="map_gogo" ` +
            `href="http://localhost:8080/detail/${rs.cafeNo}"` +
            `target="_self">` +
            // '<div class="map_info_close" onclick="closeOverlay()" title="닫기">' +
            // "</div>" +
            `<span class="map_title"> ${rs.cafe_name} </span>` +
            "  </a>" +
            "</div>"

          var customOverlay = new kakao.maps.CustomOverlay({
            map,
            content: iwContent,
            yAnchor: 0.4,
            xAnchor: 0.47,
            position: coords,
          })

          customOverlay.setMap(map)

          map.setCenter(coords)
        }
      })
    },

    locationLoadSuccess(pos) {
      var currentPos = new kakao.maps.LatLng(
        pos.coords.latitude,
        pos.coords.longitude
      )

      this.mapCon.panTo(currentPos)

      var marker = new kakao.maps.Marker({
        position: currentPos,
      })

      marker.setMap(null)
      marker.setMap(this.mapCon)
    },
    locationLoadError(pos) {
      console.log(pos)
      alert("위치 정보를 가져오는데 실패했습니다.")
    },
    getCurrentBtn() {
      navigator.geolocation.getCurrentPosition(
        this.locationLoadSuccess,
        this.locationLoadError
      )
    },
  },
}
</script>

<style lang="scss">
.input-group {
  margin: 16px 0 !important;
}

#map {
  width: 100%;
  height: 100%;
  position: relative;
  overflow: hidden;
}
.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;

  font-size: 20px;
}
.map_wrap a,
.map_wrap a:hover,
.map_wrap a:active {
  color: rgba(0, 0, 0, 0.973);
  text-decoration: none;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 100%;
}
#map_side {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  width: 20%;
  margin: 5% 0 5% 2%;
  padding: 5px;
  overflow-y: auto;
  background: white;
  z-index: 1;
  font-size: 12px;
  border-radius: 10px;
}
.map_itest {
  width: 2px;
  height: 2px;
}
#side hr {
  display: block;
  height: 1px;
}
.map_text-center {
  padding-top: 20px;

  font-size: 46px;
  color: white;
  text-align: center;
  background: #3f51b5;
}
::v-deep .v-dialog {
  overflow: hidden !important;
  width: 500px !important;
}
.map_searchIn {
  background: #3f51b5;
  padding-top: 20px;
}
.map_searchInPut {
  background: #3f51b5;
}
.v-text-field__details {
  color: #3f51b5 !important;
}
.v-input
  map_searchInPut
  theme--light
  v-text-field
  v-text-field--is-booted
  v-text-field--enclosed
  v-text-field--outlined
  v-text-field--placeholder {
  color: #3f51b5 !important;
}
.map_searchResult {
  background: #e7e7e7;
}
.map_llabel {
  font-size: 20px;
  font-weight: 500;
  float: right;
}
.map_place {
  margin-top: 15px;
  padding-bottom: 10px;
  position: relative;
  border-bottom: 1px solid rgb(221, 220, 220);
  overflow: hidden;
  cursor: pointer;
  min-height: 30px;
}
.map_label {
  margin-left: 8px;
}
.map_cafeInfo {
  font-size: 15px;
  font-weight: 500;
}

.map_cafeInfoImg {
  width: 100px;
  height: 100px;
}
.map_customoverlay {
  position: relative;
  bottom: 85px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
}
.map_customoverlay:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.map_customoverlay a {
  display: block;
  text-decoration: none;
  color: #000;
  text-align: center;
  border-radius: 6px;
  font-size: 14px;
  font-weight: bold;
  overflow: hidden;
  background: #3f51b5;
  background: #3f51b5
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.map_customoverlay .map_title {
  display: block;
  text-align: center;
  background: #fff;
  margin-right: 35px;
  padding: 10px 15px;
  font-size: 14px;
  font-weight: bold;
}
.map_customoverlay:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: -12px;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
</style>

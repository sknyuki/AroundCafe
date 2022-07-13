<template>
  <div class="map_wrap">
    <!-- <MapInit :options="options" ref="map" id="map"/>  -->
    <div id="map"></div>
    <v-container id="map_side">
      <form @submit.prevent="searchPlace()">
        <h1 class="map_text-center">Search</h1>
        <v-row class="map_searchIn">
          <v-col cols="9" class="ml-6">
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
          <v-col cols="2">
            <div class="input-group">
              <v-btn @click="getCurrentBtn()" text
                ><v-icon color="yellow">mdi-crosshairs-gps</v-icon></v-btn
              >
            </div>
          </v-col>
        </v-row>
        <v-row class="map_searchResult">
          <v-col cols="11">
            <div class="radio">
              <div>최대 검색 거리 선택</div>
              <label>
                <input type="radio" name="distance" value="1000" checked />1000M
              </label>
            </div>
          </v-col>
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
    keyword: null,
    distance: null,
    results: [],
    marker: [],
    map: null,
    geocoder: null,
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

      let keyword = document.getElementById("keyword").value

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
      let currentPos = new kakao.maps.LatLng(
        pos.coords.latitude,
        pos.coords.longitude
      )

      this.mapCon.panTo(currentPos)

      let marker = new kakao.maps.Marker({
        position: currentPos,
      })
      marker.setMap(null)
      marker.setMap(this.mapCon)

      // let distance = document.getElementById("distance").value

      // const container = document.getElementById("map")
      // const options = {
      //   center: new kakao.maps.LatLng(33.450701, 126.570667),
      //   level: 2,
      // }

      // const map = new kakao.maps.Map(container, options)

      // this.geocoder = new kakao.maps.services.Geocoder()

      // var imageSrc = require(`@/assets/images/Logo.png`),
      //   imageOption = { offset: new kakao.maps.Point(29, 29) },
      //   imageSize = new kakao.maps.Size(70, 70)

      // var markerImage = new kakao.maps.MarkerImage(
      //   imageSrc,
      //   imageSize,
      //   imageOption
      // )

      // this.geocoder = new kakao.maps.services.Geocoder()
      // this.cafeBoards.forEach((item) => {
      //   if (item.address.includes(distance)) {
      //     this.geocoder.addressSearch(item.address, function (result, status) {
      //       if (status === kakao.maps.services.Status.OK) {
      //         var coords = new kakao.maps.LatLng(result[0].y, result[0].x)

      //         this.trans.push(coords)

      //         var marker = new kakao.maps.Marker({
      //           map: map,
      //           position: coords,
      //           image: markerImage,
      //           clickable: true,
      //           radius: distance,
      //         })

      //         var bounds = new kakao.maps.LatLngBounds()
      //         for (var i = 0; i < this.trans.length; i++) {
      //           new kakao.maps.Marker(this.trans[i])
      //           bounds.extend(
      //             new kakao.maps.LatLng(this.trans[i].Ma, this.trans[i].La)
      //           )
      //         }
      //         map.setBounds(bounds)

      //         marker.setMap(map)
      //       }
      //     })
      //     this.results.push(item)
      //   } else {
      //     return false
      //   }
      // })

      var drawingFlag = false // 원이 그려지고 있는 상태를 가지고 있을 변수입니다
      var centerPosition // 원의 중심좌표 입니다
      var drawingCircle // 그려지고 있는 원을 표시할 원 객체입니다
      var drawingLine // 그려지고 있는 원의 반지름을 표시할 선 객체입니다
      var drawingOverlay // 그려지고 있는 원의 반경을 표시할 커스텀오버레이 입니다

      var circles = [] // 클릭으로 그려진 원과 반경 정보를 표시하는 선과 커스텀오버레이를 가지고 있을 배열입니다

      // 지도에 클릭 이벤트를 등록합니다
      kakao.maps.event.addListener(this.mapCon, "click", function (mouseEvent) {
        // 클릭 이벤트가 발생했을 때 원을 그리고 있는 상태가 아니면 중심좌표를 클릭한 지점으로 설정합니다
        if (!drawingFlag) {
          // 상태를 그리고있는 상태로 변경합니다
          drawingFlag = true

          // 원이 그려질 중심좌표를 클릭한 위치로 설정합니다
          centerPosition = mouseEvent.latLng

          // 그려지고 있는 원의 반경을 표시할 선 객체를 생성합니다
          if (!drawingLine) {
            drawingLine = new kakao.maps.Polyline({
              strokeWeight: 3, // 선의 두께입니다
              strokeColor: "#00a0e9", // 선의 색깔입니다
              strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
              strokeStyle: "solid", // 선의 스타일입니다
            })
          }

          // 그려지고 있는 원을 표시할 원 객체를 생성합니다
          if (!drawingCircle) {
            drawingCircle = new kakao.maps.Circle({
              strokeWeight: 1, // 선의 두께입니다
              strokeColor: "#00a0e9", // 선의 색깔입니다
              strokeOpacity: 0.1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
              strokeStyle: "solid", // 선의 스타일입니다
              fillColor: "#00a0e9", // 채우기 색깔입니다
              fillOpacity: 0.2, // 채우기 불투명도입니다
            })
          }

          // 그려지고 있는 원의 반경 정보를 표시할 커스텀오버레이를 생성합니다
          if (!drawingOverlay) {
            drawingOverlay = new kakao.maps.CustomOverlay({
              xAnchor: 0,
              yAnchor: 0,
              zIndex: 1,
            })
          }
        }
      })

      // 지도에 마우스무브 이벤트를 등록합니다
      // 원을 그리고있는 상태에서 마우스무브 이벤트가 발생하면 그려질 원의 위치와 반경정보를 동적으로 보여주도록 합니다
      kakao.maps.event.addListener(
        this.mapCon,
        "mousemove",
        function (mouseEvent) {
          // 마우스무브 이벤트가 발생했을 때 원을 그리고있는 상태이면
          if (drawingFlag) {
            // 마우스 커서의 현재 위치를 얻어옵니다
            var mousePosition = mouseEvent.latLng

            // 그려지고 있는 선을 표시할 좌표 배열입니다. 클릭한 중심좌표와 마우스커서의 위치로 설정합니다
            var linePath = [centerPosition, mousePosition]

            // 그려지고 있는 선을 표시할 선 객체에 좌표 배열을 설정합니다
            drawingLine.setPath(linePath)

            // 원의 반지름을 선 객체를 이용해서 얻어옵니다
            var length = drawingLine.getLength()

            if (length > 0) {
              // 그려지고 있는 원의 중심좌표와 반지름입니다
              var circleOptions = {
                center: centerPosition,
                radius: length,
              }

              // 그려지고 있는 원의 옵션을 설정합니다
              drawingCircle.setOptions(circleOptions)

              // 반경 정보를 표시할 커스텀오버레이의 내용입니다
              var radius = Math.round(drawingCircle.getRadius()),
                content =
                  '<div class="info">반경 <span class="number">' +
                  radius +
                  "</span>m</div>"

              // 반경 정보를 표시할 커스텀 오버레이의 좌표를 마우스커서 위치로 설정합니다
              drawingOverlay.setPosition(mousePosition)

              // 반경 정보를 표시할 커스텀 오버레이의 표시할 내용을 설정합니다
              drawingOverlay.setContent(content)

              // 그려지고 있는 원을 지도에 표시합니다
              drawingCircle.setMap(this.mapCon)

              // 그려지고 있는 선을 지도에 표시합니다
              drawingLine.setMap(this.mapCon)

              // 그려지고 있는 원의 반경정보 커스텀 오버레이를 지도에 표시합니다
              drawingOverlay.setMap(this.mapCon)
            } else {
              drawingCircle.setMap(null)
              drawingLine.setMap(null)
              drawingOverlay.setMap(null)
            }
          }
        }
      )

      // 지도에 마우스 오른쪽 클릭이벤트를 등록합니다
      // 원을 그리고있는 상태에서 마우스 오른쪽 클릭 이벤트가 발생하면
      // 마우스 오른쪽 클릭한 위치를 기준으로 원과 원의 반경정보를 표시하는 선과 커스텀 오버레이를 표시하고 그리기를 종료합니다
      kakao.maps.event.addListener(
        this.mapCon,
        "rightclick",
        function (mouseEvent) {
          if (drawingFlag) {
            // 마우스로 오른쪽 클릭한 위치입니다
            var rClickPosition = mouseEvent.latLng

            // 원의 반경을 표시할 선 객체를 생성합니다
            var polyline = new kakao.maps.Polyline({
              path: [centerPosition, rClickPosition], // 선을 구성하는 좌표 배열입니다. 원의 중심좌표와 클릭한 위치로 설정합니다
              strokeWeight: 3, // 선의 두께 입니다
              strokeColor: "#00a0e9", // 선의 색깔입니다
              strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
              strokeStyle: "solid", // 선의 스타일입니다
            })

            // 원 객체를 생성합니다
            var circle = new kakao.maps.Circle({
              center: centerPosition, // 원의 중심좌표입니다
              radius: polyline.getLength(), // 원의 반지름입니다 m 단위 이며 선 객체를 이용해서 얻어옵니다
              strokeWeight: 1, // 선의 두께입니다
              strokeColor: "#00a0e9", // 선의 색깔입니다
              strokeOpacity: 0.1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
              strokeStyle: "solid", // 선의 스타일입니다
              fillColor: "#00a0e9", // 채우기 색깔입니다
              fillOpacity: 0.2, // 채우기 불투명도입니다
            })

            // 원을 지도에 표시합니다
            circle.setMap(this.mapCon)

            // 선을 지도에 표시합니다
            polyline.setMap(this.mapCon)

            // 반경 정보 커스텀 오버레이를 지도에 표시합니다

            // 배열에 담을 객체입니다. 원, 선, 커스텀오버레이 객체를 가지고 있습니다
            var radiusObj = {
              polyline: polyline,
              circle: circle,
            }

            // 배열에 추가합니다
            // 이 배열을 이용해서 "모두 지우기" 버튼을 클릭했을 때 지도에 그려진 원, 선, 커스텀오버레이들을 지웁니다
            circles.push(radiusObj)

            // 그리기 상태를 그리고 있지 않는 상태로 바꿉니다
            drawingFlag = false

            // 중심 좌표를 초기화 합니다
            centerPosition = null

            // 그려지고 있는 원, 선, 커스텀오버레이를 지도에서 제거합니다
            drawingCircle.setMap(null)
            drawingLine.setMap(null)
            drawingOverlay.setMap(null)
          }
        }
      )

      // 지도에 표시되어 있는 모든 원과 반경정보를 표시하는 선, 커스텀 오버레이를 지도에서 제거합니다
      //   function removeCircles() {
      //     for (var i = 0; i < circles.length; i++) {
      //       circles[i].circle.setMap(null)
      //       circles[i].polyline.setMap(null)
      //       circles[i].overlay.setMap(null)
      //     }
      //     circles = []
      //   }
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

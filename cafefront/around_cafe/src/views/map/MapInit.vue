<template>
  <div class="map_wrap">
    <div id="map" style="position:relative; overflow:hidden;"></div> 
    <div id="menu_wrap" class="bg_white">
      <div class="option">
        <form @submit.prevent="searchPlace()">
          키워드 : <input type="text" value="강남구" id="keyword" size="15"> 
          <button type="submit" > 검색하기 </button>           
        </form>          
      </div>
      <div id="pagination"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: "MapInit",
  
  data: () => ({
    cafeLoca: cafeLoca,
     keyword: null,
     results: null,
    
  }),

  mounted() {

    if (window.kakao && window.kakao.maps) {
      this.initMap()
    } else {
      const script = document.createElement("script")
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap)
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey="
      document.head.appendChild(script)
    }
  },
  methods: {
    initMap () {

      var container = document.getElementById('map') 
      var options = {
        center: new kakao.maps.LatLng(37.56832, 126.97976), 
        level: 8,
        mapTypeId : kakao.maps.MapTypeId.ROADMAP
      }

      var map = new kakao.maps.Map(container, options) 

      this.cafeLoca.forEach((item) => {
        
        //마커 커스텀(주소,이미지, 일치시킬 이미지 안의 좌표 설정)
        var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png', 
            imageSize = new kakao.maps.Size(27, 34), 
            imageOption = { offset: new kakao.maps.Point(29, 29) } 

        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption)
      
        var position = new kakao.maps.LatLng(item.cafe_lon, item.cafe_lat)

        
        //커스텀 오버레이 생성
        var cafeOverlay = new kakao.maps.CustomOverlay({
          map: map,
          position: position,
          //content: content, 
          yAnchor: 1 
        })
      
        var marker = new kakao.maps.Marker({
          map: map,
          position: position,
          image: markerImage, 
          clickable: true, 
        })

        marker.setMap(map)
        cafeOverlay.setMap(map)

        var iwContent = 
        `<div class="overlaybox">`+
        `<div style="padding:5px;">${item.name}</div>`+   
        `<div class="boxtitle">hello</div>` +`</div>`,

        iwRemoveable = true

        // 인포윈도우를 생성
        var infowindow = new kakao.maps.InfoWindow({
            content : iwContent,
            removable : iwRemoveable
        });

        // 마커에 마우스오버 이벤트를 등록https://apis.map.kakao.com/web/sample/addMarkerMouseEvent/
        kakao.maps.event.addListener(marker, 'click', function() {
          
            infowindow.open(map, marker);
        })
        /*
        // 마커에 마우스아웃 이벤트를 등록
        kakao.maps.event.addListener(marker, 'click', function() {
          
            infowindow.close();
        })*/ 
      })
    },
    searchPlace(){

      this.results=[]

      var container = document.getElementById('map')
      //const bounds = new kakao.maps.LatLngBounds(this.results.cafe_lon, this.results.cafe_lat)
      var options = {
        center: new kakao.maps.LatLng(37.56832, 126.97976),
        level: 8,
        mapTypeId : kakao.maps.MapTypeId.ROADMAP
      }

      var map = new kakao.maps.Map(container, options)

      this.cafeLoca.forEach((item) => {

        var keyword = document.getElementById('keyword').value;

        if (!keyword.replace(/^\s+|\s+$/g, '')) {
          alert('키워드를 입력해주세요!');
        return false;
        } 
        //장소 검색 (이건 전체 지도의 검색)
        //var ps = new kakao.maps.services.Places(item);

        if(keyword === item.cafe_location){

        var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png', 
            imageSize = new kakao.maps.Size(35, 50), 
            imageOption = { offset: new kakao.maps.Point(29, 29) } 

        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption)
      
        var position = new kakao.maps.LatLng(item.cafe_lon, item.cafe_lat)

        var marker = new kakao.maps.Marker({
          map: map,
          position: position,
          image: markerImage, 
          clickable: true, 
        })
  
        this.results.push(item)

        //bounds.extend(new kakao.maps.LatLng(item.cafe_lon, item.cafe_lat))
        marker.setMap(map)
        this.results.push(marker)
        //cafeOverlay.setMap(map)
        }
      })   
    },
  }
}

</script>
<style scoped>

@import url("https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Poiret+One&family=Sunflower:wght@300&family=Ubuntu:wght@300&display=swap");
#map {
  width: 100%;
  height: 100%;
  position:relative;
  overflow:hidden;
}

.overlaybox { /* 지금 이거 적용안되는 듯*/
  position:relative;
  width:100px;
  height:100px;
  padding:15px 10px;
}
.map_wrap, .map_wrap * {
  margin:0;
  padding:0;
  font-family:'CookieRun-Regular',cursive;
  font-size:20px;
}
.map_wrap a, .map_wrap a:hover, .map_wrap a:active{
  color:#000;
  text-decoration: none;
}
.map_wrap {
  position:relative;
  width:100%;
  height:100%;
}
#side {
  position:absolute;
  top:0;
  left:0;
  bottom:0;
  width:20%;
  margin: 5% 0 5% 2%;
  padding:5px;
  overflow-y:auto;
  background:white;
  z-index: 1;
  font-size:12px;
  border-radius: 10px;
  
}

#side hr {
  display: block;
  height: 1px;
}
.text-center{
  padding-top:20px;
    font-family: 'Poiret One', cursive;
    font-size: 46px;
    color: white;
    text-align: center;
    background: #3f51b5;
    
}
.searchIn{
  background: #3f51b5;
  padding-top:20px;
}
.searchInPut{
  background:#3f51b5 ;

}
.v-text-field__details{
  color: #3f51b5 !important;

}

.v-input searchInPut theme--light v-text-field v-text-field--is-booted v-text-field--enclosed v-text-field--outlined v-text-field--placeholder {
    color: #3f51b5 !important;
}
.searchResult{
  background: #e7e7e7;
  
}
.llabel{
   font-family: 'Poiret One', cursive;
  font-size: 20px;
  font-weight:500;
  float:right;
}
.place{
  margin-top:15px;
  padding-bottom:10px;
  position:relative;
  border-bottom:1px solid rgb(221, 220, 220);
  overflow: hidden;
  cursor: pointer;
  min-height: 30px;
}
.label{
  margin-left: 8px;
  
}
.cafeInfo{
  font-size: 15px;
  font-weight:500;
  font-family: 'IM_Hyemin-Bold',cursive;
}
@font-face {
    font-family: 'IM_Hyemin-Bold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2106@1.1/IM_Hyemin-Bold.woff2') format('woff');
    font-weight: normal;
    font-style: normal;
}
@font-face {
    font-family: 'CookieRun-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/CookieRun-Regular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}



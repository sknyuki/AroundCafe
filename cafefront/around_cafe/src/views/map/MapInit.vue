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



<template>
  <div class="map_wrap">

    <MapRead :options="options" :cafeBoards="cafeBoards" ref="map" id="map"/>

      <v-container id="side">       
        <form @submit.prevent="searchPlace()">
          <h1 class="text-center"> Search </h1>
          <v-row class="searchIn"> 
            <v-col cols="9" class="ml-4" >
              <v-text-field class="searchInPut" type="text" outlined color="#e3c832" placeholder="area" id="keyword"/>   
            </v-col>
            <v-col cols="1">  
              <button type="submit" class="ml-2">
                <v-icon color="#c9c8c5" class="mt-5 ml-3">mdi-magnify</v-icon>
              </button>  
            </v-col>
        </v-row>  

        <v-row class="searchResult"> 
          <v-col cols="11">
            <div class="llabel"> Total &nbsp;{{cafeBoards.length}} </div>    
          </v-col>
        </v-row>  

        <v-row>
          <v-col cols="11" class="label"> 
            <div class="place" v-for="rs in cafeBoards" :key="rs.cafe_no">
              <div display: flex justify-content: space-between>
                <span>

                  <div @click="showInfo(rs)">{{rs.cafe_name}}</div>
            
                  <div class="cafeInfo">{{rs.address}}</div>

                </span>

                <v-icon color="#c9c8c5">mdi-magnify</v-icon>
      

              </div>
            </div>
         
           
          </v-col>
        </v-row>
         
        
              
        </form>          

      <div id="pagination"></div>

    </v-container>

  </div>
</template>

<script>



//import cafeLoca from '@/assets/data/csvjson.json' 
import MapRead from "@/components/Map/MapRead.vue"
import { mapState, mapActions } from "vuex"

var kakao = window.kakao 

export default {

  components: { 
    MapRead 
    },
 
  name: "MapMain",
  
  data: () => ({
  
    //cafeLoca: cafeLoca,
    keyword: null,
    results: [],
    marker:[],  
    map:null,
    geocoder: null,
    options: {
        center: {
          lat: 37.56832,
          lng: 126.97976,
        },
        level: 6
      },     
  }),
   computed: {
    ...mapState(["cafeBoards"]),
  },
   mounted() {
    this.fetchcafeBoardList()
  },

 
  methods: {
    ...mapActions(["fetchcafeBoardList"]),
    
    searchPlace(){

      this.cafeBoards=[]
     
      var keyword = document.getElementById('keyword').value;

        if (!keyword.replace(/^\s+|\s+$/g, '')) {
          alert('지역을 입력해주세요!');
        return false;
        } 

       var imageSrc = require(`@/assets/images/Logo.png`),
        imageOption = { offset: new kakao.maps.Point(29, 29) },
        imageSize = new kakao.maps.Size(70, 70)
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption)

      var container = document.getElementById('map')

      if(keyword === "강남구"){
        var options = {
          center: new kakao.maps.LatLng(37.5008, 127.022),
          level: 5,
        } 
      }
      else if(keyword === "광진구"){
        options = {
        center: new kakao.maps.LatLng(37.5522944, 127.091274),
        level: 5,
        } 
      }
      else if(keyword === "중구"){
        options = {
        center: new kakao.maps.LatLng(37.5739, 127.002),
        level: 5,
        } 
      }
      else if(keyword === "관악구"){
        options = {
        center: new kakao.maps.LatLng(37.4781098, 126.9514931),
        level: 5,
        } 
      }

      var map = new kakao.maps.Map(container, options)
    
      this.cafeBoards.forEach((item) => {

        if( item.cafe_address.includes(keyword)){

        this.geocoder.addressSearch(item.cafe_address, function (result, status) {
        console.log(kakao.maps.services.Status.OK)
        if (status === kakao.maps.services.Status.OK) {
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x)
          console.log(coords)
          //map.setCenter(coords)
        var marker = new kakao.maps.Marker({
          map: this.map,
          position: coords,
          image: markerImage, 
          clickable: true, 
          

        })

        
        marker.setMap(map)
        this.cafeBoards.push(item)

        }
      })

     
   
        // marker.setMap(map)
        // this.results.push(item)

        }
        else {
          return false
        }

        
      })   
       
    },
    showInfo(rs){

      var content = 
        `<div class="overlaybox">`+
        `<div style="padding:5px;">${rs.cafe_name}</div>`+   
        `<div class="boxtitle">hello</div>` +
        ' <img src="' +
            require(`@/assets/cafe/cafeMypage/${rs.cafeImgs[0].cafe_img}`) +
            '" width="270" height="160">' +
            `</div>` +
        +`</div>`
       
      

      var container = document.getElementById('map')
      var options = {
      center: new kakao.maps.LatLng(`${rs.cafe_lon}`, `${rs.cafe_lat}`), // 지도의 중심좌표
      level: 5,
     
      }
      var map = new kakao.maps.Map(container, options)
      var position = new kakao.maps.LatLng(`${rs.cafe_lon}`, `${rs.cafe_lat}`)

      var infowindow = new kakao.maps.InfoWindow({
          content : content,
          position:position,
          yAnchor: 1 ,
          //removable : iwRemoveable
        })
         var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png', 
            imageSize = new kakao.maps.Size(27, 34), 
            imageOption = { offset: new kakao.maps.Point(29, 29) } 

        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption)

        var marker = new kakao.maps.Marker({
          map: map,
          position: position,
          image: markerImage, 
          clickable: true, 
        })
      marker.setMap(map)
      infowindow.setContent(content);
      infowindow.open( map,marker);

    }  
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
.itest{
  width:2px;
  height:2px;
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
::v-deep .v-dialog {
  overflow: hidden !important;
  width: 500px !important;
}

.searchIn{
  background: #3f51b5;
  padding-top:20px;
}
.searchInPut{
  background: white ;

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

</style>
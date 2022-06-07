<template>
   <div>
    <br>            
          <v-card class="pa-3" flat>
            <v-row>
              <input type="text" :value="cafeBoard.cafe_name">

              <v-col>
               <v-img position="relative" class="ml-10" :src="showFile()" width="100%"/>
                    
              </v-col>
            </v-row>
          </v-card>
                  <v-card align="center" class="mt-5 pa-2">
                    
           <table class="table">
             <tr>
                <td>
                     <input type="text" :value="cafeBoard.cafe_call" readonly/>
                </td>
            </tr>
            <tr>
                <td>
                     <input type="text" :value="cafeBoard.cafe_adr1" readonly/>
                </td>
                <td>
                     <input type="text" :value="cafeBoard.cafe_adr2" readonly/>
                </td>
                <td>
                     <input type="text" :value="cafeBoard.cafe_adr3" readonly/>
                </td>
            </tr>
             <tr>
                <td>
                     <input type="text" :value="cafeBoard.cafe_time" readonly/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" :value="cafeBoard.cafe_content" readonly/>
                </td>
            </tr>
            <tr>
                <td>
                     <input type="text" :value="ProductBoardS.size" readonly/>
                </td>
            </tr>

        </table>  
        </v-card>
               
</div>
        
</template>
<script>
export default {
    name:'CafeSiteComponent',
    props:{
        cafeBoard:{
          type:Object,
        },
        cafeNo:String
    },
 methods:{
        showFile () {
      try {
          return require(`@/assets/cafe/cafeMenu/${cafeBoard.menu_img}`)
     } catch (e) {
          console.log(e)
          console.log(this.boardNo)
    }  
}
 }
}
</script>

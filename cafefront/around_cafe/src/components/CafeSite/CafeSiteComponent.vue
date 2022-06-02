<template>
   <div>
    <br>            
          <v-card class="pa-3" flat>
            <v-row>
              <v-col v-for="productBoard in ProductBoardSs" :key="productBoard.boardNo"
                cols="12" sm="6" md="4" lg="3">
                <div v-if="productBoard.soldCheck">
                <v-hover v-slot="{hover}" close-delay="50">
                  <v-card :elevation="hover ? 16:2" class="{ 'on-hover': hover }"
                   @click="handleClick(productBoard)">
                    <v-list-item>
                      <v-list-item-title>
                        {{ productBoard.name }}
                      </v-list-item-title>
                    </v-list-item>
                    
                    <v-divider></v-divider>
                    <img :src="showFile(productBoard.boardNo)"  width="100%"/>


                    <v-card-text class="caption">
                      Rank: {{productBoard.brandrank}}
                      <br>
                      Price: {{productBoard.price}}
                     
                    </v-card-text>
                  
                  </v-card>
                </v-hover>
                </div>
              </v-col>
            </v-row>
          </v-card>
 
             
</div>
        
</template>
<script>
export default {
    name:'CafeSiteComponent',

}
</script>


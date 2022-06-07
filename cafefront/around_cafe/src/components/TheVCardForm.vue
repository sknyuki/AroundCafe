<template>
        <div>
            <h4>메뉴 일람</h4>
            <v-card class="pa-3" flat>
            <v-row>
              <v-col v-for="menuLists in menuLists" :key="menuLists.menu_no"
                cols="12" sm="6" md="4" lg="3">
                <div v-if="menuLists.sold_out">
                 <v-hover v-slot="{hover}" close-delay="50">
                  <v-card :elevation="hover ? 16:2" class="{ 'on-hover': hover }"
                   @click="handleClick(menuLists)">
                    <v-list-item>
                      <v-list-item-title>
                        {{menuLists.menu_name}}
                      </v-list-item-title>
                    </v-list-item>
                    
                    <v-divider></v-divider>
                    <img :src="showFile()"  width="100%"/>

                    <v-row>
                    <v-card-text class="caption">
                      가격: {{menuLists.menu_price}}
                      <br>
                      설명: {{menuLists.menu_content}}
                    </v-card-text>
                    </v-row>
                  
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
    name:'TheVcardForm',
    data () {
            return {
            }
    },
    props:{
        menuLists:{
            type: Array,
            required: true
        },
        cafeNo: {
            type: String,
         },

    },
        methods: {
        
         showFile () {
      try {
        return require(`@/assets/cafe/cafeMenu/${menuLists.menu_img}`) 
    } catch (e) {
                return require(`@/assets/logo.png`)
                }
        },
         handleClick(menuLists){
       this.$router.push({
                            name: 'CafeSiteMenuSelectPage',
                            params: { menuNo: menuLists.menuNo.toString() } },
    
            )
}
        }
}
</script>
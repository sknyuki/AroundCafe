<template>

<div class="sidebar" style="border: 1px solid rgb(221, 221, 221); border-radius: 12px; padding: 24px; box-shadow: rgba(0, 0, 0, 0.12) 0px 6px 16px;" >
<aside >
			<h2 class="sidebar-header">
			Selected menu
			</h2>
			<ul class="list-flex">
			<li v-for="(basket, index) in baskets" :key="index">
				<span>
							menu:{{ basket.menu }}
				</span>
            <br>
				<span>
							price:{{ basket.Price }} <br>
              수량 :{{ basket.number}}
              
              <v-btn class="show-btns-up" icon
                 @click="UP(basket)">
              <v-icon>
              mdi-arrow-up
              </v-icon>
              </v-btn>
             <v-btn class="show-btns-down" icon
                 @click="DOWN(basket)"> 
              <v-icon>
               mdi-arrow-down
              </v-icon>             
             </v-btn>
				</span>
            <br>
            PerMenuPrices:{{basket.PerMenuPrices}}
             <br>
            <br>                    
			</li>
          <br>
            <span  class="total-price">
				total price: {{ totalPrice }}
			</span>  
			</ul>
		<div class="버튼">
           <v-row justify="center">
             <v-col cols="3">
                <v-btn text color="black" rounded x-large
                    style="padding: 10px; width: 30px;" @click="SelectdeMenuSubmit()" >메뉴 확정하기
                </v-btn>
             </v-col>    
             </v-row>
        </div>
</aside>
</div>


        
    

</template>

<script>
export default {
	name:'CafeStickySidebar',
   components: {
  
  },
    data(){
        return{

          totalPrice:0,
		//basket반영시 변경
        baskets:[
            {menu:"아메리카노",Price:4900,PerMenuPrices:0,number:0},
            {menu:"카페모카",Price:6700,PerMenuPrices:0,number:0}
            
			],
        }
    },
    methods:{
     
      UP(basket){
        // console.log("number의 값")
        // console.log(basket.number)
        // console.log(basket.PerMenuPrice)
        // console.log(" 길이")
        // console.log(this.baskets.length)
        

        basket.number+=1;

        basket.PerMenuPrices=basket.Price*basket.number;
        //console.log(this.totalPrice)


        for(let i=0; i< this.baskets.length; i++){
          // console.log("개당가격")
          // console.log(this.baskets[i].PerMenuPrice)
          this.totalPrice+=this.baskets[i].PerMenuPrices
          // console.log(this.totalPrice)
        }

      },
      DOWN(basket){
        // console.log("number의 값")
        // console.log(basket.number)
        basket.number-=1;

        basket.PerMenuPrices=basket.Price*basket.number;

        for(let i=0; i<this.baskets.length; i++){   
          this.totalPrice-=this.baskets[i].PerMenuPrices 
        }        
      },
	SelectdeMenuSubmit(){
		const { totalPrice, baskets} = this
            this.$emit('selectedMenu',{totalPrice, baskets})  
            console.log("넘어가는 파일:")
            console.log(totalPrice, baskets)
			}
}
}

</script>
<style scoped>

.sidebar{
  position: sticky;
  top:500px;
}
.total-price {
		color: primary-color;
		font-weight: 700;
		text-transform: uppercase;
	}

</style>
<template >
  <section>
    <v-container>
      <v-row>
        <v-col>
          <gallery-card></gallery-card>
        </v-col>
      </v-row>
    </v-container>
  </section>
</template>
<script>
import GalleryCard from '@/components/gallery/GalleryCard.vue'
export default {
  components: { GalleryCard },
  
}
</script>

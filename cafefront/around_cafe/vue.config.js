module.exports = {
  transpileDependencies: ["vuetify"],
  css: {
    loaderOptions: {
      scss: {
        additionalData: `
        @import "@/assets/scss/abstracts.scss";
    `,
      },
    },
  },
}

const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies:true,
  lintOnSave:false
})
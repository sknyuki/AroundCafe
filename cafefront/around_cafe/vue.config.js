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

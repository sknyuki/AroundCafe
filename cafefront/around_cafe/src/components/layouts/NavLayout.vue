<template>
  <nav class="lnb">
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <h1 class="visually-hidden">서브 메뉴리스트</h1>
          <ul class="lnb-list">
            <li class="lnb-item is-active"><a href="">카페</a></li>
            <li class="lnb-item"><a href="">커피</a></li>
            <li class="lnb-item"><a href="">베스트</a></li>
            <li class="lnb-item"><a href="">뉴스</a></li>
            <li class="lnb-item"><a href="">고객센터</a></li>
          </ul>
        </div>
      </div>
    </div>
  </nav>
</template>
<script>
export default {
  name: "NavLayout",
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/layouts/NavLayout";

</style>

<template>
  <div class="mysidebar col-sm-4 col-md-5 col-lg-3">
    <div class="mysidebar-content">
      <header>
        <h1>{{ infoOne }}</h1>
      </header>
      <ul>
        <li class="mysidebar-list" v-for="item in sidebarOne" :key="item.id">
          <router-link class="mysidebar-link" :to="item.link">
            <div class="mysidebar-test">
              <v-icon>{{ item.icon }}</v-icon>
              <p>{{ item.title }}</p>
            </div>
          </router-link>
        </li>
      </ul>
    </div>
    <div class="mysidebar-content">
      <header>
        <h1>{{ infoTwo }}</h1>
      </header>
      <ul>
        <li class="mysidebar-list" v-for="item in sidebarTwo" :key="item.id">
          <router-link class="mysidebar-link" :to="item.link">
            <div class="mysidebar-test">
              <v-icon>{{ item.icon }}</v-icon>
              <p>{{ item.title }}</p>
            </div>
          </router-link>
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
export default {
  name: "DashboardSidebar",
  props: {
    sidebarOne: {
      type: Array,
    },
    sidebarTwo: {
      type: Array,
    },
    infoOne: {
      type: String,
    },
    infoTwo: {
      type: String,
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/my-sidebar";
</style>

<template>
  <div>
    <div class="justify-space-between align-center ps-4 pe-3 mt-3">
      <header>
        <h1 class="logo">
          <a href="/"
            >로고
            <img src="/" alt="" />
          </a>
        </h1>

        <div class="sidebar-user">
          <a href="/">
            <div class="avatar-24">
              <img src="@/assets/images/img-user-default.png" alt="" />
            </div>
            <strong class="username">이름</strong>
          </a>
        </div>
      </header>
    </div>
    <div class="navbar-scroll">
      <v-list dense>
        <v-list-group
          v-for="item in items"
          :key="item.title"
          v-model="item.active"
          :prepend-icon="item.action"
          no-action
        >
          <template v-slot:activator>
            <v-list-item-content>
              <v-list-item-title v-text="item.title"></v-list-item-title>
            </v-list-item-content>
          </template>

          <v-list-item
            v-for="child in item.items"
            :key="child.title"
            :to="child.link"
          >
            <v-list-item-content>
              <v-list-item-title v-text="child.title"></v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list-group>
      </v-list>
    </div>
  </div>
</template>

<script>
export default {
  name: "TheNavbarList",

  data() {
    return {
      drawer: false,
      group: null,
      items: [
        {
          action: "mdi-home-outline",
          active: false,
          title: "카페",
          items: [
            {
              title: "Review",
              link: "/dashboard/Review",
            },
            {
              title: "Photos",
              link: "/dashboard/Photos",
            },
            {
              title: "Order List",
              link: "/dashboard/OrderList",
            },
            {
              title: "Order History",
              link: "/dashboard/OrderHistory",
            },
            {
              title: "Followers",
              link: "/dashboard/Followers",
            },
            {
              title: "Bookmarks",
              link: "/dashboard/Bookmarks",
            },
          ],
        },
        {
          action: "mdi-coffee-outline",
          active: false,
          title: "커피",

          items: [
            {
              title: "Food Menu",
              link: "/resturant/FoodMenu",
            },
            {
              title: "Resturant Two Column",
              link: "/resturant/ResturantTwoColumn",
            },
            {
              title: "Search Resturant",
              link: "/resturant/SearchResturant",
            },
          ],
        },
      ],
    }
  },
}
</script>

<style lang="scss" scoped></style>

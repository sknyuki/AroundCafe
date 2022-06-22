//import Vue from 'vue'
//import VueCookies from "vue-cookies";

//Vue.use(VueCookies)
//Vue.$cookies.config("14d");

class UserService {
  setUserInfo(userInfo) {
    const user = {
      nickname: userInfo["nickname"],
      email: userInfo["email"],
      role: userInfo["role"],
    }

    localStorage.setItem("user", JSON.stringify(user))
  }

  getUserInfo() {
    const userinfo = JSON.parse(localStorage.getItem("user"))
    return {
      nickname: userinfo["nickname"],
      email: userinfo["email"],
      role: userinfo["role"],
    }
  }
}

export default new UserService()

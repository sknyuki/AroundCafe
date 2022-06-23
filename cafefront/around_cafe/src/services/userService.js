//import Vue from 'vue'
//import VueCookies from "vue-cookies";

//Vue.use(VueCookies)
//Vue.$cookies.config("14d");

class UserService {
  setUserInfo(userInfo) {
    const user = {
      memNo: userInfo["memNo"],
      nickname: userInfo["nickname"],
      email: userInfo["email"],
      role: userInfo["role"],
    }

    localStorage.setItem("user", JSON.stringify(user))
  }

  getUserInfo() {
    const userInfo = JSON.parse(localStorage.getItem("user"))
    return {
      memNo: userInfo["memNo"],
      nickname: userInfo["nickname"],
      email: userInfo["email"],
      role: userInfo["role"],
    }
  }

  deleteUserInfo() {
    localStorage.removeItem("user")
  }
}

export default new UserService()

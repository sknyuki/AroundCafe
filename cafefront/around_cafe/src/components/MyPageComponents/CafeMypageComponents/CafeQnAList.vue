<template>
  <div>
    <div v-if="!qnaLists || (Array.isArray(qnaLists) && qnaLists.length === 0)">
      <p>등록된 리스트가 없습니다.</p>
    </div>
    <div v-else v-for="(item, index) in qnaLists" :key="index">
      <a @click="sendQnaNo(item)">
        <v-container style="padding: 3%" class="delect">
          <v-btn class="show-btns" @click="deleteList(item)" x-small fab
            >삭제</v-btn
          >
          <v-row>
            <v-col cols="12" sm="3"> 넘버 : {{ item.qna_no }} </v-col>
          </v-row>
          <v-row style="font-size: 13.8px">
            <v-col cols="12" sm="3"> [{{ item.type }}] </v-col>
          </v-row>
          <v-row style="font-size: 17px">
            <v-col cols="12" sm="1">
              <img
                v-if="item.received_img == null"
                v-bind:src="require(`@/assets/qna/noMemberImg.png`)"
                style="width: 30px"
              />
              <img
                v-else
                v-bind:src="require(`@/assets/qna/${item.received_img}`)"
                style="width: 200px"
              />
            </v-col>
            <v-col cols="12" sm="2">
              {{ item.received_name }}
            </v-col>
          </v-row>
          <v-row v-if="item.writer == item.received_no">
            <v-col cols="12" sm="3">
              <input :value="contentlist1" />
            </v-col>
          </v-row>
          <v-row v-else>
            <v-col cols="12" sm="1">
              <v-icon style="color: red">mdi-twitch</v-icon>
            </v-col>
            <v-col cols="12" sm="1">
              <input :value="contentlist2" />
            </v-col>
          </v-row>
          <v-row style="font-size: 12px">
            <v-col cols="12" sm="3">
              {{ item.regYear }} {{ item.regTime }}
            </v-col>
          </v-row>
        </v-container>
      </a>
      <br />
    </div>

    <div>
      <div v-if="!qnaList || (Array.isArray(qnaList) && qnaList.length === 0)">
        <h4>채팅할 상대를 선택해주세요.</h4>
      </div>
      <div v-else>
        <div v-for="item in qnaList" :key="item.qna_no" class="test">
          <ul v-if="item.writer != membNo">
            <li>
              <img
                v-if="qnaLists[0].received_img == null"
                v-bind:src="require(`@/assets/qna/noMemberImg.png`)"
                style="width: 30px"
              />
              <img
                v-else
                v-bind:src="require(`@/assets/qna/${qnaLists[0].received_img}`)"
                style="width: 200px"
              />
            </li>
            <li class="showBoxOther">
              남 : {{ item.content }} {{ item.regTime }}
            </li>
            <li class="showBoxOther" v-if="item.img != null">
              <img
                v-bind:src="require(`@/assets/qna/${item.img}`)"
                style="width: 200px; display: block"
              />
            </li>
          </ul>
          <br />
          <ul
            v-if="item.writer == membNo"
            @mousedown.right="rightMouse(item)"
            @contextmenu.prevent
          >
            <li class="showBox" v-if="item.content != null">
              {{ item.regTime }} 나 : {{ item.content }} <br />
            </li>
            <li class="showBox" v-if="item.img != null">
              <img
                v-bind:src="require(`@/assets/qna/${item.img}`)"
                style="width: 200px; display: block"
              />
            </li>
            <v-btn
              v-if="item.qna_comment_no == num"
              v-show="testBtn"
              @click="deleteBtn(item)"
              >delete</v-btn
            >
          </ul>
          <br />
        </div>

        <div>
          <textarea
            type="text"
            v-model="chatting"
            style="width: 500px; height: 100px"
          />
          <v-icon @click="handleFileUpload()" id="files1" ref="files1" multiple
            >mdi-panorama-variant
          </v-icon>
          <v-icon @click="sumbitMsg()"> mdi-arrow-up-circle</v-icon>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios"
export default {
  name: "CafeQnAList",
  props: {
    qnaLists: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      search: "",
      headerTitle1: [
        { text: "no", value: "qna_no", width: "20px" },
        { text: "제목", vlaue: "received_no", width: "200px" },
        { text: "date", value: "regDate", width: "100px" },
      ],
      membNo: 1,
      qnaList: [],
      chatting: "",
      checkQnaNo: "",
      hoveredColumn: null,
      clickQnANo: "",
      contentlist1: "업데잇 된 메시지가 없습니다.",
      contentlist2: "새로운 메시지가 있습니다.",
      testBtn: false,
      num: "",
    }
  },
  watch: {
    testBtn() {
      if (this.testBtn) {
        window.addEventListener("click", this.onClick)
      }
    },
  },
  methods: {
    sendQnaNo(item) {
      this.checkQnaNo = item.qna_no
      axios
        .get(`http://localhost:7777/qna/memberRead/${this.checkQnaNo}`)
        .then((res) => {
          console.log(res.data)
          this.qnaList = res.data
        })
        .catch(() => {
          alert("문의사항 등록에 실패하였습니다.")
        })
    },
    sumbitMsg() {
      let formData = new FormData()

      let fileInfo = {
        qnaNo: this.checkQnaNo,
        chatting: this.chatting,
      }

      formData.append(
        "info",
        new Blob([JSON.stringify(fileInfo)], { type: "application/json" })
      )

      let membNo = 1
      axios
        .post(`http://localhost:7777/qnaComment/register/${membNo}`, formData)
        .then(() => {
          axios
            .get(`http://localhost:7777/qna/memberRead/${this.checkQnaNo}`)
            .then((res) => {
              this.qnaList = res.data
              console.log(res.data)
              this.chatting = ""
            })
            .catch(() => {
              alert("멤버 리드 읽기 실패")
            })
        })
        .catch(() => {
          alert("문의사항 등록에 실패하였습니다.")
        })
    },
    handleFileUpload() {
      let qnaNo = this.checkQnaNo
      let vue = this
      let elem = document.createElement("input")
      // 이미지 파일 업로드 / 동시에 여러 파일 업로드
      elem.id = "image"
      elem.type = "file"
      elem.accept = "image/*"
      elem.multiple = true
      // 클릭
      elem.click()
      elem.onchange = function () {
        let formData = new FormData()
        for (let index = 0; index < this.files.length; index++) {
          formData.append("fileList", this.files[index])
        }

        axios
          .post(
            `http://localhost:7777/qnaComment/registerImg/${qnaNo}`,
            formData
          )
          .then((response) => {
            vue.response = response.data
            alert("사진 전송 성공")
            let checkQnaNo = qnaNo
            axios
              .get(`http://localhost:7777/qna/memberRead/${checkQnaNo}`)
              .then((res) => {
                this.qnaList = res.data
                console.log(res.data)
                alert("리스트 겟 성공")
              })
              .catch(() => {
                alert("멤버 리드 읽기 실패")
              })
          })
          .catch((error) => {
            vue.response = error.message
            alert("사진 전송 실패")
          })
      }
    },
    deleteList(item) {
      let qnaNo = item.qna_no
      var result = confirm("삭제 하시겠습니까?")
      if (result) {
        axios
          .delete(`http://localhost:7777/qna/delete/${qnaNo}`)
          .then((res) => {
            console.log(res.data)
            this.$router.go()
          })
          .catch(() => {
            alert("리스트 삭제 실패")
          })
      }
    },
    rightMouse(item) {
      this.num = item.qna_comment_no
      this.testBtn = true
    },
    onClick() {
      this.testBtn = false
    },
    deleteBtn(item) {
      let qnaCommentNo = item.qna_comment_no
      let checkQnaNo = qnaCommentNo

      axios
        .delete(`http://localhost:7777/qnaComment/delete/${qnaCommentNo}`)
        .then((res) => {
          console.log(res.data)
          axios
            .get(`http://localhost:7777/qna/memberRead/${checkQnaNo}`)
            .then((res) => {
              this.qnaList = res.data
              console.log(res.data)
            })
            .catch(() => {
              alert("멤버 리드 읽기 실패")
            })
        })
        .catch(() => {
          alert("리스트 삭제 실패")
        })
    },
  },
}
</script>

<style lang="scss" scoped>
.tokk {
  width: 400px;
  height: 600px;
  border: 1px solid black;
}

.inputSomething {
  position: absolute;
  clear: left;
  width: 400px;
  height: 50px;
  bottom: 0px;
  border: 1px solid black;
}

input {
  width: 350px;
}

.virtualScroll {
  background-color: rgb(66, 123, 228);
  border: 1px solid;
  padding: 5%;
}

.showBox {
  background-color: #eef3fd;
  border: #7689fd solid 1px;
  border-radius: 5px;
  color: #000000;
  font-size: 12px;
  font-weight: 500;
  height: auto;
  letter-spacing: -0.25px;
  margin-top: 6.8px;
  padding: 5px 11px;
  position: relative;
  width: fit-content;
  z-index: 100;
  max-height: 200;
  max-width: 300;
}

.showBox::after {
  border-color: #eef3fd transparent;
  border-style: solid;
  border-width: 0 6px 8px 6.5px;
  content: "";
  display: block;
  left: 75px;
  position: absolute;
  top: -7px;
  width: 0;
  z-index: 1;
}

.showBox::before {
  border-color: #7689fd transparent;
  border-style: solid;
  border-width: 0 6px 8px 6.5px;
  content: "";
  display: block;
  left: 75px;
  position: absolute;
  top: -8px;
  width: 0;
  z-index: 0;
}

.showBoxOther {
  background-color: #fcfcf4;
  border: #fcfcf4 solid 1px;
  border-radius: 5px;
  color: #000000;
  font-size: 12px;
  font-weight: 500;
  height: auto;
  letter-spacing: -0.25px;
  margin-top: 6.8px;
  padding: 5px 11px;
  position: relative;
  width: fit-content;
  z-index: 100;
}

.showBoxOther::after {
  border-color: #fcfcf4 transparent;
  border-style: solid;
  border-width: 0 6px 8px 6.5px;
  content: "";
  display: block;
  left: 75px;
  position: absolute;
  top: -7px;
  width: 0;
  z-index: 1;
}

.showBoxOther::before {
  border-color: #fcfcf4 transparent;
  border-style: solid;
  border-width: 0 6px 8px 6.5px;
  content: "";
  display: block;
  left: 75px;
  position: absolute;
  top: -8px;
  width: 0;
  z-index: 0;
}
.delect {
  position: relative;
  overflow: hidden;
}
.delect:hover::after {
  opacity: 1;
}
.show-btns {
  position: absolute;
  bottom: 150%;
  z-index: 1;
  transition: all 0.1s;
  position: absolute;
  left: 140px;
}
.delect:hover .show-btns {
  top: 30px;
}
.test {
  @include flexbox(between);
}
</style>

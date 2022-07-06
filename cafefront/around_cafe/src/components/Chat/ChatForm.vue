<template>
  <div class="chat">
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <div class="chat-content">
            <div class="chat-left">
              <nav class="chat-sidebar">
                <a href="">
                  <div
                    class="avatar-48"
                    v-if="
                      !qnaLists ||
                      (Array.isArray(qnaLists) && qnaLists.length === 0)
                    "
                  >
                    <img src="@/assets/images/avatar.webp" alt="" />
                  </div>
                  <div class="avatar-48" v-else>
                    <img
                      v-if="qnaLists[0].writerImg === null"
                      src="@/assets/images/avatar.webp"
                      alt=""
                    />
                    <img
                      v-if="qnaLists[0].writerImg != null && findKakao < 5"
                      v-bind:src="
                        require(`@/assets/images/memberImg/${qnaLists[0].writerImg}`)
                      "
                    />
                    <img
                      v-if="qnaLists[0].writerImg != null && findKakao > 5"
                      :src="qnaLists[0].writerImg"
                    />
                  </div>
                </a>
              </nav>
              <nav class="chat-room">
                <div class="chat-room-name">
                  <div>{{ this.nickname }}</div>
                </div>
                <div class="chat-room-text">
                  <label for="">
                    <span>안읽은 메시지만 보기</span>
                  </label>
                </div>

                <!-- 채팅방룸 -->
                <ul class="chat-room-msg" v-if="qnaLists === null">
                  <li class="chat-room-list">
                    <v-btn class="delete-button">
                      <i class="icClose"></i>
                    </v-btn>
                    <a>
                      <div class="chat-room-img"></div>
                      <div class="chat-room-box">
                        <div class="chat-room-wrapper">
                          <strong class="chat-room-nick"> </strong>
                          <span class="chat-room-cafe"></span>
                        </div>
                        <div>
                          <span class="chat-room-contents"></span>
                        </div>
                      </div>
                    </a>
                  </li>
                </ul>
                <ul class="chat-room-msg" v-else>
                  <li
                    v-for="(item, index) in qnaLists"
                    :key="index"
                    class="chat-room-list"
                    @mouseover="deleteBtn = index"
                    @mouseleave="deleteBtn = null"
                  >
                    <v-btn
                      class="delete-button"
                      x-small
                      fab
                      v-show="deleteBtn === index"
                      @click="deleteList(item)"
                    >
                      <i class="icClose"></i>
                    </v-btn>
                    <a @click="sendQnaNo(item, index)">
                      <div class="chat-room-img">
                        <img
                          v-if="item.received_img == null"
                          src="@/assets/images/avatar.webp"
                          alt=""
                        />
                        <img
                          v-else
                          v-bind:src="
                            require(`@/assets/images/memberImg/${item.received_img}`)
                          "
                        />
                      </div>
                      <div class="chat-room-box">
                        <div class="chat-room-wrapper">
                          <strong class="chat-room-nick">{{
                            item.received_name
                          }}</strong>
                          <span class="chat-room-cafe">{{ item.type }}</span>
                        </div>
                        <div v-if="item.content != null">
                          <span class="chat-room-contents">{{
                            item.content
                          }}</span>
                        </div>
                        <div v-else>
                          <span class="chat-room-contents">이미지</span>
                        </div>
                      </div>
                    </a>
                  </li>
                </ul>

                <!-- 맨밑 자주묻는 질문 -->
                <div class="chat-room-qna">
                  <a>
                    <span>자주 묻는 질문</span>
                  </a>
                </div>
              </nav>
            </div>

            <section class="chat-section">
              <!-- 채팅방 선택 안할 시 -->
              <div
                v-if="
                  !qnaList || (Array.isArray(qnaList) && qnaList.length === 0)
                "
                class="chat-section-content"
              >
                <v-icon style="width: 400px">mdi-wechat</v-icon>
                <div>채팅할 상대를 선택해주세요.</div>
              </div>

              <!-- 채팅방 선택 할 시 -->
              <div v-else class="chat-normal-room">
                <div class="chat-normal-wrapper">
                  <div class="chat-normal-header">
                    <div class="avatar-40">
                      <img
                        v-if="qnaLists[selectQna].received_img == null"
                        src="@/assets/images/avatar.webp"
                        alt=""
                      />
                      <img
                        v-else
                        v-bind:src="
                          require(`@/assets/images/memberImg/${qnaLists[selectQna].received_img}`)
                        "
                      />
                    </div>
                    <div class="chat-normal-name">
                      <span>{{ cafeName }}</span>
                    </div>
                  </div>

                  <div
                    v-for="(item, index) in qnaList"
                    :key="index"
                    class="chat-box list"
                  >
                    <div v-if="index == 0">
                      {{ qnaList[index].regYear }}
                    </div>
                    <div
                      v-if="
                        index > 0 &&
                        qnaList[index].regYear != qnaList[index - 1].regYear
                      "
                    >
                      {{ qnaList[index].regYear }}
                    </div>
                    <!-- 상대방 -->
                    <div v-if="item.writer != membNo" class="chat-box opponent">
                      <div class="avatar-48">
                        <img
                          v-if="index == 0"
                          v-bind:src="
                            require(`@/assets/images/memberImg/${qnaLists[selectQna].received_img}`)
                          "
                        />
                        <img
                          v-if="qnaLists[selectQna].received_img == null"
                          src="@/assets/images/avatar.webp"
                          alt=""
                        />
                        <img
                          v-if="
                            index > 0 &&
                            qnaList[index].writer != qnaList[index - 1].writer
                          "
                          v-bind:src="
                            require(`@/assets/images/memberImg/${qnaLists[selectQna].received_img}`)
                          "
                        />
                        <img
                          v-if="
                            index > 0 &&
                            qnaList[index].writer == qnaList[index - 1].writer
                          "
                          v-bind:src="require(`@/assets/qna/whiteImg.jpg`)"
                        />
                      </div>
                      <div v-if="item.content != null">
                        <p class="chat-box-contents opponent">
                          {{ item.content }}
                        </p>
                      </div>
                      <div v-if="item.img != null">
                        <img
                          v-bind:src="require(`@/assets/qna/${item.img}`)"
                          style="width: 200px; display: block"
                        />
                      </div>
                      <div class="chat-box-times">
                        <div class="chat-box-time">{{ item.regTime }}</div>
                      </div>
                    </div>

                    <!-- 나 -->
                    <div
                      v-if="item.writer == membNo"
                      @mousedown.right="rightMouse(item)"
                      @contextmenu.prevent
                      class="chat-box me"
                    >
                      <div class="chat-box-times">
                        <div class="chat-box-time">{{ item.regTime }}</div>
                      </div>
                      <div v-if="item.content != null">
                        <p class="chat-box-contents me">{{ item.content }}</p>
                      </div>
                      <div v-if="item.img != null">
                        <img
                          v-bind:src="require(`@/assets/qna/${item.img}`)"
                          style="width: 200px; display: block"
                        />
                      </div>
                      <v-btn
                        v-if="item.qna_comment_no == num"
                        v-show="testBtn"
                        @click="deleteComment(item)"
                        >delete</v-btn
                      >
                    </div>
                  </div>
                </div>

                <form>
                  <textarea
                    @keyup="charCount()"
                    @keydown.enter.prevent="submitMsg"
                    v-model="chatting"
                    maxlength="1000"
                    cols="120"
                    rows="32"
                    placeholder="메시지를 입력해주세요."
                  ></textarea>
                  <div class="textarea-content">
                    <div>
                      <v-icon
                        @click="handleFileUpload()"
                        id="files1"
                        ref="files1"
                        multiple
                        >mdi-panorama-variant
                      </v-icon>
                    </div>
                    <div>
                      <span>{{ totalcharacter }} / 1000</span>

                      <v-btn @click="submitMsg" class="btn-indigo btn-40"
                        >전송
                      </v-btn>
                    </div>
                  </div>
                </form>
              </div>
            </section>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios"
export default {
  name: "ChatForm",
  props: {
    qnaLists: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      deleteBtn: null,
      chatting: "",
      totalcharacter: 0,
      qnaList: [],
      cafeName: "문의자",
      membNo: JSON.parse(localStorage.getItem("user")).memNo,
      checkQnaNo: "",
      testBtn: false,
      num: "",
      nickname: JSON.parse(localStorage.getItem("user")).nickname,
      selectQna: "",
      findKakao: "",
    }
  },
  watch: {
    testBtn() {
      if (this.testBtn) {
        window.addEventListener("click", this.onClick)
      }
    },
  },
  // computed: {
  //   chatting() {
  //     return this.chatting.split("\n").join("<br>")
  //     //return this.content.replace(/(?:\r\n|\r|\n)/g, '<br>'); 정규식
  //   },
  // },
  methods: {
    charCount() {
      this.totalcharacter = this.chatting.length
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

        let membNo = JSON.parse(localStorage.getItem("user")).memNo
        axios
          .post(
            `http://localhost:7777/qnaComment/registerImg/${qnaNo}/${membNo}`,
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
    submitMsg() {
      let formData = new FormData()

      let fileInfo = {
        qnaNo: this.checkQnaNo,
        chatting: this.chatting,
      }

      formData.append(
        "info",
        new Blob([JSON.stringify(fileInfo)], { type: "application/json" })
      )

      let membNo = this.membNo
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
    sendQnaNo(item, index) {
      this.checkQnaNo = item.qna_no
      this.selectQna = index
      axios
        .get(`http://localhost:7777/qna/memberRead/${this.checkQnaNo}`)
        .then((res) => {
          console.log(res.data)
          this.qnaList = res.data
          this.cafeName = item.received_name
          this.findKakao = this.qnaLists[0].writerImg.indexOf("k.kakaocdn.net")
        })
        .catch(() => {
          alert("문의사항 등록에 실패하였습니다.")
        })
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
    deleteComment(item) {
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
@import "~@/assets/scss/components/chat/chat";
@import "~@/assets/scss/components/chat/chat-box";
</style>

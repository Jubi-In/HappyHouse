/
<template>
  <div class="container text-center mt-3">
    <div class="col-lg-10 mx-auto">
      <h2 class="p-3 mb-5 shadow bg-light">
        <mark
          style="background-color: #fdecec"
          v-if="
            this.whatTypeArticle === 'QnA' && this.writeOrModify === 'write'
          "
          >질문작성</mark
        >
        <mark
          style="background-color: #fdecec"
          v-if="
            this.whatTypeArticle === 'QnA' && this.writeOrModify === 'modify'
          "
          >질문수정</mark
        >

        <mark
          style="background-color: #fdecec"
          v-if="
            this.whatTypeArticle === 'Notice' && this.writeOrModify === 'write'
          "
          >공지사항 작성</mark
        >
        <mark
          style="background-color: #fdecec"
          v-if="
            this.whatTypeArticle === 'Notice' && this.writeOrModify === 'modify'
          "
          >공지사항 수정</mark
        >
      </h2>
    </div>

    <div class="col-lg-10 mx-auto">
      <b-row class="mb-1">
        <b-col style="text-align: left">
          <b-form @submit="onSubmit" @reset="onReset">
            <b-form-group id="subject-group" label="제목:" label-for="subject">
              <b-form-input
                id="subject"
                v-model="article.subject"
                type="text"
                required
                placeholder="제목을 입력해주세요."
              ></b-form-input>
            </b-form-group>

            <b-form-group id="content-group" label="내용:" label-for="content">
              <b-form-textarea
                id="content"
                v-model="article.content"
                placeholder="내용을 적어주세요."
                required
                rows="10"
                max-rows="15"
              ></b-form-textarea>
            </b-form-group>

            <b-button
              v-if="this.whatTypeArticle === 'QnA'"
              style="float: right"
              variant="outline-danger"
              class="m-1"
              @click="moveQnAList"
              >취소</b-button
            >

            <b-button
              v-if="this.whatTypeArticle === 'Notice'"
              style="float: right"
              variant="outline-danger"
              class="m-1"
              @click="moveNoticeList"
              >취소</b-button
            >

            <b-button
              v-if="this.writeOrModify === 'write'"
              style="float: right"
              type="submit"
              variant="outline-success"
              class="m-1"
              >글작성</b-button
            >

            <b-button
              v-if="this.writeOrModify === 'modify'"
              style="float: right"
              type="submit"
              variant="outline-success"
              class="m-1"
              >글수정</b-button
            >
          </b-form>
        </b-col>
      </b-row>
    </div>
  </div>
</template>

<script>
import { getArticle, writeArticle, modifyArticle } from "@/api/board";
import { mapState } from "vuex";
const boardStore = "boardStore";
const memberStore = "memberStore";

export default {
  name: "QnAWrite",
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(boardStore, [
      "nowViewingArticleNo",
      "whatTypeArticle",
      "writeOrModify",
    ]),
  },
  data() {
    return {
      article: {
        articleno: 0,
        subject: "",
        content: "",
      },
    };
  },
  created() {
    if (this.writeOrModify === "modify") {
      getArticle(
        this.nowViewingArticleNo,
        this.whatTypeArticle,
        (response) => {
          this.article = response.data;
        },
        (error) => {
          console.log(error);
        },
      );
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();
      if (this.writeOrModify === "write") this.doWriteArticle();
      else if (this.writeOrModify === "modify") this.doModifyArticle();
    },
    onReset(event) {
      event.preventDefault();
    },
    doWriteArticle() {
      writeArticle(
        {
          user_id: this.userInfo.user_id,
          subject: this.article.subject,
          content: this.article.content,
        },
        this.whatTypeArticle,
        ({ data }) => {
          let msg = "등록 처리 실패";
          if (data === "success") {
            msg = "등록 완료";
          }
          alert(msg);
          if (this.whatTypeArticle === "QnA") this.moveQnAList();
          else if (this.whatTypeArticle === "Notice") this.moveNoticeList();
        },
        (error) => {
          console.log(error);
        },
      );
    },
    doModifyArticle() {
      modifyArticle(
        {
          articleno: this.article.articleno,
          user_id: this.userInfo.user_id,
          subject: this.article.subject,
          content: this.article.content,
        },
        this.whatTypeArticle,
        ({ data }) => {
          if (data === "success") {
            alert("수정 완료");
          }
          if (this.whatTypeArticle === "QnA") this.moveQnAList();
          else if (this.whatTypeArticle === "Notice") this.moveNoticeList();
        },
        (error) => {
          console.log(error);
        },
      );
    },
    moveQnAList() {
      this.$router.push({ name: "QnAList" });
    },
    moveNoticeList() {
      this.$router.push({ name: "NoticeList" });
    },
  },
};
</script>

<style></style>

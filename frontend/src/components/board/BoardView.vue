/
<template>
  <div class="container text-center mt-3">
    <div class="col-lg-10 mx-auto">
      <h2 class="p-3 mb-5 shadow bg-light">
        <mark
          style="background-color: #fdecec"
          v-if="this.whatTypeArticle === 'Notice'"
          >공지사항</mark
        >
        <mark
          style="background-color: #fdecec"
          v-if="this.whatTypeArticle === 'QnA'"
          >Q&A</mark
        >
      </h2>
    </div>

    <div class="col-lg-10 mx-auto buttons">
      <div class="button">
        <a href="#" @click="toList()">목록</a>
      </div>
      <div
        class="right"
        v-show="
          (whatTypeArticle == 'Notice' &&
            isLogin &&
            userInfo.user_id == 'admin') ||
          (whatTypeArticle == 'QnA' &&
            isLogin &&
            (article.user_id == userInfo.user_id ||
              userInfo.user_id == 'admin'))
        "
      >
        <div class="button">
          <a href="#" @click="toModifyArticle()">수정</a>
        </div>
        <div class="button">
          <a href="#" @click="doDeleteArticle()">삭제</a>
        </div>
      </div>
    </div>
    <div class="col-lg-10 mx-auto">
      <div class="main-content">
        <div class="title">{{ article.subject }}</div>
        <div class="detail">
          <div class="detail-item">작성자: {{ writer }}</div>
          <div class="detail-item">작성일: {{ article.regtime }}</div>
          <div class="detail-item">조회수: {{ article.hit }}</div>
        </div>
        <div class="art-content">
          {{ article.content }}
        </div>
      </div>

      <board-memo-form></board-memo-form>
    </div>
  </div>
</template>

<script>
import BoardMemoForm from "./child/BoardMemoForm.vue";
import { mapState, mapMutations } from "vuex";
const memberStore = "memberStore";
const boardStore = "boardStore";
import { getArticle, deleteArticle } from "@/api/board";
export default {
  name: "BoardView",
  components: {
    BoardMemoForm,
  },
  data() {
    return {
      article: {},
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapState(boardStore, [
      "nowViewingArticleNo",
      "whatTypeArticle",
      "writer",
    ]),
    // message() {
    //   if (this.article.content) {
    //     return this.article.content.split("\n").join("<br>");
    //   }
    //   return "";
    // },
  },
  created() {
    // this.article = this.$route.params.articles;
    // 2021.11.23 한길 메모: 이렇게 하면 첫 접속때는 보이지만 새로고침하면 안뜬다. 수정필요 :: 해결
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
  },
  methods: {
    ...mapMutations(boardStore, [
      "SET_NOW_VIEWING_ARTICLE_NO",
      "SET_WRITE_OR_MODIFY",
    ]),
    doDeleteArticle() {
      if (confirm("정말로 삭제합니까?")) {
        deleteArticle(this.nowViewingArticleNo, this.whatTypeArticle, () => {
          this.toList();
        });
      }
    },

    toModifyArticle() {
      this.SET_WRITE_OR_MODIFY("modify");
      this.$router.push({ name: "BoardWrite" });
    },
    toList() {
      if (this.whatTypeArticle === "Notice")
        this.$router.push({ name: "NoticeList", query: { page: 1 } });
      else if (this.whatTypeArticle === "QnA")
        this.$router.push({ name: "QnAList", query: { page: 1 } });
    },
  },
};
</script>

<style>
.container {
  letter-spacing: -1px;
}

.buttons {
  display: flex;
  padding: 20px;
  justify-content: space-between;
  font-size: 18px;
}
a {
  color: black;
}
.button {
  margin: 10px;
}
.right {
  display: flex;
}

.main-content {
  padding: 20px;
}

.title {
  font-weight: 600;
  font-size: 30px;
  display: flex;
  justify-content: flex-start;
}

.detail {
  display: flex;
  justify-content: flex-end;
}

.detail-item {
  margin: 10px;
}
.art-content {
  margin-top: 20px;
  height: 300px;
  padding: 20px;
  border: 1px solid gray;
  display: flex;
  justify-content: flex-start;
}
</style>

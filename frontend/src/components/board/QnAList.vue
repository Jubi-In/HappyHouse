<template>
  <div class="container text-center mt-3">
    <div class="col-lg-10 mx-auto">
      <h2 class="p-3 mb-5 shadow bg-light">
        <mark style="background-color: #fdecec">Q&A</mark>
      </h2>
    </div>

    <div class="col-lg-12 mx-auto">
      <b-row class="mb-1">
        <b-col class="text-right">
          <b-button
            variant="outline-primary"
            @click="toWriteQna"
            v-show="isLogin"
            >글쓰기</b-button
          >
        </b-col>
      </b-row>

      <b-row>
        <b-col>
          <b-table
            hover
            :items="articles"
            :fields="fields"
            @row-clicked="viewArticle"
          >
          </b-table>
        </b-col>
      </b-row>
    </div>
    <div class="p">
      <paginate
        v-model="page"
        :page-count="endPage"
        :page-range="3"
        :click-handler="pageClick"
        :prev-text="'<'"
        :next-text="'>'"
        :container-class="'pagination'"
        :page-class="'page-item'"
      >
      </paginate>
    </div>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
import Paginate from "vuejs-paginate";
const boardStore = "boardStore";
const memberStore = "memberStore";
import { listArticle } from "@/api/board.js";
export default {
  name: "QnAList",
  components: {
    Paginate,
  },
  computed: {
    ...mapState(memberStore, ["isLogin"]),
  },
  data() {
    return {
      articles: [],
      fields: [
        // { key: "index", label: "글번호" },
        { key: "articleno", label: "글번호", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdClass" },
        { key: "user_name", label: "작성자", tdClass: "tdClass" },
        { key: "regtime", label: "작성일", tdClass: "tdClass" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
      ],
      whatType: "QnA",
      page: 1,
      endPage: 1,
    };
  },
  methods: {
    ...mapMutations(boardStore, [
      "SET_NOW_VIEWING_ARTICLE_NO",
      "SET_WHAT_TYPE_ARTICLE",
      "SET_WRITE_OR_MODIFY",
      "SET_WRITER",
    ]),
    toWriteQna() {
      this.SET_WRITE_OR_MODIFY("write");
      this.SET_WHAT_TYPE_ARTICLE("QnA");
      this.$router.push({ name: "BoardWrite" });
    },
    viewArticle(article) {
      this.SET_NOW_VIEWING_ARTICLE_NO(article.articleno);
      this.SET_WHAT_TYPE_ARTICLE("QnA");
      this.SET_WRITER(article.user_name);
      this.$router.push({ name: "BoardView" });
    },
    pageClick: function (page) {
      this.page = page;
      this.moveToPage(page);
    },
    moveToPage: function (page) {
      let param = {
        pg: page,
        spp: 10,
        key: null,
        word: null,
      };
      listArticle(
        param,
        this.whatType,
        (response) => {
          this.articles = response.data;
        },
        (error) => {
          console.log(error);
        },
      );
      this.$router.push({
        name: "QnAList",
        query: { page },
      });
    },
  },
  created() {
    let param = {
      pg: 1,
      spp: 100,
      key: null,
      word: null,
    };
    listArticle(
      param,
      this.whatType,
      (response) => {
        this.endPage = parseInt(response.data.length / 10) + 1;
      },
      (error) => {
        console.log(error);
      },
    );
    param = {
      pg: 1,
      spp: 10,
      key: null,
      word: null,
    };
    listArticle(
      param,
      this.whatType,
      (response) => {
        this.articles = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
  },
};
</script>

<style>
.p {
  display: flex;
  justify-content: center;
}
.pagination {
  font-size: 15px;
  margin-top: 30px;
  padding: 10px;
  padding-left: 10px;
  width: fit-content;
}

.page-item {
  width: 20px;
  margin-left: 10px;
  margin-right: 10px;
  color: black;
}

.page-item a {
  color: black;
}
.active {
  font-weight: 600;
}
</style>

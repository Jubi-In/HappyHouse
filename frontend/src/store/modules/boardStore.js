const boardStore = {
  namespaced: true,
  state: {
    nowViewingArticleNo: "",
    whatTypeArticle: "",
    writeOrModify: "",
    writer: "",
  },
  getters: {},
  mutations: {
    SET_NOW_VIEWING_ARTICLE_NO: (state, nowViewingArticleNo) => {
      state.nowViewingArticleNo = nowViewingArticleNo;
    },
    SET_WHAT_TYPE_ARTICLE: (state, whatTypeArticle) => {
      state.whatTypeArticle = whatTypeArticle;
    },
    SET_WRITE_OR_MODIFY: (state, writeOrModify) => {
      state.writeOrModify = writeOrModify;
    },
    SET_WRITER: (state, writer) => {
      state.writer = writer;
    },
  },
  actions: {},
};

export default boardStore;

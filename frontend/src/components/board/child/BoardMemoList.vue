/
<template>
  <div class="comment">
    <div class="comment-row" v-for="(memo, index) in memos" :key="index">
      <div class="user">
        <div class="name">{{ memo.user_name }}</div>
        <div
          v-show="
            memo.user_id == userInfo.user_id || userInfo.user_id == 'admin'
          "
        >
          <a href="" @click="doDeleteMemo(memo.memono)">삭제</a>
        </div>
      </div>
      <div class="content">
        <div>{{ memo.comment }}</div>
        <div>{{ memo.memotime }}</div>
      </div>
    </div>
  </div>
</template>

<script>
import { listMemo, deleteMemo } from "@/api/memo";
import { mapState } from "vuex";
const memberStore = "memberStore";
const boardStore = "boardStore";
export default {
  name: "BoardMemoList",
  data() {
    return {
      modifyOrNot: false,
      memos: [],
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(boardStore, ["nowViewingArticleNo", "whatTypeArticle"]),
  },
  created() {
    listMemo(
      this.nowViewingArticleNo,
      this.whatTypeArticle,
      (response) => {
        this.memos = response.data;
        console.log(response.data);
        console.log(this.memos);
      },
      (error) => {
        console.log(error);
      },
    );
  },
  methods: {
    doDeleteMemo(memono) {
      if (confirm("정말로 삭제합니까?")) {
        deleteMemo(memono, this.whatTypeArticle, () => {
          // this.$router.go();
        });
      }
    },
  },
};
</script>

<style>
.comment-row {
  border: 1px solid gray;
  text-align: left;
  margin: 20px;
  padding: 20px;
}
.name {
  font-weight: 600;
  margin-bottom: 10px;
}

.content {
  display: flex;
  justify-content: space-between;
}

.user {
  display: flex;
  justify-content: space-between;
}

a {
  color: black;
}
</style>

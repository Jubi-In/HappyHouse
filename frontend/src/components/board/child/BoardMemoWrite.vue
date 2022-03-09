/
<template>
  <div class="col-lg-12 mx-auto">
    <b-row>
      <b-col class="col-2 wname">
        {{ this.userInfo.user_name }}
      </b-col>

      <b-col>
        <b-form-textarea
          id="textarea-default"
          placeholder="덧글 입력"
          v-model="memoContent"
          rows="4"
          max-rows="5"
        ></b-form-textarea>
      </b-col>
    </b-row>
    <b-button
      class="mt-2"
      variant="outline-secondary"
      @click="doUploadMemo"
      style="float: right"
      >등록</b-button
    >
  </div>
</template>

<script>
import { writeMemo } from "@/api/memo";
import { mapState } from "vuex";
const memberStore = "memberStore";
const boardStore = "boardStore";
export default {
  name: "BoardMemoWrite",
  data() {
    return {
      memoContent: "",
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapState(boardStore, ["nowViewingArticleNo", "whatTypeArticle"]),
  },
  methods: {
    doUploadMemo() {
      if (!this.memoContent) alert("덧글 내용을 입력해주세요.");
      writeMemo(
        {
          user_id: this.userInfo.user_id,
          comment: this.memoContent,
          articleno: this.nowViewingArticleNo,
        },
        this.whatTypeArticle,
        ({ data }) => {
          if (data === "success") {
            this.memoContent = "";
            this.$router.go();
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};
</script>

<style>
.wname {
  padding-top: 40px;
}
</style>

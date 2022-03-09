<template>
  <div class="container text-center mt-3">
    <div class="col-lg-8 mx-auto">
      <h2 class="p-3 mb-5 shadow bg-light">
        <mark class="orange">회원정보</mark>
      </h2>
      <table class="usermodify-table table">
        <tbody>
          <tr id="tr-top">
            <th>아이디</th>
            <td>
              <div class="usermodify">
                <strong class="usermodify-current-id">{{
                  userInfo.user_id
                }}</strong>
              </div>
            </td>
          </tr>
          <tr>
            <th>닉네임</th>
            <td>
              <div class="usermodify">
                <strong class="usermodify-current-name">{{
                  userInfo.user_name
                }}</strong>
              </div>
            </td>
          </tr>
          <tr>
            <th>비밀번호</th>
            <td>
              <div class="usermodify">
                <button v-on:click="toChangePwd">비밀번호 변경</button>
              </div>
            </td>
          </tr>
          <tr>
            <th>이메일</th>
            <td>
              <div class="usermodify">
                <strong class="usermodify-current-email">{{
                  userInfo.user_email
                }}</strong>
              </div>
            </td>
          </tr>
          <tr>
            <th>전화번호</th>
            <td>
              <div class="usermodify">
                <strong class="usermodify-current-tel">{{
                  userInfo.user_tel
                }}</strong>
              </div>
            </td>
          </tr>
          <tr>
            <th>주소</th>
            <td>
              <div class="usermodify">
                <strong class="usermodify-current-address">{{
                  userInfo.user_address
                }}</strong>
              </div>
            </td>
          </tr>
        </tbody>
      </table>

      <div class="form-group mt-4">
        <!-- 정보수정 버튼 -->
        <b-button
          type="button"
          variant="outline-primary"
          class="m-1"
          @click="toModify"
        >
          정보수정</b-button
        >
        <!-- 회원탈퇴 버튼 -->
        <b-button
          type="button"
          variant="outline-danger"
          class="m-1"
          @click="toDelete"
        >
          회원탈퇴</b-button
        >
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
const memberStore = "memberStore";

export default {
  name: "MemberMyPage",
  computed: {
    ...mapState(memberStore, ["isPwdChange", "userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_PWD_CHANGE"]),
    toModify() {
      this.SET_IS_PWD_CHANGE(false);
      this.$router.push({ name: "MemberModify" });
    },
    toDelete() {
      this.$router.push({ name: "MemberDelete" });
    },
    toChangePwd() {
      this.SET_IS_PWD_CHANGE(true);
      this.$router.push({ name: "MemberModify" });
    },
  },
};
</script>

<style>
/* tbody {
  display: table-row-group;
  vertical-align: middle;
  border-color: inherit;
} */
.usermodify-table {
  width: 100%;
  border-top: 1px solid #d3d0d0;
  border-left: 1px solid #d3d0d0;
  border-right: 1px solid #d3d0d0;
}
tr {
  border-bottom: 1px solid #d3d0d0;
}
/* td {
  padding: 14px 30px;
  border-bottom: 1px solid #d3d0d0;
} */
th {
  /* padding: 14px 30px; */
  background-color: #f3f4f7;
  text-align: left;
  /* border-bottom: 1px solid #d3d0d0; */
}
</style>

<template>
  <!-- 2021.11.21 한길 추가 -->
  <div class="container text-center mt-3">
    <div class="col-lg-8 mx-auto">
      <h2 class="p-3 mb-5 shadow bg-light">
        <mark style="background-color: #fdecec">회원탈퇴</mark>
      </h2>
      <b-alert variant="danger" show style="font-size: 20px"
        >회원 탈퇴를 진행하시겠습니까?</b-alert
      >
      <div class="form-group mt-4">
        <!-- 정보수정 버튼 -->
        <b-button type="button" variant="primary" class="m-1" @click="toModify">
          뒤로가기</b-button
        >
        <!-- 회원탈퇴 버튼 -->
        <b-button type="button" variant="danger" class="m-1" @click="doDelete">
          회원탈퇴</b-button
        >
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";
const memberStore = "memberStore";
export default {
  name: "MemberDelete",
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userDelete"]),
    ...mapMutations(memberStore, ["SET_USER_INFO_NULL"]),
    toModify() {
      this.$router.push({ name: "MemberMyPage" });
    },
    doDelete() {
      this.userDelete(this.userInfo.user_id);
      this.SET_USER_INFO_NULL();
      sessionStorage.removeItem("access-token");
      this.$router.push({ name: "Home" });
    },
  },
};
</script>

<style>
button {
  width: 120px;
  height: 40px;
}
</style>

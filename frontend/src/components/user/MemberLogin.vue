<template>
  <div class="container text-center mt-3">
    <div class="col-lg-8 mx-auto">
      <h2 class="p-3 mb-3 shadow bg-light">
        <mark class="orange">로그인</mark>
      </h2>
      <b-form class="text-left">
        <!-- 메세지 출력창 -->
        <b-alert show variant="danger" v-if="isLoginError">
          아이디 또는 비밀번호를 확인하세요.
        </b-alert>
        <!-- 아이디 입력칸 -->
        <b-form-group label="아이디" label-for="user_id">
          <b-form-input
            id="user_id"
            v-model="user.user_id"
            required
            placeholder="아이디 입력"
            @keyup.enter="confirm"
          ></b-form-input>
        </b-form-group>
        <!-- 비밀번호 입력칸 -->
        <b-form-group label="비밀번호" label-for="user_pw">
          <b-form-input
            type="password"
            id="user_pw"
            v-model="user.user_pw"
            required
            placeholder="비밀번호 입력"
            @keyup.enter="confirm"
          ></b-form-input>
        </b-form-group>
        <div class="form-group text-center">
          <!-- 로그인 버튼 -->
          <b-button
            type="button"
            variant="outline-primary"
            class="m-1"
            @click="confirm"
          >
            로그인</b-button
          >
          <!-- 회원가입 버튼 -->
          <b-button
            type="button"
            variant="outline-success"
            class="m-1"
            @click="movePage"
          >
            회원가입</b-button
          >
        </div>
      </b-form>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberLogin",
  data() {
    return {
      user: {
        user_id: null,
        user_pw: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    // 2021.11.18 한길 수정
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      // 아이디랑 비밀번호를 가져가서 token 가져오는 과정
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        // token을 가져왔으면 그거로 그 계정의 전체 정보를 불러오는 과정
        await this.getUserInfo(token);
        this.$router.push({ name: "Home" });
      }
    },

    movePage() {
      this.$router.push({ name: "SignUp" });
    },
  },
};
</script>

<style></style>

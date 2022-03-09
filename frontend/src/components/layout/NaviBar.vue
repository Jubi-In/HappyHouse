<template>
  <nav class="navbar navbar-expand-sm mt-4">
    <router-link to="/">
      <img class="mx-5" src="@/assets/logo.png" alt="HappyHouse" />
    </router-link>

    <b-nav-text v-if="userInfo"
      >{{ userInfo.user_name }}({{ userInfo.user_id }})님
      환영합니다.</b-nav-text
    >

    <b-collapse id="nav-collapse" class="justify-content-end" is-nav>
      <b-navbar-nav>
        <b-nav-item href="#" class="mr-5">
          <router-link
            :to="{ name: 'NoticeList', query: { page: 1 } }"
            class="logo"
          >
            공지사항
          </router-link>
        </b-nav-item>

        <b-nav-item href="#" class="mr-5">
          <router-link :to="{ name: 'Favorite' }" class="logo"
            >관심 지역</router-link
          >
        </b-nav-item>

        <b-nav-item href="#" class="mr-5">
          <router-link
            :to="{ name: 'QnAList', query: { page: 1 } }"
            class="logo"
          >
            Q&A
          </router-link>
        </b-nav-item>
      </b-navbar-nav>

      <!-- 로그인 상태이면 뜨는 네비게이션바 -->
      <b-navbar-nav v-if="userInfo">
        <b-nav-item id="login" href="#" class="mr-5">
          <router-link :to="{ name: 'MemberMyPage' }" class="logo"
            >내정보</router-link
          >
        </b-nav-item>
        <b-nav-item class="mr-5" @click.prevent="onClickLogout">
          로그아웃
        </b-nav-item>
      </b-navbar-nav>

      <!-- 로그인 상태가 아닐때 뜨는 네비게이션바 -->
      <b-navbar-nav v-else>
        <b-nav-item id="login" href="#" class="mr-5">
          <router-link :to="{ name: 'SignIn' }" class="logo"
            >로그인</router-link
          >
        </b-nav-item>
        <b-nav-item id="register" href="#" class="mr-5">
          <router-link :to="{ name: 'SignUp' }" class="logo"
            >회원가입</router-link
          >
        </b-nav-item>
      </b-navbar-nav>
    </b-collapse>
  </nav>
</template>

<script>
import { mapState, mapMutations } from "vuex";
const memberStore = "memberStore";
export default {
  name: "NaviBar",
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_USER_INFO_NULL"]),
    onClickLogout() {
      this.SET_USER_INFO_NULL();
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "Home" });
    },
  },
};
</script>

<style>
.logo {
  color: black;
  text-decoration: none;
}
</style>

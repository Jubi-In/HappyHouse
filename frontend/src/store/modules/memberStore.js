import jwt_decode from "jwt-decode";
import { login } from "@/api/member.js";
import { findById } from "../../api/member";
import { registerMember } from "@/api/member.js";
import { deleteMember } from "@/api/member.js";
import { updateMember } from "@/api/member";

const memberStore = {
  namespaced: true,
  state: {
    // 로그인 되거나 안될때마다 바뀔 변수들
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    isRegister: false,
    isRegisterError: false,
    isPwdChange: false,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
  },
  mutations: {
    // state들을 변경해줄때 편리하라고 있는 mutations
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_USER_INFO_NULL: (state) => {
      state.isLogin = false;
      state.userInfo = null;
    },
    SET_IS_REGISTER: (state, isRegister) => {
      state.isRegister = isRegister;
    },
    SET_IS_REGISTER_ERROR: (state, isRegisterError) => {
      state.isRegisterError = isRegisterError;
    },
    SET_IS_PWD_CHANGE: (state, isPwdChange) => {
      state.isPwdChange = isPwdChange;
    },
  },
  actions: {
    // 비동기적 함수실행 async await 사용하시오
    async userConfirm({ commit }, user) {
      await login(
        user,
        (response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            // 로그인 성공하면 session에 남게됨.
            sessionStorage.setItem("access-token", token);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => {},
      );
    },
    getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      findById(
        // decode_token 에는 user_id 라는애가 없다.
        decode_token.userid,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userInfo);
          } else {
            console.log("유저 정보 없음!!");
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    // 2021.11.19 한길 추가 :: 회원가입
    async register({ commit }, user) {
      await registerMember(
        user,
        (response) => {
          if (response.data === "success") {
            commit("SET_IS_REGISTER", true);
            commit("SET_IS_REGISTER_ERROR", false);
          } else {
            commit("SET_IS_REGISTER", false);
            commit("SET_IS_REGISTER_ERROR", true);
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    // 2021.11.21 한길 추가 :: 회원탈퇴
    async userDelete({ commit }, user_id) {
      await deleteMember(
        user_id,
        (response) => {
          if (response.data === "success") {
            commit("SET_IS_LOGIN", false); // 이거 필요없는데 commit 안하면 안돼서 넣은거
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    // 2021.11.24 한길 추가 :: 회원정보 수정
    async userUpdate({ commit }, user) {
      await updateMember(
        user,
        (response) => {
          if (response.data === "success") {
            commit("SET_USER_INFO", user);
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};

export default memberStore;

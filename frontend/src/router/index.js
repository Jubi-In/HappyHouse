import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";

import User from "../views/User.vue";
import MemberLogin from "@/components/user/MemberLogin.vue";
import MemberJoin from "@/components/user/MemberJoin.vue";
import MemberMyPage from "@/components/user/MemberMyPage.vue";
import MemberModify from "@/components/user/MemberModify.vue";
import MemberDelete from "@/components/user/MemberDelete.vue";

import House from "../views/House.vue";

import Favorite from "../views/Favorite.vue";
import FavoriteList from "@/components/favorite/FavoriteList.vue";

import Board from "../views/Board.vue";
import BoardList from "@/components/board/BoardList.vue";
import BoardView from "@/components/board/BoardView.vue";
import BoardWrite from "@/components/board/BoardWrite.vue";
import NoticeList from "@/components/board/NoticeList.vue";
import QnAList from "@/components/board/QnAList.vue";

import store from "@/store/index.js";

Vue.use(VueRouter);

const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다.");
    if (router.currentRoute.fullPath !== "/signin")
      router.push({ name: "SignIn" });
  } else {
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/user",
    name: "User",
    component: User,
    children: [
      {
        path: "/signin",
        name: "SignIn",
        component: MemberLogin,
      },
      {
        path: "/signup",
        name: "SignUp",
        component: MemberJoin,
      },
      {
        path: "/mypage",
        name: "MemberMyPage",
        beforeEnter: onlyAuthUser,
        component: MemberMyPage,
      },
      {
        path: "/membermodify",
        name: "MemberModify",
        beforeEnter: onlyAuthUser,
        component: MemberModify,
      },
      {
        path: "/memberdelete",
        name: "MemberDelete",
        beforeEnter: onlyAuthUser,
        component: MemberDelete,
      },
    ],
  },
  {
    path: "/house",
    name: "House",
    component: House,
    children: [
      {
        path: "dong",
        name: "Dong",
        component: House,
      },
    ],
  },
  {
    path: "/favorite",
    name: "Favorite",
    beforeEnter: onlyAuthUser,
    component: Favorite,
    children: [
      {
        path: "favoritelist",
        name: "FavoriteList",
        component: FavoriteList,
      },
    ],
  },

  {
    path: "/board",
    name: "Board",
    component: Board,
    children: [
      {
        path: "/boardlist",
        name: "BoardList",
        component: BoardList,
        children: [
          {
            path: "qna",
            name: "QnAList",
            component: QnAList,
          },
          {
            path: "notice",
            name: "NoticeList",
            component: NoticeList,
          },
        ],
      },
      {
        path: "/boardwrite",
        name: "BoardWrite",
        beforeEnter: onlyAuthUser,
        component: BoardWrite,
      },
      {
        path: "/boardview",
        name: "BoardView",
        beforeEnter: onlyAuthUser,
        component: BoardView,
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;

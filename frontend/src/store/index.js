import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

import houseStore from "@/store/modules/houseStore";
import memberStore from "@/store/modules/memberStore.js";
import boardStore from "@/store/modules/boardStore.js";

const store = new Vuex.Store({
  modules: {
    houseStore,
    memberStore,
    boardStore,
  },
  plugins: [
    createPersistedState({
      storage: sessionStorage,
    }),
  ],
});

export default store;

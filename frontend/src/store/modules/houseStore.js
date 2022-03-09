import {
  sidoList,
  gugunList,
  houseList,
  houseNameList,
  houseListByName,
} from "@/api/house.js";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "시/도" }],
    guguns: [{ value: null, text: "구/군" }],
    sido: {},
    gugun: {},
    houses: [],
    house: null,
    results: [],
    searched: false,
    keyword: "",
    isSigungu: true,
    isName: true,
    isLoading: false,
    isDong: false,
    endPage: 10,
  },

  mutations: {
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({
          value: { sidoCode: sido.sidoCode, sidoName: sido.sidoName },
          text: sido.sidoName,
        });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({
          value: { gugunCode: gugun.gugunCode, gugunName: gugun.gugunName },
          text: gugun.gugunName,
        });
      });
    },
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "시/도" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "구/군" }];
    },
    SET_SIDO(state, sido) {
      state.sido = sido;
    },
    SET_GUGUN(state, gugun) {
      state.gugun = gugun;
      state.keyword = state.sido.sidoName + " " + state.gugun.gugunName;
    },
    SET_HOUSE_LIST(state, houses) {
      state.isName = false;
      state.searched = true;
      state.houses = [];
      houses.forEach((house) => {
        state.houses.push({
          aptCode: house.일련번호,
          aptName: house.아파트,
          dongCode: house.법정동읍면동코드,
          dongName: house.법정동,
          sidoName: state.sido.sidoName,
          sidoCode: state.sido.sidoCode,
          gugunCode: state.gugun.gugunCode,
          gugunName: state.gugun.gugunName,
          buildYear: house.건축년도,
          jibun: house.지번,
          recentPrice: house.거래금액,
          area: house.전용면적,
          floor: house.층,
          streetName: house.도로명,
        });
      });

      state.results = [];
      houses.forEach((house) => {
        state.results.push(house.아파트);
      });
    },
    SET_SEARCH_RESULTS(state, results) {
      state.isSigungu = false;
      if (results != []) {
        console.log(results);
        results.forEach((result) => {
          state.results.push(result);
        });
      }
    },
    CLEAR_SEARCH_LIST(state) {
      state.results = [];
      state.isSigungu = true;
      state.isName = true;
      state.searched = false;
      state.keyword = "";
    },
    SET_SEARCHED(state) {
      state.searched = true;
    },
    SET_HOUSE_LIST_BY_NAME(state, houses) {
      state.houses = houses;
    },
    SET_KEYWORD(state, word) {
      state.keyword = word;
    },
    SET_IS_LOADING(state, s) {
      state.isLoading = s;
    },
    SET_IS_DONG(state, s) {
      state.isDong = s;
    },
    SET_END_PAGE(state, num) {
      state.endPage = num;
    },
  },

  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sido) => {
      const params = {
        sido: sido.sidoCode,
      };
      commit("SET_SIDO", sido);
      gugunList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getHouseList: ({ commit, state }, page) => {
      const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
      const params = {
        LAWD_CD: state.gugun.gugunCode,
        pageNo: page,
        numOfRows: "10",
        DEAL_YMD: "202110",
        serviceKey: decodeURIComponent(SERVICE_KEY),
      };

      houseList(
        params,
        (response) => {
          const end = parseInt(response.data.response.body.totalCount / 10);
          commit("SET_END_PAGE", end + 1);
          commit("SET_HOUSE_LIST", response.data.response.body.items.item);
          commit("SET_IS_LOADING", true);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getHouseListByDong: ({ commit, state }, obj) => {
      const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
      console.log(obj.page);
      const params = {
        LAWD_CD: state.gugun.gugunCode,
        pageNo: obj.page,
        DEAL_YMD: "202110",
        numOfRows: "100",
        serviceKey: decodeURIComponent(SERVICE_KEY),
      };

      houseList(
        params,
        (response) => {
          const end = parseInt(response.data.response.body.totalCount / 10);
          const houses = [];
          response.data.response.body.items.item.forEach((house) => {
            if (house.법정동 == obj.dongName) houses.push(house);
          });
          commit("SET_END_PAGE", end + 1);
          commit("SET_HOUSE_LIST", houses);
          commit("SET_IS_LOADING", true);
          commit("SET_IS_DONG", true);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getNameList: ({ commit }, word) => {
      const params = {
        word: word,
      };
      houseNameList(
        params,
        (response) => {
          commit("SET_SEARCH_RESULTS", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getHouseListByName: ({ commit }, word) => {
      commit("SET_KEYWORD", word);
      houseListByName(
        word,
        (response) => {
          const end = parseInt(response.data.length / 10);
          console.log(response);
          commit("SET_HOUSE_LIST_BY_NAME", response.data);
          commit("SET_END_PAGE", end + 1);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default houseStore;

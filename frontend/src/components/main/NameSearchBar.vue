<template>
  <div>
    <div class="mt-3 pt-3 text-center">
      <img src="@/assets/searching.png" alt="" id="search" class="mb-3" />
      <h1>혹은 검색하세요.</h1>
    </div>

    <div id="searchArea">
      <form id="searchForm" class="text-left mb-3" method="post" action="">
        <div class="form-group">
          <input
            type="text"
            class="form-control text-center"
            id="searchbox"
            name="searchbox"
            placeholder="아파트명 검색"
            :value="keyword"
            @input="search"
            @keyup.enter="getHouseList"
          />
          <input type="text" style="display: none" />
        </div>
      </form>
      <house-name-list v-if="isLoading"></house-name-list>
    </div>
  </div>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";
import HouseNameList from "@/components/main/HouseNameList.vue";
const houseStore = "houseStore";

export default {
  name: "NameSearchBar",
  components: {
    HouseNameList,
  },
  data() {
    return {
      keyword: "",
    };
  },
  created() {
    this.clear();
  },
  methods: {
    ...mapState(houseStore, ["gugunCode", "isLoading"]),
    ...mapActions(houseStore, ["getNameList", "getHouseListByName"]),
    ...mapMutations(houseStore, [
      "CLEAR_SEARCH_LIST",
      "SET_SEARCHED",
      "SET_IS_LOADING",
    ]),
    clear() {
      this.CLEAR_SEARCH_LIST();
    },
    search(e) {
      this.keyword = e.target.value;
      this.CLEAR_SEARCH_LIST();
      this.SET_IS_LOADING(false);
      if (this.keyword.length >= 1) {
        this.getNameList(this.keyword);
        this.SET_IS_LOADING(true);
      }
    },
    getHouseList() {
      this.SET_SEARCHED();
      this.getHouseListByName(this.keyword);
    },
  },
};
</script>

<style>
#search {
  margin-top: 20px;
}
#searchArea {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
#searchbox {
  margin-top: 20px;
  width: 300px;
}
</style>

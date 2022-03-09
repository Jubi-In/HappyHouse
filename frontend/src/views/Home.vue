<template>
  <div class="container main-container">
    <div class="col-lg-12 text-center">
      <sigungu-search-bar v-show="isSigungu"></sigungu-search-bar>
      <name-search-bar v-show="isName"></name-search-bar>
      <!-- <house-map> </house-map> -->
      <router-link
        v-show="searched"
        :to="{ name: 'House', query: { page: 1 } }"
        class="info-link"
        >> 상세 정보 확인하기</router-link
      >
    </div>
  </div>
</template>

<script>
import SigunguSearchBar from "@/components/main/SigunguSearchBar.vue";
import NameSearchBar from "@/components/main/NameSearchBar.vue";
// import HouseMap from "@/components/house/HouseMap.vue";
import { mapState, mapMutations } from "vuex";

const houseStore = "houseStore";

export default {
  name: "Home",
  components: {
    SigunguSearchBar,
    NameSearchBar,
    // HouseMap,
  },
  created() {
    this.clear();
  },
  computed: {
    ...mapState(houseStore, ["searched", "isSigungu", "isName"]),
  },
  methods: {
    ...mapMutations(houseStore, ["CLEAR_SEARCH_LIST", "SET_SEARCHED"]),
    clear() {
      this.CLEAR_SEARCH_LIST();
    },
  },
};
</script>

<style>
.main-container {
  height: 300rem;
  margin-top: 200px;
  letter-spacing: -2px;
}

.dropdown-menu {
  max-height: 280px;
  overflow-y: auto;
}

.info-link {
  margin-top: 20px;
  font-size: 25px;
  color: black;
}
</style>

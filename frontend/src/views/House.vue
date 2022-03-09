<template>
  <div class="container info-container text-center">
    <div class="info">
      <span class="area">{{ keyword }}</span
      ><span> 의 검색 결과입니다.</span>
    </div>
    <favorite-register v-if="isSigungu"></favorite-register>
    <house-map v-if="isLoading"></house-map>
    <house-list></house-list>
    <house-pagination></house-pagination>
  </div>
</template>

<script>
import HouseList from "@/components/house/HouseList.vue";
import HouseMap from "@/components/house/HouseMap.vue";
import FavoriteRegister from "@/components/favorite/FavoriteRegister.vue";
import HousePagination from "@/components/house/HousePagination.vue";
import { mapState, mapMutations } from "vuex";
// import Paginate from "vuejs-paginate";

const houseStore = "houseStore";

export default {
  name: "House",
  data() {
    return {
      rows: 100,
      perPage: 1,
      currentPage: 5,
    };
  },
  components: {
    HouseList,
    HouseMap,
    FavoriteRegister,
    HousePagination,
    // Paginate,
  },
  computed: {
    ...mapState(houseStore, ["keyword", "houses", "isSigungu", "isLoading"]),
  },
  created() {},
  methods: {
    ...mapMutations(houseStore, ["CLEAR_SEARCH_LIST"]),
  },
};
</script>
<style>
.info-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.info {
  letter-spacing: -2px;
  font-size: 20px;
}
.area {
  font-size: 30px;
  font-weight: 600;
}
/* .pagination {
  font-size: 15px;
  margin-top: 30px;
  padding: 10px;
  padding-left: 10px;
  width: fit-content;
}

.page-item {
  width: 20px;
  margin-left: 10px;
  margin-right: 10px;
  color: black;
} */
</style>

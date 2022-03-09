<template>
  <table class="table col-12 mt-5 fav_table">
    <thead>
      <tr>
        <th class="col-1">번호</th>
        <th class="col-10">지역명</th>
      </tr>
    </thead>

    <tbody id="favorite">
      <tr
        v-for="(favorite, index) in favorites"
        :key="index"
        @click="
          moveHouse(
            favorite.sidoCode,
            favorite.sidoName,
            favorite.gugunCode,
            favorite.gugunName
          )
        "
      >
        <td class="col-3" id="apt">{{ index + 1 }}</td>
        <td class="col-3" id="dong">
          {{ favorite.sidoName }} {{ favorite.gugunName }}
        </td>
      </tr>
    </tbody>
  </table>
</template>
<script>
import { mapState, mapActions, mapMutations } from "vuex";
import { favoriteList } from "@/api/favorite.js";

const memberStore = "memberStore";
const houseStore = "houseStore";

export default {
  name: "FavoriteList",

  data() {
    return {
      favorites: [],
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(houseStore, ["houses", "isLoading"]),
  },
  watch: {
    isLoading(newValue) {
      if (newValue) this.$router.push({ name: "House", query: { page: 1 } });
    },
  },
  created() {
    const params = {
      user_id: this.userInfo.user_id,
    };

    favoriteList(params, (response) => {
      response.data.forEach((favorite) => {
        this.favorites.push(favorite);
      });
    });
  },

  methods: {
    ...mapActions(houseStore, ["getHouseList"]),
    ...mapMutations(houseStore, [
      "SET_SIDO",
      "SET_IS_LOADING",
      "SET_GUGUN",
      "SET_IS_DONG",
    ]),

    moveHouse(sidoCode, sidoName, gugunCode, gugunName) {
      var gc = gugunCode.substring(0, 5);
      var sc = sidoCode.substring(0, 2);
      this.SET_SIDO({ sidoCode: sc, sidoName });
      this.SET_GUGUN({ gugunCode: gc, gugunName });
      this.SET_IS_LOADING(false);
      this.SET_IS_DONG(false);
      this.getHouseList({ gugunCode: gc, gugunName });
    },
  },
};
</script>
<style scoped>
.fav_table {
  width: 50%;
}
</style>

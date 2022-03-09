<template>
  <div class="selectArea">
    <h1>원하는 지역을 선택하세요.</h1>
    <div class="select-container">
      <div class="box">
        <b-form-select
          v-model="sido"
          :options="sidos"
          @change="gugunList"
        ></b-form-select>
      </div>
      <div class="box">
        <b-form-select
          v-model="gugun"
          :options="guguns"
          @change="showMap"
        ></b-form-select>
      </div>
    </div>
    <house-name-list></house-name-list>
  </div>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";
import HouseNameList from "@/components/main/HouseNameList.vue";
const houseStore = "houseStore";

//import SigunguDropdown from "@/components/house/SigunguDropdown.vue";

export default {
  name: "HouseSearchBar",
  components: {
    HouseNameList,
  },
  data() {
    return {
      sido: null,
      gugun: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "houses", "searched"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.sidoList();
  },
  methods: {
    ...mapActions(houseStore, ["getSido", "getGugun", "getHouseList"]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "SET_SEARCHED",
      "SET_GUGUN",
      "SET_IS_DONG",
    ]),

    sidoList() {
      this.getSido();
    },
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sido.sidoCode) this.getGugun(this.sido);
    },
    showMap() {
      if (this.gugun.gugunCode) {
        this.SET_GUGUN(this.gugun);
        this.SET_IS_DONG(false);
        this.getHouseList(1);
      }
      //this.SET_SEARCHED();
    },
  },
};
</script>

<style>
.select-container {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}
.box {
  margin: 20px;
}
.custom-select {
  width: 200px;
}
</style>

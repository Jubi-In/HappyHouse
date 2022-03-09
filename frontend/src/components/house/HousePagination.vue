<template>
  <div class="p">
    <paginate
      v-model="page"
      :page-count="endPage"
      :page-range="3"
      :click-handler="pageClick"
      :prev-text="'<'"
      :next-text="'>'"
      :container-class="'pagination'"
      :page-class="'page-item'"
    >
    </paginate>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";
import Paginate from "vuejs-paginate";
const houseStore = "houseStore";

export default {
  name: "Pagination",
  components: {
    Paginate,
  },
  data() {
    return {
      perPage: 1,
      page: this.$route.query.page,
      dongName: null,
    };
  },
  created() {
    this.page = parseInt(this.$route.query.page);
    console.log(this.page);
  },
  watch: {
    isDong(newValue) {
      if (newValue) {
        this.page = parseInt(this.$route.query.page);
      }
    },
  },
  computed: {
    ...mapState(houseStore, ["isLoading", "isDong", "endPage"]),
  },
  methods: {
    ...mapActions(houseStore, ["getHouseList", "getHouseListByDong"]),
    ...mapMutations(houseStore, ["SET_IS_LOADING", "SET_IS_DONG"]),

    pageClick: function (page) {
      this.page = page;
      if (!this.isDong) this.getHouseListByPage(page);
      if (this.isDong) {
        this.dongName = this.$route.query.dong;
        this.getHouseListByDongPage(page);
      }
    },
    getHouseListByPage: function (page) {
      console.log("house: " + this.page);
      this.SET_IS_LOADING(false);
      this.getHouseList(page);
      this.$router.push({
        name: "House",
        query: { page },
      });
    },
    getHouseListByDongPage: function (page) {
      console.log("dong: " + this.page);
      this.SET_IS_LOADING(false);
      this.getHouseListByDong({ dongName: this.dongName, page });
      this.$router.push({
        name: "Dong",
        query: { dong: this.dongName, page },
      });
    },
  },
};
</script>
<style>
.pagination {
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
}

.page-item a {
  color: black;
}
.active {
  font-weight: 600;
}
</style>

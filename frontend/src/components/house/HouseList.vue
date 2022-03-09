<template>
  <div class="info-container">
    <div class="col-12 text-center mt-5 desc">
      원하는 아파트를 클릭하면 상세 정보를 볼 수 있습니다.
    </div>

    <table v-if="!houses" class="table table-active text-center">
      <tr class="table-info">
        <td>해당 정보가 없습니다.</td>
      </tr>
    </table>
    <table v-if="houses" class="table text-center col-12 mt-5 info-table">
      <thead>
        <tr>
          <th class="col-3">아파트 이름</th>
          <th class="col-3">동</th>
          <th class="col-3">전용면적</th>
          <th class="col-3">거래금액</th>
        </tr>
      </thead>

      <tbody id="info">
        <tr v-for="(house, index) in houses" :key="index">
          <td
            @click="getLatLng(house)"
            v-b-modal="'my-modal'"
            class="col-3"
            id="apt"
          >
            {{ house.aptName }}
          </td>
          <td @click="getHouseList(house)" class="col-3 text-center" id="dong">
            {{ house.dongName }}
          </td>
          <td class="col-3">{{ house.area }}㎡</td>
          <td class="col-3" v-if="house.recentPrice">
            {{
              (parseInt(house.recentPrice.replace(",", "")) * 10000) | price
            }}원
          </td>
        </tr>
      </tbody>
    </table>
    <detail-modal
      v-if="showModal"
      :detailInfo="detailInfo"
      :houseInfo="houseInfo"
    ></detail-modal>
  </div>
</template>

<script>
import { mapActions, mapState, mapMutations } from "vuex";
import { detailInfo, latLngByAddress } from "@/api/house.js";
import DetailModal from "@/components/house/DetailModal.vue";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    DetailModal,
  },
  data() {
    return {
      showModal: false,
      detailInfo: {
        stationName: null,
        stationCnt: null,
        schoolCnt: null,
        csCnt: null,
        hospitalCnt: null,
        bankCnt: null,
      },
      houseInfo: {
        aptName: null,
        address: null,
        floor: null,
        buildYear: null,
      },
      page: 10,
      dongName: null,
    };
  },
  created() {},
  computed: {
    ...mapState(houseStore, ["houses", "isLoading", "isDong"]),
  },
  watch: {
    isLoading(newValue) {
      if (newValue) {
        this.$router.push({
          name: "Dong",
          query: { dong: this.dongName, page: 1 },
        });
      }
    },
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  methods: {
    ...mapActions(houseStore, ["getHouseListByDong"]),
    ...mapMutations(houseStore, [
      "SET_GUGUN",
      "SET_IS_LOADING",
      "SET_IS_DONG",
      "SET_KEYWORD",
    ]),
    getLatLng(house) {
      const address =
        house.sidoName + house.gugunName + house.dongName + house.jibun;

      this.houseInfo.aptName = house.aptName;
      if (house.streetName != null) {
        this.houseInfo.address =
          house.sidoName +
          " " +
          house.gugunName +
          house.dongName +
          " " +
          house.streetName +
          " " +
          house.jibun;
      } else {
        this.houseInfo.address =
          house.sidoName +
          " " +
          house.gugunName +
          " " +
          house.dongName +
          " " +
          house.jibun;
      }
      this.houseInfo.floor = house.floor;
      this.houseInfo.buildYear = house.buildYear;

      latLngByAddress(address, (response) => {
        var lng = response.data.documents[0].x;
        var lat = response.data.documents[0].y;

        // 역 정보
        var params = {
          category_group_code: "SW8",
          y: lat,
          x: lng,
          radius: 1000,
        };

        detailInfo(params, (response) => {
          if (response.data.documents.length > 0) {
            console.log(response.data.documents[0].place_name);
            this.detailInfo.stationName = response.data.documents[0].place_name;
            this.detailInfo.stationCnt = response.data.documents.length;
          } else {
            this.detailInfo.stationName =
              "반경 1km 내에 지하철역이 존재하지 않습니다.";
            this.detailInfo.stationCnt = 0;
          }

          // 학교 정보
          params = {
            category_group_code: "SC4",
            y: lat,
            x: lng,
            radius: 1000,
          };
          detailInfo(params, (response) => {
            if (response.data.documents.length > 0) {
              this.detailInfo.schoolCnt = response.data.documents.length;
            } else {
              this.detailInfo.schoolCnt = 0;
            }
            // 편의점 정보
            params = {
              category_group_code: "CS2",
              y: lat,
              x: lng,
              radius: 1000,
            };
            detailInfo(params, (response) => {
              if (response.data.documents.length > 0) {
                this.detailInfo.csCnt = response.data.documents.length;
              } else {
                this.detailInfo.csCnt = 0;
              }

              //  병원 정보
              params = {
                category_group_code: "HP8",
                y: lat,
                x: lng,
                radius: 1000,
              };
              detailInfo(params, (response) => {
                if (response.data.documents.length > 0) {
                  this.detailInfo.hospitalCnt = response.data.documents.length;
                } else {
                  this.detailInfo.hospitalCnt = 0;
                }

                // 은행 정보
                params = {
                  category_group_code: "BK9",
                  y: lat,
                  x: lng,
                  radius: 1000,
                };
                detailInfo(params, (response) => {
                  if (response.data.documents.length > 0) {
                    this.detailInfo.bankCnt = response.data.documents.length;
                  } else {
                    this.detailInfo.bankCnt = 0;
                  }
                });
              });
            });
          });
        });

        // 편의점 정보
      });

      this.showModal = true;
    },
    getHouseList(house) {
      this.SET_IS_LOADING(false);
      this.SET_IS_DONG(true);
      this.dongName = house.dongName;
      if (house.gugunCode) {
        this.getHouseListByDong({ dongName: house.dongName, page: 1 });
      }
      this.SET_KEYWORD(house.sidoName + " " + house.gugunName + house.dongName);
    },
  },
};
</script>
<style>
.info-container {
  width: 100%;
}

.area {
  font-weight: 600;
  font-size: 30px;
  letter-spacing: -2px;
}

.info-table {
  font-size: 17px;
  letter-spacing: -1px;
}

tr {
  height: 50px;
}

.desc {
  letter-spacing: -1px;
}

td {
  transition: 0.3s;
}
td:hover {
  background-color: mintcream;
}

th {
  font-weight: 600;
}
</style>

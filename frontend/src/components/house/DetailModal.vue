<template>
  <div>
    <b-modal size="lg" id="my-modal" :title="title">
      <div class="modal-body">
        <div class="content">
          <table class="table table-bordered first_table">
            <colgroup>
              <col width="100" />
              <col width="*" />
            </colgroup>
            <tbody>
              <tr>
                <th class="text-center">상세 주소</th>
                <td>{{ houseInfo.address }}</td>
              </tr>
              <tr v-if="houseInfo.floor">
                <th class="text-center">층수</th>
                <td>{{ houseInfo.floor }}층</td>
              </tr>
              <tr>
                <th class="text-center">건축연도</th>
                <td>{{ houseInfo.buildYear }}년</td>
              </tr>
              <tr>
                <th class="text-center">가까운 역</th>
                <td class="text-left" id="nStation">
                  {{ detailInfo.stationName }}
                </td>
              </tr>
            </tbody>
          </table>
          <table class="table table-bordered second_table">
            <colgroup>
              <col width="170" />
              <col width="*" />
            </colgroup>
            <tbody>
              <tr>
                <th class="text-center">근처 지하철역 수</th>
                <td class="text-left">{{ detailInfo.stationCnt }}개</td>
              </tr>
              <tr>
                <th class="text-center">근처 학교 수</th>
                <td class="text-left">{{ detailInfo.schoolCnt }}개</td>
              </tr>
              <tr>
                <th class="text-center">근처 편의점 수</th>
                <td class="text-left">{{ detailInfo.csCnt }}개</td>
              </tr>
              <tr>
                <th class="text-center">근처 병원 수</th>
                <td class="text-left">{{ detailInfo.hospitalCnt }}개</td>
              </tr>
              <tr>
                <th class="text-center">근처 은행(ATM) 수</th>
                <td class="text-left">{{ detailInfo.bankCnt }}개</td>
              </tr>
            </tbody>
          </table>
        </div>
        <div style="font-size: 12px">*반경 1km 기준</div>
      </div>
    </b-modal>
  </div>
</template>

<script>
export default {
  name: "DetailModal",
  props: {
    detailInfo: Object,
    houseInfo: Object,
  },
  data() {
    return {
      title: this.houseInfo.aptName + " 세부 정보",
    };
  },
  created() {
    console.log(this.houseInfo);
  },
  computed: {},
  methods: {},
};
</script>
<style scoped>
.content {
  display: flex;
  margin: 10px;
}

th {
  font-weight: 600;
}
</style>

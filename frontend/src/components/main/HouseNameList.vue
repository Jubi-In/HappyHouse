<template>
  <div class="grid">
    <div
      class="mb-4 d-flex justify-content-center"
      id="aptresult"
      v-for="(result, index) in results"
      :key="index"
    >
      {{ result }}
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseNameList",
  data() {
    return {};
  },
  computed: {
    ...mapState(houseStore, ["results"]),
  },
};
</script>

<style>
.grid {
  display: grid;
  width: 100%;
  grid-template-columns: repeat(auto-fit, minmax(200px, auto));
}
</style>

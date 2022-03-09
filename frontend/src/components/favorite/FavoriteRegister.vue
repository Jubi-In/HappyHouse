<template>
  <div class="favorite">
    관심 지역
    <a href="#" v-if="!isRegistered" @click="registerFav()">등록</a>
    <a href="#" v-if="isRegistered" @click="deleteFav()">해제</a>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { registerFavorite, favCheck, deleteFavorite } from "@/api/favorite.js";

const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  name: "FavoriteRegister",
  data() {
    return {
      isRegistered: false,
    };
  },
  computed: {
    ...mapState(houseStore, ["sido", "gugun"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    if (this.userInfo) {
      const params = {
        user_id: this.userInfo.user_id,
        sidoCode: this.sido.sidoCode + "00000000",
        gugunCode: this.gugun.gugunCode + "00000",
      };
      favCheck(params, (response) => {
        if (response.data.favcount == 1) {
          this.isRegistered = true;
        } else {
          this.isRegistered = false;
        }
      });
    }
  },
  methods: {
    registerFav() {
      if (this.userInfo == null) {
        alert("로그인 후 이용 가능합니다.");
      } else {
        const favorite = {
          user_id: this.userInfo.user_id,
          sidoCode: this.sido.sidoCode + "00000000",
          gugunCode: this.gugun.gugunCode + "00000",
          sidoName: this.sido.sidoName,
          gugunName: this.gugun.gugunName,
        };

        registerFavorite(favorite, (response) => {
          if (response.data === "success") {
            this.isRegistered = true;
          }
        });
      }
    },
    deleteFav() {
      const params = {
        user_id: this.userInfo.user_id,
        sidoCode: this.sido.sidoCode + "00000000",
        gugunCode: this.gugun.gugunCode + "00000",
      };
      deleteFavorite(params, (response) => {
        if (response.data === "success") {
          this.isRegistered = false;
        }
      });
    },
  },
};
</script>
<style scoped>
.favorite {
  margin: 20px;
  font-size: 20px;
}
</style>

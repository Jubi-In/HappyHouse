<template>
  <div class="container fav-container">
    <h1 class="title">관심 지역 둘러보기</h1>
    <div class="desc">
      <span style="font-weight: 600">{{ userInfo.user_name }}</span> 님의 관심
      지역 목록입니다.
    </div>
    <favorite-list></favorite-list>
  </div>
</template>

<script>
import { mapState } from "vuex";
import FavoriteList from "@/components/favorite/FavoriteList.vue";

const memberStore = "memberStore";

export default {
  name: "Favorite",
  components: {
    FavoriteList,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style>
.title {
  letter-spacing: -3px;
}
.desc {
  margin: 20px;
  font-size: 20px;
  letter-spacing: -1;
}

.fav-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>

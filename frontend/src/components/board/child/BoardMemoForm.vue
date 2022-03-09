/
<template>
  <div>
    <board-memo-list></board-memo-list>
    <board-memo-write></board-memo-write>
  </div>
</template>

<script>
import BoardMemoWrite from "./BoardMemoWrite.vue";
import BoardMemoList from "./BoardMemoList.vue";
export default {
  name: "BoardMemoForm",
  components: {
    BoardMemoWrite,
    BoardMemoList,
  },
};
</script>

<style></style>

<template>
  <div class="map-container">
    <div id="map"></div>
    <div class="button-group"></div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
const houseStore = "houseStore";

export default {
  name: "KakaoMap",
  data() {
    return {
      map: null,
      markers: [],
      latlngs: [],
      infowindow: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");

      script.onload = () => kakao.maps.load(this.initMap);
      script.src = `http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_MAP_API_KEY}&libraries=services`;
      document.head.appendChild(script);
    }
  },
  methods: {
    ...mapActions(houseStore, ["getLatLngs"]),
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };
      this.map = new kakao.maps.Map(container, options);
      this.displayMarkers(this.houses);
    },
    displayMarkers(houses) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      // 주소로 lat lng 구해야 함
      const geocoder = new kakao.maps.services.Geocoder();
      let cnt = 0;
      houses.forEach((house) => {
        const address =
          house.sidoName +
          " " +
          house.gugunName +
          house.dongName +
          " " +
          house.jibun;

        geocoder.addressSearch(address, (result, status) => {
          if (status === kakao.maps.services.Status.OK) {
            cnt++;
            this.latlngs.push([result[0].y, result[0].x]);
          }

          if (cnt === houses.length) {
            if (this.markers.length > 0) {
              this.markers.forEach((marker) => marker.setMap(null));
            }

            const positions = this.latlngs.map(
              (position) => new kakao.maps.LatLng(...position)
            );
            this.markers = positions.map(
              (position) =>
                new kakao.maps.Marker({
                  map: this.map,
                  position,
                })
            );

            const bounds = positions.reduce(
              (bounds, latlng) => bounds.extend(latlng),
              new kakao.maps.LatLngBounds()
            );

            this.map.setBounds(bounds);
          }
        });
      });
    },

    displayInfoWindow() {
      if (this.infowindow && this.infowindow.getMap()) {
        //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
        this.map.setCenter(this.infowindow.getPosition());
        return;
      }

      var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

      this.infowindow = new kakao.maps.InfoWindow({
        map: this.map, // 인포윈도우가 표시될 지도
        position: iwPosition,
        content: iwContent,
        removable: iwRemoveable,
      });

      this.map.setCenter(iwPosition);
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->

<style>
#map {
  width: 100%;
  height: 500px;
  margin: 30px;
}
.map-container {
  width: 100%;
}
.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}
</style>

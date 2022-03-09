import {
  apiInstance,
  houseInstance,
  addressInstance,
  detailInstance,
} from "./index.js";

const api = apiInstance();
const house = houseInstance();
const latLng = addressInstance();
const detail = detailInstance();

function sidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

function houseList(params, success, fail) {
  house.get(``, { params: params }).then(success).catch(fail);
}

function houseNameList(params, success, fail) {
  api.get(`/search/listSearchWord`, { params }).then(success).catch(fail);
}

function houseListByName(word, success, fail) {
  api.get(`/search/searchWithName/${word}`).then(success).catch(fail);
}

function latLngByAddress(address, success, fail) {
  latLng.get(encodeURIComponent(address)).then(success).catch(fail);
}

function detailInfo(params, success, fail) {
  detail.get(``, { params: params }).then(success).catch(fail);
}

export {
  sidoList,
  gugunList,
  houseList,
  houseNameList,
  houseListByName,
  latLngByAddress,
  detailInfo,
};

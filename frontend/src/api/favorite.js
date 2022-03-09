import { apiInstance } from "./index.js";

const api = apiInstance();

function favoriteList(params, success, fail) {
  api.get(`/favorite/list`, { params: params }).then(success).catch(fail);
}

function registerFavorite(favorite, success, fail) {
  api
    .post(`/favorite/register`, JSON.stringify(favorite))
    .then(success)
    .catch(fail);
}

function favCheck(params, success, fail) {
  api.get(`/favorite/check`, { params: params }).then(success).catch(fail);
}

function deleteFavorite(params, success, fail) {
  api.delete(`/favorite`, { params: params }).then(success).catch(fail);
}

export { favoriteList, registerFavorite, favCheck, deleteFavorite };

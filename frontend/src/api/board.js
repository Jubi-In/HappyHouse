import { apiInstance } from "./index.js";
const api = apiInstance();

function writeArticle(article, whatType, success, fail) {
  api
    .post(`/board/${whatType}`, JSON.stringify(article))
    .then(success)
    .catch(fail);
}

function listArticle(param, whatType, success, fail) {
  api.get(`/board/${whatType}`, { params: param }).then(success).catch(fail);
}

function getArticle(articleno, whatType, success, fail) {
  api.get(`/board/${articleno}/${whatType}`).then(success).catch(fail);
}

function deleteArticle(articleno, whatType, success, fail) {
  api.delete(`/board/${articleno}/${whatType}`).then(success).catch(fail);
}

function modifyArticle(article, whatType, success, fail) {
  api
    .put(`/board/${whatType}`, JSON.stringify(article))
    .then(success)
    .catch(fail);
}

export { writeArticle, listArticle, getArticle, deleteArticle, modifyArticle };

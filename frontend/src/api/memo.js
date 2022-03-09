import { apiInstance } from "./index.js";
const api = apiInstance();

function writeMemo(memoInfo, whatType, success, fail) {
  api
    .post(`/memo/${whatType}`, JSON.stringify(memoInfo))
    .then(success)
    .catch(fail);
}

function listMemo(articleno, whatType, success, fail) {
  api.get(`/memo/${articleno}/${whatType}`).then(success).catch(fail);
}

function deleteMemo(memono, whatType, success, fail) {
  api.delete(`/memo/${memono}/${whatType}`).then(success).catch(fail);
}

export { writeMemo, listMemo, deleteMemo };

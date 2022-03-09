import { apiInstance } from "./index.js";

const api = apiInstance();

// 로그인해서 토큰받아오기
async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

// 토큰으로 회원정보 전체 받아오기
async function findById(user_id, success, fail) {
  // 이렇게 보내면 api에서 request.getHeader("access-token") 으로 받을 수 있음 ㄷㄷ
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${user_id}`).then(success).catch(fail);
}

// 회원정보 입력받고 회원가입 버튼 누르면 등록시키기
async function registerMember(user, success, fail) {
  await api
    .post(`/user/register`, {
      user_id: user.user_id,
      user_pw: user.user_pw,
      user_name: user.user_name,
      user_address: user.user_address,
      user_email: user.user_email,
      user_tel: user.user_tel,
    })
    .then(success)
    .catch(fail);
}

// 2021.11.21 한길 추가 :: 회원탈퇴
async function deleteMember(user_id, success, fail) {
  await api.delete(`/user/${user_id}`).then(success).catch(fail);
}

// 2021.11.23 한길 추가 :: 회원가입할때 아이디 유효한지 체크
function checkIdValid(user_id, success, fail) {
  api.get(`/user/idcheck/${user_id}`).then(success).catch(fail);
}

// 2021.11.24 한길 추가 :: 회원정보 수정
function updateMember(user, success, fail) {
  api.put(`/user`, user).then(success).catch(fail);
}

export {
  login,
  findById,
  registerMember,
  deleteMember,
  checkIdValid,
  updateMember,
};

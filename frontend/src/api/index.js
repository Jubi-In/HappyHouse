import axios from "axios";
import {
  API_BASE_URL,
  APT_DEAL_URL,
  APT_ADDRESS_URL,
  APT_INFO_URL,
} from "@/config";

// axios 객체 생성
function apiInstance() {
  const instance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}

function houseInstance() {
  const instance = axios.create({
    baseURL: APT_DEAL_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}

function addressInstance() {
  const instance = axios.create({
    baseURL: APT_ADDRESS_URL,
    headers: {
      "Content-type": "application/json",
      Authorization: "KakaoAK 5fb93deba4e9bdd15f30d0b59c25430c",
    },
  });
  return instance;
}

function detailInstance() {
  const instance = axios.create({
    baseURL: APT_INFO_URL,
    headers: {
      "Content-type": "application/json",
      Authorization: "KakaoAK 5fb93deba4e9bdd15f30d0b59c25430c",
    },
  });
  return instance;
}

export { apiInstance, houseInstance, addressInstance, detailInstance };

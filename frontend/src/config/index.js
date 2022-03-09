// 공공데이터나 우리의 DB에 접근할 수 있는 API의 주소를 모아놓는 곳

const API_BASE_URL = "http://localhost:8080";
const APT_DEAL_URL =
  "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
// 한길 memo : 쓰시는 공공데이터 URL 넣으면 됩니다.
// "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";

const APT_ADDRESS_URL =
  "https://dapi.kakao.com/v2/local/search/address.json?query=";
const APT_INFO_URL = "https://dapi.kakao.com/v2/local/search/category.json?";

export { API_BASE_URL, APT_DEAL_URL, APT_ADDRESS_URL, APT_INFO_URL };

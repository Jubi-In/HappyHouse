<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<%@ include file="/WEB-INF/views/include/nav.jsp"%>
<link rel="stylesheet" href="/css/apt.css">
<script type="text/javascript" src="js/main.js"></script>

<div class="container main-container">
	<div class="col-lg-12 text-center">
		<h1>원하는 지역을 선택하세요.</h1>
	</div>

	<div class="text-center pt-3 pb-3 mb-3">
		<div class="dropdown">
			<div class="btn-group mt-3">
				<button type="button" id="sido"
					class="btn btn-light dropdown-toggle dropdown-toggle-split"
					data-toggle="dropdown">시/도</button>
				<input type="hidden" id="sidoVal" value="0" />
				<div id="sido_div" class="dropdown-menu"></div>
			</div>

			<div class="btn-group mt-3">
				<button type="button" id="gugun"
					class="btn btn-light dropdown-toggle dropdown-toggle-split"
					data-toggle="dropdown">구/군</button>
				<input type="hidden" id="sigunguVal" value="0" />
				<div id="gugun_div" class="dropdown-menu"></div>
			</div>

			<div class="btn-group mt-3">
				<button type="button" id="dong"
					class="btn btn-light dropdown-toggle dropdown-toggle-split"
					data-toggle="dropdown">읍/면/동</button>
				<input type="hidden" id="sigunguVal" value="0" />
				<div id="dong_div" class="dropdown-menu"></div>
			</div>
		</div>
	</div>

	<div class="col-lg-12 mt-3 pt-3 text-center">
		<h1>혹은 검색하세요.</h1>
	</div>
	<div class="text-center">
		<img src="img/searching.png" alt="" id="search" class="mb-3"/>
	</div>
	<div id="searchArea">
		<form id="searchForm" class="text-left mb-3" method="post" action="">
			<div class="form-group">
				<input type="text" class="form-control text-center" id="searchbox" name="searchbox" placeholder="아파트명 검색">
				<input type="text" style="display:none;"/>
		   	</div>
	   	</form>
	   	<div class="text-primary mb-4 d-flex justify-content-center" id="aptresult"></div>
	</div>

	<div class="container mt-6">
		<div id="map"
			style="width: 100%; height: 500px; margin-top: 30px;"></div>
		<script type="text/javascript"
			src="//dapi.kakao.com/v2/maps/sdk.js?appkey=34495ef8421db8a7e0a779c719c0b067&libraries=services"></script>
		<script type="text/javascript" src="js/map.js"></script>
	</div>

	<table class="col-12 mt-5 info-table" style="display: none;">
		<thead>
			<tr>
				<th class="col-3">아파트 이름</th>
				<th class="col-3">주소</th>
				<th class="col-3">건축연도</th>
				<th class="col-3">거래금액</th>
			</tr>
		</thead>
		<tbody id="info">
		</tbody>
	</table>
	
	<div class="modal" id="stationInfo">
		<div class="modal-dialog">
			<div class="modal-content">
			
				<div class="modal-header">
					<h4 class="modal-title">세부 정보</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				
				<div class="modal-body">
					<table class="table table-bordered">
						<colgroup>
		                	<col width="200">
		                	<col width="*">
		           		</colgroup>
		           		<tbody>
		           		<tr>
		           			<th class="text-center">가장 가까운 역</th>
		           			<td class="text-left" id="nStation"></td>
		           		</tr>
		           		<tr>
		                	<th class="text-center">근처 지하철역 수</th>
		                	<td class="text-left" id="stationCnt"></td>
		            	</tr>
		           		<tr>
		           			<th class="text-center">근처 학교 수</th>
		           			<td class="text-left" id="schoolCnt"></td>
		           		</td>
		           		<tr>
		           			<th class="text-center">근처 편의점 수</th>
		           			<td class="text-left" id="csCnt"></td>
		           		</tr>
		           		</tbody>
					</table>
					<div style="font-size:12px">*반경 1km 기준</div>
				</div>
			
			</div>
		</div>
	</div>
	
</div>
</body>
</html>

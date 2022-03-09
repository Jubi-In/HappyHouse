<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%@ include file="../include/nav.jsp"%>
<script type="text/javascript" src="/js/dealinfo.js"></script>
</head>
<body>
	<div class="container info-container text-center">
		<div>
			<span class="area"></span> <span>의 검색 결과입니다.</span>
		</div>
		<div id="map" class=""
			style="width: 100%; height: 500px; margin-top: 30px"></div>
		<div class="col-12 text-center mt-5 desc">원하는 아파트와 동을 클릭하면 상세
			정보를 볼 수 있습니다.</div>

		<table class="table table-active text-center">
			<tr class="table-info">
				<td>해당 정보가 없습니다.</td>
			</tr>
		</table>

		<table class="col-12 mt-5 info-table">
			<thead>
				<tr>
					<th class="col-3">아파트 이름</th>
					<th class="col-3">동</th>
					<th class="col-3">전용면적</th>
					<th class="col-3">거래금액</th>
				</tr>
			</thead>
			<!-- 거래 내역 출력 -->
			<!-- 아파트 이름을 클릭하면 해당 아파트에 대한 거래 정보 요청, 동 이름을 클릭하면 해당 동에 대한 거래 정보 요청 -->
			<tbody id="info">
			<%-- 	<tr>
					<td class="col-3" id="apt">${hd.aptName}</td>
					<td class="col-3" id="dong">${hd.dong}</td>
					<td class="col-3">${hd.area}</td>
					<td class="col-3">${hd.rentMoney}</td>
				</tr> --%>
			</tbody>
		</table>
		<!-- pagination -->
		<ul class="pagination justify-content-center mt-5">
			<li class="page-item"><a class="page-link" href="#">Previous</a></li>
			<li class="page-item"><a class="page-link" href="#">1</a></li>
			<li class="page-item"><a class="page-link" href="#">2</a></li>
			<li class="page-item"><a class="page-link" href="#">3</a></li>
			<li class="page-item"><a class="page-link" href="#">4</a></li>
			<li class="page-item"><a class="page-link" href="#">5</a></li>
			<li class="page-item"><a class="page-link" href="#">Next</a></li>
		</ul>
	</div>
</body>
</html>

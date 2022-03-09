$(document).ready(function () {
	$(document).ready(function(){					
		$.get(root + "/map/sido"
			,function(data, status){
				$.each(data, function(index, vo) {
					$("#sido_div").append("<label class='dropdown-item sido'>"+vo.sidoName+"</label>");
					$("#sido_div").append("<input type='hidden' value='"+vo.sidoCode+"'>");
				});
			}
			, "json"
		);
	});
	$(document).on("click", ".dropdown-item.sido", function() {
		var sido = $(this).next().val();
		$("#sido").text($(this).text());
		$.get(root + "/map/gugun"
				,{sido: sido}
				,function(data, status){
					$("#gugun_div").empty();
					$.each(data, function(index, vo) {
						$("#gugun_div").append("<label class='dropdown-item gugun'>"+vo.gugunName+"</label>");
						$("#gugun_div").append("<input type='hidden' value='"+vo.gugunCode+"'>");
					});
				}
				, "json"
		);
	});
	$(document).on("click", ".dropdown-item.gugun", function() {
		var gugun = $(this).next().val();
		$("#gugun").text($(this).text());
		$.get(root + "/map/dong"
				,{gugun: gugun}
				,function(data, status){
					$("#dong_div").empty();
					$.each(data, function(index, vo) {
						$("#dong_div").append("<label class='dropdown-item dong'>"+vo.dongName+"</label>");
						$("#dong_div").append("<input type='hidden' value='"+vo.dongCode+"'>");
					});
				}
				, "json"
		);
	});
	
	$(document).on("click", ".dropdown-item.dong", function() {
		$(".info-table").css("display", "");

		var dong = $(this).next().val();
		$("#dong").text($(this).text());
		$.get(root + "/map/apt"
				,{dong: dong}
				,function(data, status){
					$("#info").empty();
					$.each(data, function(index, vo) {
						let str = `
							<tr id="view" address="${vo.sidoName} ${vo.gugunName} ${vo.dongName} ${vo.jibun}">
							<td class="col-3" >${vo.aptName}</td>
							<td class="col-3">${vo.sidoName} ${vo.gugunName} ${vo.dongName} ${vo.jibun}</td>
							<td class="col-3">${vo.buildYear}</td>
							<td class="col-3">${vo.recentPrice}</td>
							</tr>
						`;
						$("#info").append(str);
					});
					displayMarkers(data);
					//$("#map").css("display", "");
				}
				, "json"
		);
		
		var param = {
				serviceKey:'OQ+nGaT/G9grSF3JwrM109PyS6fZhbualKY8EOZCwpGIgisMcnIn1OC0etPDfsbzotOwBHtZgFJN7bm31bDKug==',
				pageNo:encodeURIComponent('1'),
				numOfRows:encodeURIComponent('100'),
				LAWD_CD:encodeURIComponent($(".dropdown-item.gugun").next().val()),
				DEAL_YMD:encodeURIComponent('202110')
		};
		$.get('http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev'
				,param
				,function(data, status){
					var items = $(data).find('item');
					var jsonArray = new Array();
					items.each(function() {
						var jsonObj	= new Object();
						jsonObj.aptCode = $(this).find('일련번호').text();
						jsonObj.aptName = $(this).find('아파트').text();
						jsonObj.dongCode = $(this).find('법정동읍면동코드').text();
						//jsonObj.dongName = $(this).find('').text();
						//jsonObj.sidoName = $(this).find('').text();
						//jsonObj.gugunName = $(this).find('').text();
						jsonObj.buildYear = $(this).find('건축년도').text();
						jsonObj.jibun = $(this).find('지번').text();
						jsonObj.recentPirce = $(this).find('거래금액').text();
							
						jsonObj = JSON.stringify(jsonObj);
						//String 형태로 파싱한 객체를 다시 json으로 변환
						jsonArray.push(JSON.parse(jsonObj));
					});
					console.log(jsonArray);
					//displayMarkers(jsonArray);
				}
				, "xml"
		);
	});
	
	// 거래 내역 클릭했을 때 근처 정보 띄우기
	$(document).on("click", "#view", function() {
		let address = $(this).attr("address");
		$("#stationInfo").modal();
		$(".modal-body td").empty();
	/*	
		for(var i=0; i<places.length; i++) {
			var placePosition = new kakao.maps.LatLng(places[i].lat, places[i].lng);
		}*/
		
		   $.ajax({
	           url:'https://dapi.kakao.com/v2/local/search/address.json?query='+encodeURIComponent(address),
	           type:'GET',
	           headers: {'Authorization' : 'KakaoAK 5fb93deba4e9bdd15f30d0b59c25430c'},
	           
	           success:function(data){
	        	   var lng = data.documents[0].x;
	        	   var lat = data.documents[0].y;
	        	   console.log(lat + ", " + lng);
	        	   $.ajax({
	        		   url:'https://dapi.kakao.com/v2/local/search/category.json?category\_group\_code=SW8&y='+lat+'&x='+lng+'&radius=1000',
	        		   type:'GET',
	        		   headers: {'Authorization' : 'KakaoAK 5fb93deba4e9bdd15f30d0b59c25430c'},
	   
	        		   success:function(data){
	        			   $("#stationCnt").append(data.documents.length+"개");	
	        			   $("#nStation").append(data.documents[0].place_name);
	        			   	
	        		   }, function(e){
	        			   console.log(e);
	        		   }
	        	   });
	        	   
	        	   $.ajax({
	        		   url:'https://dapi.kakao.com/v2/local/search/category.json?category\_group\_code=SC4&y='+lat+'&x='+lng+'&radius=1000',
	        		   type:'GET',
	        		   headers: {'Authorization' : 'KakaoAK 5fb93deba4e9bdd15f30d0b59c25430c'},
	   
	        		   success:function(data){
	        			   $("#schoolCnt").append(data.documents.length+"개");	
	        			   	
	        		   }, function(e){
	        			   console.log(e);
	        		   }
	        	   });
	        	   
	        	   $.ajax({
	        		   url:'https://dapi.kakao.com/v2/local/search/category.json?category\_group\_code=CS2&y='+lat+'&x='+lng+'&radius=1000',
	        		   type:'GET',
	        		   headers: {'Authorization' : 'KakaoAK 5fb93deba4e9bdd15f30d0b59c25430c'},
	   
	        		   success:function(data){
	        			   $("#csCnt").append(data.documents.length+"개");	
	        			   	
	        		   }, function(e){
	        			   console.log(e);
	        		   }
	        	   });
	   },
	   error : function(e){
	       console.log(e);
	   }
	});
	});
	
	// 검색
	$("#searchbox").keyup(function() {
		var word = $("#searchbox").val();
		if(1 <= word.length){
			$.ajax({
				url: root+'/search/listSearchWord',
				contentType:'application/json;charset=utf-8',
	        	data: {'word': word},
	          	type: 'GET',
	          	dataType: 'json',
	          	success: function (apts) {
	          		console.log(apts);
	          		if(apts == null) {
	          			$("#aptresult").text("");
	          		} else {
	                	$("#aptresult").text(apts);
	          		}
	          	}, 
	          	error: function(request, status, error) {
	          		console.log("status : " + request.status + "\tmsg : " + error);
	          	}
			})
		}
	});
	
	$("#searchbox").keydown(function(key) {
		if (key.keyCode == 13) {
			searchAptName();
		}
	});
	
	function searchAptName() {
		if(!$("#searchbox").val()) {
			return;
		} else{
			console.log("검색중");
// 			$("#searchForm").attr("action", "${root}/search/searchWithName").submit();
			var word = $("#searchbox").val();
			$.ajax({
				url: root+'/search/searchWithName/' + word,
				contentType:'application/json;charset=utf-8',
				type: 'GET',
	          	dataType: 'json',
	          	success: function (apts) {
// 	          		애초에 word가 controller로 전달이 안되고있다 null 이 뜨는중.
// 					GET으로 바꾼뒤 전달 됨. apts 에 json 형식으로 전달되는중
	          		console.log("엔터키로")
	          		console.log(apts);
          			$("#info").empty();
          			$(".info-table").css("display", "");
					$.each(apts, function(index, vo) {
						let str = `
							<tr id="view" address="${vo.sidoName} ${vo.gugunName} ${vo.dongName} ${vo.jibun}">
							<td class="col-3" >${vo.aptName}</td>
							<td class="col-3">${vo.sidoName} ${vo.gugunName} ${vo.dongName} ${vo.jibun}</td>
							<td class="col-3">${vo.area}</td>
							<td class="col-3">${vo.recentPrice}</td>
							</tr>
						`;
						$("#info").append(str);
					});
	          	
	          				
	          		
	          	}
// 				error: function(request, status, error) {
// 	          		console.log("status : " + request.status + "\tmsg : " + error);
// 	          	}
			})
		}
	};
});

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <script type="text/javascript">
        $(document).ready(function () {
            $("#mvlogin").click(function () {
//             	location.href = "${root}/user?act=mvlogin"
            	location.href = "${root}/user/login"
            });
            $("#mvregister").click(function () {
            	location.href = "${root}/user/register"
            });
            $("#logout").click(function () {
            	location.href = "${root}/user/logout"
            });
            $("#mvupdate").click(function () {
            	location.href = "${root}/user/update"
            });
            $("#management").click(function () {
            	location.href = "${root}/user/list"
            });
        });
    </script>

<nav class="navbar navbar-expand-sm mt-4">
	<a href="${root}/"> <img class="ml-5" src="${root}/img/logo.png"
		alt="HappyHouse" />
	</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#collapsibleNavbar">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse justify-content-end"
		id="collapsibalNavbar">
		<ul class="navbar-nav">
			<li class="nav-item mr-5">공지사항</li>
			<li class="nav-item mr-5">관심 지역 설정</li>
			<li class="nav-item mr-5">관심 지역 둘러보기</li>
			<c:if test="${empty userinfo}">
				<li id="mvlogin" class="nav-item mr-5">로그인</li>
				<li id="mvregister" class="nav-item mr-5">회원가입</li>
			</c:if>
			<c:if test="${!empty userinfo}">
				<li id="logout" class="nav-item mr-5">로그아웃</li>
				<c:if test="${userinfo.user_id ne 'admin'}">
					<li id="mvupdate" class="nav-item mr-5">내정보 수정</li>
				</c:if>
				<c:if test="${userinfo.user_id eq 'admin'}">
					<li id="management" class="nav-item mr-5">회원관리</li>
				</c:if>
			</c:if>		
<!-- 			<li class="nav-item mr-5"><img src="img/search.png" alt="" id="search"/></li> -->
		</ul>
	</div>
</nav>
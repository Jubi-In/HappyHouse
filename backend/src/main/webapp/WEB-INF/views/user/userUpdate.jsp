<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<%@ include file="/WEB-INF/views/include/nav.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#updateBtn").click(function () {
            	console.log("업데이트를 눌렀나요?")
                if (!$("#user_name").val()) {
                    alert("이름 입력!!!");
                    return;
                } 
                else if ($("#user_pw_old").val() != ${userinfo.user_pw}) {
                    alert("기존 비밀번호가 틀립니다.");
                    console.log("기존비번틀림");
                    return;
                }
                else if (!$("#user_pw").val()) {
                    alert("새 비밀번호 입력!!!");
                    console.log("새비번입력");
                    return;
                } 
                else if ($("#user_pw").val() != $("#pwdcheck").val()) {
                    alert("새 비밀번호 재입력이 틀렸습니다.");
                    console.log("새비번재입력틀림");
                    return;
                } 
                else if (!$("#emailid").val()) {
                    alert("이메일 입력 바랍니다.");
                    console.log("이메일입력");
                    return;
                } 
                else if (!$("#tel2").val() || !$("#tel3").val()) {
                    alert("전화번호를 정확히 입력 바랍니다.");
                    console.log("전화번호누락");
                    return;
                } 
                else if (!$("#address").val()) {
                    alert("주소를 입력 바랍니다.");
                    console.log("주소누락");
                    return;
                } 
                else {
                	$("#user_id").val();
                	$("#user_email").val($("#emailid").val() + "@" + $("#emaildomain").val());
                	$("#user_tel").val($("#tel1").val() + "-" + $("#tel2").val() + "-" + $("#tel3").val());
                	$("#user_address").val($("#address").val());
                    $("#memberform").attr("action", "${root}/user/update").submit();
                }
            });
            
            $("#deleteBtn").click(function () {
            	console.log("회원탈퇴를 눌렀나요?")
	        	var confirm = window.confirm("정말 탈퇴하시겠습니까?");
	           	if (confirm) {
	            	location.href = "${root}/user/delete?userid=${userinfo.user_id}"
	           	}
        	})
        });
    </script>

<c:if test="${!empty userinfo }">
    <div class="container text-center mt-3">
        <div class="col-lg-8 mx-auto">
            <h2 class="p-3 mb-3 shadow bg-light"><mark class="orange">회원정보 수정</mark></h2>
            <form id="memberform" class="text-left mb-3" method="post" action="">
<!--                 <input type="hidden" name="act" id="act" value="update"> -->
			<input type="hidden" id="user_email", name="user_email">
			<input type="hidden" id="user_tel", name="user_tel">
			<input type="hidden" id="user_address", name="user_address">
			<input type="hidden" id="user_id", name="user_id">
                <div class="form-group">
                    <label for="username">이름</label>
                    <input type="text" class="form-control" id="user_name" name="user_name" value="${userinfo.user_name }">
                </div>
                <div class="form-group">
                    <label for="userid">아이디</label>
                    <input type="text" class="form-control" id="user_ids" name="user_ids" value="${userinfo.user_id }" disabled>
                    <div id="idresult" class="mt-1"></div>
                </div>
                <div class="form-group">
                    <label for="userpwdold">기존 비밀번호 입력</label>
                    <input type="password" class="form-control" id="user_pw_old" name="user_pw_old">
                </div>
                <div class="form-group">
                    <label for="userpwd">새 비밀번호 입력</label>
                    <input type="password" class="form-control" id="user_pw" name="user_pw">
                </div>
                <div class="form-group">
                    <label for="pwdcheck">새 비밀번호 재입력</label>
                    <input type="password" class="form-control" id="pwdcheck" name="pwdcheck" placeholder="비밀번호재입력...">
                </div>
                <div class="form-group">
                    <label for="emailid">이메일</label><br>
                    <div id="email" class="custom-control-inline">
                    	<c:set var="mail1" value="${userinfo.user_email}"/>
                    	<c:set var="mail2" value="${fn:split(mail1,'@')[0]}"/>
                    	<c:set var="mail3" value="${fn:split(mail1,'@')[1]}"/>
                        <input type="text" class="form-control" id="emailid" name="emailid" placeholder="이메일아이디입력..."
                            size="25" value="${mail2}"> @
                        <select class="form-control" id="emaildomain" name="emaildomain">
                            <option value="ssafy.com" <c:if test="${mail3 eq 'ssafy.com'}">selected</c:if>>싸피</option>
                            <option value="naver.com" <c:if test="${mail3 eq 'naver.com'}">selected</c:if>>네이버</option>
                            <option value="kakao.com" <c:if test="${mail3 eq 'kakao.com'}">selected</c:if>>카카오</option>
                            <option value="google.com" <c:if test="${mail3 eq 'google.com'}">selected</c:if>>구글</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="tel1">전화번호</label>
                    <div id="tel" class="custom-control-inline">
                        <c:set var="tele" value="${userinfo.user_tel}"/>
                        <c:set var="tele1" value="${fn:split(tele,'-')[0]}"/>
                        <select class="form-control" id="tel1" name="tel1">
                        	<option value="010" <c:if test="${tele1 eq '010'}">selected</c:if>>010</option> 
                            <option value="02" <c:if test="${tele1 eq '02'}">selected</c:if>>02</option>
                            <option value="031" <c:if test="${tele1 eq '031'}">selected</c:if>>031</option>
                            <option value="032" <c:if test="${tele1 eq '032'}">selected</c:if>>032</option>
                            <option value="041" <c:if test="${tele1 eq '041'}">selected</c:if>>041</option>
                            <option value="051" <c:if test="${tele1 eq '051'}">selected</c:if>>051</option>
                            <option value="061" <c:if test="${tele1 eq '061'}">selected</c:if>>061</option>
                        </select> _
                        <input type="text" class="form-control" id="tel2" name="tel2" placeholder="" 
                        	value="${fn:split(tele, '-')[1]}"> _
                        <input type="text" class="form-control" id="tel3" name="tel3" placeholder=""
                        	value="${fn:split(tele, '-')[2]}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="zipcode">주소</label><br>
                    <input type="text" class="form-control" id="address" name="address" value="${userinfo.user_address }">
                </div>
                
                <div class="form-group text-center">
                    <button type="button" id="updateBtn" class="btn btn-outline-primary">수정</button>
                    <button type="button" id="deleteBtn" class="btn btn-outline-danger">회원탈퇴</button>
                </div>
            </form>
        </div>
    </div>
    </c:if>
</body>
</html>
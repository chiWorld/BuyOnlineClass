<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" pageEncoding="utf-8"%>
<%
	if(request.getAttribute("msg") != null) {
%>
	<script>alert('${msg}');</script>
<%		
	} else if(request.getAttribute("koreaITurl") != null) {
%>
	<script>location.href='${koreaITurl}';</script>
<%
	}
%>
<%
	if(session.getAttribute("loginId") == null) {
		request.setAttribute("msg", "로그인이 필요합니다.");
		request.getRequestDispatcher("/").forward(request, response);
	}
%>
<html>
<head>
	<title>메인페이지</title>
	<style>
		.koreaIt {margin-left: 850px;}
	</style>
</head>
<body>
	<div class="header">
		<table>
			<tr>
				<td><h1><b>메인페이지</b></h1></td>
				<td style="padding-left: 700px;"><b>${name}(${id})님 안녕하세요.</b></td>
				<td><a href="logoutAction"><button name="logout" value="logout">로그아웃</button></a></td>
			</tr>
			<tr>
				<td></td>
				<td style="padding-left: 700px;"><b>포인트 : ${point}</b></td>
				<td></td>
			</tr>
		</table>
	</div>
	<div>
		<h4><b>구입할 컨텐츠를 선택하세요.</b></h4>
	</div>
	<div class="clickPhoto">
		<table>
			<tr>
				<td><a href="order?className=intro350"><img src="resources/img/Intro_350_408.png"></a></td>
				<td><a href="order?className=java350"><img src="resources/img/Java_350_408.png"></a></td>
				<td><a href="order?className=cpp350"><img src="resources/img/Cpp_350_408.png"></a></td>
			</tr>
		</table>
		<div class="koreaIT">
			<a href="point1000"><img src="resources/img/korea_it.png"></a>
		</div>
	</div>
</body>
</html>

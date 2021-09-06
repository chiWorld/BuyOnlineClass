<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" pageEncoding="utf-8"%>
<%@ page import="com.mm.dto.*" %>
<%@ page import="java.util.*" %>
<%
	if(request.getAttribute("msg") != null) {
%>
	<script>alert('${msg}');</script>
<%
	}
%>
<html>
<head>
	<title>회원관리</title>
	<style>
		body>div {width: 525px; margin: 20px auto; padding: 20px; border: 1px solid #afafaf;}
		body>div table {border-collapse: collapse;}
		body>div table th, body>div table td {width: 100px; border: 1px solid #afafaf; text-align: center;}
		tr > td input {width: 100%;}
		.fr {float: rigth;}
		.small{font-size: 0.5em;}
		.gray {color: #9f9f9f;}
	</style>
</head>
<body>
	<div>
		<h1>
			<b>회원관리</b>
			<span class="fr small gray">
				<a href="<%=application.getContextPath()%>/">
					<button>로그인</button>
				</a>
			</span>
		</h1>
		<br/>
		<table>
			<tr>
				<td><b>ID</b></td>
				<td><b>PW</b></td>
				<td><b>Name</b></td>
				<td><b>Point</b></td>
				<td><b>수정</b></td>
				<td><b>삭제</b></td>
			</tr>
			<c:forEach var="vo" items="${member}">
			<tr>
				<td><c:out value="${vo.id}"/></td>
				<td>${vo.pw}</td>
				<td>${vo.name}</td>
				<td>${vo.point}</td>
				<td><a href="adminModify?id=${vo.id}"><button>수정</button></a></td>
				<td><a href="adminDelete?id=${vo.id}"><button>삭제</button></a></td>
			</tr>
			</c:forEach>
		</table>
		<br/>
		<br/>
		<h1><b>스케줄러관리</b></h1>
		<a href="adminScheStart"><button>스케줄러(20초마다 포인트 1 증가) 실행 시작</button></a>
		<a href="adminScheEnd"><button>스케줄러 실행 종료</button></a>
	</div>
</body>
</html>

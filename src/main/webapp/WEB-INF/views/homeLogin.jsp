<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="utf-8"%>
<%
	if(request.getAttribute("msg") != null) {
%>
	<script>alert('${msg}');</script>
<%		
	}
%>
<html>
<head>
	<title>로그인</title>
	<style>
		body>div {width: 225px; margin: 200px auto; padding: 20px; border: 1px solid #afafaf;}
		tr > td imput {width: 100%;}
	</style>
</head>
<body>
	<!-- localhost:9090/ex0811/loginAction -->
	<h1><b>로그인</b></h1>
	<form action="loginAction" method="post">
		<table>
			<tr>
				<td>ID : </td>
				<td><input type="text" name="id"/></td>
			</tr>
			<tr>
				<td>PW : </td>
				<td><input type="text" name="pw"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="로그인"/><br/></td>
			</tr>
			<tr>
				<td colspan="2"><a href="register"><button>회원가입</button></a></td>
			</tr>
		</table>
	</form>
</body>
</html>

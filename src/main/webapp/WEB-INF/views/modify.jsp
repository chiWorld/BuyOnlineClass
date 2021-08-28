<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="utf-8"%>
<%@ page import = "com.mm.dto.*" %>
<html>
<head>
	<title>회원관리-수정관리자</title>
	<style>
		body>div {width: 525px; margin: 200px auto; padding: 20px; border: 1px solid #afafaf;}
	</style>
</head>
<body>
	<h1>회원관리-수정관리자</h1>
	<br/>
	<form action="adminModifyAction" method="post">
		<table>
			<tr>
				<td><b>ID :</b></td>
				<td><input type="text" name="id" value="${info.id}" readonly="readonly"/></td>
			</tr>
			<tr>
				<td><b>PW : </b></td>
				<td><input type="text" name="pw" value="${info.pw}"/></td>
			</tr>
			<tr>
				<td><b>Name : </b></td>
				<td><input type="text" name="name" value="${info.name}"/></td>
			</tr>
			<tr>
				<td><b>point : </b></td>
				<td><input type="text" name="point" value="${info.point}"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="제출" style="width: 100%;"/></td>
			</tr>
		</table>
	</form>
</body>
</html>

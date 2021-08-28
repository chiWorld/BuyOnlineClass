<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="utf-8"%>
<html>
<head>
	<title>회원가입</title>
	<style>
		body>div {width: 250px; margin: 200px auto; padding:20px; border: 1px solid #afafaf;}
		tr > td input {width: 100%;}
	</style>
</head>
<body>
	<h1>회원가입</h1>
	<!-- localhost:9000/ex0801/registerAction -->
	<form action="registerAction" method="post">
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
				<td>Name : </td>
				<td><input type="text" name="name"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="회원가입"/></td>
			</tr>
		</table>
	</form>
</body>
</html>

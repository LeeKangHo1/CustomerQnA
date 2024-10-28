<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 로그인</title>
</head>
<body>
	<form method="post">
		<label>아이디 <input type="text" name="id"></label>
		<label>패스워드 <input type="password" name="password"></label>
		<input type="submit">
	</form>
		<c:if test="${not empty error}">
			<p class="error">${error}</p>
		</c:if>
</body>
</html>
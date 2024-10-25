<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>비밀번호 확인</title>
</head>
<body>
    <form method="post">
        <label>비밀번호 확인<input type="password" name="password"></label>
        <input type="submit" value="확인">
        <c:if test="${not empty error}">
            <p style="color:red;">${error}</p>
        </c:if>
    </form>
</body>
</html>



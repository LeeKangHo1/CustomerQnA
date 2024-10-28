<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비밀번호 확인</title>
    <link rel="stylesheet" type="text/css" href="/css/checkPass.css">
</head>
<body>
    <div class="password-confirm">
        <form method="post">
            <label>비밀번호 확인<input type="password" name="password" required></label>
            <input type="submit" value="확인">
            <c:if test="${not empty error}">
                <p class="error">${error}</p>
            </c:if>
        </form>
    </div>
</body>
</html>




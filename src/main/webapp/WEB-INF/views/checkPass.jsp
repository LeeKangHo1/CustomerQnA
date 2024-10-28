<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정/삭제를 위한 비밀번호 확인</title>
</head>
<body>
	<c:choose>
		<c:when test="${actionType == 'delete'}">
			<p>정말 이 게시글을 삭제하시겠습니까?</p>
			<p>삭제하시려면 비밀번호를 입력하세요</p>
			<form action="/qna/${article_id}/delete" method="post">
			<input type="password" name="password" required />
				<button type="submit">확인</button>
			</form>
		</c:when>
		<c:when test="${actionType == 'edit'}">
			<p>게시글을 수정하려면 비밀번호를 입력하세요.</p>
			<form action="/qna/${article_id}/edit" method="post">
			<input type="password" name="password" required />
				<button type="submit">확인</button>
			</form>
		</c:when>
	</c:choose>
		<c:if test="${not empty error}">
            <p style="color:red;">${error}</p>
        </c:if>
		
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객 센터</title>
</head>
<body>
	<h1>게시글 목록 구현</h1>
		<ul>
			<c:forEach var="qna" items="${ qnaList }">
				<c:url var="link" value="${ '/qna/' += qna.article_id }"></c:url>
				<li><a href="${ link }">${ qna.title }</a></li>
			</c:forEach>
		</ul>
		<div>
			<a href="/qna/form">게시글 작성</a>
		</div>
</body>
</html>
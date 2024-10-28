<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객 센터</title>
</head>
<body>
	<h1>게시글 목록 구현</h1>
	
	 <div>
        <a href="<c:url value='/qna?page=${ currentPage }&sortBy=article_id'/>">최근 게시글 순</a>
         |
        <a href="<c:url value='/qna?page=${ currentPage }&sortBy=views'/>">조회수 순</a>
    </div>
	
	<ul>
		<c:forEach var="qna" items="${qnaList}">
			<li><a href="<c:url value='/qna/${qna.article_id}' />">${qna.title}</a></li>
		</c:forEach>
	</ul>

	<div>
		<a href="/qna/form">게시글 작성</a>
	</div>
	
	<nav>
		<c:if test="${currentPage > 0}">
			<c:url var="previousPage" value="/qna?page=${currentPage - 1}"></c:url>
		</c:if>
		<c:if test="${qnaList.size() == 20}">
			<c:url var="nextPage" value="/qna?page=${currentPage + 1}"></c:url>
		</c:if>
		<a href="${ previousPage }">Previous</a>
		 |
		<a href="${ nextPage }">Next</a>
	</nav>

</body>
</html>
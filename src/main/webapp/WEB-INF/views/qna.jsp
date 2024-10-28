<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객 센터</title>
<link rel="stylesheet" type="text/css" href="/css/qna.css">
</head>
<body>
    <h1 class="title">Q & A 게시판</h1>
    <div class="search-form">
        <form method="get" action="/qna/search">
            <select name="searchType" id="searchType">
                <option value="searchTitle">제목</option>
                <option value="searchContent">내용</option>
            </select>
            <input type="text" name="keyword" placeholder="검색어 입력">
            <input type="submit" value="검색">
        </form>
    </div>
    <div class="sort-options">
        <a href="<c:url value='/qna?page=${ currentPage }&sortBy=article_id'/>">최근 게시글 순</a>
        |
        <a href="<c:url value='/qna?page=${ currentPage }&sortBy=views'/>">조회수 순</a>
    </div>
    <div class="write-post">
        <a href="/qna/form">게시글 작성</a>
    </div>
    <nav class="pagination">
        <c:if test="${currentPage > 0}">
            <c:url var="previousPage" value="/qna?page=${currentPage - 1}"></c:url>
        </c:if>
        <c:if test="${qnaList.size() == 10}">
            <c:url var="nextPage" value="/qna?page=${currentPage + 1}"></c:url>
        </c:if>
        <a href="${ previousPage }">Previous</a>
        <label>| ${currentPage} |</label>
        <a href="${ nextPage }">Next</a>
    </nav>
    <ul class="qna-list">
        <c:forEach var="qna" items="${qnaList}">
            <li>제목: <a href="<c:url value='/qna/${qna.article_id}' />">${qna.title}</a></li>
        </c:forEach>
    </ul>
    <div class="admin">
        <c:if test="${ empty id }">
            <button id="login">관리자 로그인</button>
        </c:if>
        <c:if test="${ not empty id }">
            <button onclick="logoutWithAlert()">로그아웃</button>
        </c:if>
    </div>
</body>
	<script>
	let loginBtn = document.getElementById('login');
	loginBtn.addEventListener("click", (e) => {
		window.location.href='/qna/login'
	}); 
	
	function logoutWithAlert() {
        window.location.href = "/qna/logout";
        alert("로그아웃되었습니다.");
    }
	</script>
</html>

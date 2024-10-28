<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 조회 페이지</title>
    <link rel="stylesheet" type="text/css" href="/css/article.css">
</head>
<body>
    <article id="qna-article">
        <header>
            <div class="title-block">
                <h3 class="title">제목 : ${qna.title}</h3>
            </div>
            <div class="meta-info">
                <h4 class="author">작성자 : ${qna.username}</h4>
                <p class="views">조회수 : ${qna.views}</p>
            </div>
        </header>
        <section class="content">
            <h4>내용</h4>
            <p>${qna.content}</p>
        </section>
        <footer>
            <p class="created-at">작성일 : ${qna.created_at}</p>
        </footer>
    </article>
    <c:if test="${ not empty qnaComment }">
    <div class="comment-block">
        <span class="comment-content">${qnaComment.comment}</span>
    </div>
    </c:if>
	<c:if test="${ not empty id }">
		<div class="comments-section">
			<form method="post" action="/qna/${qna.article_id}/comment"
				class="comment-form">
				<label>내용</label> <input type="text" name="comment"
					class="input-content" required>
				<button type="submit">댓글 작성</button>
			</form>
		</div>
	</c:if>
	<div class="link-buttons">
        <button id="btn-link">게시글 링크 보기</button>
        <dialog id="link">
            <span>링크: </span><span id="linkUrl">http://localhost:8080/qna/${qna.article_id}</span>
            <button id="copy-btn">링크 복사</button>
            <button id="close-btn">닫기</button>
        </dialog>
    </div>
    <div class="actions">
        <a href="/qna/${qna.article_id}/edit">게시글 수정하기</a>
        <a href="/qna/${qna.article_id}/delete">게시글 삭제하기</a>
        <a href="/qna">게시글 목록으로</a>
    </div>
    <script src="/js/articleJs.js"></script>
</body>
</html>




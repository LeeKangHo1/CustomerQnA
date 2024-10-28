<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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


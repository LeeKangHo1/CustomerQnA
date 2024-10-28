<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 조회 페이지</title>
</head>
<body>
	<article>
		<header>
			<h3>제목 : ${ qna.title }</h3>
			<h4>작성자 : ${ qna.username }</h4>
			<p>조회수 : ${ qna.views }</p>
		</header>
		<p>내용</p>
		<p>${ qna.content }
		<footer>
<%-- 			<p>비밀글 : ${ qna.is_secure }</p> --%>
			<p>작성일 : ${ qna.created_at }</p>
		</footer>
	</article>
		<div>
			<a href="/qna/${qna.article_id}/edit">게시글 수정하기</a>
		</div>
		<div>
			<a href="/qna/${qna.article_id}/delete">게시글 삭제하기</a>
		</div>
		<div>
			<a href="/qna">게시글 목록으로</a>
		</div>
</body>
</html>
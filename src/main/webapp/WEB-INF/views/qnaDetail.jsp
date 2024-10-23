<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 디테일</title>
</head>
<body>
		<p>제목: ${ qna.title }</p>
		<p>내용: ${ qna.content }</p>
		<p>유저이름: ${ qna.username }</p>
		<p>조회수: ${ qna.views }</p>
		<p><a href="/qna">게시글 목록으로 돌아가기</a>
</body>
</html>
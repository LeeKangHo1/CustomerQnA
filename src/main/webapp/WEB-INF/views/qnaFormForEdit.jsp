<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정 form</title>
</head>
<body>
	<form method="post" action="/qna/${qna.article_id}/editForm">
		<div>
			<label>제목 수정<input type="text" name="title"
				value="${ qna.title }"></label>
		</div>
		<div>
			<label>내용 수정<input type="text" name="content"
				value="${ qna.content }"></label>
		</div>
		<div>
			<label>유저네임 수정<input type="text" name="username"
				value="${ qna.username }"></label>
		</div>
		<div>
			<label>비밀번호 수정<input type="password" name="password"></label>
		</div>
		<div>
			<label>비밀글 여부<input type="checkbox" name="is_secure"
				${ qna.is_secure ? 'checked' : '' }>
			</label>
		</div>

		<input type="submit">
	</form>
</body>
</html>
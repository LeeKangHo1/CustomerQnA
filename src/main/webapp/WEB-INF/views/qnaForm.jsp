<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성 form</title>
</head>
<body>
	<form method="post">
		<label>제목<input type="text" name="title"></label>
		<label>내용<input type="text" name="content"></label>
		<label>닉네임<input type="text" name="username"></label>
		<label>비밀번호<input type="password" name="password"></label>
		<label>비밀글여부<input type="number" name="is_secure" min="0" max="1"></label>
		<input type="submit">
	</form>
</body>
</html>
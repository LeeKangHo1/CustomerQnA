<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 수정 form</title>
    <link rel="stylesheet" type="text/css" href="/css/qnaForm.css">
</head>
<body>
    <div class="form-container">
        <form method="post" action="/qna/${qna.article_id}/editForm">
            <div class="form-group">
                <label for="title">제목 수정</label>
                <input type="text" id="title" name="title" value="${qna.title}" required>
            </div>
            <div class="form-group">
                <label for="content">내용 수정</label>
                <input type="text" id="content" name="content" value="${qna.content}" required>
            </div>
            <div class="form-group">
                <label for="username">유저네임 수정</label>
                <input type="text" id="username" name="username" value="${qna.username}" required>
            </div>
            <div class="form-group">
                <label for="password">비밀번호 수정</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="is_secure">비밀글 여부</label>
                <input type="checkbox" id="is_secure" name="is_secure" ${qna.is_secure ? 'checked' : ''}>
            </div>
            <input type="submit" value="수정">
        </form>
    </div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 작성 form</title>
    <link rel="stylesheet" type="text/css" href="/css/qnaForm.css">
</head>
<body>
    <div class="form-container">
        <form method="post">
            <div class="form-group">
                <label for="title">제목</label>
                <input type="text" id="title" name="title" required>
            </div>
            <div class="form-group">
                <label for="content">내용</label>
                <input type="text" id="content" name="content" required>
            </div>
            <div class="form-group">
                <label for="username">닉네임</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">비밀번호</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="is_secure">비밀글여부</label>
                <input type="checkbox" id="is_secure" name="is_secure">
            </div>
            <input type="submit" value="작성">
        </form>
    </div>
</body>
</html>

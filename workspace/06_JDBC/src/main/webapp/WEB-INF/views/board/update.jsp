<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>게시물 수정 화면</h1>
	<form action="/ex06/board/updateBoard.do" method="post">
		<input type="text" name="title" value="${board.title}">
		<input type="text" name="content" value="${board.content}">
		<input type="hidden" name="no" value="${board.no}">
		<button>수정 완료</button>
		<input type="button" value="목록" onclick="location.href='/ex06/board/selectBoardList.do'">
	</form>

</body>
</html>
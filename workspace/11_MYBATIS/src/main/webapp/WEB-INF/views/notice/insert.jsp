<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
</head>
<body>

	<h1>공지사항 작성 화면</h1>
	<form action="/ex11/notice/insertNotice" method="post"> <br>
		<input type="text" id="title" name="title" placeholder="제목" style="width:227px"><br>
		<textarea id="content" name="content" placeholder="내용" rows="2" cols="30"></textarea><br>
		<button>작성 완료</button> &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
		<input type="button" value="목록" onclick="location.href='/ex11/notice/selectNoticeList'">
	</form>
</body>
</html>
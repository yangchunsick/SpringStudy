<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>회원 아이디 : ${member1.id}</h1> <!-- 실제로 동작하는 건 config에 있는 get id -->
	<h1>회원 아이디 : ${member1.pw}</h1> <!-- 실제로 동작하는 건 config에 있는 get pw -->
	<h1>안녕 세상아 !</h1>
	
	<a href="memberView.do">회원 정보 보기</a>
	<br>
	<a href="boardView.do">게시글 보기</a>
</body>
</html>
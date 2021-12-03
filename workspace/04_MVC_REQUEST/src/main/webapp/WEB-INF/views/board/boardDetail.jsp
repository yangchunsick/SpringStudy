<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>게시판 정보</h1>
	<h3>게시글 번호 : ${board.no}</h3>
	<h3>제목 : ${board.title}</h3>
	<h3>내용 : ${board.content}</h3>
	<a href="/ex04">index로 돌아가기</a> <!-- @GetMapping("/") 매핑과 연결됨. -->	
</body>
</html>
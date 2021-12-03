<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>회원 정보</h1>
	<h3>회원번호 : ${member.idx}</h3>
	<h3>회원 아이디 : ${member.id}</h3>
	<h3>회원 이름 : ${member.name}</h3>
	
	<br>
	
	<a href="/ex04">index로 돌아가기</a> <!-- @GetMapping("/") 매핑과 연결됨. -->
	<br>
	<a href="/ex04/index.do">index로 돌아가기</a> <!-- @GetMapping("index.do") 매핑과 연결됨. -->
	
</body>
</html>
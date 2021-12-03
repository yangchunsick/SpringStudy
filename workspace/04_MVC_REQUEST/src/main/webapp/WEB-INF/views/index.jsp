<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>안녕 세상아..!</h1>

	<!--
		member로 시작하는 맵핑은 "member/"로 하겠다..!
		
	-->
	<!-- 파라미터 넘기는 방법 -->
	<a href="member/v1.do">회원 1</a><br>
		<!-- ? 전 까지가 맵핑이고 그 뒤에가 파라미터 값들임 !! -->
	<a href="member/v2.do?idx=1&id=user&name=chun">회원 2</a><br>
	<a href="member/v3.do?idx=1&id=user&name=chun">회원 3</a><br>
	<a href="member/v4.do">회원 4</a><br>
	<a href="member/v5.do?idx=1&id=user&name=chun">회원 5</a><br>
	<a href="member/v6.do?idx=1&id=user&name=chun">회원 6</a><br>
	<hr>
	
	<!-- ModelAndView 클래스 사용 방법 -->
	<a href="board/v1.do">게시판1</a><br>
	<a href="board/v2.do">게시판2</a><br>
	<a href="board/v3.do?no=10&title=공지&content=내용">게시판3</a><br>
	<hr>
	
	
	<!-- form 데이터 처리 법 -->
	<form action="product/v1.do" method="get">
		<input type="text" name="modelName" placeholder="모델명"><br>
		<input type="text" name="price" placeholder="가격"><br>
		<button>전송</button>	<!-- 일반 button 태그는 submit 이벤트를 가지고 있음 -->
		<!-- <input type="button"> input type의  -->
	</form>
	
	<hr>
	
	<form action="product/v2.do" method="post">
		<input type="text" name="modelName" placeholder="모델명"><br>
		<input type="text" name="price" placeholder="가격"><br>
		<button>전송</button>	<!-- 일반 button 태그는 submit 이벤트를 가지고 있음 -->
		<!-- <input type="button"> input 태그의 button type은 단순한 버튼임  -->
	</form>
	
	<!-- redirect 하는 방법 -->
	<a href="gallery/v1.do?page=1">갤러리1</a><br>
	<a href="gallery/v3.do?page=1">갤러리2</a><br>
	<a href="gallery/v5.do?page=1">갤러리3</a><br>

</body>
</html>
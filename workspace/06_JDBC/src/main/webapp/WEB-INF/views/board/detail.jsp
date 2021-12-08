<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>게시물 상세 보기</h1>
	<ul>
		<li>게시글 번호 : ${board.no}</li>
		<li>작성자 : ${board.writer}</li>
		<li>제목 : ${board.title}</li>
		<li>내용 : ${board.content}</li>
		<li>최초 등록일 : ${board.created}</li>
		<li>최종 수정일 : ${board.lastModified}</li>
	</ul>
	
	<form>
		<!-- 게시글을 삭제 할 때 해당 게시글의 번호를 가져가서 삭제를 작업한다 -->
		<!-- 게시글을 수정 할 때 해당 게시글의 번호와 제목을 가져가서 수정을 작업한다. -->
		<input type="hidden" name="no" value="${board.no}">
		<input type="hidden" name="title" value="${board.title}">
		<input type="button" value="수정" onclick="fnUpdateBoardForm(this.form)">
		<input type="button" value="삭제" onclick="fnDeleteBoard(this.form)">
		<input type="button" value="목록" onclick="fnSelectBoardList()">
	</form>
	<script>
		//	수정또는 삭제를 할 때 	hidden으로 숨겨 놓은 no, title을 가져감
		// 수정
		function fnUpdateBoardForm(f) {
			f.action = '/ex06/board/updateBoardForm.do';
			f.submit();
		}
		// 삭제
		function fnDeleteBoard(f) {
			f.action = '/ex06/board/deleteBoard.do';
			f.submit();
		}
		// 목록
		function fnSelectBoardList() {
			location.href="/ex06/board/selectBoardList.do";
		}
	</script>
</body>
</html>
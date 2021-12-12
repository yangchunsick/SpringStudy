<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script type="text/javascript">
 $(document).ready(function() {
	// 수정
	$('#update_btn').on('click', function() {
		if( $('#title').val() == '${gallery.title}' &&
		    $('#content').val() == '${gallery.content}' &&
		    $('#newFile').val() == ''){
			alert('수정 내용이 없습니다.');
			return;
		}
		$('#f').attr('action', '/ex12/gallery/updateGallery');
		$('#f').submit();
	});
	
	// 삭제
	$('#delete_btn').on('click', function() {
		if(confirm('해당 갤러리를 삭제 할까요 ?')){
			$('#f').attr('action', '/ex12/gallery/deleteGallery');
			$('#f').submit();			
		}
	});
	
	
});
</script>
<title>Insert title here</title>
</head>
<body>
	
	<h1>갤러리 상세 보기 화면</h1>
	
	<!-- 내용을 수정하는 form -->         <!-- multipartRequest가 필요함 -->
	<form id="f" method="post" enctype="multipart/form-data">
		
		<!-- no를 가지고 삭제, 수정  -->
		<input type="hidden" name="created" value="${gallery.created}">
		<input type="hidden" name="no" value="${gallery.no}">
		<!-- 기존에 첨부되어 있던 파일 정보도 같이 hidden으로 넘겨줘야함 -->
		<input type="hidden" name="origin" value="${gallery.origin}">
		<input type="hidden" name="path" value="${gallery.path}">
		<input type="hidden" name="saved" value="${gallery.saved}">
		
		<input type="button" id="update_btn" value="수정">
		<input type="button" id="delete_btn" value="삭제">
		<input type="button" id="list_btn" value="목록" onclick="location.href='/ex12/gallery/selectGalleryList'"> <br>
	 	
		작성자<br>
		${gallery.writer}<br><br>
		
		작성일<br>
		${gallery.created}<br><br>
		
		수정일<br>
		${gallery.lastModified}<br><br>
		
		작성IP<br>
		${gallery.ip}<br><br>
		
		제목<br>
		<input type="text" id="title" name="title" value="${gallery.title}"><br><br>
		
		내용<br>
		<input type="text" id="content" name="content" value="${gallery.content}"><br><br>
		
		첨부 변경하기<br>
		<input type="file" id="newFile" name="newFile"><br><br>
	</form>
				
		<!-- 다운로드 받는 form -->
		<c:if test="${not empty gallery.origin}">
			기존 첨부 : ${gallery.origin}<br><br>
			<img alt="${gallery.origin}" src="/ex12/${gallery.path}/${gallery.saved}" width="500px"><br>
			<form action="/ex12/gallery/download" method="post">
				<input type="hidden" name="origin" value="${gallery.origin}"> <!-- 사용자가 파일을 첨부 했을 때의 원래 이름 -->
				<input type="hidden" name="path" value="${gallery.path}"> <!-- 파일이 있는 경로/위치 -->
				<input type="hidden" name="saved" value="${gallery.saved}"> <!-- 서버에 저장되어 있는 파일의 이름 -->
				<button>다운로드</button>															
			</form>			
		</c:if>
	
</body>
</html>
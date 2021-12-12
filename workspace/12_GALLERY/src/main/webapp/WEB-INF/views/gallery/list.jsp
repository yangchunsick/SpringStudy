<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<title>Insert title here</title>
</head>
<body>

	<h1>갤러리 목록 보긴</h1>
	<a href="/ex12/gallery/insertPage">새 갤러리 작성</a>
	<hr>
	<table border="1">
		<thead>
			<tr>
				<td>번호</td>
				<td>썸네일</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>첨부 다운로드</td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty list}">
				<tr>
					<td colspan="6">작성된 갤러리가 없습니다.</td>
				</tr>			
			</c:if>
			<c:if test="${not empty list}">
				<c:forEach var="gallery" items="${list}">
					<tr>
						<td>${gallery.no}</td>
						<td><img alt="${gallery.origin}" src="/ex12/${gallery.path}/s_${gallery.saved}"></td>
						<td><a href="/ex12/gallery/selectGalleryByNo?no=${gallery.no}">${gallery.title}</a></td>
						<td>${gallery.writer}</td>
						<td>${gallery.created}</td>
						<td>
							<c:if test="${not empty gallery.origin}">
								<form action="/ex12/gallery/download" method="post">
									<input type="text" name="origin" value="${gallery.origin}"> <!-- 사용자가 파일을 첨부 했을 때의 원래 이름 -->
									<input type="hidden" name="path" value="${gallery.path}"> <!-- 파일이 있는 경로/위치 -->
									<input type="hidden" name="saved" value="${gallery.saved}"> <!-- 서버에 저장되어 있는 파일의 이름 -->
									<button>
										<i class="fas fa-paperclip"></i>
									</button>															
								</form>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>

</body>
</html>
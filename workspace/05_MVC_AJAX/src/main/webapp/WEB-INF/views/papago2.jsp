<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<title>Insert title here</title>
<style>
	.wrap {
		display: flex;
		justify-content: center;
	}
	.left, .right {
		padding: 10px;
	}
</style>
<script>
	$(document).ready(function() {
		
		// <select>태그의 <option> change 이벤트 바꿔주는 이벤트
		$('#source').on('change', function() {
			if($(this).val() == ''){
				$('#target').val('');
				// source의 값이 ''이라면 traget의 값도 ''으로 바꿔주겠다.
			}else if($(this).val() == 'ko'){
				$('#target').val('en');
				// source의 값이 'ko'이라면 traget의 값을 'en'으로 바꿔주겠다.
			}else if($(this).val() == 'en'){
				$('#target').val('ko');
				// source의 값이 'en'이라면 traget의 값은 'ko'으로 바꿔주겠다.
			}
		});// Change Event 
		
		// 번역
		$('#btn').on('click', function(){
			// #btn을 누르면 function이 발동하는데
			$.ajax({
				url: 'papago2.do',
				// 요청 경로는 papago2.do
				type: 'post',
				// type은 숨겨서 post로
				data: JSON.stringify({
					"source" : $('#source').val(),
					"target" : $('#target').val,
					"text" : $('#text').val()
					// 보내는 데이터는 JSON의 String 형식으로
					// source, target, text의 param들을 넘겨 줄 것이다.
				}),
				contentType: 'application/json',
				// contentType은 JSON 방식으로 변환해주고
				dataType: 'json',
				// 받아오는 dataType은 json형식으로 받아온다.
				success: function() {
					
				},
				error: function() {
					
				}
			});//ajax
		});//함수
		
		
		
		
	});


</script>
</head>
<body>

	<h1>파파고 번역하기</h1>
	<div class="wrap">
		<div class="left">
			<form>
				<select id="source">
					<option value="">언어선택</option>
					<option value="ko">한국어</option>
					<option value="en">영어</option>
				</select><br>
				<textarea rows="20" cols="60" id="text" placeholder="번역할  내용 입력"></textarea>
				<input type="button" value="번역하기" id="btn">
			</form>
		</div>
		
		<div class="right">
			<form>
				<select id="target">
					<option value=""></option>
					<option value="ko">한국어</option>
					<option value="en">영어</option>
				</select>
				<textarea rows="20" cols="60" id="translated" placeholder="번역 받은 내용" readonly></textarea>
			</form>
		</div>
		
	</div>

</body>
</html>
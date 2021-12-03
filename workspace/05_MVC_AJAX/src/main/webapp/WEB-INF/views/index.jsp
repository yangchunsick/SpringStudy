<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<style type="text/css">



</style>
<script>
	// 페이지 로드
	$(document).ready(function(){
		$('#btn1').on('click', function() {fnAjax1();});
		$('#btn2').on('click', function() {fnAjax2();});
		$('#btn3').on('click', function() {fnAjax3();});
		$('#btn4').on('click', function() {fnAjax4();});
	});
	// 함수
	function fnAjax1() {
		
		$.ajax({
			url : 'member/v1.do',
			type: 'get',
			data: 'id=' + $('#id').val(), // 아이디로된 파라미터를 보낸다.
			dataType: "text",
			success: function(result){
				$('#result').empty();
				$('#result').text(result);
			},
			error: function(xhr) {
				$('#result').empty();
				$('#result').text(xhr.responseText);
			}
		});
	}// fnAjax1()
	
	function fnAjax2() {
		$.ajax({
			url: 'member/v2.do',
			type: 'get',
			data: $('#f').serialize(), // form에 있는 모든 input들의 Param들을 넘겨주는 친구
			dataType: 'json',
			success: function(member) {
					// JSON 타입으로 받아온 member는 JACKSJON에 의해서 
					// member = {"id" : "aaa", "pw" : "sss"} 와 같이 변한다.
				$('#result').empty();
				$('<ul>')
				.append( $('<li>').text(member.id))
				.append( $('<li>').text(member.pw))
				.appendTo('#result');
			},
			error: function(xhr) {
				$('#result').empty();
				$('#result').text(xhr.responseText);
			}
		});
	}// fnAjax2()
	
	function fnAjax3() {
		$.ajax({		
			url: 'member/v3.do',
			type: 'get',
			data: $('#f').serialize(),
			dataType: 'json',
			success: function (map) {
				$('#result').empty();
				$('<h1>').text(map.id).appendTo('#result');
				$('<h1>').text(map.pw).appendTo('#result');
			},
			erorr: function(xhr) {
				$('#result').empty();
				$('#result').text(xhr.responseText);
			}
		});
	} // fnAjax3()
	
	function fnAjax4() {
		$.ajax({
			url: 'member/v4.do',
			type: 'post',	// json 데이터를 본문에 포함 시켜서 전송
			data: JSON.stringify({ // 보내는 데이터가 JSON
				"id" : $('#id').val(),
				"pw" : $('#pw').val()	
			}),	// json 데이터를 서버로 보냄
			contentType: 'application/json', // 보내는 데이터 타입
			dataType : 'json',
			success: function (map) {
				$('#result').empty();
				$('<ol>')
				.append($('<li>').text(map.id))
				.append($('<li>').text(map.pw))
				.append($('<li>').text(map.addOn))
				.appendTo('#result');
			},
			error : function(xhr) {
				$('#result').empty();
				$('#result').text(xhr.responseText)
			}
		});
	}// fnAjax4
	


</script>
</head>
<body>

	<form id="f">
		<input type="text" name="id" id="id" placeholder="ID">
		<input type="password" name="pw" id="pw" placeholder="PW">
		<input type="button" value="전송 1" id="btn1">
		<input type="button" value="전송 2" id="btn2">
		<input type="button" value="전송 3" id="btn3">
		<input type="button" value="전송 4" id="btn4">
	</form>
	<div id="result"></div>
	
	<hr>
	
	<form id="f2">
		<input type="text" id="title" name="title" placeholder="제목">
		<input type="text" id="content" name="content" placeholder="내용">
		<input type="button" id="button1" value="전송1">
		<input type="button" id="button2" value="전송2">
		<input type="button" id="button3" value="전송3">
	</form>
	<div id="result2"></div>

	<script>
		$('#button1').on('click', function() {
			$.ajax({
				url: 'board/v1.do',
				type: 'get',
				data: $('#f2').serialize(),
				dataType: 'json',
				success: function(board) {
					$('#result2').empty();
					$('<h1>').text(board.title).appendTo('#result2');
					$('<p>').text(board.content).appendTo('#result2');
				},
				error: function(xhr) {
					$('#result2').empty();
					$('#result2').text(xhr.responseText);
				}
			});
		}); // button 1
		

		$('#button2').on('click', function() {
			$.ajax({
				url: 'board/v2.do',
				type: 'post',
				data: JSON.stringify({ // 보내는 데이터가 JSON
					"title" : $('#title').val(),
					"content" : $('#content').val()	
				}),	// json 데이터를 서버로 보냄
				contentType: 'application/json', // 보내는 데이터 타입
				dataType: 'json',
				success: function(board) {
					$('#result2').empty();
					$('<h1>').text(board.title).appendTo('#result2');
					$('<p>').text(board.content).appendTo('#result2');
				},
				error: function(xhr) {
					$('#result2').empty();
					$('#result2').text(xhr.responseText);
				}
			});
			
		}); // button 2
		

		$('#button3').on('click', function() {
			$.ajax({
				url : 'board/v3.do',
				type: 'post',
				data: JSON.stringify({ // 보내는 데이터가 JSON
					"title" : $('#title').val(),
					"content" : $('#content').val(),
					"hit": 0
				}),
				contentType : 'application/json; charset=UTF-8',
				dataType: 'json',
				success: function(map) {
					$('#result2').empty();
					$('<h1>').text(map.title).appendTo('#result2');
					$('<p>').text(map.content).appendTo('#result2');
					$('<p>').text(map.hit).appendTo('#result2');
				},
				error: function(xhr) {
					$('#result2').empty();
					$('#result2').text(xhr.responseText);
				}
			});
			
		});
	
	</script>
	
	<a href="openPapago.do">파파고 1</a>
	<a href="openPapago2.do">파파고 2</a>
</body>
</html>
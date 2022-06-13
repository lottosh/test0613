<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page trimDirectiveWhitespaces="true" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action="MemInsertProc.do" method="post">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id" id="id"><div id="result" style="color:red"></div></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="text" name="pw"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="phone"></td>
			</tr>
			<tr>
				<td>생일</td>
				<td><input type="text" name="birth"></td>
			</tr>
		</table>
		<input type="submit" value="회원가입">
	</form>
	
	
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script>
		$(document).ready(function(){
			$("#id").keyup(function(){
				var iddata ={
							id: $("#id").val()
						  }	// json 형태의 넘길값 형태 만들기
				//alert(JSON.stringify(data));
				// 테스트 코드 : json data를 String 타입으로변경하는 스크립트 메서드
				
				$.ajax({
					type:"post",
					url:"isIdExsit.do",
					data:JSON.stringify(iddata),
					contentType:"application/json; charset=utf8",
					dataType:"json",
					success: function(args){
						$("#result").text(args.res);
					},
					error:function(args){
						$("#result").html(args.responseText+" 에러!!");
					}
				});
				
			});
		});
	</script>
</body>
</html>
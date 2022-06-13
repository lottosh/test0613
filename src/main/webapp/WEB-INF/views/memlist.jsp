<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page trimDirectiveWhitespaces="true" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1>모든 회원정보</h1>
<table border="1">
	<tr>
		<td>번호</td>
		<td>아이디</td>
		<td>비밀번호</td>
		<td>이메일</td>
		<td>이름</td>
		<td>전화번호</td>
		<td>생일</td>
		<td>가입일</td>
		<td>레벨</td>
	</tr>
	<c:forEach var="memDto" items="${memberListAll }">
	<tr>
		<td>${memDto.idx }</td>
		<td>${memDto.id }</td>
		<td>${memDto.pw }</td>
		<td>${memDto.email }</td>
		<td>${memDto.name }</td>
		<td>${memDto.phone }</td>
		<td>${memDto.birth }</td>
		<td>${memDto.regdate }</td>
		<td>${memDto.level }</td>
	</tr>
	</c:forEach>
</table>
<input type="button" value="뒤로가기" onclick="javascript:history.back()">
</body>
</html>
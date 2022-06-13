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
	<table border="1">
			<tr>
				<td>번호</td>
				<td><input type="text" name="idx" value="${boardDto.idx }" readonly></td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id" value="${boardDto.id }" readonly></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" value="${boardDto.title }" readonly></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><input type="text" name="content" value="${boardDto.content }" readonly></td>
			</tr>
			<tr>
				<td>이미지</td>
				<td><img src="resources/upload/${boardDto.img }" style="width: 100px;height: 100px"></td>
			</tr>
	 </table>
	 
	<c:if test="${userInfo.id ne null }">
		<a href="BoardUpdate.do?idx=${boardDto.idx }">수정</a>
		<a href="BoardDelete.do?idx=${boardDto.idx }">삭제</a>
	</c:if>
	<input type="button" value="뒤로가기" onclick="javascript:history.back()">
</body>
</html>
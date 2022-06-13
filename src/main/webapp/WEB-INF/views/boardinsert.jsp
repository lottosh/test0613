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
<h1>게시판 글쓰기</h1>
<form action="BoardInsertProc.do" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id" value="${userInfo.id }" readonly></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value="${userInfo.name }" readonly></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><input type="text" name="content"></td>
			</tr>
			<tr>
				<td>파일</td>
				<td><input type="file" name="img"></td>
			</tr>
		</table>
		<input type="submit" value="글쓰기">
</form>
</body>
</html>
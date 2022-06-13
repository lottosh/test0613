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
<h1>게시판 수정</h1>
<form action="BoardUpdateProc.do" method="post" enctype="multipart/form-data">
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
				<td><input type="text" name="title" value="${boardDto.title }" ></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><input type="text" name="content" value="${boardDto.content }" ></td>
			</tr>
			<tr>
				<td>이미지</td>
				<td><input type="file" name="img" value="${boardDto.img }"></td>
			</tr>
	 </table>
	 <input type="submit" value="수정완료">
</form>	 
</body>
</html>
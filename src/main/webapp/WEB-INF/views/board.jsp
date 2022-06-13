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
<h1>게시판</h1>
<form action="BoardSearchList.do">
	제목검색:<input type="text" name="title">
	<input type="submit" value="검색">
</form>
<table border="1">
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>작성일</td>
		<td>조회수</td>
	</tr>
	<c:forEach var="boardDto" items="${boardList }">
	<tr>
		<td>${boardDto.idx }</td>
		<td><a href="BoardSelectView.do?idx=${boardDto.idx }">${boardDto.title }</a></td>
		<td>${boardDto.id }</td>
		<td>${boardDto.regdate }</td>
		<td>${boardDto.hit }</td>
	</tr>	
	</c:forEach>
</table>
<c:forEach var="i" begin="1" end="${pageCount }" step="1">
	<a href="BoardList.do?pageNum=${i }">[${i }]</a>
</c:forEach><br>

<c:if test="${userInfo ne null }">
	<a href="BoardInsert.do">글쓰기</a>
</c:if>
<input type="button" value="뒤로가기" onclick="javascript:history.back()">



</body>
</html>
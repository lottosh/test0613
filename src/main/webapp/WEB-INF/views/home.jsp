<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>

<c:if test="${userInfo eq null }">
	<a href="MemInsert.do">회원가입</a>
	<a href="Login.do">로그인</a>
</c:if>

<c:if test="${userInfo ne null }">
	${userInfo.id }님 반갑습니다.
	<a href="Logout.do">로그아웃</a>
	
	<c:if test="${userInfo.level == 10}">
		<a href="MemListAll.do">모든회원정보</a>
	</c:if>
</c:if>

<!-- <a href="Board.do">게시판</a> -->
<a href="BoardList.do">게시판</a>
</body>
</html>

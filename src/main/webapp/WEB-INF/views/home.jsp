<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>

<c:if test="${userInfo eq null }">
	<a href="MemInsert.do">ȸ������</a>
	<a href="Login.do">�α���</a>
</c:if>

<c:if test="${userInfo ne null }">
	${userInfo.id }�� �ݰ����ϴ�.
	<a href="Logout.do">�α׾ƿ�</a>
	
	<c:if test="${userInfo.level == 10}">
		<a href="MemListAll.do">���ȸ������</a>
	</c:if>
</c:if>

<!-- <a href="Board.do">�Խ���</a> -->
<a href="BoardList.do">�Խ���</a>
</body>
</html>

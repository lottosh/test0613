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
<h1>��� ȸ������</h1>
<table border="1">
	<tr>
		<td>��ȣ</td>
		<td>���̵�</td>
		<td>��й�ȣ</td>
		<td>�̸���</td>
		<td>�̸�</td>
		<td>��ȭ��ȣ</td>
		<td>����</td>
		<td>������</td>
		<td>����</td>
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
<input type="button" value="�ڷΰ���" onclick="javascript:history.back()">
</body>
</html>
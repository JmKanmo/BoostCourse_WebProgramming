<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.setAttribute("k", 10);
	request.setAttribute("m", true);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${k}<br>
	${m}<br>
	let's print k ${requestScope.k}
	<br> k + 5 : ${requestScope.k+5}
	<br> k * 5 = ${requestScope.k*5}
	<br> k/5 = ${requestScope.k div 5}
	<br>
</body>
</html>
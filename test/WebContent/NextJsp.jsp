<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		int dice = (Integer) request.getAttribute("dice");
	%>

	<%
		for (int i = 0; i < dice; i++) {
	%>
	hello
	<%
		}
	%>
</body>
</html>

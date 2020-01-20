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
	StringBuffer url =  request.getRequestURL();
	out.print("url : "+url.toString());
	out.print("<br>");
	%>
	
	<%
		for (int i = 1; i <= 5; i++) {
	%>
	
	<h<%=i%>> 동해물과 백두산이 </h<%=i%>>
	<%
		}
	%>
</body>
</html>
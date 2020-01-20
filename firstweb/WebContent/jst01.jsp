<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="value1" scope="request" value="kang" />
<c:set var="n" scope="request" value="10" />
<c:set var="score" scope="request" value="155" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<c:if test="${n==10}">
	n과 10은 같습니다.
	</c:if>

	<c:choose>
		<c:when test="${score>=255}">
	${score }점은 A학점입니다
	</c:when>

		<c:otherwise>
	${score }점은 B학점입니다
	</c:otherwise>
	</c:choose>
	
	<c:remove var="score"/>
</body>
</html>

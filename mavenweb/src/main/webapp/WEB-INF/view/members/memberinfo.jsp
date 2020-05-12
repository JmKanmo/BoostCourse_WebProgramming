<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>회원 가입폼</title>
</head>
<body>
	<a href="javascript: window.history.back();">돌아가기</a>
	<a href="/calculator/main">메인화면</a>
	<br>

	<div>
		<div>
			<h1>회원정보</h1>
			<p>로그인한 회원 정보를 표기합니다.</p>
		</div>

		<div>
			<label>id</label>
			<p>${member.id}</p>
		</div>
		<div>
			<label>이름</label>
			<p>${member.name}</p>
		</div>
		<div>
			<label>암호</label>
			<p>${member.password}</p>
		</div>
		<div>
			<label>이메일</label>
			<p>${member.email}</p>
		</div>
		<div>
			<label>등록일</label>
			<p>${member.createDate}</p>
		</div>
		<div>
			<label>수정일</label>
			<p>${member.modifyDate}</p>
		</div>
	</div>
</body>
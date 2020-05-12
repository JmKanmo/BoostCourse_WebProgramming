<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="javascript: window.history.back();">뒤로가기</a>
	<br>
	<form method="POST" action="/calculator/members/join">
		<label for="name_input">이름입력</label> <input type="text"
			id="name_input" name="name" /> <br> <label for="email_input">이메일입력</label>
		<input type="email" id="email_input" name="email" /> <br> <label
			for="password_input">비밀번호입력</label> <input type="password"
			id="password_input" name="password" /> <br> <label
			for="isAdmin">관리자:</label> <input type='checkbox' name='admin'
			id="isAdmin" value='admin' /><br> <label for="isUser">사용자:</label>
		<input type="checkbox" name="user" id="isUser" value="user" /> <br />
		<input type="submit" />
	</form>
</body>
</html>
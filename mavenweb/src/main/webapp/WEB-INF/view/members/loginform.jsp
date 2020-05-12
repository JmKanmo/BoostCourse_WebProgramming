<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>로그인</title>
</head>
<body>
	<a href="javascript: window.history.back();">뒤로가기</a>
	<br>
	<a href="/calculator/main">메인화면</a>
	<br>
	<div>
		<div>
			<form method="post" action="/calculator/authenticate">
				<div>
					<label>ID</label> <input type="text" name="userId">
				</div>
				<div>
					<label>암호</label> <input type="password" name="password">
				</div>
				<div>
					<label></label> <input type="submit" value="로그인">
				</div>
			</form>
		</div>
	</div>
</body>
</html>
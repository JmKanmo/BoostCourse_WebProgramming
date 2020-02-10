<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./resources/css/common.css" />
<link rel="stylesheet" href="./resources/css/mainpage.css" />
<title>예약서비스</title>
</head>

<body>
	<div class="wrap">
		<!--  헤더영역 -->
		<header class="header">
		<div class="logo_box clear_fix">
			<img src="./resources/img/favicon.ico" alt="로고이미지" />
			<p>예약</p>
		</div>

		<div class="email_box">
			<a href="">nebi25@naver.com</a>
		</div>
		</header>

		<!-- 프로모션영역 -->
		<div class="promotion_box">
			<ul class="promotion">
				<c:forEach items="${promotions}" var="item">
					<li class="item"><img src="./resources/${item.saveFileName}"
						style="width: 100%; height: 100%;" alt=""></li>
				</c:forEach>
			</ul>
		</div>

		<div class="main_box">
			<ul class="category clear_fix">
				<li class="item" id="0">
					<p>전체리스트</p>
				</li>
				<c:forEach items="${categories}" var="item">
					<li class="item" id="${item.id}">
						<p>${item.name}</p>
					</li>
				</c:forEach>
			</ul>

			<div class="category_cnt"></div>

		</div>

		<footer class="footer"> </footer>
	</div>
	<script type="text/javascript" src="./resources/js/mainpage.js"></script>
</body>
</html>
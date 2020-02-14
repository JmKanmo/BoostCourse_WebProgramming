<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/common.css" />
<link rel="stylesheet" href="./css/main.css">
<title>할일목록화면</title>
</head>

<body>
	<div class="wrap clear_fix">
		<div class="todo_box clear_fix">
			<h1 class="main_title">나의 해야할 일들</h1>
			<a href="/mavenweb/TodoFormServlet" class="register">새로운
				TODO 등록</a>
			<nav class="menu">
				<h2 class="status_title">TODO</h2>
				<ul class="todo_list" id="todo">
					<c:forEach items="${todo_list}" var="item">
						<li class="item">
							<h3 class="todo_title">${item.title}</h3>
							<p class="todo_text">등록날짜:${item.regDate}, ${item.name}
								우선순위:${item.sequence}</p>
							<button type="button" class="move_btn" data-id="${item.id}">
								<span class="blind">카드우측한칸이동</span>
							</button>
						</li>
					</c:forEach>
				</ul>
			</nav>

			<nav class="menu">
				<h2 class="status_title">DOING</h2>
				<ul class="todo_list" id="doing">
					<c:forEach items="${doing_list}" var="item">
						<li class="item">
							<h3 class="todo_title">${item.title}</h3>
							<p class="todo_text">등록날짜:${item.regDate}, ${item.name}
								우선순위:${item.sequence}</p>
							<button type="button" class="move_btn" data-id="${item.id}">
								<span class="blind">카드우측한칸이동</span>
							</button>
						</li>
					</c:forEach>
				</ul>
			</nav>

			<nav class="menu">
				<h2 class="status_title">DONE</h2>
				<ul class="todo_list" id="done">
					<c:forEach items="${done_list}" var="item">
						<li class="item">
							<h3 class="todo_title">${item.title}</h3>
							<p class="todo_text">등록날짜:${item.regDate}, ${item.name}
								우선순위:${item.sequence}</p>
						</li>
					</c:forEach>
				</ul>
			</nav>
		</div>
	</div>
	<script type="text/javascript" src="./js/main.js"></script>
</body>
</html>

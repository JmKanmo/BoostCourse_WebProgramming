<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.fasterxml.jackson.core.type.TypeReference"%>
<%@page import="java.util.Map"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
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
	<script type="text/javascript">
		let todo_set = [];
		let doing_set = [];
	</script>
	<div class="wrap clear_fix">
		<div class="todo_box clear_fix">
			<a href="/mavenweb/TodoFormServlet" class="register" alt="">새로운
				TODO 등록</a>
			<nav class="menu">
				<h1 class="status_title">TODO</h1>
				<ul class="todo_list" id="todo">
					<c:forEach items="${todo_list}" var="item">
						<li class="item">
							<h2 class="todo_title">${item.title}</h2>
							<p class="todo_text">등록날짜:${item.regDate}, ${item.name}
								우선순위:${item.sequence}</p>
							<button type="button" class="move_btn">
								<span class="blind">카드우측한칸이동</span>
							</button>
						</li>
						<script>
							todo_set.push({
								id : "${item.id}"
							});
						</script>
					</c:forEach>
				</ul>
			</nav>

			<nav class="menu">
				<h1 class="status_title">DOING</h1>
				<ul class="todo_list" id="doing">
					<c:forEach items="${doing_list}" var="item">
						<li class="item">
							<h2 class="todo_title">${item.title}</h2>
							<p class="todo_text">등록날짜:${item.regDate}, ${item.name}
								우선순위:${item.sequence}</p>
							<button type="button" class="move_btn">
								<span class="blind">카드우측한칸이동</span>
							</button>
						</li>
						<script>
							doing_set.push({
								id : "${item.id}"
							});
						</script>
					</c:forEach>
				</ul>
			</nav>

			<nav class="menu">
				<h1 class="status_title">DONE</h1>
				<ul class="todo_list" id="done">
					<c:forEach items="${done_list}" var="item">
						<li class="item">
							<h2 class="todo_title">${item.title}</h2>
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

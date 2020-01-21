<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/todoForm.css">
</head>

<body>
	<div class="wrap">
		<form action="/mavenweb/TodoAddServlet" method="POST">
			<h1 class="title">할일 등록</h1>

			<div class="work_box">
				<label for="work">어떤일인가요?</label> <input type="text" id="work"
					class="workInput" placeholder="부코웹플밍 수강하기(24자까지)" required
					maxlength="24" />
			</div>

			<div class="who_box">
				<label for="who">누가 할일인가요?</label> <input type="text" id="who"
					class="whoInput" placeholder="강준모" required />
			</div>

			<div class="priority_box clear_fix">
				<label for="priority_1th">우선순위를 선택하세요</label>

				<div class="priority clear_fix">
					<input type="radio" id="priority_1th" name="priority" value="1th"
						required> <label for="priority_1th">1순위</label>
				</div>

				<div class="priority clear_fix">
					<input type="radio" id="priority_2th" name="priority" value="2th"
						required> <label for="priority_2th">2순위</label>
				</div>

				<div class="priority clear_fix">
					<input type="radio" id="priority_3th" name="priority" value="3th"
						required> <label for="priority_3th">3순위</label>
				</div>
			</div>

			<div class="button_box clear_fix">
				<button type="button" class="prev_btn" value="뒤로가기"
					onclick="history.back();">
					<p>< 이전</p>
				</button>

				<div class="btn_group clear_fix">
					<button class="submit_btn" type="submit" value="result">
						제출</button>
					<button class="reset_btn" type="reset" value="Reset">
						내용지우기</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
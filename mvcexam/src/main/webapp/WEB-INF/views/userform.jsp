<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>

<body>
	<form action="/guestbook/regist" method="post" id="hello" enctype="multipart/form-data">
		name: <input type="text" name="name" /> <br> email: <input type="text" name="email" /> <br> age: <input
			type="text" name="age" /> <br> <button type = "submit" class="submitBtn">접속하기</button> <input type="file" id="img_file"
			name="file" accept="image/*" multiple="multiple">
		<input type="hidden" name="tempData" value="453543" />
		<div id="file_list">
			<img class="thumb_img" style="width: 300px; height: 300px;" src="" alt="" />
		</div>
		<input type="submit" />
		<img src="" alt="" />
	</form>

	<script>
		function validImageType(image) {
			const result = (['image/jpeg',
				'image/png',
				'image/jpg'
			].indexOf(image.type) > -1);
			return result;
		}
	</script>

</body>

</html>
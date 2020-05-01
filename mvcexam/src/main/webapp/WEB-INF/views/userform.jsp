<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="regist" method="post" id="hello"
		enctype="multipart/form-data">
		name: <input type="text" name="name" /> <br> email: <input
			type="text" name="email" /> <br> age: <input type="text"
			name="age" /> <br> <input type="submit" value="확인" /> <input
			type="file" id="img_file" name="file" accept="image/*"
			multiple="multiple">
		<div id = "file_list">
			<img class="thumb_img" style = "width:300px; height:300px;" src="" alt="" />
		</div>
	</form>

	<script>
	    function validImageType(image) {
	    	const result = ([ 'image/jpeg',
	    					  'image/png',
	    					  'image/jpg' ].indexOf(image.type) > -1);
	    	return result;
	    }
	    
        const elImage = document.querySelector("#img_file");
       
        elImage.addEventListener("change", (evt) => {
            const image = evt.target.files[0];

            if (!validImageType(image)) {
                console.warn("invalide image file type");
                return;
            }
            const elImage = document.querySelector(".thumb_img");
            elImage.src = window.URL.createObjectURL(image);
        });
    </script>

</body>
</html>
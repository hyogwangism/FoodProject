<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<head>
<title>회원가입</title>
<style>
* {
	box-sizing: border-box;
}

body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
}

h1 {
	text-align: center;
	margin-top: 50px;
}

#main-image-container {
	text-align: center;
}

#main-image {
	width: 400px;
	height: 400px;
	border: 1px solid #ccc;
	margin-bottom: 20px;
}

.thumbnail {
	display: inline-block;
	margin-right: 10px;
	cursor: pointer;
	border: 1px solid #ccc;
}

.thumbnail img {
	width: 100px;
	height: 100px;
	text-align: center;
}

#food-details {
	max-width: 600px;
	margin: 0 auto;
	padding: 20px;
	border: 1px solid #ccc;
	text-align: center;
}

h2 {
	margin-top: 0;
}

p {
	margin: 5px 0;
}

button {
	background-color: #4CAF50;
	border: none;
	color: white;
	padding: 10px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 5px 10px;
	cursor: pointer;
}

button:hover {
	background-color: #3e8e41;
}

.thumbnail-container {
	display: flex;
	justify-content: center;
	align-items: center;
}
</style>
<script src="./js/jquery.js"></script>
<script src="./js/food.js"></script>
<script src="./js/member.js"></script>
<script>
      function changeImage(img) {
        document.getElementById("main-image").src = img.src;
      }
    </script>
    
</head>
<body>
	<h1>회원가입</h1>


	<div id="food-details">
		<form method="post" action="member_join_ok" onsubmit="return join_Check();">
		
			아이디 : <input type="text" name="user_id" id="user_id"><br><br>
			비밀번호 : <input type="password" name="user_pwd" id="user_pwd"><br><br>
			비밀번호 확인 : <input type="password" name="user_pwd2" id="user_pwd2"><br><br>
			이름 : <input type="text" name="user_name" id="user_name"><br><br>
			성별 : <input type="radio" name="user_gender" id="user_gender" value="남자">남자 
			     <input type="radio" name="user_gender" id="user_gender" value="여자">여자
			     <br><br>
			생년월일 : <input type="text" name="user_birth" id="user_birth"><br><br>
			이메일 : <input type="email" name="user_email" id="user_email"><br><br>
			전화번호 : <input type="text" name="user_phone" id="user_phone"><br><br>

			<button type="submit">확인</button>
			<button type="button" onclick="location.href='/food_main'">취소</button>
		</form>
	</div>
</body>
</html>
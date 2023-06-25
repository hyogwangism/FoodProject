<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<head>
<title>비밀번호 변경</title>
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
</head>
<body>
	<h1>비밀번호 변경</h1>
	<div id="food-details">
		<form method="post" action="member_pwdEdit_ok" onsubmit="return pwd_Check();">
		
			현재 비밀번호 : <input type="password" name="user_pwd" id="user_pwd" ><br><br>
			새 비밀번호 : <input type="password" name="new_pwd" id="new_pwd" ><br><br>
			새 비밀번호 확인 : <input type="password" name="new_pwd2" id="new_pwd2" ><br><br>
			
			<button type="submit">확인</button>
			<button type="button" onclick="location.href='food_main';">취소</button>
		</form>
	</div>
			<c:if test="${!empty errorMessage}">
    <script>
    $(document).ready(function(){
        alert('${errorMessage}');
        location.href='/member_pwdEdit';
    });     	
    </script>
    </c:if>
</body>
</html>
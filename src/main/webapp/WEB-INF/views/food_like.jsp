<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<head>
<title>음식 상세 페이지</title>
<style>
* {
	box-sizing: border-box;
}

div.clear {
	clear: both;
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

.container{
	text-align: center;
}

      #main-image-container {
        text-align: center;
      }
.sub-image-container {
	display: inline-block;
	float: none;
}

#main-image {
	width: 400px;
	height: 400px;
	border: 1px solid #ccc;
	margin-bottom: 20px;
}

#sub-image {
	width: 130px;
	height: 130px;
	border: 1px solid #ccc;
	margin: 20px 20px;
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


#CommentWrite {
	width: 800px;
	margin: 0 auto;
}

textarea {
	resize: none;
}

#CommentList {
	text-align: left;
	width: 800px;
	min-height: 500px;
	margin: 0 auto;
	padding: 20px 5px;
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
      a:hover {
        background-color: #3e8e41;
      }
.thumbnail-container {
	display: flex;
	justify-content: center;
	align-items: center;
}
.hr-line {
	border-top: 1px solid #000;
}

.comment_hr-line {
	border-bottom: 1px dashed black;
}
#like-image {
	width: 100px;
	height: 100px;
	border: 1px solid #ccc;
	margin-bottom: 20px;
}



</style>


</head>
<body>
	<h1>찜목록</h1>
	<div style="text-align: center;">
  <a href="/food_main">메인으로</a>
</div>
    <br>
   
		<table border="1"  style="margin: 0 auto;">
  <tr>
    
    <th>음식사진</th>
    <th>식당이름</th>
    <th>음식이름</th>
    <th>지역이름</th>
    <th>타입이름</th>
  </tr>

  <c:forEach var="a" items="${likelist}">
    <tr>
    
  
      <td><img id="like-image" src="/upload${a.PHOTO_URL}"  alt="음식 이미지1" ></td>
      <td>${a.RESTAURANT_NAME}</td>
      <td>${a.FOOD_NAME}</td>
      <td>${a.REGION_NAME}</td>
      <td>${a.TYPE_NAME}</td>
      
      
    </tr>
  </c:forEach>
</table>
	
	
</body>
</html>
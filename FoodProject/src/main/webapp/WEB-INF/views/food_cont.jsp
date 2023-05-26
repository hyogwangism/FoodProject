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
</style>

<script src="./js/jquery.js"></script>
<script src="./js/food.js"></script>

</head>
<body>
	<h1>음식 상세 페이지</h1>
    <div id="main-image-container">
      <img id="main-image" src="/upload${f.food_photo01}"  alt="음식 이미지1">
    </div>
    <div class =thumbnail-container>
      <div class="thumbnail" onclick="changeImage(this);">
        <img src="/upload${f.food_photo01}" alt="음식 이미지1">
      </div>
      <c:if test="${!empty f.food_photo02}">
      <div class="thumbnail" onclick="changeImage(this);">
        <img src="/upload${f.food_photo02}" alt="음식 이미지2">
      </div>
      </c:if>
      <c:if test="${!empty f.food_photo03}">
     <div class="thumbnail" onclick="changeImage(this);">
        <img src="/upload${f.food_photo03}" alt="음식 이미지3">
      </div>
      </c:if>
    </div>
		
	<div class="clear"></div>

	<br>
	<br>
	<div id="food-details">
		<h2>가게 이름 : ${f.food_place}</h2>
		<p>음식이름: ${f.food_name}</p>
		<p>맛: ${f.food_taste}</p>
		<p>양: ${f.food_amount}</p>
		<p>지역: ${f.food_location}</p>
		<p>종류: ${f.food_type}</p>
		<p>공간: ${f.food_space}</p>
		<p>음식 가격: ${f.food_price}</p>
		<p>주차: ${f.food_parking}</p>
		<p>후기: ${f.food_review}</p>
		<button onclick="location.href='food_main'">목록</button>	
		<c:if test="${session_id !=  null && session_id ne 'admin'}">
		<button type="button" class="LikeBtn">찜하기</button>	
		</c:if>
		<c:if test="${session_id eq 'admin'}">
		<button onclick="location.href='food_edit?food_no=${f.food_no}'">수정</button>
		<button onclick="return del(${f.food_no});" id="delBtn">삭제</button>
		</c:if>
	</div>
			    <input type="hidden" id="likeCount" value="${likeCount}" />
			  <c:if test="${FoodLike.food_no != null}">
			    <input type="hidden" id="foodNo" value="${FoodLike.food_no}" />
			  </c:if>
			  <c:if test="${session_id != null}">
			    <input type="hidden" id="session_id" value="${session_id}" />
			  </c:if>
   <script>
  function changeImage(img) {
    document.getElementById("main-image").src = img.querySelector('img').src;
  }
  
  $(document).ready(function() {
	  
	  
	  // Bind click event handler to LikeBtn
	  $('.LikeBtn').click(function() {
	    var likeval = parseInt(document.getElementById("likeCount").value);
	    console.log(likeval);

	    if (likeval > 0) {
	      console.log(likeval + "좋아요 누름");
	      $.ajax({
	        type: 'post',
	        url: '/likeDown',
	        contentType: 'application/json',
	        data: JSON.stringify({
	          "food_no" : parseInt(document.getElementById("foodNo").value),
	          "session_id" : document.getElementById("session_id").value
	        }),
	        success: function(data) {
	          console.log('취소 성공염');
	          $('.LikeBtn').html("찜하기");
	          // Update likeCount value if needed
	          document.getElementById("likeCount").value = 0;
	        }
	      });
	    } else if (likeval == 0) {
	      console.log(likeval + "좋아요 안누름");
	      console.log(session_id);
	      $.ajax({
	        type: 'post',
	        url: '/likeUp',
	        contentType: 'application/json',
	        data: JSON.stringify({
	          "food_no" : parseInt(document.getElementById("foodNo").value),
	          "session_id" : document.getElementById("session_id").value
	        }),
	        success: function(data) {
	          console.log('성공염');
	          $('.LikeBtn').html("찜하기 취소");
	          // Update likeCount value if needed
	          document.getElementById("likeCount").value = 1;
	        }
	      });
	    }
	  });

	  // Initialize like button state
	  var likeval = parseInt(document.getElementById("likeCount").value);
	  $('.LikeBtn').html(likeval > 0 ? "찜하기 취소" : "찜하기");
	});
</script>
</body>
</html>
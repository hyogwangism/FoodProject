<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<head>
<title>식당 상세정보</title>
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

.container {
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

.hr-line {
	border-top: 1px solid #000;
}

.comment_hr-line {
	border-bottom: 1px dashed black;
}
</style>

<script src="./js/jquery.js"></script>
<script src="./js/foodComment.js"></script>
<script src="./js/foodLike.js"></script>


</head>
<body>
	<h1>식당 상세정보</h1>
	<div id="main-image-container">
		<img id="main-image" src="/upload${fcont.PHOTO_URL}" alt="음식 이미지1">
	</div>
	<div class=thumbnail-container>
		<div class="thumbnail" onclick="changeImage(this);">
			<img src="/upload${fcont.PHOTO_URL}" alt="음식 이미지1">
		</div>
		<c:if test="${!empty fcont.PHOTO_URL}">
			<div class="thumbnail" onclick="changeImage(this);">
				<img src="/upload${fcont.PHOTO_URL}" alt="음식 이미지2">
			</div>
		</c:if>
		<c:if test="${!empty fcont.PHOTO_URL}">
			<div class="thumbnail" onclick="changeImage(this);">
				<img src="/upload${fcont.PHOTO_URL}" alt="음식 이미지3">
			</div>
		</c:if>
	</div>

	<div class="clear"></div>

	<br>
	<br>
	<div id="food-details">
		<input type="hidden" name="NAVI_NO" id="NAVI_NO"
			value="${fcont.NAVI_NO}">
		<h2>가게 이름 : ${fcont.RESTAURANT_NAME}</h2>
		<p>음식이름: ${fcont.FOOD_NAME}</p>
		<p>지역: ${fcont.REGION_NAME}</p>
		<p>종류: ${fcont.TYPE_NAME}</p>
		<p>음식 가격: ${fcont.PRICE_WON}</p>
		<p>후기: ${fcont.RESTAURANT_REVIEW}</p>
		<button onclick="location.href='food_main'">목록</button>
		<c:if test="${session_id !=  null && session_id ne 'admin'}">
			<button type="button" class="LikeBtn">찜하기</button>
		</c:if>
		<c:if test="${session_id eq 'admin'}">
			<button onclick="return del();" id="delBtn">삭제</button>
		</c:if>
	</div>

	<br>
	<br>
	<br>
	<c:if test="${!empty session_id}">
	<div id="CommentWrite">
		댓글달기 :
		<textarea cols="90" rows="5" id="InputComment" name="InputComment"></textarea>
		<button type="button" id="commentBtn" onclick="CommentWrite();">확인</button>
	</div>
	</c:if>
	<br>
	<br>

	<div class="hr-line"></div>

	<br>
	<div align="center">댓글</div>
	<br>

	<div class="hr-line"></div>

	<div id="CommentList">
	<c:forEach var="clist" items="${clist}">
	 <span><b>${clist.user_id}</b></span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
          <span>${clist.contents}</span> &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; 
          <div align="right">

        <c:if test="${session_id eq clist.user_id}">
    	  <span><button type="button" onclick="return Del_check(${clist.comment_no});">삭제</button></span>
    	</c:if>
          <span style="font-size: 13px;">${clist.comment_date.substring(0, 10)}</span>
          </div>
          <div class="comment_hr-line"></div>
          <br><br>
          </c:forEach>
	</div>

	<div class="hr-line"></div>
	
	<div class="page" style="text-align: center; padding:20px;">

		<c:forEach begin="1" end="${totalPages}" var="i">
			<c:choose>
				<c:when test="${i eq 1}">
					<b><span class="page-link" data-page="${i}"
						style="display: inline-block; margin: 0 2px; cursor: pointer; color:blue;">[${i}]</span></b>
				</c:when>
				<c:otherwise>
					<c:if test="${i <= 10}">
						<span class="page-link" data-page="${i}"
							style="display: inline-block; margin: 0 2px; cursor: pointer; color:blue;">[${i}]</span>
					</c:if>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<span class="page-link" data-page="${1 + 1}"
			style="display: inline-block; margin: 0 2px; cursor: pointer;">>>다음</span>

	</div>


	<input type="hidden" id="likeCount" value="${likeCount}" />
	<c:if test="${FoodLike.like_navi_no != null}">
		<input type="hidden" id="foodNo" value="${FoodLike.like_navi_no}" />
	</c:if>
	<c:if test="${session_id != null}">
		<input type="hidden" id="session_id" value="${session_id}" />
	</c:if>
	
	<script>
  function changeImage(img) {
    document.getElementById("main-image").src = img.querySelector('img').src;
  }

</script>
</body>
</html>
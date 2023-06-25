<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>맛집 리스트</title>
<style>
.container {
	width: 100%;
	margin: 0 auto;
	text-align: center;
}

.tab {
	display: block;
	width: 100%;
	list-style: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
}

.foodList {
	width: 100%;
	padding: 6px 12px;
}

a {
	display: inline-block;
	color: #000;
	text-align: center;
	text-decoration: none;
	padding: 14px 16px;
	font-size: 17px;
	transition: 0.3s;
}

a:visited {
	color: black;
	text-decoration: none;
}

li.tabmenu {
	float: left;
	display: inline-block;
	color: #000;
	text-align: center;
	text-decoration: none;
	padding: 14px 16px;
	font-size: 17px;
}

li.check_li {
	float: left;
	display: inline-block;
	color: #000;
	text-align: center;
	text-decoration: none;
	padding: 14px 16px;
	font-size: 17px;
}

.overview {
	width: 350px;
	height: 300px;
	margin: 40px 10px;
	padding: 6px 12px;
	float: left;
}

.overview img {
	width: 300px;
	height: 250px;
}

.header {
	margin: 0 auto;
	text-align: center;
	display: flex;
	width: 100%;
}

.searchField {
	margin: 0 auto;
	text-align: center;
	display: flex;
	width: 100%;
}

#title {
	position: relative;
	margin: 0 auto;
}

.search_box {
	position: relative;
	margin: 0 auto;
	width: 400px;
}

#memberBtn {
	margin-left: 75%;
	position: absolute;
	width: 350px;
}

.search_ctn {
	margin: 0 auto;
	float: left;
	width: 100%;
	height: 200px;
}

.clear {
	clear: both;
}
</style>
<script src="./js/jquery.js"></script>

</head>
<body>

	<div class="container">

		<div class="header">

			<div id="title">
				<h1>맛집 리스트</h1>
			</div>

			<c:if test="${empty session_id}">
				<div id="memberBtn">
					<input type="button" onclick="location.href='/member_join'"
						value="회원가입"> <input type="button"
						onclick="location.href='/member_login'" value="로그인">
				</div>
			</c:if>

			<c:if test="${!empty session_id}">
				<div id="memberBtn">
					<a href="/member_edit"><b style="font-size: 20px;">${session_id}</b></a>님
					환영합니다 &nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
						onclick="location.href='/member_logout'" value="로그아웃">
				</div>
			</c:if>

			<c:if test="${session_id eq 'admin'}">
				<div id="memberBtn">
					<a href="/admin_main"><b style="font-size: 20px;">${session_id}</b></a>님
					환영합니다 &nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
						onclick="location.href='/member_logout'" value="로그아웃">
				</div>
			</c:if>

		</div>

		<div class="clear"></div>

		<div class="search_ctn">
			<div class="search_div1">
				<ul>
					<li class="check_li">종류 :</li>

					<li class="check_li"><input type="checkbox" id="s1"
						name=food_type value="한식" /><label for="s1">한식</label></li>

					<li class="check_li"><input type="checkbox" id="s2"
						name="food_type" value="중식" /><label for="s2">중식</label></li>

					<li class="check_li"><input type="checkbox" id="s3"
						name="food_type" value="일식" /><label for="s3">일식</label></li>

					<li class="check_li"><input type="checkbox" id="s4"
						name="food_type" value="양식" /><label for="s4">양식</label></li>

					<li class="check_li"><input type="checkbox" id="s5"
						name="food_type" value="기타" /><label for="s5">기타</label></li>

					<li class="check_li"><input type="checkbox" id="s6"
						name="food_type" value="디저트" /><label for="s6">디저트</label></li>
				</ul>
			</div>

			<br>
			<div class="clear"></div>

			<div class="search_div2">
				<ul>
					<li class="check_li">지역 :</li>
				</ul>
				<ul>
					<li class="check_li"><input type="checkbox" id="r1"
						name="food_location" value="서울" /> <label for="r1">서울</label></li>
					<li class="check_li"><input type="checkbox" id="r2"
						name="food_location" value="인천" /> <label for="r2">인천</label></li>
					<li class="check_li"><input type="checkbox" id="r3"
						name="food_location" value="대전" /> <label for="r3">대전</label></li>
				</ul>
				<ul>
					<li class="check_li"><input type="checkbox" id="r4"
						name="food_location" value="광주" /> <label for="r4">광주</label></li>
					<li class="check_li"><input type="checkbox" id="r5"
						name="food_location" value="부산" /> <label for="r5">부산</label></li>
					<li class="check_li"><input type="checkbox" id="r6"
						name="food_location" value="대구" /> <label for="r6">대구</label></li>
				</ul>
				<ul>
					<li class="check_li"><input type="checkbox" id="r7"
						name="food_location" value="경기" /> <label for="r7">경기</label></li>
					<li class="check_li"><input type="checkbox" id="r8"
						name="food_location" value="강원" /> <label for="r8">강원</label></li>
					<li class="check_li"><input type="checkbox" id="r9"
						name="food_location" value="충청" /> <label for="r9">충청</label></li>
				</ul>
				<ul>
					<li class="check_li"><input type="checkbox" id="r10"
						name="food_location" value="호남" /> <label for="r10">호남</label></li>
					<li class="check_li"><input type="checkbox" id="r11"
						name="food_location" value="영남" /> <label for="r11">영남</label></li>
					<li class="check_li"><input type="checkbox" id="r12"
						name="food_location" value="제주" /> <label for="r12">제주</label></li>
				</ul>
			</div>
		</div>

		<br>
	</div>
	<!-- search_ctn END -->
	<div class="searchField">
		<c:if
			test="${(empty fvo.user_id || !empty fvo.user_id) && (fvo.user_id ne 'admin')}">
			<div class="search_box">
				<select name="search_type" id="search_type">
					<option value="All"
						<c:if test="${search_type == 'All'}"> ${'selected'}</c:if>>전체</option>
					<option value="food_place"
						<c:if test="${search_type == 'food_place'}"> ${'selected'}</c:if>>식당명</option>
					<option value="food_name"
						<c:if test="${search_type == 'food_name'}"> ${'selected'}</c:if>>음식명</option>
				</select> <input type="text" class="searchbox" id="search_field"
					onkeyup="search_FoodList()" name="search_field" /> <input
					type="button" id="searchBtn" onclick="search_FoodList()" value="검색">
			</div>
		</c:if>


		<c:if test="${fvo.user_id eq 'admin'}">
			<div class="search_box">
				<select name="search_type" id="search_type">
					<option value="All"
						<c:if test="${search_type == 'All'}"> ${'selected'}</c:if>>전체</option>
					<option value="food_place"
						<c:if test="${search_type == 'food_place'}"> ${'selected'}</c:if>>식당명</option>
					<option value="food_name"
						<c:if test="${search_type == 'food_name'}"> ${'selected'}</c:if>>음식명</option>
				</select> <input type="text" class="searchbox" id="search_field"
					name="search_field" onkeyup="search_FoodList()" /> <input
					type="button" id="searchBtn" onclick="search_FoodList()" value="검색">
				<button type="button" onclick="location.href='food_write'">글등록</button>

			</div>
		</c:if>
		<!-- search_box END-->
	</div>

	<br>
	<br>

	<div class="tab">
		<ul>
			<li class='tabmenu' value="All" id="food_type"
				onclick="searchFoodList();" style="background-color: green;">전체</li>
			<li class='tabmenu' value="한식" id="food_type"
				onclick="searchFoodList();">한식</li>
			<li class='tabmenu' value="중식" id="food_type"
				onclick="searchFoodList();">중식</li>
			<li class='tabmenu' value="일식" id="food_type"
				onclick="searchFoodList();">일식</li>
			<li class='tabmenu' value="양식" id="food_type"
				onclick="searchFoodList();">양식</li>
			<li class='tabmenu' value="기타" id="food_type"
				onclick="searchFoodList();">기타</li>
			<li class='tabmenu' value="디저트" id="food_type"
				onclick="searchFoodList();">디저트</li>
		</ul>
	</div>

	<div class="page" style="text-align: center;">

		<c:forEach begin="1" end="${pagecount}" var="i">
			<c:choose>
				<c:when test="${i eq 1}">
					<b><span class="page-link" data-page="${i}"
						style="display: inline-block; margin: 0 2px; cursor: pointer;">[${i}]</span></b>
				</c:when>
				<c:otherwise>
					<c:if test="${i <= 10}">
						<span class="page-link" data-page="${i}"
							style="display: inline-block; margin: 0 2px; cursor: pointer;">[${i}]</span>
					</c:if>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<span class="page-link" data-page="${1 + 1}"
			style="display: inline-block; margin: 0 2px; cursor: pointer;">다음</span>

	</div>



	<div class="foodList" align="center">
		<%--Ajax 적용영역--%>

		<c:forEach var="a" items="${all}">

			<div class="overview">
				<img src="/upload${a.food_photo01}"
					onclick="location.href='food_cont?NAVI_NO=${a.NAVI_NO}'" /><br>
				<p>
				
					<span><b>찜</b></span>&nbsp;&nbsp;&nbsp;${a.LIKE_CNT}개<br>
					<span><b>식당명</b></span>&nbsp;&nbsp;&nbsp;${a.RESTAURANT_NAME}<br>
					<span><b>음식명</b></span>&nbsp;&nbsp;&nbsp;${a.FOOD_NAME}<br> <span><b>지역</b></span>&nbsp;&nbsp;&nbsp;${a.REGION_NAME}<br>
				</p>
			</div>

		</c:forEach>
	</div>
	<script src="./js/food.js"></script>

</body>
</html>

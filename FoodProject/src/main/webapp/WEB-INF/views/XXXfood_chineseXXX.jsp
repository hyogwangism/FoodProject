<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

li {
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
	margin: 30px 10px;
	padding: 6px 12px;
	float: left;
}

.overview img {
	width: 300px;
	height: 250px;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<%--
<script>
	$(function() {
		// tab operation
		$('.tabmenu')
				.click(
						function(e) { // <- e는 클릭 당한(사용자가 클릭한) 객체의 이벤트 
							var activeTab = $(this).attr('data-tab');
							$('li').css('background-color', 'white');
							$(this).css('background-color', 'green');

							// 나중에 console.log(e)찍어보기
							// 나중에 console.log(e.target) 찍어보기
							// e.target -> 사용자가 클릭한 li 를 가리킨다
	const kind = $(e.target).attr('data-tab')
			alert(kind);
			$.ajax({
				type : 'POST',                 //get방식으로 통신
				url : "/food/ajax",    //탭의 data-tab속성의 값으로 된 html파일로 통신
				data : {
					"kind":kind
				},
				dataType : "json",  //html형식으로 값 읽기
				
				// 데이터 받는 단계
				// 에러났을 때 -> 400, 500번대 에러
				error : function() {          //통신 실패시
					alert('통신실패!');
				},
				success : function(data) {    //<- 여기서  data 는 board/ajax의 return, data는 jsp 코드
					
					console.log(data);
					alert('통신성공!');
					$('#tabcontent').append(data);
				}
			}); 
	});
		$('#default').click();
	});
</script>--%>
</head>
<body>

	<div class="container">

		<h1>맛집 리스트</h1>

		<div class="search_area">
			<form action="food_main" method="get" id="searchForm">
				<div class="search_ctn">
					<div class="search_div1">
						<ul>
							<li class="search_li">종류 :</li>
							<li class="check_li"><input type="checkbox" id="s1"
								name=attract value="1001" /><label for="s1">한식</label></li>
							<li class="check_li"><input type="checkbox" id="s2"
								name="attract" value="1002" /><label for="s2">중식</label></li>
							<li class="check_li"><input type="checkbox" id="s3"
								name="attract" value="1003" /><label for="s3">일식</label></li>
							<li class="check_li"><input type="checkbox" id="s4"
								name="attract" value="1004" /><label for="s4">디저트</label></li>
						</ul>
					</div>
					<br> <br>
					<div class="search_div2">
						<ul>
							<li class="search_li">지역 :</li>
						</ul>
						<ul>
							<li><input type="checkbox" id="r1" name="area" value="2001" />
								<label for="r1">서울</label></li>
							<li><input type="checkbox" id="r2" name="area" value="2005" />
								<label for="r2">경기</label></li>
							<li><input type="checkbox" id="r3" name="area" value="2009" />
								<label for="r3">부산</label></li>
						</ul>
						<ul>
							<li><input type="checkbox" id="r4" name="area" value="2002" />
								<label for="r4">강원</label></li>
							<li><input type="checkbox" id="r5" name="area" value="2006" />
								<label for="r5">제주</label></li>
							<li><input type="checkbox" id="r6" name="area" value="2010" />
								<label for="r6">대전</label></li>
						</ul>


					</div>
					<br> <br>
					<div class="search_div2">
						<ul>
							<li class="search_li">주차 :</li>
						</ul>
						<ul>
							<li><input type="checkbox" id="p1" name="park" value="3001" />
								<label for="p1">가능</label></li>
							<li><input type="checkbox" id="p2" name="park" value="3002" />
								<label for="p2">불가능</label></li>

						</ul>
					</div>

					<br> <br> <br> <br>
				</div>
				<!-- search_ctn END -->

				<div class="search_box">
					<input type="text" class="searchbox" name="find_name" /> <input
						type="submit" value="검색">

					<button type="button" onclick="location.href='food_write'">글
						등록</button>

				</div>
				<!-- search_box END-->

			</form>

			<br> <br>

		</div>
		<div class="tab">
			<ul>
				<li data-tab="1" class='tabmenu' id="default" value="all" onclick="location.href='food_main'">전체</li>
				<li data-tab="2" class='tabmenu' value="korean" onclick="location.href='food_korean'">한식</li>
				<li data-tab="3" class='tabmenu' value="chinese" onclick="location.href='food_chinese'"  style="background-color: green;">중식</li>
				<li data-tab="4" class='tabmenu' value="japanese" onclick="location.href='food_japanese'">일식</li>
				<li data-tab="5" class='tabmenu' value="dessert" onclick="location.href='food_dessert'">디저트</li>
			</ul>
		</div>

		<div class="foodList">
			<c:if test="${!empty flist}">  
				<c:forEach items="${flist}" var="f">
				<c:if test="${f.food_type=='중식'}">
					<div class="overview">
						<img src="/upload${f.food_photo}" /><br>
						<p>
							<span><b>식당명</b></span>&nbsp;&nbsp;&nbsp;${f.food_place}<br> 
							<span><b>상품명</b></span>&nbsp;&nbsp;&nbsp;${f.food_name}<br>
							<span><b>지역</b></span>&nbsp;&nbsp;&nbsp;${f.food_location}<br>
						</p>
					</div>
					<div class="gdsInfo"></div>
					</c:if>
				</c:forEach>
			</c:if>
		</div>
	</div>
</body>
</html>
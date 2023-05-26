<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX</title>
<script src="./js/jquery.js"></script>
<script src="./js/member.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	searchFunction();
	});
</script>


<style type="text/css">
td#ajaxTable {
	height: 50px;
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
</style>

</head>
<body>
	<br>
	<div class="container" align="center">
		<div class="form-group row pull-right">
			<div class="col-xs-8">
			<h1>회원 목록 검색</h1>
					<select name="search_type" id="search_type">
   						<option value="All"<c:if test="${search_type == 'All'}"> ${'selected'}</c:if>>전체</option>
   						<option value="user_id"<c:if test="${search_type == 'user_id'}"> ${'selected'}</c:if>>아이디</option>
   						<option value="user_name" <c:if test="${search_type == 'user_name'}"> ${'selected'}</c:if>>이름</option>
   						<option value="user_birth" <c:if test="${search_type == 'user_birth'}"> ${'selected'}</c:if>>생년월일</option>
   						<option value="user_email" <c:if test="${search_type == 'user_email'}"> ${'selected'}</c:if>>이메일</option>
   						<option value="user_phone" <c:if test="${search_type == 'user_phone'}"> ${'selected'}</c:if>>전화번호</option>
					</select>
					<input class="form-control" id="search_field" name="search_field" onkeyup="searchFunction()" size="20"> &nbsp;&nbsp;&nbsp; 
					<input class="btn btn-primary" type="button" name="searchBtn" id="searchBtn" onclick="searchFunction();" value="검색">
			</div>
		</div>
		<br><br>
		<table class="table"
			style="text-align: center; border: 1px solid black;">
			<thead>
				<tr>

					<th
						style="background-color: lightgray; text-align: center; width: 100px;">아이디</th>
					<th
						style="background-color: lightgray; text-align: center; width: 150px;">이름</th>
					<th
						style="background-color: lightgray; text-align: center; width: 100px;">생년월일</th>
					<th
						style="background-color: lightgray; text-align: center; width: 100px;">이메일</th>
					<th
						style="background-color: lightgray; text-align: center; width: 300px;">전화번호</th>
				</tr>
			</thead>

			<tbody id="ajaxTable">

			</tbody>

		</table>
	</div>
	
	<br>
	
	<div align="center">
	<button type="button" onclick="location.href='food_main';">홈으로</button>
	</div>
</body>
</html>
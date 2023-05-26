<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<head>
<title>음식 상세 페이지</title>
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

img#overview {
	width: 300px;
	height: 250px;
}

.file-input-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
<script>
      function changeImage(img) {
        document.getElementById("main-image").src = img.src;
      }
    </script>
</head>
<body>
	<h1>음식 등록 페이지</h1>

	<div id="food-details">
		<form method="post" action="food_edit_ok"  enctype="multipart/form-data">
		<input type="hidden" name="food_no" id="food_no" value="${f.food_no}">
			가게 이름: <input type="text" name="food_place" id="food_place" value="${f.food_place}"><br>
			음식이름: <input type="text" name="food_name" id="food_name" value="${f.food_name}"><br>
			맛: <input type="text" name="food_taste" id="food_taste" value="${f.food_taste}"><br>
			양: <input type="text" name="food_amount" id="food_amount" value="${f.food_amount}"><br>
			지역: <input type="text" name="food_location" id="food_location" value="${f.food_location}"><br>
			종류: <input type="text" name="food_type" id="food_type" value="${f.food_type}"><br>
			공간: <input type="text" name="food_space" id="food_space" value="${f.food_space}"><br>
			음식 가격: <input type="text" name="food_price" id="food_price" value="${f.food_price}"><br>
			주차: <input type="text" name="food_parking" id="food_parking" value="${f.food_parking}"><br>
			후기: <input type="text" name="food_review" id="food_review" value="${f.food_review}"><br>
			<div class="file-input-wrapper">
				<table>
					<tbody id="insert">
						<tr>
							<td>사진첨부: <input type="file" name="food_photo01" id="food_photo01" required />
							<br>${f.food_photo01}
							</td>
						</tr>
						<tr>
							<td>사진첨부: <input type="file" name="food_photo02" id="food_photo02" required />
							<br>${f.food_photo02}
							</td>
							</tr>
						<tr>	
							<td>사진첨부: <input type="file" name="food_photo03" id="food_photo03" required />
							<br>${f.food_photo03}
							</td>
							</tr>
			
					</tbody>
				</table>
			</div>

				
<%--			<c:if test="${!empty f.food_photo01 || !empty f.food_photo02 || !empty f.food_photo03}">
			사진파일1 : <img id="overview" src="/upload${f.food_photo01}"><br>
			사진파일2 : <img id="overview" src="/upload${f.food_photo02}"><br>
			사진파일3 : <img id="overview" src="/upload${f.food_photo03}"><br>
			</c:if>	
			
			<c:if test="${empty f.food_photo01 || empty f.food_photo02 || empty f.food_photo03}">
			사진파일1 추가 : <input type="file" name="food_photo01" id="food_photo01"> 
			사진파일2 추가 : <input type="file" name="food_photo02" id="food_photo02"> 
			사진파일3 추가 : <input type="file" name="food_photo03" id="food_photo03"> 
			</c:if> --%>	
			<button type="submit">확인</button>
			<button type="button" onclick="location.href='food_cont?food_no=${f.food_no}'">취소</button>
		</form>
	</div>
<%--
		<script>
var rowIndex = 1;
function addFile(form) {
  if(rowIndex > 2) {
    alert('최대 3개까지만 등록 가능합니다');
    return false;
  }
  rowIndex++;
  var getTable = document.getElementById("insert");
  var oCurrentRow = getTable.insertRow(getTable.rows.length);
  var oCurrentCell = oCurrentRow.insertCell(0);
  oCurrentCell.innerHTML = "<table><tr><td colspan=2><input type='file' name='food_photo0"+rowIndex+"'size=25></td></tr></table>";
}

function deleteFile(form){
  if(rowIndex < 2) {
    return false;
  } else {
    rowIndex--;
    var getTable = document.getElementById("insert");
    getTable.deleteRow(rowIndex);
  }
}
</script>
 --%>
</body>
</html>
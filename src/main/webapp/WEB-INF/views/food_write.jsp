<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<form method="post" action="food_write_ok"
			enctype="multipart/form-data">
			가게이름: <input type="text" name="food_place" id="food_place"><br>
			음식이름: <input type="text" name="food_name" id="food_name"><br>
			지역: <input type="text" name="food_location" id="food_location"><br>
			종류: <input type="text" name="food_type" id="food_type"><br>
			가격: <input type="text" name="food_price" id="food_price"><br>
			후기: <input type="text" name="food_review" id="food_review"><br>
			<br>
			<div class="file-input-wrapper">
				<table>
					<tbody id="insert">
						<tr>
							<td>사진첨부1:<br> <input type="file" name="food_photo01" img-type="title" id="food_photo01" required /></td>
							<td>사진첨부2:<br> <input type="file" name="food_photo02" img-type="sub" id="food_photo02" required /></td>
							<td>사진첨부3:<br> <input type="file" name="food_photo03" img-type="sub" id="food_photo03" required /></td>
						<%--	<td><input type="button" value="추가" onclick="addFile(this.form)">
								<input type="button" value="삭제" onclick="deleteFile(this.form)"></td>
								 --%>
						</tr>
					</tbody>
				</table>
			</div>

			<button type="submit">확인</button>
			<button onclick="location.href='food_main';" >취소</button>
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
		<%--	사진파일 1: <input type="file" name="food_photo01" id="food_photo01"
				multiple="multiple"><br> 사진파일 2: <input type="file"
				name="food_photo02" id="food_photo02" multiple="multiple"><br>
			사진파일 3: <input type="file" name="food_photo03" id="food_photo03"
				multiple="multiple"><br>
 --%>
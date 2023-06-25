<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<head>
<title>게시물 관리 페이지</title>
<style>
  .container {
    width: 1500px;
    margin: 0 auto;
  }

  .contentNav {
    list-style: none;
    padding: 0;
    margin: 0;
    display: flex;
  }

  .contentNav li {
    margin-right: 10px;
  }

  .contentNav li a {
    text-decoration: none;
    color: #000;
    padding: 5px 10px;
    background-color: #eee;
    border-radius: 5px;
  }

  .contentNav li.active a {
    background-color: #fff;
    font-weight: bold;
  }

  .tab_cont .tab-content {
    display: none;
  }

  .tab_cont .tab-content.active {
    display: block;
  }
  table {
    display: inline-block;
    vertical-align: top;
    margin-right: 20px;
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
	<h1 align="center">테이블 관리</h1>
	
	
	<div style="text-align: center;">
  <a href="/admin_main">뒤로가기</a>
</div>
	
	<div class="container" >
  <ul class="contentNav">
  <li class="tab-link ${currentTab == 'tab1' ? 'active' : ''}" data-tab="tab1"><a>음식</a></li>
  <li class="tab-link ${currentTab == 'tab2' ? 'active' : ''}" data-tab="tab2"><a>가격</a></li>
  <li class="tab-link ${currentTab == 'tab3' ? 'active' : ''}" data-tab="tab3"><a>사진</a></li>
  <li class="tab-link ${currentTab == 'tab4' ? 'active' : ''}" data-tab="tab4"><a>식당</a></li>
  <li class="tab-link ${currentTab == 'tab5' ? 'active' : ''}" data-tab="tab5"><a>지역</a></li>
  <li class="tab-link ${currentTab == 'tab6' ? 'active' : ''}" data-tab="tab6"><a>타입</a></li>
  <li class="tab-link ${currentTab == 'tab7' ? 'active' : ''}" data-tab="tab7"><a>등록</a></li>
</ul>
<br>

<div class="tab_cont">
  <div id="tab1" class="tab-content ${currentTab == 'tab1' ? 'active' : ''}">
    <h2>음식</h2>
    <!-- 음식 내용 -->
    <form action="food_manage_ok">
      <input type="hidden" name="table" value="t_food">
      <input type="hidden" name="seq" value="food_no_seq.nextval">
      음식이름 :<input type="text" name="name"><br>
      <input type="submit" value="등록">
    </form>
    <br><br>
    
    
    
    <form action="delete_ok">
    
        <input type="submit"  value="삭제" ><br>
      	<input type="hidden" name="table"  value="t_food" >
      	<input type="hidden" name="status"  value="food_status" >
      	<input type="hidden" name="no"  value="food_no" >
    <table border="1">
    <tr>
    <th>No</th>
    <th>음식이름</th>
   
    
    
    
  </tr>
  <c:forEach var="a" items="${food}">
    <tr>
      <td>${a.FOOD_NO}</td>
      <td>${a.FOOD_NAME}</td>
      <td>
        <input type="checkbox" name="deleteCheckbox" class="deleteCheckbox" value="${a.FOOD_NO}" >
      </td>
      </tr>
      </c:forEach>
      </table>
      </form>
  </div>
  <div id="tab2" class="tab-content ${currentTab == 'tab2' ? 'active' : ''}">
    <h2>가격</h2>
    <!-- 가격 내용 -->
    <form action="price_ok">
    <input type="submit" value="등록"><br><br>
    가격:<input type="text" name="price">원<br><br>
    
    
    가격등록 안된 음식 리스트<br>
    <table border="1">
  <tr>
    <th>No</th>
    <th>음식이름</th>
  </tr>
  <c:forEach var="a" items="${no_price}">
    <tr>
      <td>${a.FOOD_NO}</td>
      <td>${a.FOOD_NAME}</td>
      <td>
        <input type="checkbox" name="priceCheckbox" class="price-checkbox" value="${a.FOOD_NO}">
      </td>
    </tr>
  </c:forEach>
</table>
</form>
  </div>
  <div id="tab3" class="tab-content ${currentTab == 'tab3' ? 'active' : ''}">
    <h2>사진</h2>
    <!-- 사진 내용 -->
    <form action="photo_ok" method="post" enctype="multipart/form-data">
    <input type="submit" value="등록"><br><br>
    사진이름:<input type="text" name="photo_name"><br>
    사진URL:<input type="file" name="photo_url"><br>
    사진타입:<input type="text" name="photo_type">
    
    <br><br>
    
    
    사진등록 안된 음식 리스트<br>
     <table border="1">
  <tr>
    <th>No</th>
    <th>음식이름</th>
  </tr>
  <c:forEach var="a" items="${no_url}">
    <tr>
      <td>${a.FOOD_NO}</td>
      <td>${a.FOOD_NAME}</td>
      <td>
        <input type="checkbox" name="photoCheckbox" class="photo-checkbox" value="${a.FOOD_NO}">
      </td>
    </tr>
  </c:forEach>
</table>
</form>
  </div>
  <div id="tab4" class="tab-content ${currentTab == 'tab4' ? 'active' : ''}">
    <h2>식당</h2>
    <!-- 식당 내용 -->
    <form action="food_manage_ok_2">
      식당이름 :<input type="text" name="restaurant_name"><br>
      식당후기 :<input type="text" name="restaurant_review"><br>
      <input type="submit" value="등록">
    </form>
    <br><br>
    
    <form action="delete_ok">
    
        <input type="submit"  value="삭제" ><br>
      	<input type="hidden" name="table"  value="t_RESTAURANT" >
      	<input type="hidden" name="status"  value="RESTAURANT_status" >
      	<input type="hidden" name="no"  value="RESTAURANT_no" >
    <table border="1">
    <tr>
    <th>No</th>
    <th>식당이름</th>
    <th>식당후기</th>
    
  </tr>
  <c:forEach var="a" items="${rest}">
    <tr>
      <td>${a.RESTAURANT_NO}</td>
      <td>${a.RESTAURANT_NAME}</td>
      <td>${a.RESTAURANT_REVIEW}</td>
      <td>
        <input type="checkbox" name="deleteCheckbox" class="deleteCheckbox" value="${a.RESTAURANT_NO}" >
      </td>
      </tr>
      </c:forEach>
      </table>
      </form>
  </div>
  <div id="tab5" class="tab-content ${currentTab == 'tab5' ? 'active' : ''}">
    <h2>지역</h2>
    <!-- 지역 내용 -->
    <form action="food_manage_ok">
      <input type="hidden" name="table" value="t_region">
      <input type="hidden" name="seq" value="region_no_seq.nextval">
      지역이름 :<input type="text" name="name"><br>
      <input type="submit" value="등록">
    </form>
    <br><br>
    
    <form action="delete_ok">
    
        <input type="submit"  value="삭제" ><br>
      	<input type="hidden" name="table"  value="t_region" >
      	<input type="hidden" name="status"  value="region_status" >
      	<input type="hidden" name="no"  value="region_no" >
    <table border="1">
    <tr>
    <th>No</th>
    <th>지역이름</th>
    
    
  </tr>
  <c:forEach var="a" items="${region}">
    <tr>
      <td>${a.REGION_NO}</td>
      <td>${a.REGION_NAME}</td>
      <td>
        <input type="checkbox" name="deleteCheckbox" class="deleteCheckbox" value="${a.REGION_NO}" >
      </td>
      </tr>
      </c:forEach>
      </table>
      </form>
  </div>
  <div id="tab6" class="tab-content ${currentTab == 'tab6' ? 'active' : ''}">
    <h2>타입</h2>
    <!-- 타입 내용 -->
    <form action="food_manage_ok">
      <input type="hidden" name="table" value="t_type">
      <input type="hidden" name="seq" value="type_no_seq.nextval">
     
      타입이름 :<input type="text" name="name"><br>
      <input type="submit" value="등록"><br>
    </form>
    <br><br>
    
    <form action="delete_ok">
    
        <input type="submit"  value="삭제" ><br>
      	<input type="hidden" name="table"  value="t_type" >
      	<input type="hidden" name="status"  value="type_status" >
      	<input type="hidden" name="no"  value="type_no" >
    <table border="1">
    <tr>
    <th>No</th>
    <th>타입이름</th>
    
    
  </tr>
  <c:forEach var="a" items="${type}">
    <tr>
      <td>${a.TYPE_NO}</td>
      <td>${a.TYPE_NAME}</td>
      <td>
        <input type="checkbox" name="deleteCheckbox" class="deleteCheckbox" value="${a.TYPE_NO}" >
      </td>
      </tr>
      </c:forEach>
      </table>
      </form>
  </div>
  <div id="tab7" class="tab-content ${currentTab == 'tab7' ? 'active' : ''}">
    <h2>등록</h2>
    <!-- 등록 내용 -->
    

<div>
<form action="Navi_ok">

<input type="submit" value="등록"> <br><br>
  <table border="1">
  <tr>
    <th>No</th>
    <th>음식이름</th>
    <th>음식가격</th>
    <th>음식사진</th>
    <th></th>
  </tr>
  <c:forEach var="a" items="${price_url}">
    <tr>
      <td>${a.FOOD_NO}</td>
      <td>${a.FOOD_NAME}</td>
      <td>${a.PRICE_WON}</td>
      <td>${a.PHOTO_URL}</td>
      <td>
        <input type="checkbox" name="foodCheckbox" class="food-checkbox" value="${a.FOOD_NO}">
      </td>
    </tr>
  </c:forEach>
</table>
<table border="1">
  <tr>
    <th>No</th>
    <th>식당이름</th>
    
    <th></th>
  </tr>
  <c:forEach var="a" items="${rest}">
    <tr>
      <td>${a.RESTAURANT_NO}</td>
      <td>${a.RESTAURANT_NAME}</td>
      
      <td>
        <input type="checkbox" name="restCheckbox" class="rest-checkbox" value="${a.RESTAURANT_NO}">
      </td>
    </tr>
  </c:forEach>
</table>
<table border="1">
  <tr>
    <th>No</th>
    <th>지역이름</th>
    <th></th>
  </tr>
  <c:forEach var="a" items="${region}">
    <tr>
      <td>${a.REGION_NO}</td>
      <td>${a.REGION_NAME}</td>
      <td>
        <input type="checkbox" name="regionCheckbox" class="region-checkbox" value="${a.REGION_NO}">
      </td>
    </tr>
  </c:forEach>
</table>
<table border="1">
  <tr>
    <th>No</th>
    <th>타입이름</th>
    <th></th>
  </tr>
  <c:forEach var="a" items="${type}">
    <tr>
      <td>${a.TYPE_NO}</td>
      <td>${a.TYPE_NAME}</td>
      <td>
        <input type="checkbox" name="typeCheckbox" class="type-checkbox" value="${a.TYPE_NO}">
      </td>
    </tr>
  </c:forEach>
</table>
</form>

<br><br>

<form action="delete_ok">
    
        <input type="submit"  value="삭제" ><br>
      	<input type="hidden" name="table"  value="t_navi" >
      	<input type="hidden" name="status"  value="navi_status" >
      	<input type="hidden" name="no"  value="navi_no" >

현재 등록된 맛집리스트<br>
<table border="1">
  <tr>
    <th>No</th>
    <th>식당이름</th>
    <th>음식이름</th>
    <th>지역이름</th>
    <th>타입이름</th>
  </tr>

  <c:forEach var="a" items="${navi}">
    <tr>
    <td>${a.NAVI_NO}</td>
      <td>${a.RESTAURANT_NAME}</td>
      <td>${a.FOOD_NAME}</td>
      <td>${a.REGION_NAME}</td>
      <td>${a.TYPE_NAME}</td>
      <td>
        <input type="checkbox" name="deleteCheckbox" class="deleteCheckbox" value="${a.NAVI_NO}" >
      </td>
      
    </tr>
  </c:forEach>
</table>

</form>
</div>

  </div>
</div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
  $(document).ready(function() {
    // 탭 클릭 이벤트 처리
    $(".tab-link").click(function() {
      // 클릭한 탭의 data-tab 값을 가져옴
      var tabId = $(this).attr("data-tab");

      // 모든 탭과 탭 내용 숨김
      $(".tab-link").removeClass("active");
      $(".tab-content").removeClass("active");

      // 선택한 탭과 탭 내용 표시
      $(this).addClass("active");
      $("#" + tabId).addClass("active");
    });
  });
</script>
<script>
  $(document).ready(function() {
    // 탭 클릭 이벤트 처리
    $(".tab-link").click(function() {
      // 클릭한 탭의 data-tab 값을 가져옴
      var tabId = $(this).attr("data-tab");

      // 모든 탭과 탭 내용 숨김
      $(".tab-link").removeClass("active");
      $(".tab-content").removeClass("active");

      // 선택한 탭과 탭 내용 표시
      $(this).addClass("active");
      $("#" + tabId).addClass("active");

      // 서버로 탭 정보 전송
      $.ajax({
        url: "/save_tab_to_session",
        method: "POST",
        data: { "tabId": tabId },
        success: function(response) {
          console.log("탭 정보를 세션에 저장했습니다.");
        },
        error: function(xhr, status, error) {
          console.error("탭 정보 저장에 실패했습니다.", error);
        }
      });
    });
  });
</script>
<script>
  var checkboxes = document.querySelectorAll('input[type="checkbox"]');

  for (var i = 0; i < checkboxes.length; i++) {
    checkboxes[i].addEventListener('change', function () {
      var checkboxGroup = this.closest('table');
      var groupCheckboxes = checkboxGroup.getElementsByClassName(this.classList[0]);

      for (var j = 0; j < groupCheckboxes.length; j++) {
        if (groupCheckboxes[j] !== this) {
          groupCheckboxes[j].checked = false;
        }
      }
    });
  }
</script>
</body>
</html>
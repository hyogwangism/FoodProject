/**
 * 
 */
 
/* 탭 검색목록 */
function searchFoodList(){
      $('.tabmenu').click(function(e) { // <- e는 클릭 당한(사용자가 클릭한) 객체의 이벤트
       
         $('li').css('background-color', 'white');
         $(this).css('background-color', 'green');
         
         console.log($(e.target).attr('data-tab'));
      
         // 나중에 console.log(e)찍어보기
         // 나중에 console.log(e.target) 찍어보기
         // e.target -> 사용자가 클릭한 li 를 가리킨다
         
         //alert(kind);
         $.ajax({
            // 데이터 준비단계 1
            type : 'POST',                 //get방식으로 통신
            url : "/food_list",    //탭의 data-tab속성의 값 컨트롤러 주소
            data : {
               "food_type": $(e.target).attr('value')
            },
            datatype:"json",
            
            // 성공했을 때 -> 200번
            success : function(res) {    //<- 여기서  data 는 board/ajax의 return, data는 jsp 코드
               document.querySelector('.foodList').innerHTML = "";
               for(let i=0; i<res.length; i++){
               const html = `
             					<div class="overview">
             					
						<a href="food_cont?food_no=${res[i].food_no}"><img src="/upload${res[i].food_photo01}" /></a>
						<p>
							<span><b>식당명</b></span>&nbsp;&nbsp;&nbsp;${res[i].food_place}<br> 
							<span><b>상품명</b></span>&nbsp;&nbsp;&nbsp;${res[i].food_name}<br>
							<span><b>지역</b></span>&nbsp;&nbsp;&nbsp;${res[i].food_location}<br>
						</p>
					</div>    
               `
             	$('.foodList').append(html); 
               }
            }
         });
      });      
   }//searchFoodList()
   
   

/* 체크박스 검색목록 */   
$('.search_div1 input[type=checkbox], .search_div2 input[type=checkbox]').on('change', function() {
     var selectedValues1 = [];
     var selectedValues2 = [];
     
     $('.search_div1 input[type=checkbox]:checked').each(function() {
       selectedValues1.push($(this).val());
     });
     
     $('.search_div2 input[type=checkbox]:checked').each(function() {
          selectedValues2.push($(this).val());
        });

     
     $.ajax({
       type: 'POST',
       url: '/food_list2',
       data: JSON.stringify({list1 : selectedValues1, list2 : selectedValues2}),
       contentType: 'application/json',
       dataType: 'json',
       success: function(res) {         
                  document.querySelector('.foodList').innerHTML = "";
               for(let i=0; i<res.length; i++){
               const html = `
             					<div class="overview">
						<a href="food_cont?food_no=${res[i].food_no}"><img src="/upload${res[i].food_photo01}"/></a>
						<p>
							<span><b>식당명</b></span>&nbsp;&nbsp;&nbsp;${res[i].food_place}<br> 
							<span><b>상품명</b></span>&nbsp;&nbsp;&nbsp;${res[i].food_name}<br>
							<span><b>지역</b></span>&nbsp;&nbsp;&nbsp;${res[i].food_location}<br>
						</p>
					</div>    
               `
             	$('.foodList').append(html); 
               } 
               } 
			,

      error: function(jqXHR, textStatus, errorThrown) {
        // 에러 처리
      }
    });
 });  


/* 검색필드 검색목록 */    
function search_FoodList(){
	//$('#searchBtn').click(function(e) {
	   var searchField =  $('#search_field').val();
	   var searchType =  $('#search_type').val();



  $.ajax({//$는 jQuery란 뜻. $.ajax 뜻은 jQuery 내의 아작스 실행
      url:"/search_FoodList", //url 패턴 매핑주소 경로
      type:"POST",//데이터를 서버로 보내는 방법
      data: {
      	"search_field" : searchField,
      	"search_type" : searchType
      },  
      datatype: "json",//서버의 실행된 결과값을 사용자로 받아오는 자료형
		traditional : true,
      success: function (res) {	
                  document.querySelector('.foodList').innerHTML = "";
               for(let i=0; i<res.length; i++){
               const html = `
             					<div class="overview">
             					<a href="food_cont?food_no=${res[i].food_no}"><img src="/upload${res[i].food_photo01}"/></a>
						<p>
							<span><b>식당명</b></span>&nbsp;&nbsp;&nbsp;${res[i].food_place}<br> 
							<span><b>상품명</b></span>&nbsp;&nbsp;&nbsp;${res[i].food_name}<br>
							<span><b>지역</b></span>&nbsp;&nbsp;&nbsp;${res[i].food_location}<br>
						</p>
					</div>    
               `
             	$('.foodList').append(html); 
               }   
	  }
    });//$.ajax
}

/*
function Food_Like() {
  var likeval = parseInt(document.getElementById("likeCount").value);
  console.log(likeval);
	
	
  
  if (likeval > 0) {
    console.log(likeval + "찜하기 취소");
    $('#LikeBtn').html("찜하기 취소");
    $('#LikeBtn').click(function() {
   
      $.ajax({
        type: 'post',
        url: '/likeDown',
        contentType: 'application/json',
        data: JSON.stringify({
          "food_no" : parseInt(document.getElementById("foodNo").value),
          "session_id" : document.getElementById("session_id").value
        }),
        success: function(res) {
          console.log(res);
          console.log('취소 성공염');
        }
      });
    });
  } else {
    console.log(likeval + "찜하기 클릭");
    console.log(session_id);
    $('#LikeBtn').html("찜하기");
    $('#LikeBtn').click(function() {
    
      $.ajax({
        type: 'post',
        url: '/likeUp',
        contentType: 'application/json',
        data: JSON.stringify({
          "food_no" : parseInt(document.getElementById("foodNo").value),
          "session_id" : document.getElementById("session_id").value
        }),
        success: function(res) {
        console.log(res);
         console.log('성공염');
        }
      });
    });
  }
  $('#LikeBtn').html(likeval > 0 ? "찜하기 취소" : "찜하기");
}
*/

/*
$('.search_div1').on('change', 'input[type=checkbox]', function() {
    var typeValues = [];
    $('input[name=food_type]:checked').each(function() {
      typeValues.push($(this).val());
    });

    var locationValues = [];
      $('input[name=food_location]:checked').each(function() {
      locationValues.push($(this).val());
    });

    var checkedList = [{"food_type" : typeValues, "food_location" : locationValues}];
    
    alert(typeValues); // 선택된 체크박스의 값 확인

    
    $.ajax({
      type: 'POST',
      url: '/food_list2',
      data: JSON.stringify(checkedList),
      contentType: 'application/json',
      dataType: 'json',
      success: function(res) {         
      	$('.foodList').empty();
         
         for(let i =0; i<res.length; ++i){
         
         
         const html = `
         <li>
               <div >
               <a href="/food_cont?food_no=${res[i].food_no}"><img src="../upload${res[i].food_photo}" width="300" height="200" /></a>
               </div>
               
                <div >
         <p>
          <span>상품명</span>${res[i].food_name}<br />
          <span>개당 가격</span>${res[i].food_price} 원<br />             
         </p>
               </div>
            </li>     
         `
         
         
         $('.foodList').append(html);
         }
         
       },

      error: function(jqXHR, textStatus, errorThrown) {
        // 에러 처리
      }
    });
    });
    
    */   
/* 
   function searchFunction(){
	// $('#searchBtn').click(function(e) {
	   var find_field =  $('#find_field').val();

  $.ajax({//$는 jQuery란 뜻. $.ajax 뜻은 jQuery 내의 아작스 실행
      url:"/user_search", //url 패턴 매핑주소 경로
      type:"POST",//데이터를 서버로 보내는 방법
      data: {
      	"user_name" : userName
      },  //좌측 id 피라미터 이름에 우측 $mem_id변수값을 저장
      datatype: "json",//서버의 실행된 결과값을 사용자로 받아오는 자료형
		traditional : true,
      success: function (res) {	
			 document.getElementById('ajaxTable').innerHTML = "";
		 for(let i = 0; i < res.length; i++){
			 const html = `
				<tr>
			 <td>${res[i].user_no}</td>
			 <td>${res[i].user_name}</td>
			 <td>${res[i].user_age}</td>
			 <td>${res[i].user_gender}</td>
			 <td>${res[i].user_email}</td>
			 </tr>
			 `
             //통신 성공시 탭 내용담는 div를 읽어들인 값으로 채운다.
             $('#ajaxTable').append(html); 
  		 }
	  }
    });//$.ajax
    	//  });
}
   */
   
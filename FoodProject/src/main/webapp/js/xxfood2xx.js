/**
 * 
 */
 
/* 탭 검색목록 */
function searchFoodList(){
      $('.tabmenu').click(function(e) { // <- e는 클릭 당한(사용자가 클릭한) 객체의 이벤트
       
         $('li').css('background-color', 'white');
         $(this).css('background-color', 'green');
         
     var selectedValues1 = [];
     var selectedValues2 = [];
     
     $('.search_div1 input[name=food_type]:checked').each(function() {
       selectedValues1.push($(this).val());
     });
     
     $('.search_div2 input[name=food_location]:checked').each(function() {
          selectedValues2.push($(this).val());
        });
        
     	   var searchField =  $('#search_field').val();
	   var searchType =  $('#search_type').val();   

         $.ajax({
            // 데이터 준비단계 1
            type : 'POST',                 //get방식으로 통신
            //contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            url : "/food_list",    //탭의 data-tab속성의 값 컨트롤러 주소
            data : JSON.stringify({"food_type": $(e.target).attr('data-tab') 
            , "list1" : selectedValues1, "list2" : selectedValues2
            , "search_type" : searchType, "search_field" : searchField
            })
               
               ,
            datatype:"json",
            
            // 성공했을 때 -> 200번
            success : function(res) {    //<- 여기서  data 는 board/ajax의 return, data는 jsp 코드
               document.querySelector('.foodList').innerHTML = "";
               for(let i=0; i<res.length; i++){
               const html = `
             					<div class="overview">
						<img src="/upload${res[i].food_photo01}" onclick="location.href='food_cont?food_no=${res[i].food_no}'"/><br>
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
   
   

/* 체크박스 검색목록  
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
						<img src="/upload${res[i].food_photo01}" onclick="location.href='food_cont?food_no=${res[i].food_no}'"/><br>
						<p>
							<span><b>식당명</b></span>&nbsp;&nbsp;&nbsp;${res[i].food_place}<br> 
							<span><b>상품명</b></span>&nbsp;&nbsp;&nbsp;${res[i].food_name}<br>
							<span><b>지역</b></span>&nbsp;&nbsp;&nbsp;${res[i].food_location}<br>
						</p>
					</div>    
               `
             	$('.foodList').append(html); 
               }   
               	 ,

      error: function(jqXHR, textStatus, errorThrown) {
        // 에러 처리
      }
    });
 });  
 */ 

/* 검색필드 검색목록   
function search_FoodList(){
	//$('#searchBtn').click(function(e) {
	   var searchField =  $('#search_field').val();
	   var searchType =  $('#search_type').val();

  $.ajax({//$는 jQuery란 뜻. $.ajax 뜻은 jQuery 내의 아작스 실행
      url:"/food_list2", //url 패턴 매핑주소 경로
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
						<img src="/upload${res[i].food_photo01}" onclick="location.href='food_cont?food_no=${res[i].food_no}'"/><br>
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
 */ 
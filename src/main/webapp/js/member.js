
	function join_Check(){
    
    if($.trim($('#user_id').val())==""){
      alert('아이디를 입력하세요');
      $('#user_id').val('').focus();
      return false;
    }

    var idReg = /^[a-z]+[a-z0-9]{3,19}$/g;
        if( !idReg.test( $("input[name=user_id]").val() ) ) {
            alert("아이디는 영문자로 시작하는 4~20자 영문자 또는 숫자이어야 합니다.");
            return false;
        }

   $user_pwd=$.trim($("#user_pwd").val());
   if($user_pwd == ""){
      alert("비밀번호를 입력하세요!");
      $("#user_pwd").val("").focus();
      return false;
   }

      var pwdReg = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/;
        if( !pwdReg.test( $("input[name=user_pwd]").val() ) ) {
            alert("영문 숫자 특수기호 조합 8자리 이상으로 입력하세요.");
            return false;
        }

   $user_pwd2=$.trim($("#user_pwd2").val());
   if($.trim($("#user_pwd2").val())==""){
      alert("비밀번호 확인을 입력하세요!");
      $("#user_pwd2").val("").focus();
      return false;
   }

   if($user_pwd != $user_pwd2){
      alert("비밀번호가 다릅니다!");
      $("#user_pwd").val("");//비번 입력박스를 초기화
      $("#user_pwd2").val("");
      $("#user_pwd").focus();
      return false;
   }
   
   if($.trim($("#user_name").val())==""){
      alert("회원이름을 입력하세요!");
      $("#user_name").val("").focus();
      return false;
   }
     let regexName = /^[ㄱ-힣]{2,30}$/;
     if(!regexName.test($("#user_name").val())){
      alert("회원이름은 한글로 두 글자 이상 입력해주세요!");
      $("#user_name").val("").focus();
      return false;
   }
   
	if(!$("input[name='user_gender']:checked").val()) {
  	 alert("성별을 선택하세요!");
 	  return false;
	}
   
   if($.trim($("#user_birth").val())==""){
      alert("생년월일 입력하세요!");      
      return false;
   }

    let regexBirth = /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;
    if(!regexBirth.test($("#user_birth").val())){
      alert("생년월일은 예)1999년 1월 1일 -> 19990101로 입력해주세요");      
      $("#user_birth").val("").focus();
      return false;
   }

   if($.trim($("#user_email").val())==""){
      alert("이메일 입력하세요!");
      $("#user_email").val("").focus();
      return false;
   }

   let regexEmail = /^[a-zA-Z][\w]+@[a-zA-Z]+\.(com|net|co\.kr|or\.kr)$/;
    if(!regexEmail.test($("#user_email").val())) {
      alert("이메일 형식으로 입력해주세요!");
      $("#user_email").val("").focus();
      return false;
   } 

   if($.trim($("#user_phone").val())==""){
      alert("폰번호를 입력하세요!");
      $("#user_phone").val("").focus();
      return false;
   }

     let regexPhone = /^[0-9]{11}$/;
    if(!regexPhone.test($("#user_phone").val())){
      alert("폰번호는 예)010-1234-5678 -> 01012345678로 입력해주세요!");
      $("#user_phone").val("").focus();
      return false;
   }
   
  }
  
  	function edit_Check(){
    
    if($.trim($('#user_id').val())==""){
      alert('아이디를 입력하세요');
      $('#user_id').val('').focus();
      return false;
    }

    var idReg = /^[a-z]+[a-z0-9]{3,19}$/g;
        if( !idReg.test( $("input[name=user_id]").val() ) ) {
            alert("아이디는 영문자로 시작하는 6~20자 영문자 또는 숫자이어야 합니다.");
            return false;
        }

   
   if($.trim($("#user_name").val())==""){
      alert("회원이름을 입력하세요!");
      $("#user_name").val("").focus();
      return false;
   }
     let regexName = /^[ㄱ-힣]{2,30}$/;
     if(!regexName.test($("#user_name").val())){
      alert("회원이름은 한글로 두 글자 이상 입력해주세요!");
      $("#user_name").val("").focus();
      return false;
   }

   
	if(!$("input[name='user_gender']:checked").val()) {
  	 alert("성별을 선택하세요!");
 	  return false;
	}

   if($.trim($("#user_birth").val())==""){
      alert("생년월일 입력하세요!");      
      return false;
   }

    let regexBirth = /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;
    if(!regexBirth.test($("#user_birth").val())){
      alert("생년월일은 예)1999년 1월 1일 -> 19990101로 입력해주세요");      
      $("#user_birth").val("").focus();
      return false;
   }

   if($.trim($("#user_email").val())==""){
      alert("이메일 입력하세요!");
      $("#user_email").val("").focus();
      return false;
   }

   let regexEmail = /^[a-zA-Z][\w]+@[a-zA-Z]+\.(com|net|co\.kr|or\.kr)$/;
    if(!regexEmail.test($("#user_email").val())) {
      alert("이메일 형식으로 입력해주세요!");
      $("#user_email").val("").focus();
      return false;
   } 

   if($.trim($("#user_phone").val())==""){
      alert("폰번호를 입력하세요!");
      $("#user_phone").val("").focus();
      return false;
   }

     let regexPhone = /^[0-9]{11}$/;
    if(!regexPhone.test($("#user_phone").val())){
      alert("폰번호는 예)010-1234-5678 -> 01012345678로 입력해주세요!");
      $("#user_phone").val("").focus();
      return false;
   }
   
  }
  
  
  	function pwd_Check(){

   $user_pwd=$.trim($("#user_pwd").val());

   if($user_pwd == ""){
      alert("현재 비밀번호를 입력하세요!");
      $("#user_pwd").val("").focus();
      return false;
   }


   $new_pwd=$.trim($("#new_pwd").val());
   $new_pwd2=$.trim($("#new_pwd2").val());
   if($.trim($("#new_pwd").val())==""){
      alert("새 비밀번호를 입력하세요!");
      $("#new_pwd").val("").focus();
      return false;
   }
      var pwdReg = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/;
        if( !pwdReg.test( $("input[name=new_pwd]").val() || $("input[name=new_pwd2]").val() ) ) {
            alert("영문 숫자 특수기호 조합 8자리 이상으로 입력하세요.");
            return false;
        }
        
    if($.trim($("#new_pwd2").val())==""){
      alert("새 비밀번호 확인을 입력하세요!");
      $("#new_pwd2").val("").focus();
      return false;
   }
   
   if($new_pwd != $new_pwd2){
      alert("비밀번호가 다릅니다!");
      $("#new_pwd").val("");//비번 입력박스를 초기화
      $("#new_pwd2").val("");
      $("#new_pwd").focus();
      return false;
   }
  }
  

//관리자 회원관리페이지
$(document).ready(function(){
	searchFunction();
	});
//회원검색
function searchFunction(){
	//$('#searchBtn').click(function(e) {
	   var searchField =  $('#search_field').val();
	   var searchType =  $('#search_type').val();

  $.ajax({//$는 jQuery란 뜻. $.ajax 뜻은 jQuery 내의 아작스 실행
      url:"/member_search", //url 패턴 매핑주소 경로
      type:"POST",//데이터를 서버로 보내는 방법
      data: {
      	"search_field" : searchField,
      	"search_type" : searchType
      },  //좌측 id 피라미터 이름에 우측 $mem_id변수값을 저장
      datatype: "json",//서버의 실행된 결과값을 사용자로 받아오는 자료형
		traditional : true,
      success: function (res) {	
			 document.getElementById('ajaxTable').innerHTML = "";
		 for(let i = 0; i < res.length; i++){
			 const html = `
				<tr>
			 <td>${res[i].user_id}</td>
			 <td>${res[i].user_name}</td>
			 <td>${res[i].user_gender}</td>
			 <td>${res[i].user_birth}</td>
			 <td>${res[i].user_email}</td>
			 <td>${res[i].user_phone}</td>
			 </tr>
			 `
             //통신 성공시 탭 내용담는 div를 읽어들인 값으로 채운다.
             $('#ajaxTable').append(html); 
  		 }
	  }
    });//$.ajax
    	//  });
/* end */	
//});
}




function registerFunction(){
	 $('#registerBtn').click(function(e) {
	   var userName =  $('#registerName').val();
	   var userAge =  $('#registerAge').val();
	   var userGender =  $('input[name=registerGender]:checked').val();
	   var userEmail =  $('#registerEmail').val();

  $.ajax({//$는 jQuery란 뜻. $.ajax 뜻은 jQuery 내의 아작스 실행
      url:"/user_register", //url 패턴 매핑주소 경로
      type:"POST",//데이터를 서버로 보내는 방법
      data: {
		"registerName" : userName
      	,"registerAge" : userAge
		,"registerGender" : userGender
		,"registerEmail" : userEmail
      },  //좌측 id 피라미터 이름에 우측 $mem_id변수값을 저장
      datatype: "json",//서버의 실행된 결과값을 사용자로 받아오는 자료형
		traditional : true,
      success: function (res) {
     
			alert('회원등록 성공')	
			document.getElementById('registerName').value = '';
			document.getElementById('registerAge').value = '';
			document.getElementById('registerEmail').value = '';
			searchFunction();
			
  		 }
	  });
    });//$.ajax
    	  }
	
	
	function withdrawal_check() {
		var answer;
		answer=confirm("회원탈퇴를 하시겠습니까?");
							
		if(answer == true){
			return true;
		} else if (answer == false){
			return false;
		}
	}
	
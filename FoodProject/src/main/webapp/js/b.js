/**
 * b.js
 */
function edit_check(){
	if($.trim($("#writer").val())==""){
		alert("글쓴이를 입력하세요!");
		$("#writer").val("").focus();
		return false;
	}
	if($.trim($("#title").val())==""){
		alert("제목을 입력하세요!");
		$("#title").val("").focus();
		return false;
	}
	if($.trim($("#content").val())==""){
		alert("내용을 입력하세요!");
		$("#content").val("").focus();
		return false;
	}
}
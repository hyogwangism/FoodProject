
	/*
	 * 댓글기능
	 */
	 //댓글 등록
	function CommentWrite(){

	$.ajax({
		type: 'post',
		url: '/CommentWrite',
		contentType: 'application/json',
		data: JSON.stringify({
		  "NAVI_NO" : parseInt(document.getElementById("NAVI_NO").value),
		  "session_id" : $('#session_id').val(),
		  "InputComment" : $('#InputComment').val()
		}),
		success: function(res) {
				alert('댓글 등록 성공')	
				document.getElementById('InputComment').value = '';
				CommentList();
		}
	});
}

	 //댓글 목록 조회

$(document).on('click', '.page-link', function() {
	const page = $(this).data('page');
	console.log('page:'+page);
	CommentList(page);
  });

function CommentList(currentPage) {

  $.ajax({
    type: 'post',
    url: '/CommentList',
	contentType: 'application/json',
    data: JSON.stringify({
      "NAVI_NO": $('#NAVI_NO').val(),
	  "currentPage" : currentPage
    }),
    dataType: "json",
    traditional: true,
    success: function (res) {
      var clist = res.clist;
      var totalPages = res.totalPages;

	  document.getElementById('CommentList').innerHTML = "";
      for (var i = 0; i < clist.length; i++) {
        var html =
          `
          <span><b>${clist[i].user_id}</b></span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
          <span>${clist[i].contents}</span> &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; 
          <div align="right">`;

        if ($('#session_id').val() === clist[i].user_id) {
          html += `
    		<span><button type="button" onclick="return Del_check(${clist[i].comment_no});">삭제</button></span>`;
        }

        html += `
          <span style="font-size: 13px;">${clist[i].comment_date.substring(0, 10)}</span>
          </div>
          <div class="comment_hr-line"></div>
          <br><br>`;

        $('#CommentList').append(html);
      }

	  const maxPageLinks = 10; // 한 번에 표시할 최대 페이지 링크 개수
	  const blockSize = 10; // 한 블록의 페이지 개수
	  let startPage;
	  let endPage;

	  if (currentPage <= maxPageLinks) {
		startPage = 1;
		endPage = Math.min(totalPages, maxPageLinks);
	  } else {
		const currentBlock = Math.ceil(currentPage / blockSize);
		startPage = (currentBlock - 1) * blockSize + 1;
		endPage = Math.min(startPage + blockSize - 1, totalPages);
	  }
	
	  // 페이지 링크 생성 및 추가
	  $('.page').empty();
	
	  if (currentPage > 1) {
		const prevPageHtml = `<span class="page-link" data-page="${currentPage - 1}" style="display: inline-block; margin: 0 5px; cursor: pointer;"><< 이전</span>`;
		$('.page').append(prevPageHtml);
	  }
	
	  for (let i = startPage; i <= endPage; i++) {
		let pageHtml;
		if (i === currentPage) {
		  pageHtml = `<b><span class="page-link" data-page="${i}" style="display: inline-block; margin: 0 5px; cursor: pointer; color:blue;">[${i}]</span></b>`;
		} else {
		  pageHtml = `<span class="page-link" data-page="${i}" style="display: inline-block; margin: 0 5px; cursor: pointer; color:blue;">[${i}]</span>`;
		}
		$('.page').append(pageHtml);
	  }
	
	  if (currentPage < totalPages) {
		const nextPageHtml = `<span class="page-link" data-page="${currentPage + 1}" style="display: inline-block; margin: 0 5px; cursor: pointer;">>> 다음</span>`;
		$('.page').append(nextPageHtml);
	  }
    }
  });
}

  
function CommentDel(comment_no) {
  $.ajax({
    type: 'post',
    url: '/CommentDel',
    contentType: 'application/json',
    data: JSON.stringify({
      "comment_no": comment_no
    }),
    success: function(res) {
      alert('댓글 삭제 성공');
      CommentList();
    }
  });
}

	function Del_check(comment_no) {
		var answer;
		answer=confirm("댓글을 삭제 하시겠습니까?");
							
		if(answer == true){
			CommentDel(comment_no);
			return true;
		} else if (answer == false){
			return false;
		}
	}
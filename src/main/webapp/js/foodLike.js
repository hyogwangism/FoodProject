
/*
 * 좋아요 기능
 */
  $(document).ready(function() {
	  
	  $('.LikeBtn').click(function() {
	    var likeval = parseInt(document.getElementById("likeCount").value);
	    console.log(likeval);

	    if (likeval > 0) {
	      console.log(likeval + "좋아요 누름");
	      $.ajax({
	        type: 'post',
	        url: '/likeDown',
	        contentType: 'application/json',
	        data: JSON.stringify({
	          "NAVI_NO" : parseInt(document.getElementById("NAVI_NO").value),
	          "session_id" : document.getElementById("session_id").value
	        }),
	        success: function(data) {
	          console.log('취소 성공염');
	          $('.LikeBtn').html("찜하기");
	          document.getElementById("likeCount").value = 0;
	        }
	      });
	    } else if (likeval == 0) {
	      console.log(likeval + "좋아요 안누름");
	      console.log(session_id);
	      $.ajax({
	        type: 'post',
	        url: '/likeUp',
	        contentType: 'application/json',
	        data: JSON.stringify({
	          "NAVI_NO" : parseInt(document.getElementById("NAVI_NO").value),
	          "session_id" : document.getElementById("session_id").value
	        }),
	        success: function(data) {
	          console.log('성공염');
	          $('.LikeBtn').html("찜하기 취소");
	          document.getElementById("likeCount").value = 1;
	        }
	      });
	    }
	  });

	  var likeval = parseInt(document.getElementById("likeCount").value);
	  $('.LikeBtn').html(likeval > 0 ? "찜하기 취소" : "찜하기");
	});
	

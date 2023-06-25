package net.daum.vo;

import lombok.Data;

@Data
public class FoodCommentVO {

	private int comment_no; //댓글 시퀀스
	private int comment_navi_no; //게시글 번호
	private String user_id; //유저 ID
	private String contents; //댓글 내용
	private String comment_date; //댓글 게시일

}

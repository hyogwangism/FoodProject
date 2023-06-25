package net.daum.vo;

import lombok.Data;

@Data
public class FoodLikeVO {

	private int like_no; //좋아요 시퀀스
	private int like_navi_no; //게시글 번호
	private String session_id; //유저 ID
	private int like_cnt; //좋아요 카운트

}

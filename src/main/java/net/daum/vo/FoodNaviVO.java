package net.daum.vo;

import lombok.Data;

/*
 * 연결테이블 VO
 */

@Data
public class FoodNaviVO {
	private int NAVI_NO; //네비 번호
	private String RESTAURANT_NAME; //식당 이름
	private String FOOD_NAME; //음식 이름
	private String REGION_NAME; //지역 이름
	private String PHOTO_URL; //사진 경로
	private String TYPE_NAME; //음식 종류
	private String RESTAURANT_REVIEW; //식당후기
	private String PRICE_WON; //음식 가격
}

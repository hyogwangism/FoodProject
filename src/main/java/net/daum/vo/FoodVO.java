package net.daum.vo;

import java.util.List;

import lombok.Data;

@Data
public class FoodVO {
	private int food_no; //가게 번호
	private String food_place;//가게 이름 
	private String food_name; //음식명
	private String food_taste; //맛
	private String food_amount; //양
	private String food_location; //지역
	private String food_type; //종류
	private String food_space; //공간
	private String food_price; //가격
	private String food_parking; //주차
	private String food_review; //후기
	private String food_photo01; //사진파일
	private String food_photo02; //사진파일
	private String food_photo03; //사진파일
    private String search_type; //검색어 분류
    private String search_field; //검색어 영역
}

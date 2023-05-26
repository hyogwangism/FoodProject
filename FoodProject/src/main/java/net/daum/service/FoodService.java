package net.daum.service;

import java.util.List;
import java.util.Map;

import net.daum.vo.FoodLikeVO;
import net.daum.vo.FoodVO;
import net.daum.vo.Food_MemberVO;

public interface FoodService {

	void insertFood(FoodVO f);

	List<FoodVO> getFoodList(String food_type);

	FoodVO getFoodCont(int food_no);

	void updateFood(FoodVO f);

	void delFood(int food_no);

	List<FoodVO> getFoodAllList(FoodVO f);

	void insertJoin(Food_MemberVO fvo);

	Food_MemberVO loginCheck(String user_id);

	void editInfo(Food_MemberVO fvo);


	void pwdEdit(Food_MemberVO fvo);

	List<Food_MemberVO> getMemberList(Food_MemberVO fvo);

	List<FoodVO> selectFood(List<String> list1, List<String> list2, String food_type);

	List<FoodVO> searchFoodList(FoodVO f);

	List<FoodVO> selectFoodWhenAll(List<String> list1, List<String> list2);

	int getLikeCount(FoodLikeVO like);

	void likeUp(String session_id, int food_no);

	void likeDown(String session_id, int food_no);

	FoodLikeVO getFoodLike(String session_id, int food_no);

}

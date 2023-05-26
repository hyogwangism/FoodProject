package net.daum.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.FoodDAO;
import net.daum.vo.FoodLikeVO;
import net.daum.vo.FoodVO;
import net.daum.vo.Food_MemberVO;

@Service
public class FoodServiceImpl implements FoodService {
	@Autowired
	private FoodDAO foodDao;

	@Override
	public void insertFood(FoodVO f) {
		this.foodDao.insertFood(f);
	}

	@Override
	public List<FoodVO> getFoodList(String food_type) {
		return this.foodDao.getFoodList(food_type);
	}

	@Override
	public FoodVO getFoodCont(int food_no) {
		return this.foodDao.getFoodCont(food_no);
	}

	@Override
	public void updateFood(FoodVO f) {
		this.foodDao.updateFood(f);
	}

	@Override
	public void delFood(int food_no) {
		this.foodDao.delFood(food_no);
	}

	@Override
	public List<FoodVO> getFoodAllList(FoodVO f) {
		return this.foodDao.getFoodAllList(f);
	}

	@Override
	public void insertJoin(Food_MemberVO fvo) {
		this.foodDao.insertJoin(fvo);
	}

	@Override
	public Food_MemberVO loginCheck(String user_id) {
		return this.foodDao.loginCheck(user_id);
	}

	@Override
	public void editInfo(Food_MemberVO fvo) {
		this.foodDao.editInfo(fvo);
	}

	@Override
	public void pwdEdit(Food_MemberVO fvo) {
		this.foodDao.pwdEdit(fvo);
	}

	@Override
	public List<Food_MemberVO> getMemberList(Food_MemberVO fvo) {
		return this.foodDao.getMemberList(fvo);
	}

	@Override
	public List<FoodVO> selectFood(List<String> list1, List<String> list2, String food_type) {
		return this.foodDao.selectFood(list1, list2, food_type);
	}

	@Override
	public List<FoodVO> searchFoodList(FoodVO f) {
		return this.foodDao.searchFoodList(f);
	}

	@Override
	public List<FoodVO> selectFoodWhenAll(List<String> list1, List<String> list2) {
		return this.foodDao.selectFoodWhenAll(list1, list2);
	}

	@Override
	public int getLikeCount(FoodLikeVO like) {
		return this.foodDao.getLikeCount(like);
	}

	@Override
	public void likeUp(String session_id, int food_no) {
		this.foodDao.likeUp(session_id, food_no);
	}

	@Override
	public void likeDown(String session_id, int food_no) {
		this.foodDao.likeDown(session_id, food_no);
	}

	@Override
	public FoodLikeVO getFoodLike(String session_id, int food_no) {
		return this.foodDao.getFoodLike(session_id, food_no);
	}

}

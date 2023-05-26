package net.daum.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.FoodLikeVO;
import net.daum.vo.FoodVO;
import net.daum.vo.Food_MemberVO;

@Repository
public class FoodDAOImpl implements FoodDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertFood(FoodVO f) {
		this.sqlSession.insert("food_in", f);
	}

	@Override
	public List<FoodVO> getFoodList(String food_type) {
		return this.sqlSession.selectList("food_list", food_type);
	}

	//게시물 내용 가져오기(번호값 기준)
	@Override
	public FoodVO getFoodCont(int food_no) {
		return this.sqlSession.selectOne("food_cont", food_no);
	}

	@Override
	public void updateFood(FoodVO f) {
		this.sqlSession.update("food_edit", f);
	}

	@Override
	public void delFood(int food_no) {
		this.sqlSession.delete("food_del", food_no);
	}

	@Override
	public List<FoodVO> getFoodAllList(FoodVO f) {
		return this.sqlSession.selectList("food_all_list", f);
	}

	@Override
	public void insertJoin(Food_MemberVO fvo) {
		this.sqlSession.insert("member_join", fvo);
	}

	@Override
	public Food_MemberVO loginCheck(String user_id) {
		return this.sqlSession.selectOne("member_login", user_id);
	}

	@Override
	public void editInfo(Food_MemberVO fvo) {
		this.sqlSession.update("member_edit", fvo);
	}

	@Override
	public void pwdEdit(Food_MemberVO fvo) {
		this.sqlSession.update("member_pwdEdit", fvo);
	}

	@Override
	public List<Food_MemberVO> getMemberList(Food_MemberVO fvo) {
		return this.sqlSession.selectList("member_search", fvo);
	}

	@Override
	public List<FoodVO> selectFood(List<String> list1, List<String> list2, String food_type) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("list1", list1);
		paramMap.put("list2", list2);
		paramMap.put("food_type", food_type);
		
		return sqlSession.selectList("items", paramMap);
	}

	@Override
	public List<FoodVO> searchFoodList(FoodVO f) {
		return this.sqlSession.selectList("searchFoodList", f);
	}

	@Override
	public List<FoodVO> selectFoodWhenAll(List<String> list1, List<String> list2) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("list1", list1);
		paramMap.put("list2", list2);
		return sqlSession.selectList("items2", paramMap);
	}

	@Override
	public int getLikeCount(FoodLikeVO like) {
		return sqlSession.selectOne("like_count", like);
	}

	@Override
	public void likeUp(String session_id, int food_no) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("session_id", session_id);
		paramMap.put("food_no", food_no);
		sqlSession.insert("likeUp", paramMap);
	}

	@Override
	public void likeDown(String session_id, int food_no) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("session_id", session_id);
		paramMap.put("food_no", food_no);
		sqlSession.insert("likeDown", paramMap);
	}

	@Override
	public FoodLikeVO getFoodLike(String session_id, int food_no) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("session_id", session_id);
		paramMap.put("food_no", food_no);
		return sqlSession.selectOne("items3", paramMap);
	}


	
}

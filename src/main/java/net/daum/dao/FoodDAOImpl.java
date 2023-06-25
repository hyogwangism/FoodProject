package net.daum.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.FoodCommentVO;
import net.daum.vo.FoodLikeVO;
import net.daum.vo.FoodNaviVO;
import net.daum.vo.FoodVO;
import net.daum.vo.Food_MemberVO;

@Repository
public class FoodDAOImpl implements FoodDAO {
	@Autowired
	private SqlSession sqlSession;
	
	/*
	 * Food_main
	 */
	@Override
	public List<Map<String, Object>> getFoodAllList() {
		return this.sqlSession.selectList("food_all_list");
	}

	@Override
	public List<Map<String, Object>> getListNew(String search_type, String search_field, String food_type, List<String> list1,
			List<String> list2, int startrow, int endrow) {
		
	    
	    Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("startrow", startrow);
	    paramMap.put("endrow", endrow);
	    paramMap.put("food_type", food_type);
	    paramMap.put("search_type", search_type);
	    paramMap.put("search_field", search_field);
	    paramMap.put("list1", list1);
	    paramMap.put("list2", list2);
	    

	    System.out.println(list1);
		System.out.println(list2);
		System.out.println(food_type);
		System.out.println(search_type);
		System.out.println(search_field);
	    
	    
	    return sqlSession.selectList("new", paramMap);
	}

	@Override
	public int getTotalItems(int startrow, int endrow, String food_type, List<String> list1,
			List<String> list2) {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("startrow", startrow);
	    paramMap.put("endrow", endrow);
	    paramMap.put("food_type", food_type);
	    paramMap.put("list1", list1);
	    paramMap.put("list2", list2);
	    
		return sqlSession.selectOne("count", paramMap);
	}
	
	/*
	 * 상세내용 보기
	 */
	@Override
	public FoodNaviVO getFoodCont(int navi_no) {
		return this.sqlSession.selectOne("food_cont", navi_no);
	}
	
	/*
	 * 찜하기
	 */
	//찜하기 눌렀는지 안눌렀는지 확인하는 메서드 
	@Override
	public int getLikeCount(FoodLikeVO like) {
		return sqlSession.selectOne("like_count", like);
	}
	
	//찜하기 UP(insert)
	@Override
	public void likeUp(String session_id, int like_navi_no) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("session_id", session_id);
		paramMap.put("like_navi_no", like_navi_no);
		sqlSession.insert("likeUp", paramMap);
	}

	//찜하기 DOWN(delete)
	@Override
	public void likeDown(String session_id, int like_navi_no) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("session_id", session_id);
		paramMap.put("like_navi_no", like_navi_no);
		sqlSession.insert("likeDown", paramMap);
	}

	/*
	 * 회원 영역 
	 */
	
	//회원가입
	@Override
	public void insertJoin(Food_MemberVO mvo) {
		this.sqlSession.insert("member_join", mvo);
	}

	//session_id 기준 회원정보 조회 
	@Override
	public Food_MemberVO loginCheck(String user_id) {
		return this.sqlSession.selectOne("member_login", user_id);
	}

	//회원정보 수정
	@Override
	public void editInfo(Food_MemberVO mvo) {
		this.sqlSession.update("member_edit", mvo);
	}

	//회원 비밀번호 수정
	@Override
	public void pwdEdit(Food_MemberVO mvo) {
		this.sqlSession.update("member_pwdEdit", mvo);
	}

	//회원탈퇴
	@Override
	public void updateState(String session_id) {
		this.sqlSession.update("updateState", session_id);
	}

	//검색에 따라 회원목록 조회
	@Override
	public List<Food_MemberVO> getMemberList(Food_MemberVO mvo) {
		return this.sqlSession.selectList("member_search", mvo);
	}

	/*
	 * 댓글기능
	 */
	
	//댓글 저장
	@Override
	public int insertComment(String session_id, int comment_navi_no, String inputComment) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("session_id", session_id);
		paramMap.put("comment_navi_no", comment_navi_no);
		paramMap.put("inputComment", inputComment);
		return this.sqlSession.insert("insert_comment", paramMap);
	}

	//댓글 목록 조회
	@Override
	public List<FoodCommentVO> getCommentList(int NAVI_NO, int startrow, int endrow) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("NAVI_NO", NAVI_NO);
		paramMap.put("startrow", startrow);
		paramMap.put("endrow", endrow);
		return this.sqlSession.selectList("comment_list", paramMap);
	}

	//댓글 삭제
	@Override
	public int commentDel(int comment_no) {
		return this.sqlSession.delete("comment_del", comment_no);
	}
	
	//댓글 개수
	@Override
	public int getTotalComments(int NAVI_NO) {
		return this.sqlSession.selectOne("comment_count", NAVI_NO);
	}
	
	/*
	 * 관리자(식당, 음식 관련 추가 or 목록 조회)
	 */
	//음식,지역,타입 테이블 insert 
	@Override
	public void insertTable(String table, String seq, String name) {
		
		Map<String, Object> params = new HashMap<>();
		params.put("table", table);
		params.put("seq", seq);
		params.put("name", name);
	
		this.sqlSession.insert("insertTable",params);
		
	}
	
	//식당 테이블 insert 
	@Override
	public void insertRest(String restaurant_name, String restaurant_review) {
		
		Map<String, Object> params = new HashMap<>();
		params.put("restaurant_name", restaurant_name);
		params.put("restaurant_review", restaurant_review);
		
		this.sqlSession.insert("insertRest",params);
	}
	
	//가격 테이블 insert
	@Override
	public void insertPrice(String priceCheckbox, String price) {
		Map<String, Object> params = new HashMap<>();
		params.put("priceCheckbox", priceCheckbox);
		params.put("price", price);	
		
		sqlSession.insert("insertPrice", params);
	}
	
	//사진 테이블 insert 
	@Override
	public void insertPhoto(String photoCheckbox, String photo_name, String photo_url, String photo_type) {
		Map<String, Object> params = new HashMap<>();
		params.put("photoCheckbox", photoCheckbox);
		params.put("photo_name", photo_name);
		params.put("photo_url", photo_url);
		params.put("photo_type", photo_type);
		
		sqlSession.insert("insertPhoto", params);
	}
	
	//Navi 테이블 insert 
	@Override
	public void insertNavi(String foodCheckbox, String restCheckbox, String regionCheckbox,
			String typeCheckbox) {
		Map<String, Object> params = new HashMap<>();
		params.put("foodCheckbox", foodCheckbox);
		params.put("restCheckbox", restCheckbox);
		params.put("regionCheckbox", regionCheckbox);
		params.put("typeCheckbox", typeCheckbox);
		
		sqlSession.insert("insertNavi", params);
	}
	
	//음식 테이블 select 
	@Override
	public List<Map<String, Object>> selectFood() {
		return this.sqlSession.selectList("selectFood");
		
	}
	
	//식당 테이블 select 
	@Override
	public List<Map<String, Object>> selectRest() {
		return this.sqlSession.selectList("selectRest");
		
	}
	
	//지역 테이블 select 
	@Override
	public List<Map<String, Object>> selectRegion() {
		return this.sqlSession.selectList("selectRegion");
		
	}
	
	//음식 종류 테이블 select 
	@Override
	public List<Map<String, Object>> selectType() {
		return this.sqlSession.selectList("selectType");
		
	}
	
	//Navi 테이블 select
	@Override
	public List<Map<String, Object>> selectNavi() {
		return this.sqlSession.selectList("selectNavi");
		
	}

	//가격 사진 등록된 음식 테이블 select 
	@Override
	public List<Map<String, Object>> selectPriceUrl() {
		return this.sqlSession.selectList("selectPriceUrl");
	}

	//가격  등록안된 음식 테이블 select
	@Override
	public List<Map<String, Object>> selectNoPrice() {
		return this.sqlSession.selectList("selectNoPrice");
	}

	//사진  등록안된 음식테이블 select 
	@Override
	public List<Map<String, Object>> selectNoUrl() {
		return this.sqlSession.selectList("selectNoUrl");
	}

	@Override
	public void deleteTable(String table, String status, String deleteCheckbox, String no) {


		Map<String, Object> params = new HashMap<>();
		params.put("deleteCheckbox", deleteCheckbox);
		params.put("table", table);
		params.put("status", status);
		params.put("no", no);
		
		sqlSession.insert("deleteTable", params);
		
	}

	@Override
	public List<Map<String, Object>> getLikeList(String session_id) {
		return sqlSession.selectList("likelist", session_id);
	}



	
}

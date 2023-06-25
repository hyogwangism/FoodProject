package net.daum.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.FoodDAO;
import net.daum.vo.FoodCommentVO;
import net.daum.vo.FoodLikeVO;
import net.daum.vo.FoodNaviVO;
import net.daum.vo.FoodVO;
import net.daum.vo.Food_MemberVO;

@Service
public class FoodServiceImpl implements FoodService {
	@Autowired
	private FoodDAO foodDao;


	/*
	 * food_main
	 */
	@Override
	public List<Map<String, Object>> getFoodAllList() {
		return this.foodDao.getFoodAllList();
	}
	
	@Override
	public List<Map<String, Object>> getListNew(String search_type, String search_field, String food_type, List<String> list1,
			List<String> list2, int startrow, int endrow) {
		
		return this.foodDao.getListNew(search_type,search_field, food_type, list1, list2,startrow, endrow);
	}

	@Override
	public int getTotalItems(int startrow, int endrow, String food_type,List<String> list1, List<String> list2) {
		return this.foodDao.getTotalItems(startrow, endrow, food_type, list1, list2);
	}

	/*
	 * 상세내용 보기
	 */
	@Override
	public FoodNaviVO getFoodCont(int navi_no) {
		return this.foodDao.getFoodCont(navi_no);
	}
	
	/*
	 * 찜하기
	 */
	
	//찜하기 눌렀는지 안눌렀는지 확인하는 메서드 
	@Override
	public int getLikeCount(FoodLikeVO like) {
		return this.foodDao.getLikeCount(like);
	}

	//찜하기 UP(insert)
	@Override
	public void likeUp(String session_id, int like_navi_no) {
		this.foodDao.likeUp(session_id, like_navi_no);
	}
	
	//찜하기 DOWN(delete)
	@Override
	public void likeDown(String session_id, int like_navi_no) {
		this.foodDao.likeDown(session_id, like_navi_no);
	}

	/*
	 * 회원 영역
	 */
	
	//회원가입
	@Override
	public void insertJoin(Food_MemberVO mvo) {
		this.foodDao.insertJoin(mvo);
	}

	//session_id 기준 회원정보 조회 
	@Override
	public Food_MemberVO loginCheck(String user_id) {
		return this.foodDao.loginCheck(user_id);
	}
	
	//회원정보 수정
	@Override
	public void editInfo(Food_MemberVO mvo) {
		this.foodDao.editInfo(mvo);
	}
	
	//회원 비밀번호 수정
	@Override
	public void pwdEdit(Food_MemberVO mvo) {
		this.foodDao.pwdEdit(mvo);
	}
	
	//회원 탈퇴
	@Override
	public void updateState(String session_id) {
		this.foodDao.updateState(session_id);
	}
	
	//검색에 따라 회원목록 조회
	@Override
	public List<Food_MemberVO> getMemberList(Food_MemberVO mvo) {
		return this.foodDao.getMemberList(mvo);
	}

	
	/*
	 * 댓글기능
	 */
	
	//댓글저장
	@Override
	public int insertComment(String session_id, int comment_navi_no, String inputComment) {
		return this.foodDao.insertComment(session_id, comment_navi_no, inputComment);
	}

	//댓글 목록 조회
	@Override
	public List<FoodCommentVO> getCommentList(int NAVI_NO, int startrow, int endrow) {
		return this.foodDao.getCommentList(NAVI_NO, startrow, endrow);
	}

	//댓글 삭제
	@Override
	public int commentDel(int comment_no) {
		return this.foodDao.commentDel(comment_no);
	}
	
	//댓글 개수
	@Override
	public int getTotalComments(int NAVI_NO) {
		return this.foodDao.getTotalComments(NAVI_NO);
	}
	
	/*
	 * 관리자(식당, 음식 관련 추가 or 목록 조회)
	 */
	//음식,지역,타입 테이블 insert 
	@Override
	public void insertTable(String table, String seq, String name) {
		this.foodDao.insertTable(table,seq,name);
		
	}
	
	//식당 테이블 insert 
	@Override
	public void insertRest(String restaurant_name, String restaurant_review) {
		this.foodDao.insertRest(restaurant_name,restaurant_review);
		
	}
	
	//가격 테이블 insert 
	@Override
	public void insertPrice(String priceCheckbox, String price) {
		foodDao.insertPrice(priceCheckbox,price);
		
	}

	//사진 테이블 insert 
	@Override
	public void insertPhoto(String photoCheckbox, String photo_name, String photo_url, String photo_type) {
		foodDao.insertPhoto(photoCheckbox,photo_name,photo_url,photo_type);
	}
	
	//Navi 테이블 insert 
	@Override
	public void insertNavi(String foodCheckbox, String restCheckbox, String regionCheckbox, String typeCheckbox) {	
		this.foodDao.insertNavi(foodCheckbox,restCheckbox,regionCheckbox,typeCheckbox);;
	}
	
	//음식 테이블 select 
	@Override
	public List<Map<String, Object>> selectFood() {
		return this.foodDao.selectFood();	
	}
	
	//식당 테이블 select 
	@Override
	public List<Map<String, Object>> selectRest() {
		return this.foodDao.selectRest();	
	}
	
	//지역 테이블 select 
	@Override
	public List<Map<String, Object>> selectRegion() {
		return this.foodDao.selectRegion();
	}
	
	//음식 종류 테이블 select 
	@Override
	public List<Map<String, Object>> selectType() {
		return this.foodDao.selectType();
		
	}
	
	//Navi 테이블 select 
	@Override
	public List<Map<String, Object>> selectNavi() {
		return this.foodDao.selectNavi();
	}

	//가격 사진 등록된 음식 테이블 select 
	@Override
	public List<Map<String, Object>> selectPriceUrl() {
		return foodDao.selectPriceUrl();
	}
	
	//가격  등록안된 음식 테이블 select
	@Override
	public List<Map<String, Object>> selectNoPrice() {
		return foodDao.selectNoPrice();
	}

	//사진  등록안된 음식테이블 select 
	@Override
	public List<Map<String, Object>> selectNoUrl() {
		return foodDao.selectNoUrl();
	}
	
	//테이블 삭제

	@Override
	public void deleteTable(String table, String status , String deleteCheckbox	, String no) {
		foodDao.deleteTable(table, status,deleteCheckbox,no);
		
	}
	
	@Override
	public List<Map<String, Object>> getLikeList(String session_id) {
		return foodDao.getLikeList(session_id);
	}

}


package net.daum.service;

import java.util.List;
import java.util.Map;

import net.daum.vo.FoodCommentVO;
import net.daum.vo.FoodLikeVO;
import net.daum.vo.FoodNaviVO;
import net.daum.vo.FoodVO;
import net.daum.vo.Food_MemberVO;

public interface FoodService {


	/*
	 * food_main
	 */
	List<Map<String, Object>> getFoodAllList();
	

	List<Map<String, Object>> getListNew(String search_type, String search_field, String food_type, List<String> list1,
			List<String> list2, int startrow, int endrow);

	int getTotalItems(int startrow, int endrow, String food_type,List<String> list1, List<String> list2);
	
	/*
	 * 상세내용 보기
	 */
	FoodNaviVO getFoodCont(int navi_no);
	
	
	/*
	 * 찜하기
	 */
	
	//찜하기 눌렀는지 안눌렀는지 확인하는 메서드 
	int getLikeCount(FoodLikeVO like);

	//찜하기 UP(insert)
	void likeUp(String session_id, int like_navi_no);
	
	//찜하기 DOWN(delete)
	void likeDown(String session_id, int like_navi_no);
	
	
	/*
	 * 회원 영역
	 * 
	 */
	
	//회원가입
	void insertJoin(Food_MemberVO mvo);
	
	//session_id 기준 회원정보 조회
	Food_MemberVO loginCheck(String user_id);
	
	//회원정보 수정
	void editInfo(Food_MemberVO mvo);
	
	//회원 비밀번호 수정
	void pwdEdit(Food_MemberVO mvo);
	
	//회원 탈퇴
	void updateState(String session_id);

	//검색에 따라 회원목록 조회
	List<Food_MemberVO> getMemberList(Food_MemberVO mvo);


	/*
	 * 댓글기능
	 */
	//댓글 저장
	int insertComment(String session_id, int comment_navi_no, String inputComment);

	//댓글 목록 조회
	List<FoodCommentVO> getCommentList(int NAVI_NO, int startrow, int endrow);

	//댓글 삭제
	int commentDel(int comment_no);
	
	//댓글 개수
	int getTotalComments(int NAVI_NO);

	
	/*
	 * 관리자(식당, 음식 관련 추가 or 목록 조회)
	 */
	//음식,지역,타입 테이블 insert 
	void insertTable(String table, String seq, String name);

	//식당 테이블 insert 
	void insertRest(String restaurant_name, String restaurant_review);

	//가격 테이블 insert 
	void insertPrice(String priceCheckbox, String price);
	
	//사진 테이블 insert 
	void insertPhoto(String photoCheckbox, String photo_name, String photo_url, String photo_type);
	
	//Navi 테이블 insert 
	void insertNavi(String foodCheckbox, String restCheckbox, String regionCheckbox,
			String typeCheckbox);
	
	//음식 테이블 select 
	List<Map<String, Object>> selectFood();

	//식당 테이블 select 
	List<Map<String, Object>> selectRest();
	
	//지역 테이블 select 
	List<Map<String, Object>> selectRegion();
	
	//음식 종류 테이블 select 
	List<Map<String, Object>> selectType();

	//Navi 테이블 select 
	List<Map<String, Object>> selectNavi();
	
	//가격 사진 등록된 음식 테이블 select 
	List<Map<String, Object>> selectPriceUrl();
	
	//가격  등록안된 음식 테이블 select
	List<Map<String, Object>> selectNoPrice();
	
	//사진  등록안된 음식테이블 select 
	List<Map<String, Object>> selectNoUrl();

	void deleteTable(String table, String status, String deleteCheckbox, String no);

	List<Map<String, Object>> getLikeList(String session_id);



}

package net.daum.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.daum.service.FoodService;
import net.daum.vo.FoodCommentVO;
import net.daum.vo.FoodLikeVO;
import net.daum.vo.FoodNaviVO;
import net.daum.vo.FoodVO;

@Controller
public class FoodController {

	@Autowired
	private FoodService foodService;

	//동적화면 메인
	@RequestMapping("/food_main")
	public String Food_main(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session, FoodVO f) {
		String session_id = (String)session.getAttribute("session_id"); 	
		
		//int navi_no = Integer.parseInt(request.getParameter("NAVI_NO"));
		if(session_id != null) {
			model.addAttribute("session_id", session_id);
			int page = 1;
			String food_type = "All";
			List<String> list1 = null;
			List<String> list2 = null;

			int limit = 10; // 한 페이지에 보여지는 목록 개수 
			int startrow = ((page - 1) * limit + 1); // 시작 행 번호
			int endrow = (startrow + limit - 1); // 끝 행 번호

			int totalItems = this.foodService.getTotalItems(startrow, endrow, food_type, list1, list2);
			int totalPages = (int) Math.ceil((double) totalItems / limit);

			int pagecount = totalPages;
			
			List<Map<String,Object>> all = foodService.getFoodAllList();
			System.out.println("all:"+all);
			model.addAttribute("all", all);
			model.addAttribute("pagecount", pagecount);
			
			return "/food_main"; 

		} else {
			int page = 1;
			String food_type = "All";
			List<String> list1 = null;
			List<String> list2 = null;

			int limit = 10; // 한 페이지에 보여지는 목록 개수 
			int startrow = ((page - 1) * limit + 1); // 시작 행 번호
			int endrow = (startrow + limit - 1); // 끝 행 번호

			int totalItems = this.foodService.getTotalItems(startrow, endrow, food_type, list1, list2);
			int totalPages = (int) Math.ceil((double) totalItems / limit);

			int pagecount = totalPages;
			
			List<Map<String,Object>> all = foodService.getFoodAllList();
			System.out.println("all:"+all);
			model.addAttribute("all",all);
			model.addAttribute("pagecount",pagecount);
			
			return "/food_main";
		}

	}
	//AJAX 메인
	@ResponseBody
	@RequestMapping(value="/food_list2")
	public Map<String, Object> checkbox_Search(@RequestBody HashMap<String,Object> requestData, 
			HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

		String food_type = (String) requestData.get("food_type");
	    List<String> list1 =  (List<String>) requestData.get("list1");
	    List<String> list2 = (List<String>) requestData.get("list2");
	    String search_field = (String) requestData.get("search_field");
	    String search_type = (String) requestData.get("search_type");
	    Integer pageStr = (Integer) requestData.get("page");
	    
	    System.out.println("requestData :"+requestData);
	    
	    session.setAttribute("food_type", food_type);
		
	    int page;
		  
		  System.out.println("페이지확인:"+pageStr);
		  if (pageStr == null) {
		    page = 1;
		  } else {
		    page = pageStr;
		    System.out.println("페이지:" + page);
		  }

		  int limit = 10; // 한 페이지에 보여지는 목록 개수 
		  int startrow = ((page - 1) * limit + 1); // 시작 행 번호
		  int endrow = (startrow + limit - 1); // 끝 행 번호

		  int totalItems = this.foodService.getTotalItems(startrow, endrow, food_type, list1, list2);
		  int totalPages = (int) Math.ceil((double) totalItems / limit);
		  
		  System.out.println(totalItems);
		  System.out.println(totalPages);

		  List<Map<String,Object>> flist = this.foodService.getListNew(search_type,search_field, food_type, list1, list2, startrow, endrow);
		  System.out.println("flist: "+ flist);
		  
		  // Map에 flist와 totalPages를 담아서 반환
		  Map<String, Object> resultMap = new HashMap<>();
		  resultMap.put("flist", flist);
		  resultMap.put("totalPages", totalPages);

		  return resultMap;
	}	

	/*
	 * 관리자 식당, 음식 테이블 관리
	 */
	@RequestMapping("/food_manage")
	public String food_manage(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		request.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html;charset=UTF-8");

		String session_id = (String)session.getAttribute("session_id");

		String currentTab = (String) session.getAttribute("currentTab");

		// 탭 상태를 설정하기 위해 모델에 전달
		model.addAttribute("currentTab", currentTab);

		if(session_id==null) {
			model.addAttribute("errorMessage", "다시 로그인 하세요.");
			return "forward:/member_login";
		} else {

			List<Map<String,Object>> food = foodService.selectFood();
			List<Map<String,Object>> rest = foodService.selectRest();
			List<Map<String,Object>> region = foodService.selectRegion();
			List<Map<String,Object>> type = foodService.selectType();
			List<Map<String,Object>> navi = foodService.selectNavi();
			List<Map<String,Object>> price_url = foodService.selectPriceUrl();
			List<Map<String,Object>> no_price = foodService.selectNoPrice();
			List<Map<String,Object>> no_url = foodService.selectNoUrl();


			model.addAttribute("food", food);
			model.addAttribute("rest", rest);
			model.addAttribute("region", region);
			model.addAttribute("type", type);
			model.addAttribute("navi", navi);
			model.addAttribute("price_url", price_url);
			model.addAttribute("no_price", no_price);
			model.addAttribute("no_url", no_url);

			return "/food_manage";
		}
	}

	
	@RequestMapping("/food_manage_ok")
	public String food_manage_ok(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

		String table =request.getParameter("table");
		String seq =request.getParameter("seq");
		String name =request.getParameter("name");

		foodService.insertTable(table,seq,name);

		return "redirect:/food_manage";
	}

	@RequestMapping("/food_manage_ok_2")
	public String food_manage_ok_rest(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {


		String restaurant_name =request.getParameter("restaurant_name");
		String restaurant_review =request.getParameter("restaurant_review");

		foodService.insertRest(restaurant_name,restaurant_review);

		return "redirect:/food_manage";
	}

	@RequestMapping("/Navi_ok")
	public String navi_ok(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {


		String foodCheckbox =request.getParameter("foodCheckbox");
		String restCheckbox =request.getParameter("restCheckbox");
		String regionCheckbox =request.getParameter("regionCheckbox");
		String typeCheckbox =request.getParameter("typeCheckbox");

		foodService.insertNavi(foodCheckbox,restCheckbox,regionCheckbox,typeCheckbox);

		return "redirect:/food_manage";
	}

	@RequestMapping("/price_ok")
	public String price_ok(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {					

		String priceCheckbox =request.getParameter("priceCheckbox");
		String price =request.getParameter("price");


		foodService.insertPrice(priceCheckbox,price);

		return "redirect:/food_manage";
	}

	@RequestMapping("/delete_ok")
	public String delete_ok(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		
		String table =request.getParameter("table");
		String status =request.getParameter("status");
		String deleteCheckbox =request.getParameter("deleteCheckbox");
		String no =request.getParameter("no");
		
		foodService.deleteTable(table,status,deleteCheckbox,no);
		
		

	    return "redirect:/food_manage";
	}
	

	 @ResponseBody
	 @PostMapping("/save_tab_to_session")
	  public String saveTabToSession( HttpServletRequest request) {
	    HttpSession session = request.getSession();
	    String tabId = request.getParameter("tabId");
	    session.setAttribute("currentTab", tabId);
	    return "null";
	  }
	

	//게시글 내용 보기
	@RequestMapping("/food_cont")
	public String Food_cont(Model model, HttpServletRequest request, HttpSession session) {
		String session_id = (String)session.getAttribute("session_id");
		int navi_no = Integer.parseInt(request.getParameter("NAVI_NO"));
		
		
		
		if(session_id != null) { 	

			FoodNaviVO fcont = this.foodService.getFoodCont(navi_no);
			
			model.addAttribute("session_id", session_id);
			model.addAttribute("fcont", fcont);

			System.out.println("상세보기 페이지");

			FoodLikeVO like = new FoodLikeVO();

			like.setLike_navi_no(navi_no);
			like.setSession_id(session_id);
			int re = this.foodService.getLikeCount(like);
			System.out.println(re);
			model.addAttribute("FoodLike", like);
			model.addAttribute("likeCount", re);
			model.addAttribute("session_id", session_id);
			
			//댓글 목록, 페이징
			int page = 1;
			int limit = 5; // 한 페이지에 보여지는 목록 개수 
			int startrow = ((page - 1) * limit + 1); // 시작 행 번호
			int endrow = (startrow + limit - 1); // 끝 행 번호
			int totalComments = this.foodService.getTotalComments(navi_no);
			int totalPages = (int) Math.ceil((double) totalComments / limit);
			List<FoodCommentVO> clist = this.foodService.getCommentList(navi_no, startrow, endrow);
			model.addAttribute("clist", clist);
			model.addAttribute("totalPages", totalPages);

			return "/food_cont";
		} else {
			FoodNaviVO fcont = this.foodService.getFoodCont(navi_no);
			model.addAttribute("fcont", fcont);
			
			//댓글 목록, 페이징
			int page = 1;
			int limit = 5; // 한 페이지에 보여지는 목록 개수 
			int startrow = ((page - 1) * limit + 1); // 시작 행 번호
			int endrow = (startrow + limit - 1); // 끝 행 번호
			int totalComments = this.foodService.getTotalComments(navi_no);
			int totalPages = (int) Math.ceil((double) totalComments / limit);
			List<FoodCommentVO> clist = this.foodService.getCommentList(navi_no, startrow, endrow);
			model.addAttribute("clist", clist);
			model.addAttribute("totalPages", totalPages);
			
			return "/food_cont";
		}
	}
	
	/* 찜하기 AJAX
	 * 
	 */
	@ResponseBody 
	@RequestMapping("/likeUp")
	public void likeup(@RequestBody Map<String, Object> request) {
	  int like_navi_no = (int) request.get("NAVI_NO");
	  String session_id = (String) request.get("session_id");
	  this.foodService.likeUp(session_id, like_navi_no);
	}

	@ResponseBody
	@RequestMapping("/likeDown")
	public void likeDown(@RequestBody Map<String, Object> request) {
	  int like_navi_no = (int) request.get("NAVI_NO");
	  String session_id = (String) request.get("session_id");
	  this.foodService.likeDown(session_id, like_navi_no);

	}
	
	//찜목록
		@RequestMapping("/like_list")
		public String admin_main(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
			request.setCharacterEncoding("UTF-8");	
			response.setContentType("text/html;charset=UTF-8");

			String session_id = (String)session.getAttribute("session_id");

			if(session_id==null) {
				model.addAttribute("errorMessage", "다시 로그인 하세요.");
				return "forward:/member_login";
			} else {
				
				 List<Map<String,Object>> likelist = this.foodService.getLikeList(session_id);
				 
				  
				 model.addAttribute("likelist", likelist);
				
				return "/food_like";
			}
		}
	
	
		/*
		 * 댓글기능
		 */
		//댓글 작성
		@ResponseBody
		@RequestMapping("/CommentWrite")
		public int CommentWrite(@RequestBody Map<String, Object> requestData) {
			int comment_navi_no = (int) requestData.get("NAVI_NO");
			String session_id = (String) requestData.get("session_id");
			String InputComment = (String) requestData.get("InputComment");

			int re = this.foodService.insertComment(session_id, comment_navi_no, InputComment);

			return re;
		}

		//댓글 목록 조회
		@ResponseBody
		@RequestMapping("/CommentList")
		public Map<String, Object> CommentList(Model model, @RequestBody Map<String, Object> requestData) {
			int NAVI_NO = Integer.parseInt((String) requestData.get("NAVI_NO"));
			Integer pageStr = (Integer) requestData.get("currentPage");
			
			int page;

			System.out.println("페이지확인:"+pageStr);
			if (pageStr == null) {
				page = 1;
			} else {
				page = pageStr;
				System.out.println("페이지:" + page);
			}

			int limit = 5; // 한 페이지에 보여지는 목록 개수 
			int startrow = ((page - 1) * limit + 1); // 시작 행 번호
			int endrow = (startrow + limit - 1); // 끝 행 번호

			int totalComments = this.foodService.getTotalComments(NAVI_NO);
			int totalPages = (int) Math.ceil((double) totalComments / limit);

			List<FoodCommentVO> clist = this.foodService.getCommentList(NAVI_NO, startrow, endrow);
			
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("clist", clist);
			resultMap.put("totalPages", totalPages);

			return resultMap;
		}

		//댓글 삭제
		@ResponseBody
		@RequestMapping("/CommentDel")
		public int CommentDel(@RequestBody Map<String, Object> requestData) {
			int comment_no = (Integer) requestData.get("comment_no");
			System.out.println("commentNO:"+comment_no);
			int re = this.foodService.commentDel(comment_no);

			return re;
		}
	
}

	
	/*
	@PostMapping("/photo_ok")
	public String photo_ok(Model model, @RequestParam("photo_url") MultipartFile file, HttpServletRequest request, HttpSession session) throws Exception {
	    // Firebase 스토리지 버킷 정보
	    String projectId = "juan-40a06";
	    String bucketName = "juan-40a06.appspot.com";

	    // Firebase 인증 정보 가져오기
	    String filePath = "foodproject-55a53-firebase-adminsdk-s20kv-b21eb99647.json";
	    InputStream serviceAccount = getClass().getResourceAsStream("/" + filePath);
	    GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

	    // Firebase 스토리지 클라이언트 초기화
	    FirebaseOptions options = FirebaseOptions.builder()
	            .setCredentials(credentials)
	            .setProjectId(projectId)
	            .build();
	   
	    if (FirebaseApp.getApps().isEmpty()) {
	        // FirebaseApp이 초기화되지 않은 경우에만 초기화
	        FirebaseApp.initializeApp(options);
	    }
	    

	    // Firebase 스토리지 클라이언트 가져오기
	    StorageClient storageClient = StorageClient.getInstance();

	    String photoCheckbox = request.getParameter("photoCheckbox");
	    String photo_name = request.getParameter("photo_name");
	    String photo_type = request.getParameter("photo_type");

	    String photo_url = ""; // 기본값으로 빈 문자열 설정

	    if (!file.isEmpty()) {
	        // 파일 업로드 처리
	        Calendar cal = Calendar.getInstance();
	        int year = cal.get(Calendar.YEAR);
	        int month = cal.get(Calendar.MONTH) + 1;
	        int date = cal.get(Calendar.DATE);

	        String homedir = year + "-" + month + "-" + date;
	        String fileName = "photo" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
	        String fileDBName = homedir + "/" + fileName;

	        // 업로드 및 url 생성
	        Bucket bucket = storageClient.bucket(bucketName);
	        Blob blob = bucket.create(fileDBName, file.getInputStream());
	        URL url = blob.signUrl(1, TimeUnit.DAYS);
	        String fileUrl = url.toString();
	        photo_url = fileUrl;

	       

	        foodService.insertPhoto(photoCheckbox, photo_name, photo_url, photo_type);
	    } else {
	        String fileDBName = ""; // 첨부하지 않았을 때 빈 공백 저장
	        photo_url = fileDBName;
	        System.out.println("사진" + photo_url);
	        foodService.insertPhoto(photoCheckbox, photo_name, photo_url, photo_type);
	    }

	    // photo_url을 활용하여 원하는 작업 수행

	    return "redirect:/food_manage";
	}
	*/
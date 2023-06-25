package net.daum.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import net.daum.service.FoodService;
import net.daum.vo.Food_MemberVO;

@Controller
public class FoodMemberController {

	@Autowired
	private FoodService foodService;

	/*
	 * 회원가입
	 */
	@RequestMapping("/member_join")
	public String Member_join(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return "member_join";
	}

	//회원가입 ok
	@RequestMapping("/member_join_ok")
	public String Member_join_ok(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String user_id = request.getParameter("user_id");
		String user_pwd = request.getParameter("user_pwd");
		String user_name = request.getParameter("user_name");
		String user_birth = request.getParameter("user_birth");
		String user_email = request.getParameter("user_email");
		String user_phone = request.getParameter("user_phone");
		String user_gender = request.getParameter("user_gender");

		Food_MemberVO mvo = new Food_MemberVO();

		mvo.setUser_id(user_id); mvo.setUser_pwd(user_pwd); mvo.setUser_name(user_name);
		mvo.setUser_birth(user_birth); mvo.setUser_email(user_email); mvo.setUser_phone(user_phone);
		mvo.setUser_gender(user_gender);


		this.foodService.insertJoin(mvo);

		model.addAttribute("mvo", mvo);
		model.addAttribute("errorMessage", "회원정보 가입 성공.");

		return "forward:/member_login";
	}


	/*
	 * 회원 로그인
	 */
	@RequestMapping("/member_login")
	public String Member_login(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return "member_login";
	}

	//로그인 ok
	@RequestMapping("/member_login_ok")
	public String Member_login_ok(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {


		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String user_id = request.getParameter("user_id");
		String user_pwd = request.getParameter("user_pwd");

		Food_MemberVO mvo = this.foodService.loginCheck(user_id); //로그인 인증

		if(mvo == null || mvo.getUser_state() != 0) {
			model.addAttribute("errorMessage", "가입되지 않은 회원입니다.");
			return "forward:/member_login";
		}else {
			if (!mvo.getUser_pwd().equals(user_pwd)) {
				model.addAttribute("errorMessage", "비밀번호가 다릅니다.");
				return "forward:/member_login";

			}else {
				session.setAttribute("session_id", user_id);//세션 id 속성키 이름에 아이디를 저장

				return "redirect:/food_main";
			}

		}
	}

	//로그아웃
	@RequestMapping("/member_logout")
	public String Member_logout(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

		response.setContentType("text/html;charset=UTF-8");
		//브라우저에 출력되는 문자와 태그 ,언어코딩 타입을 설정

		session.invalidate();//세션 만료 => 로그아웃
		model.addAttribute("errorMessage", "로그아웃 되었습니다.");
		return "redirect:/food_main";		
	}

	//회원정보 수정
	@RequestMapping("/member_edit")
	public String Member_edit(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		request.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html;charset=UTF-8");

		String session_id = (String)session.getAttribute("session_id");

		if(session_id==null) {
			model.addAttribute("errorMessage", "다시 로그인 하세요.");
			return "forward:/member_login";
		} else {

			Food_MemberVO mvo = this.foodService.loginCheck(session_id);

			model.addAttribute("mvo", mvo);
			String user_gender = mvo.getUser_gender();
			model.addAttribute("isMale", user_gender.equals("남자"));
			model.addAttribute("isFemale", user_gender.equals("여자"));

			return "/member_edit";
		}
	}	

	//회원정보 수정
	@RequestMapping("/member_edit_ok")
	public String Member_edit_ok(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		request.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html;charset=UTF-8");
		//브라우저에 출력되는 문자와 태그 ,언어코딩 타입을 설정

		String session_id = (String)session.getAttribute("session_id");

		if(session_id==null) {
			model.addAttribute("errorMessage", "다시 로그인 하세요.");
			return "forward:/member_login";

		} else {

			String user_name = request.getParameter("user_name");
			String user_birth = request.getParameter("user_birth");
			String user_email = request.getParameter("user_email");
			String user_phone = request.getParameter("user_phone");
			String user_gender = request.getParameter("user_gender");

			Food_MemberVO mvo = new Food_MemberVO();

			mvo.setUser_id(session_id); mvo.setUser_name(user_name); mvo.setUser_gender(user_gender); 
			mvo.setUser_birth(user_birth); mvo.setUser_email(user_email); mvo.setUser_phone(user_phone);

			this.foodService.editInfo(mvo);

			model.addAttribute("errorMessage", "회원정보 수정 성공.");

			return "forward:/food_main";
		}
	}	

	@RequestMapping("/member_pwdEdit")
	public String member_pwdEdit(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		request.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html;charset=UTF-8");
		//브라우저에 출력되는 문자와 태그 ,언어코딩 타입을 설정

		String session_id = (String)session.getAttribute("session_id");

		if(session_id==null) {
			model.addAttribute("errorMessage", "다시 로그인 하세요.");
			return "forward:/member_login";
		} else {
			return "/member_pwdEdit";
		}
	}


	//비밀번호 수정
	@RequestMapping("/member_pwdEdit_ok")
	public String Member_pwdEdit_ok(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html;charset=UTF-8");
		//브라우저에 출력되는 문자와 태그 ,언어코딩 타입을 설정
		HttpSession session=request.getSession();//세션 객체 생성

		String session_id = (String)session.getAttribute("session_id");

		if(session_id==null) {
			model.addAttribute("errorMessage", "다시 로그인 하세요.");
			return "forward:/member_login";
		} else {

			String user_pwd = request.getParameter("user_pwd");
			String new_pwd = request.getParameter("new_pwd");

			Food_MemberVO mvo = this.foodService.loginCheck(session_id);
			if(user_pwd.equals(mvo.getUser_pwd())) {
				mvo.setUser_pwd(new_pwd);	
				this.foodService.pwdEdit(mvo);
				model.addAttribute("errorMessage", "비밀번호 변경 성공.");
				return "forward:/food_main";
			} else {
				model.addAttribute("errorMessage", "현재 비밀번호가 다릅니다.");
				return "forward:/member_pwdEdit";
			}								
		}
	}	

	// 회원 탈퇴
	@RequestMapping("/member_withdrawal")
	public String member_del(Model model, HttpSession session, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		String session_id = (String)session.getAttribute("session_id"); // 세션아이디를 구함
		if(session_id==null) {
			model.addAttribute("errorMessage", "다시 로그인 하세요.");
			return "forward:/member_login";
		} else {
			return "/member_withdrawal";		
		}
	}

	// 회원 탈퇴 ok
	@RequestMapping("/member_withdrawal_ok")
	public String member_del_ok(Model model, HttpSession session, HttpServletResponse response, HttpServletRequest request) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		String session_id = (String)session.getAttribute("session_id"); // 세션아이디를 구함
		if(session_id==null) {
			model.addAttribute("errorMessage", "다시 로그인 하세요.");
			return "forward:/member_login";
		} else {

			String user_id = request.getParameter("user_id");
			String user_pwd = request.getParameter("user_pwd");

			Food_MemberVO mvo = this.foodService.loginCheck(session_id);
			if(user_pwd.equals(mvo.getUser_pwd()) && user_id.equals(session_id)) {
				this.foodService.updateState(session_id);
				session.invalidate();
				model.addAttribute("errorMessage", "회원탈퇴 성공");
				return "forward:/food_main";
			} else {
				model.addAttribute("errorMessage", "아이디 또는 비밀번호가 다릅니다.");
				return "forward:/member_withdrawal";
			}		
		}
	}

	//관리자 메인 페이지
	@RequestMapping("/admin_main")
	public String admin_main(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		request.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html;charset=UTF-8");

		String session_id = (String)session.getAttribute("session_id");

		if(session_id==null) {
			model.addAttribute("errorMessage", "다시 로그인 하세요.");
			return "forward:/member_login";
		} else {
			return "/admin_main";
		}
	}

	//관리자 회원관리
	@RequestMapping("/member_manage")
	public String member_manage(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		request.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html;charset=UTF-8");

		String session_id = (String)session.getAttribute("session_id");

		if(session_id==null) {
			model.addAttribute("errorMessage", "다시 로그인 하세요.");
			return "forward:/member_login";
		} else {
			return "/member_manage";
		}
	}

	/* 관리자 회원정보 조회 AJAX
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/member_search")
	public List<Food_MemberVO> member_search (Model model, Food_MemberVO fvo, String search_field, String search_type) throws Exception{

		search_field = "%" + search_field + "%" ;

		System.out.println("search_type : " + search_type);
		System.out.println("search_field : " + search_field);

		fvo.setSearch_type(search_type);
		fvo.setSearch_field(search_field);

		List<Food_MemberVO> mlist = this.foodService.getMemberList(fvo);			
		System.out.println(mlist.get(0).getUser_name());
		return mlist;
	}
}

package net.daum.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.daum.service.FoodService;
import net.daum.vo.FoodVO;
import net.daum.vo.Food_MemberVO;

@Controller
public class FoodMemberController {

	@Autowired
	private FoodService foodService;

	//회원가입
	@RequestMapping("/member_join")
	public ModelAndView Member_join(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView fm = new ModelAndView();

		fm.setViewName("member_join");

		return fm;
	}

	@RequestMapping("/member_join_ok")
	public ModelAndView Member_join_ok(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String user_id = request.getParameter("user_id");
		String user_pwd = request.getParameter("user_pwd");
		String user_name = request.getParameter("user_name");
		String user_birth = request.getParameter("user_birth");
		String user_email = request.getParameter("user_email");
		String user_phone = request.getParameter("user_phone");

		Food_MemberVO fvo = new Food_MemberVO();

		fvo.setUser_id(user_id); fvo.setUser_pwd(user_pwd); fvo.setUser_name(user_name);
		fvo.setUser_birth(user_birth); fvo.setUser_email(user_email); fvo.setUser_phone(user_phone);


		this.foodService.insertJoin(fvo);

		ModelAndView fm = new ModelAndView();

		fm.setViewName("member_login");
		fm.addObject("fvo", fvo);

		return fm;
	}

	@RequestMapping("/member_login")
	public ModelAndView Member_login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView fm = new ModelAndView();

		fm.setViewName("member_login");

		return fm;
	}

	@RequestMapping("/member_login_ok")
	public ModelAndView Member_login_ok(HttpServletRequest request, HttpServletResponse response) throws Exception {


		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		String user_id = request.getParameter("user_id");
		String user_pwd = request.getParameter("user_pwd");

		Food_MemberVO fvo = this.foodService.loginCheck(user_id); //로그인 인증

		if(fvo == null) {
			out.println("<script>");
			out.println("alert('가입되지 않은 회원입니다.')");
			out.println("history.back();");
			out.println("</script>");
		}else {
			if(!fvo.getUser_pwd().equals(user_pwd)) {
				out.println("<script>");
				out.println("alert('비밀번호가 다릅니다.')");
				out.println("history.go(-1);");
				out.println("</script>");

			}else {
				HttpSession session = request.getSession(); //세션 객체 생성
				session.setAttribute("user_id", user_id);//세션 id 속성키 이름에 아이디를 저장
				ModelAndView fm = new ModelAndView();

				fm.addObject("fvo", fvo);
				fm.setViewName("food_main");

				return fm;
			}

		}
		return null;
	}

	//로그아웃
	@RequestMapping("/member_logout")
	public ModelAndView Member_logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=UTF-8");
		//브라우저에 출력되는 문자와 태그 ,언어코딩 타입을 설정
		PrintWriter out=response.getWriter();//출력스트림 객체 생성
		HttpSession session=request.getSession();//세션 객체 생성

		session.invalidate();//세션 만료 => 로그아웃

		ModelAndView fm = new ModelAndView();

		out.println("<script>");
		out.println("alert('로그아웃 되었습니다!');");
		out.println("</script>");

		fm.setViewName("food_main");
		return fm;		
	}

	//회원정보 수정
	@RequestMapping("/member_edit")
	public ModelAndView Member_edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html;charset=UTF-8");
		//브라우저에 출력되는 문자와 태그 ,언어코딩 타입을 설정
		PrintWriter out=response.getWriter();//출력스트림 객체 생성
		HttpSession session=request.getSession();//세션 객체 생성

		String session_id = (String)session.getAttribute("user_id");

		if(session_id==null) {
			out.println("<script>");
			out.println("alert('다시 로그인하세요')");
			out.println("location='member_login';");
			out.println("</script>");
		} else {

			Food_MemberVO fvo = this.foodService.loginCheck(session_id);


			ModelAndView fm = new ModelAndView();

			fm.addObject("fvo", fvo);

			fm.setViewName("member_edit");

			return fm;
		}
		return null;
	}	

	//회원정보 수정
	@RequestMapping("/member_edit_ok")
	public ModelAndView Member_edit_ok(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html;charset=UTF-8");
		//브라우저에 출력되는 문자와 태그 ,언어코딩 타입을 설정
		PrintWriter out=response.getWriter();//출력스트림 객체 생성
		HttpSession session=request.getSession();//세션 객체 생성

		String session_id = (String)session.getAttribute("user_id");

		if(session_id==null) {
			out.println("<script>");
			out.println("alert('다시 로그인하세요')");
			out.println("location='member_login';");
			out.println("</script>");
		} else {

			String user_name = request.getParameter("user_name");
			String user_birth = request.getParameter("user_birth");
			String user_email = request.getParameter("user_email");
			String user_phone = request.getParameter("user_phone");

			Food_MemberVO fvo = new Food_MemberVO();

			fvo.setUser_id(session_id); fvo.setUser_name(user_name); 
			fvo.setUser_birth(user_birth); fvo.setUser_email(user_email); fvo.setUser_phone(user_phone);

			this.foodService.editInfo(fvo);

			ModelAndView fm = new ModelAndView();
			
			fm.addObject("fvo", fvo);
			
			fm.setViewName("food_main");
			return fm;
		}
		return null;
	}	

	@RequestMapping("/member_pwdEdit")
	public ModelAndView member_pwdEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html;charset=UTF-8");
		//브라우저에 출력되는 문자와 태그 ,언어코딩 타입을 설정
		PrintWriter out=response.getWriter();//출력스트림 객체 생성
		HttpSession session=request.getSession();//세션 객체 생성

		String session_id = (String)session.getAttribute("user_id");

		if(session_id==null) {
			out.println("<script>");
			out.println("alert('다시 로그인하세요')");
			out.println("location='member_login';");
			out.println("</script>");
		} else {

			ModelAndView fm = new ModelAndView();

			fm.setViewName("member_pwdEdit");

			return fm;
		}
		return null;
	}


	//비밀번호 수정
	@RequestMapping("/member_pwdEdit_ok")
	public ModelAndView Member_pwdEdit_ok(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html;charset=UTF-8");
		//브라우저에 출력되는 문자와 태그 ,언어코딩 타입을 설정
		PrintWriter out=response.getWriter();//출력스트림 객체 생성
		HttpSession session=request.getSession();//세션 객체 생성

		String session_id = (String)session.getAttribute("user_id");

		if(session_id==null) {
			out.println("<script>");
			out.println("alert('다시 로그인하세요')");
			out.println("location='member_login';");
			out.println("</script>");
		} else {

			String session_pwd = request.getParameter("user_pwd");
			String user_pwd = request.getParameter("new_pwd");

			ModelAndView fm = new ModelAndView();

			Food_MemberVO fvo = this.foodService.loginCheck(session_id);
			if(session_pwd.equals(fvo.getUser_pwd())) {
				fvo.setUser_pwd(user_pwd);	
				this.foodService.pwdEdit(fvo);
				out.println("<script>");
				out.println("alert('비밀번호 변경 성공');");
				out.println("</script>");
				fm.setViewName("food_main");
				fm.addObject("fvo", fvo);
				return fm;
			} else {
				fm.setViewName("member_pwdEdit");
				return fm;
			}								
		}
		return null;
	}	
	
	//관리자 회원관리
	@RequestMapping("/member_manage")
	public ModelAndView member_manage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html;charset=UTF-8");
		//브라우저에 출력되는 문자와 태그 ,언어코딩 타입을 설정
		PrintWriter out=response.getWriter();//출력스트림 객체 생성
		HttpSession session=request.getSession();//세션 객체 생성

		String session_id = (String)session.getAttribute("user_id");
		
		if(session_id==null) {
			out.println("<script>");
			out.println("alert('다시 로그인하세요')");
			out.println("location='member_login';");
			out.println("</script>");
		} else {

			ModelAndView fm = new ModelAndView();

			fm.setViewName("member_manage");

			return fm;
		}
		return null;
	}
	
	//Ajax 회원관리 데이터 Response
	@ResponseBody
	@RequestMapping(value = "/member_search")
	public List<Food_MemberVO> member_search (Model model, Food_MemberVO fvo, String search_field, String search_type) throws Exception{
		
		search_field = "%" + search_field + "%" ;
		
		System.out.println("search_type : " + search_type);
		System.out.println("search_field : " + search_field);
		
		fvo.setSearch_type(search_type);
		fvo.setSearch_field(search_field);
		
			
		List<Food_MemberVO> mlist = this.foodService.getMemberList(fvo);			
		//System.out.println(user_name);
		System.out.println(mlist.get(0).getUser_name());
		return mlist;
	}
}

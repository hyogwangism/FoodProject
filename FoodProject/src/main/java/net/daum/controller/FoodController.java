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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.daum.service.FoodService;
import net.daum.vo.FoodLikeVO;
import net.daum.vo.FoodVO;
import net.daum.vo.Food_MemberVO;

@Controller
public class FoodController {

	@Autowired
	private FoodService foodService;

	@RequestMapping("/food_main")
	public ModelAndView Food_main(HttpServletRequest request, HttpServletResponse response, FoodVO f) {
		HttpSession session=request.getSession();//세션 객체 생성
		if((String)session.getAttribute("user_id") != null) {
			String session_id = (String)session.getAttribute("user_id"); 	
			Food_MemberVO fvo = this.foodService.loginCheck(session_id);
			ModelAndView fm = new ModelAndView();

			fm.addObject("fvo", fvo);
			fm.setViewName("food_main");
			return fm; /*정신차려 이 각박한 세상속에서*/

		} else {
			ModelAndView fm = new ModelAndView();
			fm.setViewName("food_main");
			return fm;
		}

	}



	//글 등록
	@RequestMapping("/food_write")
	public ModelAndView Food_write() {

		ModelAndView fm = new ModelAndView();

		fm.setViewName("food_write");
		return fm;
	}

	//자료실 저장
	@RequestMapping("/food_write_ok")
	public String bbs_write_ok(@ModelAttribute FoodVO f, HttpServletRequest request) throws Exception{
		String saveFolder=request.getRealPath("upload");
		//이진파일 업로드 서버경로
		System.out.println("이진파일 업로드 서버 실제 경로:"+saveFolder);

		int fileSize=5*1024*1024;//이진파일 업로드 최대크기
		MultipartRequest multi=null;//이진파일을 받을 참조변수

		multi=new MultipartRequest(request,saveFolder,fileSize,"UTF-8", new DefaultFileRenamePolicy());
		String food_place = multi.getParameter("food_place");
		String food_name = multi.getParameter("food_name");
		String food_taste = multi.getParameter("food_taste");
		String food_amount = multi.getParameter("food_amount");
		String food_location = multi.getParameter("food_location");
		String food_type = multi.getParameter("food_type");
		String food_space = multi.getParameter("food_space");
		String food_price= multi.getParameter("food_price");
		String food_parking = multi.getParameter("food_parking");
		String food_review = multi.getParameter("food_review");

		File UpFile01 = multi.getFile("food_photo01");//첨부한 이진파일
		File UpFile02 = multi.getFile("food_photo02");
		File UpFile03 = multi.getFile("food_photo03");
		//을 받아옴.
		if(UpFile01 != null || UpFile02 != null || UpFile03 != null) {//첨부한 이진파일이 있다면
			String fileName01 = null;
			String fileName02 = null;
			String fileName03 = null;

			if(UpFile01 == null) {
				fileName01 = "";
			} else {
				fileName01 = UpFile01.getName();
			}

			if(UpFile02 == null) {
				fileName02 = "";
			} else {
				fileName02 = UpFile02.getName();
			}

			if(UpFile03 == null) {
				fileName03 = "";
			} else {
				fileName03 = UpFile03.getName();
			}

			Calendar c=Calendar.getInstance();//칼렌더는 추상
			//클래스로 new로 객체 생성을 못함. 년월일 시분초 값을 반환
			int year=c.get(Calendar.YEAR);//년도값
			int month=c.get(Calendar.MONTH)+1;//월값. +1을 한
			//이유가 1월이 0으로 반환 되기 때문이다.
			int date=c.get(Calendar.DATE);//일값
			String homedir=saveFolder+"/"+year+"-"+month+"-"+date;//오늘
			//날짜 폴더 경로 저장
			File path1=new File(homedir);
			if(!(path1.exists())) {
				path1.mkdir();//오늘날짜 폴더경로를 생성
			}
			Random r=new Random();
			int random01=r.nextInt(100000000);
			int random02=r.nextInt(100000000);
			int random03=r.nextInt(100000000);

			/*첨부 파일 확장자 구함*/
			int index01=fileName01.lastIndexOf(".");//마침표 위치
			int index02=fileName02.lastIndexOf(".");//마침표 위치
			int index03=fileName03.lastIndexOf(".");//마침표 위치
			//번호를 구함
			String fileExtension01=fileName01.substring(index01+1);//마침표
			String fileExtension02=fileName02.substring(index02+1);//마침표
			String fileExtension03=fileName03.substring(index03+1);//마침표
			//이후부터 마지막 문자까지 구함.첨부파일 확장자를 구함
			String refileName01 = "";
			String refileName02 = "";
			String refileName03 = "";

			String fileDBName01 = "";
			String fileDBName02 = "";
			String fileDBName03 = "";

			if(fileName01 != "") {
				refileName01="food"+year+month+date+random01+"." + fileExtension01;//새로운 이진파일명 저장
				fileDBName01="/"+year+"-"+month+"-"+date+"/"+ refileName01;//디비에 저장될 레코드값
				UpFile01.renameTo(new File(homedir+"/"+refileName01));
			}

			if(fileName02 != "") {
				refileName02="food"+year+month+date+random02+"." + fileExtension02;//새로운 이진파일명 저장
				fileDBName02="/"+year+"-"+month+"-"+date+"/"+ refileName02;//디비에 저장될 레코드값
				UpFile02.renameTo(new File(homedir+"/"+refileName02));
			}

			if(fileName03 != "") {
				refileName03="food"+year+month+date+random03+"." + fileExtension03;//새로운 이진파일명 저장
				fileDBName03="/"+year+"-"+month+"-"+date+"/"+ refileName03;//디비에 저장될 레코드값
				UpFile03.renameTo(new File(homedir+"/"+refileName03));
			}


			//바뀌어진 이진파일로 업로드
			f.setFood_photo01(fileDBName01); f.setFood_photo02(fileDBName02); f.setFood_photo03(fileDBName03);
		}else {//mybatis에서는 컬럼에 null을 insert하지 못함.
			String fileDBName01=""; String fileDBName02=""; String fileDBName03="";

			f.setFood_photo01(fileDBName01);//첨부하지 않았을때 빈공백을 저장
			f.setFood_photo02(fileDBName02); f.setFood_photo03(fileDBName03);

		}

		f.setFood_place(food_place); f.setFood_name(food_name);
		f.setFood_taste(food_taste); f.setFood_amount(food_amount);
		f.setFood_location(food_location); f.setFood_type(food_type);
		f.setFood_space(food_space); f.setFood_price(food_price);
		f.setFood_parking(food_parking); f.setFood_review(food_review);

		this.foodService.insertFood(f);//자료실 저장

		return "redirect:/food_main";
		//return null;
	}//food_write_ok()


	//게시글 내용 보기
	@RequestMapping("/food_cont")
	public ModelAndView Food_cont(HttpServletRequest request, HttpSession session) {
		String session_id = (String)session.getAttribute("user_id");
		int food_no = Integer.parseInt(request.getParameter("food_no"));
		if(session_id != null) { 	
			ModelAndView fm = new ModelAndView();

			FoodVO f = this.foodService.getFoodCont(food_no);
			fm.addObject("f", f);
			fm.addObject("session_id", session_id);

			
			System.out.println("상세보기 페이지");
			
			FoodLikeVO like = new FoodLikeVO();
			
			like.setFood_no(food_no);
			like.setUser_id(session_id);
			int re = this.foodService.getLikeCount(like);
//			like = this.foodService.getFoodLike(session_id, food_no);
			fm.addObject("FoodLike", like);
			fm.addObject("likeCount", re);
			fm.addObject("session_id", session_id);

			fm.setViewName("food_cont");
			return fm;
		} else {
			ModelAndView fm = new ModelAndView();

			FoodVO f = this.foodService.getFoodCont(food_no);
			fm.addObject("f", f);

			fm.setViewName("food_cont");
			return fm;
		}
	}

	//자료실 수정
	@RequestMapping("/food_edit")
	public ModelAndView Food_edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

			int food_no = Integer.parseInt(request.getParameter("food_no"));
			ModelAndView fm = new ModelAndView();
			FoodVO f = this.foodService.getFoodCont(food_no);
			fm.setViewName("food_edit");
			fm.addObject("f", f);
			return fm;
		}
		return null;
	}

	@RequestMapping("/food_edit_ok")
	public String bbs_edit_ok(@ModelAttribute FoodVO f, HttpServletRequest request) throws Exception{
		String saveFolder=request.getRealPath("upload");
		//이진파일 업로드 서버경로
		System.out.println("이진파일 업로드 서버 실제 경로:"+saveFolder);

		int fileSize=5*1024*1024;//이진파일 업로드 최대크기
		MultipartRequest multi=null;//이진파일을 받을 참조변수

		multi=new MultipartRequest(request,saveFolder,fileSize,"UTF-8", new DefaultFileRenamePolicy());
		int food_no = Integer.parseInt(multi.getParameter("food_no"));
		String food_place = multi.getParameter("food_place");
		String food_name = multi.getParameter("food_name");
		String food_taste = multi.getParameter("food_taste");
		String food_amount = multi.getParameter("food_amount");
		String food_location = multi.getParameter("food_location");
		String food_type = multi.getParameter("food_type");
		String food_space = multi.getParameter("food_space");
		String food_price= multi.getParameter("food_price");
		String food_parking = multi.getParameter("food_parking");
		String food_review = multi.getParameter("food_review");

		File UpFile01 = multi.getFile("food_photo01");//첨부한 이진파일
		File UpFile02 = multi.getFile("food_photo02");
		File UpFile03 = multi.getFile("food_photo03");
		//을 받아옴.
		FoodVO f2 = this.foodService.getFoodCont(food_no);
		if(UpFile01 != null || UpFile02 != null || UpFile03 != null) {//첨부한 이진파일이 있다면
			String fileName01 = null;
			String fileName02 = null;
			String fileName03 = null;

			if(UpFile01 == null) {
				fileName01 = "";
			} else {
				fileName01 = UpFile01.getName();
			}

			if(UpFile02 == null) {
				fileName02 = "";
			} else {
				fileName02 = UpFile02.getName();
			}

			if(UpFile03 == null) {
				fileName03 = "";
			} else {
				fileName03 = UpFile03.getName();
			}

			File DelFile01=new File(saveFolder+f2.getFood_photo01());
			File DelFile02=new File(saveFolder+f2.getFood_photo02());
			File DelFile03=new File(saveFolder+f2.getFood_photo03());
			
			if(DelFile01.exists() || DelFile02.exists() || DelFile03.exists()) {
				DelFile01.delete();//기존 이진파일을 삭제
				DelFile02.delete();//기존 이진파일을 삭제
				DelFile03.delete();//기존 이진파일을 삭제
			}

			Calendar c=Calendar.getInstance();//칼렌더는 추상
			//클래스로 new로 객체 생성을 못함. 년월일 시분초 값을 반환
			int year=c.get(Calendar.YEAR);//년도값
			int month=c.get(Calendar.MONTH)+1;//월값. +1을 한
			//이유가 1월이 0으로 반환 되기 때문이다.
			int date=c.get(Calendar.DATE);//일값
			String homedir=saveFolder+"/"+year+"-"+month+"-"+date;//오늘
			//날짜 폴더 경로 저장
			File path1=new File(homedir);
			if(!(path1.exists())) {
				path1.mkdir();//오늘날짜 폴더경로를 생성
			}
			Random r=new Random();
			int random01=r.nextInt(100000000);
			int random02=r.nextInt(100000000);
			int random03=r.nextInt(100000000);

			/*첨부 파일 확장자 구함*/
			int index01=fileName01.lastIndexOf(".");//마침표 위치
			int index02=fileName02.lastIndexOf(".");//마침표 위치
			int index03=fileName03.lastIndexOf(".");//마침표 위치
			//번호를 구함
			String fileExtension01=fileName01.substring(index01+1);//마침표
			String fileExtension02=fileName02.substring(index02+1);//마침표
			String fileExtension03=fileName03.substring(index03+1);//마침표
			//이후부터 마지막 문자까지 구함.첨부파일 확장자를 구함
			String refileName01 = "";
			String refileName02 = "";
			String refileName03 = "";

			String fileDBName01 = "";
			String fileDBName02 = "";
			String fileDBName03 = "";

			if(fileName01 != "") {
				refileName01="food"+year+month+date+random01+"." + fileExtension01;//새로운 이진파일명 저장
				fileDBName01="/"+year+"-"+month+"-"+date+"/"+ refileName01;//디비에 저장될 레코드값
				UpFile01.renameTo(new File(homedir+"/"+refileName01));
			}

			if(fileName02 != "") {
				refileName02="food"+year+month+date+random02+"." + fileExtension02;//새로운 이진파일명 저장
				fileDBName02="/"+year+"-"+month+"-"+date+"/"+ refileName02;//디비에 저장될 레코드값
				UpFile02.renameTo(new File(homedir+"/"+refileName02));
			}

			if(fileName03 != "") {
				refileName03="food"+year+month+date+random03+"." + fileExtension03;//새로운 이진파일명 저장
				fileDBName03="/"+year+"-"+month+"-"+date+"/"+ refileName03;//디비에 저장될 레코드값
				UpFile03.renameTo(new File(homedir+"/"+refileName03));
			}


			//바뀌어진 이진파일로 업로드
			f.setFood_photo01(fileDBName01); f.setFood_photo02(fileDBName02); f.setFood_photo03(fileDBName03);
		}else {//mybatis에서는 컬럼에 null을 insert하지 못함.
			String fileDBName01=""; String fileDBName02=""; String fileDBName03="";
			if(f2.getFood_photo01() != null || f2.getFood_photo02() != null || f2.getFood_photo03() != null) {
				f.setFood_photo01(f2.getFood_photo01()); f.setFood_photo02(f2.getFood_photo02());
				f.setFood_photo03(f2.getFood_photo03());
			} else {
				f.setFood_photo01(fileDBName01);//첨부하지 않았을때 빈공백을 저장
				f.setFood_photo02(fileDBName02); f.setFood_photo03(fileDBName03);
			}
			
		}
		
		f.setFood_no(food_no);
		f.setFood_place(food_place); f.setFood_name(food_name);
		f.setFood_taste(food_taste); f.setFood_amount(food_amount);
		f.setFood_location(food_location); f.setFood_type(food_type);
		f.setFood_space(food_space); f.setFood_price(food_price);
		f.setFood_parking(food_parking); f.setFood_review(food_review);

		this.foodService.updateFood(f);//자료실 저장

		return "redirect:/food_cont?food_no=" + food_no;
		//return null;
	}//food_write_ok()

	//게시글 삭제
	@RequestMapping("/food_del")
	public ModelAndView Food_del(HttpServletRequest request, HttpServletResponse response , int food_no) throws Exception {
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
			this.foodService.delFood(food_no);
			fm.setViewName("food_main");

			return fm;
		}
		return null;
	}

}

package net.daum.controller;

import java.io.IOException;
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
import net.daum.vo.FoodLikeVO;
import net.daum.vo.FoodVO;

@Controller
public class AjaxController {

	@Autowired
	private FoodService foodService;

	/*음식 목록 AJAX
	 * 
	 */

	@ResponseBody
	@RequestMapping("/food_list")
	public List<FoodVO> Food_list(HttpServletRequest request, FoodVO f, HttpSession session) {
		String food_type = request.getParameter("food_type");
		session.setAttribute("food_type", food_type);
		System.out.println(food_type);
		if(food_type.equals(null) || food_type.equals("All")) {
			List<FoodVO> flist=this.foodService.getFoodAllList(f);
			return flist;
		} else {
			List<FoodVO> flist=this.foodService.getFoodList(food_type);
			return flist;
		}
	} 

	@ResponseBody
	@RequestMapping("/search_FoodList")
	public List<FoodVO> search_FoodList (Model model, FoodVO f, String search_field, String search_type) throws Exception{

		search_field = "%" + search_field + "%" ;

		System.out.println("search_type : " + search_type);
		System.out.println("search_field : " + search_field);

		f.setSearch_type(search_type);
		f.setSearch_field(search_field);


		List<FoodVO> flist = this.foodService.searchFoodList(f);			
		return flist;
	} 

	@ResponseBody
	@RequestMapping(value="/food_list2")
	public List<FoodVO> checkbox_Search(@RequestBody Map<String, List<String>> map, FoodVO f, 
			HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

		List<String> list1 = map.get("list1");
		List<String> list2 = map.get("list2");
		List<String> search_type = map.get("search_type");
		List<String> search_field = map.get("search_field");

		String food_type = (String)session.getAttribute("food_type");

		System.out.println(list1);
		System.out.println(list2);
		System.out.println(food_type);
		System.out.println(search_type);
		System.out.println(search_field);
		//System.out.println("search_field : " + search_field);


		if(food_type.equals("All")) {

			if (list1.isEmpty() && list2.isEmpty()) {

				List<FoodVO> food=foodService.getFoodAllList(f);
				model.addAttribute("food_list", food);

				return food;

			} else {

				System.out.println("리스트1" + list1);
				System.out.println("리스트2" + list2);


				List<FoodVO> food = foodService.selectFoodWhenAll(list1, list2);
				model.addAttribute("food_list", food);


				return food;
			}


		} else {

			if (list1.isEmpty() && list2.isEmpty()) {
				List<FoodVO> food = foodService.getFoodList(food_type);

				model.addAttribute("food_list", food);

				return food;

			} else {

				List<FoodVO> food=foodService.selectFood(list1, list2, food_type);
				model.addAttribute("food_list", food);

				return food;
			}
		}

	}	
	

	/* 찜하기 AJAX
	 * 
	 */
	@ResponseBody 
	@RequestMapping("/likeUp")
	public void likeup(@RequestBody Map<String, Object> request) {
	  int food_no = (int) request.get("food_no");
	  String session_id = (String) request.get("session_id");
	  System.out.println("컨트롤러 연결 성공");
	  this.foodService.likeUp(session_id, food_no);
	}

	@ResponseBody
	@RequestMapping("/likeDown")
	public void likeDown(@RequestBody Map<String, Object> request) {
	  int food_no = (int) request.get("food_no");
	  String session_id = (String) request.get("session_id");
	  System.out.println("좋아요 싫어요!");
	  this.foodService.likeDown(session_id, food_no);

	}
}

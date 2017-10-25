package com.qshuoo.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.qshuoo.pojo.User;

/**
 * 测试页面转发、重定向、数据流入、数据流出
 * @author qshuoo
 *
 */

@Controller

//存放name对应的session
//@SessionAttributes(names = {"map","model","mView"})
//存放type对应的session
//@SessionAttributes(types = String.class)
public class ControllerTest {
	
	/**
	 * 执行所有方法前都会执行 ModelAttribute注解的方法
	 */
	@ModelAttribute
	public void testModelAttribute() {
		System.out.println("testModelAttribute");
	}
	
	/**
	 * ModelAttribute设置参数时，会在request作用域中添加attribute可在前台直接访问
	 * @return
	 */
	@ModelAttribute("modelAttribute")
	public String testModelAttribute02() {
		System.out.println("testModelAttribute02");
		return "modelAttribute";
	}
	
	/**
	 * 普通转发
	 * @return
	 */
	@RequestMapping("/test01")
	public String test01() {
		return "success";
	}
	
	/**
	 * 重定向
	 * @return
	 */
	@RequestMapping("/test02")
	public String test02() {
		return "redirect:success.jsp";
	}
	
	/**
	 * 测试requestparam数据流入
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping("/test03")
	public String test03(@RequestParam(value="id") Long id, @RequestParam(value="name") String name) {
		System.out.println(id + " ++ " + name);
		return "success";
	}
	
	/**
	 * 入参处传值，参数名与input表单name相同，或url中key相同
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping("/test04")
	public String test04(Long id, String name) {
		System.out.println(id + " ++ " + name);
		return "success";
		
	}
	
	/**
	 * cookievalue传参
	 * @param sessionId
	 * @return
	 */
	@RequestMapping("/test05")
	public String test05(@CookieValue("JSESSIONID") String sessionId) {
		System.out.println(sessionId);
		return "success";
	}
	/**
	 * 传pojo
	 * @param user
	 * @return
	 */
	@RequestMapping("/test06")
	public String test06(User user) {
		System.out.println(user);
		return "success";
	}
	
	/**
	 * 测试ModelAndView
	 * @return
	 */
	@RequestMapping( "/test07")
	public ModelAndView test07() {
		ModelAndView mView = new ModelAndView("success");
		mView.addObject("mView", "mView");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("m1", "m1");
		map.put("m2", "m2");
		mView.addAllObjects(map);
//		mView.setViewName("success");
		return mView;
	}
	
	/**
	 * 前台el表达式输出map
	 * @param map
	 * @return
	 */
	@RequestMapping("/test08")
	public String test08(Map<String, Object> map) {
		map.put("map", "map");
		return "success";
	}
	
	/**
	 * 前台el表达式输出model
	 * @param model
	 * @return
	 */
	@RequestMapping("/test09")
	public String test09(Model model) {
		model.addAttribute("model", "model");
		return "success";
	}
	
	/**
	 * 前台"没有"输出modelandview
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/test10")
	public String test10(ModelAndView modelAndView) {
		modelAndView.addObject("modelandview", "modelandview");
		return "success";
	}

}

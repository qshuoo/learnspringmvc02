package com.qshuoo.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qshuoo.pojo.User;

@Controller
public class ControllerTest {
	
	
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

}

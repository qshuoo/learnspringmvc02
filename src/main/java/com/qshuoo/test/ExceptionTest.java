package com.qshuoo.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionTest {

	@RequestMapping("/ex01")
	public String ex01() {
		System.out.println(1/0);
		return "success";
	}
}

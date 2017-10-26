package com.qshuoo.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


/**
 * 自定义编写的异常类
 * @author Administrator
 *
 */
public class MyExceptionResolver implements HandlerExceptionResolver {

	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
			Exception arg3) {
		// TODO Auto-generated method stub
		
		ModelAndView mView = new ModelAndView("error01");
		
		//编写异常处理操作
		System.out.println("ex01");
		
		return mView;
	}

}

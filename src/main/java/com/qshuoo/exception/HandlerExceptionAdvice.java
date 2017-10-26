package com.qshuoo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerExceptionAdvice {
	
	/**
	 * ExceptionHanler未跟参数，拦截所有异常
	 * @return
	 */
	@ExceptionHandler
	public String exception01(Exception ex) {
		System.out.println("ex02");
		return "error02";
	}

}

package com.qshuoo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TimeInterceptor implements HandlerInterceptor{
	
	ThreadLocal<Long> startTimeInfo = new ThreadLocal<Long>();

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		long beginTime = startTimeInfo.get();
		long endTime = System.currentTimeMillis();
		long startTime = endTime - beginTime;
		System.out.println("开始时间：" + beginTime);
		System.out.println("结束时间：" + endTime);
		System.out.println("处理时间：" + startTime);
		
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		long beginTime = System.currentTimeMillis();
		startTimeInfo.set(beginTime);
		return true;
	}

}

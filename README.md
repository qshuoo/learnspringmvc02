# learnspringmvc2
再次学习springmvc
## springmvc 概述
一种轻量级的、基于MVC的Web层应用框架

## 常用组件
*	DispatcherServlet：前端控制器(核心控制器)
*	Controller：处理器/页面控制器
*	HandlerMapping ：请求映射到处理器
*	ViewResolver ： 视图解析器
*	LocalResolver：本地化、国际化
*	MultipartResolver：文件上传解析器
*	HandlerExceptionResolver：异常处理器

## 环境搭建
### jar包
	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-webmvc</artifactId>
	    <version>4.3.10.RELEASE</version>
	</dependency>

### 配置springmvc.xml
1.	添加约束

	<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:mvc="http://www.springframework.org/schema/mvc"
		xmlns:context="http://www.springframework.org/schema/context"
		xsi:schemaLocation="http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-4.1.xsd
			http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
			http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.1.xsd">
			
2.	设置扫描组件的包

	<context:component-scan base-package="com.qshuoo.*"/>
	
3.	配置映射解析路径

	<bean id="internalResourceViewResolver"
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/" />
		<property name="suffix" value=".jsp" />
	</bean>

### 配置web.xml
*	配置springmvc核心控制器

	<servlet>
		<servlet-name>springDispatcherServlet</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<!-- 配置DispatcherServlet的初始化參數：设置文件的路径和文件名称 -->
	<init-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>classPath:springmvc.xml</param-value>
	</init-param>
	<load-on-startup>1</load-on-startup>
	</servlet>
	<servlet-mapping>
		<servlet-name>springDispatcherServlet</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping> 

				


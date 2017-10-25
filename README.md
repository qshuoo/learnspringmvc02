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

添加约束

	<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:mvc="http://www.springframework.org/schema/mvc"
		xmlns:context="http://www.springframework.org/schema/context"
		xsi:schemaLocation="http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-4.1.xsd
			http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
			http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.1.xsd">
			
设置扫描组件的包

	<context:component-scan base-package="com.qshuoo.*"/>

配置映射解析路径

	<bean id="internalResourceViewResolver"
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/" />
		<property name="suffix" value=".jsp" />
	</bean>

### 配置web.xml
配置springmvc核心控制器

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
		<url-pattern>*.do</url-pattern>
	</servlet-mapping> 
	
`<url-pattern>/</url-pattern>中如果使用/会拦截静态资源访问不到，可以使用*.do`


## 数据流入
	
*	springmvc数据流入	

	1	使用HttpServletRequest获取	
	
	2	@RequestParam注解	
	
	3	通过入参处的参数		
	
	4	@RequestHeader 	
	
	5	@CookieValue 		
	
	6	使用POJO（实体类）作为参数		
	
	7	使用Servlet原生API作为参数		

## 输出模型

*	springmvc数据流出

	ModelAndView	

	Map 及 Model

	@SessionAttributes

	@ModelAttribute

`sringmvc在执行处理用户请求的方法之前会创建一个对象，这个对象用于存储数据，并且这个对象可以提供给处理请求的方法使用。`

`如果方法的入参为 Map 或 Model 类型，Spring MVC 会将隐含模型的引用传递给这些入参。`

`在方法体内，开发者可以通过这个入参对象访问到模型中的所有数据，也可以向模型中添加新的属性数据`
				

## 文件上传

Spring MVC 为文件上传提供了直接的支持，这种支持是通过即插即用的 MultipartResolver 实现的。          
		
Spring 用 Commons FileUpload 技术实现了一个 MultipartResolver 实现类：CommonsMultipartResovler     

### 实现步骤

导包 （fileupload 和commons-io）		

spring.xml中配置 MultipartResolver		


	<bean id="multipartResolver"
		class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<property name="defaultEncoding" value="UTF-8"></property>
		<property name="maxUploadSize" value="1024000"></property>
	</bean>


编写前端页面		

编写控制器java代码	

## 文件下载

ResponseEntity 响应实体类，除了可以指定返回数据，还可以定义返回的HttpHeaders和HttpStatus。

ResponseEntity是一个对响应数据，响应头，响应码进行封装的一个是实体类

## 拦截器

类似于Servlet开发中的过滤器Filter，用于对处理器进行预处理和后处理。		

用户可以自定义拦截器来实现特定的功能，自定义的拦截器必须实现HandlerInterceptor接口 		

### 常见应用场景

1。	日志记录：记录请求信息的日志，以便进行信息监控、信息统计、计算PV（Page View）等。
2。	权限检查：如登录检测，进入处理器检测检测是否登录，如果没有直接返回到登录页面；
3。	性能监控：有时候系统在某段时间莫名其妙的慢，可以通过拦截器在进入处理器之前记录开始时间，在处理完后记录结束时间，从而得到该请求的处理时间；(运维，测试)
4。	通用行为：读取cookie得到用户信息并将用户对象放入请求，从而方便后续流程使用，还有如提取Locale、Theme信息等，只要是多个处理器都需要的即可使用拦截器实现。
5。	OpenSessionInView：如Hibernate，在进入处理器打开Session，在完成后关闭Session。

### 核心接口 HandlerInterceptor

核心方法	
1.	preHandle

	实现预处理，返回值为true时，继续流程，调用下一个拦截器或处理器，false表示流程中断，使用response响应
	
2.	postHandle
	
	后处理回调，实现处理器的后处理，但在渲染视图之前
	
3.	afterCompletion

	整个请求处理完毕回调，即视图渲染完毕之后，但仅调用处理器执行链中preHandle返回true的拦截器的afterCompletion
	
### 自定义拦截器实现步骤

1，	自定义实现HandlerInterceptor接口
2.	重写HandlerInterceptor接口方法
3.	在springmvc.xml进行配置，配置拦截器拦截范围

`HandlerInterceptor线程不安全，在某些需要线程安全的问题上，可以使用ThreadLocal对线程进行变量绑定`






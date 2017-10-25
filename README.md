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

	1使用HttpServletRequest获取	
	
	2@RequestParam注解	
	
	3通过入参处的参数		
	
	4@RequestHeader 	
	
	5@CookieValue 		
	
	6使用POJO（实体类）作为参数		
	
	7使用Servlet原生API作为参数		

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



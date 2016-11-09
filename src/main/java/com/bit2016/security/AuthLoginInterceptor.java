package com.bit2016.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bit2016.mysite.service.UserService;
import com.bit2016.mysite.vo.UserVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler /*HandlerMethod*/)
			throws Exception {

		String email =request.getParameter("email");
		String password=request.getParameter("password");
		
		
		//만들어져있는 콘테이너를 불러오기   
		//Web Application Context 받아오기
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		//콘테이너 사용방법
		//Container 안에 있는 UserService Bean(객체) 를 받아오기 
		UserService userService = ac.getBean(UserService.class); //ac.getBean("userService"); 로 해줘도 된다
		//userservice 는 new를 써서 디펜드하지 못한다.  
		
		//로그인구현 
		//데이터베이스에서 해당 UserVo 받아오기
		UserVo userVo = userService.login(email, password);
		//패스워드나 이메일이 일치하지 않는경우
		if(userVo == null){
			response.sendRedirect( request.getContextPath()+"/user/loginform?result=fail");
			return false;
		}
		
		//인증처리
		HttpSession session=request.getSession(true	);
		session.setAttribute("authUser", userVo);
		response.sendRedirect( request.getContextPath() );
		
		return false;
	}

}

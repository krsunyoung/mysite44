package com.bit2016.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bit2016.mysite.vo.UserVo;

public class AuthIntercetor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler /* HandelerMethod 객체*/)
			throws Exception {
		
		//1.handler 종류
		if(handler instanceof HandlerMethod==false){
			return true;
		}
		//2. @Auth가 붙어있는지 없는지 확인
		Auth auth=((HandlerMethod)handler).getMethodAnnotation(Auth.class);
		if(auth==null){ //접근제어 가 필요없는 handler
			return true;
		}
		//3. 접근제어 (인증체크)
		HttpSession session =request.getSession();
		if(session == null){
			response.sendRedirect(request.getContextPath()+"/user/loginform");
			return false;
		}
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null){
			response.sendRedirect(request.getContextPath()+"/user/loginform");
			return false;
		}
		//4. 인증된 사용자
		return true;
	}

}

package com.bit2016.mysite.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlabalExceptionHandler {
	
//	@ExceptionHandler(UserDaoException.class)
//	public String handelrDaoException(HttpServletRequest request, Exception e ){
// 위아래 다른점은 아래는 모든 exception 오류를 다 받지만 위에는 userdaoexception 만 받는다. 
	
	/*@ExceptionHandler(Exception.class)
	public String handlerException(HttpServletRequest request, Exception e ){
		//1.로깅
		System.out.println("exception : "+e);
		
		//2.ajax 요청 여부 판단
		
		 if("application/json".equals(request.getContentType())){
		} 
		
		
		return "error/exception";
	}
	*/
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(HttpServletRequest request, Exception e ){
		//1.로깅
		System.out.println("exception : "+e);
		
		//2.ajax 요청 여부 판단
		/*
		 if("application/json".equals(request.getContentType())){
		} */
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("exceptionMessage",e.getMessage());
		mav.setViewName("error/exception");
		return mav;
	}
}

package com.bit2016.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlabalExceptionHandler {
	private static final Log log = LogFactory.getLog(GlabalExceptionHandler.class);
	
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
		
		//e.printStackTrace();
		
		//1.로깅
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		
		
		//System.out.println("exception : "+e);
		log.error(errors.toString());
		
		//2. 시스템 오류 안내화면
		//ajax 요청 여부 판단
		/*
		 if("application/json".equals(request.getContentType())){
		} */
		
		ModelAndView mav = new ModelAndView();
		//mav.addObject("exceptionMessage",e.getMessage());
		mav.setViewName("error/exception");
		return mav;
	}
}

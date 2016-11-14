package com.bit2016.mysite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bit2016.mysite.service.UserService;
import com.bit2016.mysite.vo.UserVo;
import com.bit2016.security.Auth;
import com.bit2016.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/joinform")
	public String joinForm(@ModelAttribute UserVo userVo){
		return "user/joinform";
	}
	@RequestMapping("/loginform")
	public String loginForm(){
		return "user/loginform";
	}
	@RequestMapping("/join")
	public String join(@ModelAttribute @Valid UserVo userVo,
			BindingResult result,
			Model model){ 
		//결과가 안에 있음 bindingresult 
		//uservo에 변수를 valid를 함.
		//어떤 유효성을 지정할것인지 vo에 설정해준다.
		
		if(result.hasErrors()==true){
/*			List<ObjectError> list= result.getAllErrors();
			for(ObjectError o : list){
				System.out.println("Object :"+o);
			}
*/			return "user/joinform";
		}
		
		userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}
	@RequestMapping("/joinsuccess")
	public String joinSuccess(){
		return "user/joinsuccess";
	}
	/*@RequestMapping("/login")
	public String login(
			@RequestParam(value="email", required=true, defaultValue=" ") String email,
			@RequestParam(value="password", required=true, defaultValue=" ") String password,
			HttpSession session){
		UserVo userVo = userService.login(email, password);
		if(userVo == null){
			return "redirect:/user/loginform?result=fail";
		}
		//인증성공 (처리)
		session.setAttribute("authUser", userVo);
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}
	*/
	
	@Auth
	@RequestMapping("/modifyform")
	public String modifyForm(@AuthUser UserVo authUser, Model model){
		/*	UserVo authUser=(UserVo)session.getAttribute("authUser");
		
		//접근제한 (로그인없이 수정하는 사이트 들어갈때)
		if( authUser == null){
			return "redirect:/user/loginform";
		}*/
		UserVo vo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo",vo);  //modifyform 에 받는 값. 
		return "user/modifyform";
		
	}

	@Auth
	@RequestMapping("/modify")
	public String modify(@AuthUser UserVo authUser, @ModelAttribute UserVo vo){
		/*UserVo authUser = (UserVo)session.getAttribute("authUser");
		//접근제어
		if( authUser == null){
			return "redirect : /user/loginform";
		}*/
		
		vo.setNo(authUser.getNo());
		userService.updateUser(vo);
		authUser.setName(vo.getName());
		return "redirect:/user/modifyform?update=success";
	}
	/*
	@ExceptionHandler(UserDaoException.class)
	public String handlerUserDaoException(){
		//1. loggin (파일에 내용 저장)
		
		//2. 사용자에게 사과(안내(error) 페이지)
		return "error/500";
	}*/
}

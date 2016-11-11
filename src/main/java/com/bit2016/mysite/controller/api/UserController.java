package com.bit2016.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit2016.dto.JSONResult;
import com.bit2016.dto.SingletonClass;
import com.bit2016.mysite.service.UserService;

//id 를 주지않으면 다른 userController 이름과 충돌나서 에러가남. 
@Controller("userApiController")
@RequestMapping("/user/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/checkemail")
	public Object checkEmail(  //Map<String, Object>  대신 Object로 써줘도 됨
			@RequestParam(value="email",required=true, defaultValue="" ) String email
			){
		SingletonClass sc = SingletonClass.getInstance();
		
		boolean result = userService.emailExist(email);
		
		return JSONResult.success(result ? "exist" : "not exist");
/*		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		
		if(result){
			map.put("data", "exist");
		}else{
			map.put("data", "not exist");
		}
		
		return map; */
	}

}

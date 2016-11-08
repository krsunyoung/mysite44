package com.bit2016.mysite.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit2016.mysite.service.GuestbookService;
import com.bit2016.mysite.vo.GuestbookVo;

@Controller("guestbookApiController")
@RequestMapping("/guestbook/api")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody
	@RequestMapping("/list")
	public Object list(
		@RequestParam(value="p", required=true, defaultValue="1") Integer page
			){ //defaultValue 는 null 값일경우 1로 반환을 한다. 
		List<GuestbookVo> list = guestbookService.list(page);
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("result", "success");
		map.put("data", list);
		
		return map;
	}
	
	@RequestMapping(value="/delete")
	public Object delete(@ModelAttribute GuestbookVo vo){
		guestbookService.delete(vo);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("result", "success");
		map.put("data",vo.getNo());
		return map;
	}
	
}

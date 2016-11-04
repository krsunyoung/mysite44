package com.bit2016.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bit2016.mysite.service.GuestbookService;
import com.bit2016.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping("")
	public String list(Model model){
		List<GuestbookVo> list= guestbookService.list();
		model.addAttribute("list", list);
		return "guestbook/list";
	}

	@RequestMapping(value="/insert")
	public String insert(@ModelAttribute GuestbookVo vo){
		guestbookService.add(vo);
		return "redirect:/guestbook";
	}
	
	
	@RequestMapping("/deleteform/{no}")
	public String deleteform(@PathVariable("no") int no, Model model){
		//parameter를 받고 싶으면 requestparam을 써주면 된다.
		model.addAttribute("no", no);
		return "guestbook/deleteform";
		
	}
	
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@ModelAttribute GuestbookVo vo){
		guestbookService.delete(vo);
		return "redirect:/guestbook";
	}
	
}

package com.bit2016.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bit2016.mysite.service.GalleryService;
import com.bit2016.mysite.vo.GalleryVo;
import com.bit2016.mysite.vo.UserVo;
import com.bit2016.security.Auth;
import com.bit2016.security.AuthUser;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("")
	public String index(){
		
		return "gallery/index";
	}
	
	
	@RequestMapping("/form")
	public String form(){
		return "gallery/form";
	}
	
	@Auth
	@ResponseBody
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(
		@AuthUser UserVo authUser,
		@ModelAttribute GalleryVo vo,
		@RequestParam(value = "comments", required=true, defaultValue="") String comments,
		@RequestParam(value = "file") MultipartFile file,
		Model model){
		String url = galleryService.restore(file);
		vo.setUsersNo(authUser.getNo());
		model.addAttribute("url",url);
	//	galleryService.upload(vo);
		return "redirect:/gallery";
	}
	
	
}

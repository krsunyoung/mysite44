package com.bit2016.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit2016.mysite.service.BoardService;
import com.bit2016.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("")
	public String list(
			@RequestParam(value="keyword", required =true, defaultValue=" ")String keyword,
			@RequestParam(value="page", required =true, defaultValue=" ")Integer page,
			@RequestParam(value="size", required =true, defaultValue=" ")Integer size,
			Model model){
		List<BoardVo> list= boardService.list(keyword, page, size);
		model.addAttribute("list", list);
		return "board/list";
	}
	
}

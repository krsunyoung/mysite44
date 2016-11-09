package com.bit2016.mysite.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit2016.mysite.service.BoardService;
import com.bit2016.mysite.vo.BoardVo;
import com.bit2016.mysite.vo.UserVo;
import com.bit2016.security.Auth;
import com.bit2016.security.AuthUser;

@Controller
@RequestMapping( "/board" )
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping( "" )
	public String index(
		@AuthUser UserVo authUser,
		@RequestParam( value="p", required=true, defaultValue="1") Integer page,
		@RequestParam( value="kwd", required=true, defaultValue="") String keyword,
		Model model ) {
		
		Map<String, Object> map = 
				boardService.getMessageList( page, keyword );
		
		model.addAttribute( "map", map );
		return "board/index";
	}

	@RequestMapping( "/view" )
	public String view(
		@RequestParam( value="no", required=true, defaultValue="0") Long no,
		@RequestParam( value="p", required=true, defaultValue="1") Integer page,
		@RequestParam( value="kwd", required=true, defaultValue="") String keyword,
		Model model ){
		
		BoardVo boardVo = boardService.getMessage(no);
		
		model.addAttribute( "boardVo", boardVo );
		model.addAttribute( "page", page );
		model.addAttribute( "keyword", keyword );

		return "board/view";
	}
	
	@Auth
	@RequestMapping( value="/modify", method=RequestMethod.GET )
	public String modify(
		@AuthUser UserVo authUser,
		@RequestParam( value="no", required=true, defaultValue="0") Long no,
		@RequestParam( value="p", required=true, defaultValue="1") Integer page,
		@RequestParam( value="kwd", required=true, defaultValue="") String keyword,
		Model model ){
		/*/ 권한 체크
		/////////////////////////////////////
		UserVo authUser = (UserVo)session.getAttribute( "authUser" );
		if( authUser == null ){
			return "redirect:/user/loginform";
		}
		////////////////////////////////////*/
			
		BoardVo boardVo = boardService.getMessage(no);
			
		model.addAttribute( "boardVo", boardVo );
		model.addAttribute( "page", page );
		model.addAttribute( "keyword", keyword );
		//keyword에 WebUtil을 만들어서 따로 encoding을 해줬지만 안해줘도 아직 차이를 느끼지 못하여 삭제 하고 진행
		// 추후 문제가 있을시 WebUtil을 만들어서 encoding 설정을 해야함.
		return "board/modify";
	}
	@Auth
	@RequestMapping( value="/reply", method=RequestMethod.GET )
	public String reply(
		@AuthUser UserVo authUser,
		@RequestParam( value="no", required=true, defaultValue="0") Long no,
		@RequestParam( value="p", required=true, defaultValue="1") Integer page,
		@RequestParam( value="kwd", required=true, defaultValue="") String keyword,
		Model model ){
		/*/ 권한 체크
		/////////////////////////////////////
		UserVo authUser = (UserVo)session.getAttribute( "authUser" );
		if( authUser == null ){
			return "redirect:/user/loginform";
		}
		////////////////////////////////////*/
			
		BoardVo boardVo = boardService.getMessage(no);
			
		model.addAttribute( "boardVo", boardVo );
		model.addAttribute( "page", page );
		model.addAttribute( "keyword", keyword );

		return "board/reply";
	}
	
	@Auth
	@RequestMapping( value="/modify", method=RequestMethod.POST )
	public String modify(
			@AuthUser UserVo authUser,
			@ModelAttribute BoardVo boardVo,
		@RequestParam( value="p", required=true, defaultValue="1") Integer page,
		@RequestParam( value="kwd", required=true, defaultValue="") String keyword ){
		/*/ 권한 체크
		/////////////////////////////////////
		UserVo authUser = (UserVo)session.getAttribute( "authUser" );
		if( authUser == null ){
			return "redirect:/user/loginform";
		}
		////////////////////////////////////*/
		
		boardVo.setUserNo( authUser.getNo() );
		boardService.updateMessage(boardVo);
		
		return 
			"redirect:/board/view" +	
			"?no=" + boardVo.getNo() + 
			"&kwd=" + keyword;
	}
	
	@Auth
	@RequestMapping( "/delete" )
	public String delete(
		@AuthUser UserVo authUser,
		@ModelAttribute BoardVo vo,
		@RequestParam( value="p", required=true, defaultValue="1") Integer page,
		@RequestParam( value="kwd", required=true, defaultValue="") String keyword ){
	/*	// 권한 체크
		/////////////////////////////////////
		UserVo authUser = (UserVo)session.getAttribute( "authUser" );
		if( authUser == null ){
			return "redirect:/user/loginform";
		}
		/////////////////////////////////////
	*/
		vo.setUserNo( authUser.getNo() );
		boardService.deleteMessage( vo );
		
		return 
			"redirect:/board" +
			"?p=" + page + 
			"&kwd=" + keyword;
	}

	@Auth
	@RequestMapping( value="/write", method=RequestMethod.GET )
	public String write( @AuthUser UserVo authUser) {
/*		// 권한 체크
		/////////////////////////////////////
		UserVo authUser = (UserVo)session.getAttribute( "authUser" );
		if( authUser == null ){
			return "redirect:/user/loginform";
		}
		/////////////////////////////////////
*/
		return "board/write";
	}
	
	@Auth
	@RequestMapping( value="/write", method=RequestMethod.POST )
	public String write(
			@AuthUser UserVo authUser,
			@ModelAttribute BoardVo vo,
		@RequestParam( value="p", required=true, defaultValue="1") Integer page,
		@RequestParam( value="kwd", required=true, defaultValue="") String keyword ){
		/*/ 권한 체크
		/////////////////////////////////////
		UserVo authUser = (UserVo)session.getAttribute( "authUser" );
		if( authUser == null ){
			return "redirect:/user/loginform";
		}
		////////////////////////////////////*/
			
		vo.setUserNo( authUser.getNo() );
		boardService.writeMessage( vo );
		
		return
			"redirect:/board" +
			"?p=" + page + 
			"&kwd=" +keyword;
	}
}
package com.bit2016.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2016.mysite.repository.BoardDao;
import com.bit2016.mysite.vo.BoardVo;

@Service
public class BoardService {
	
		@Autowired
		private BoardDao boardDao;
		
		public List<BoardVo> list( String keyword, Integer page, Integer size){
			List<BoardVo> list= boardDao.getList(keyword, page, size);
			return  list;
		}
		
}

package com.bit2016.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2016.mysite.repository.GuestbookDao;
import com.bit2016.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;
	
	public void add(GuestbookVo vo){
		guestbookDao.insert(vo);
	}
	
	public List<GuestbookVo> list(){
		List<GuestbookVo> list= guestbookDao.getList();
		return  list;
	}
	public void delete(GuestbookVo vo){
		guestbookDao.delete(vo);
	}
	
}

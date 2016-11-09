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
		Long no = guestbookDao.insert(vo);
		System.out.println(no);
		//GuestbookVo vo = guestbookDao.get
	}

	public GuestbookVo add2(GuestbookVo vo){
		Long no = guestbookDao.insert(vo);
		return guestbookDao.get(no);
	}
	public List<GuestbookVo> list(){
		List<GuestbookVo> list= guestbookDao.getList();
		return  list;
	}
	public List<GuestbookVo> list(int page){
		return guestbookDao.getList(page);
	}
	public void delete(GuestbookVo vo){
		guestbookDao.delete(vo);
	}

	
}

package com.bit2016.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.bit2016.mysite.vo.GalleryVo;
import com.bit2016.mysite.vo.GuestbookVo;

public class GalleryDao {
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestbookVo> getList(){
		return sqlSession.selectList("gallery.getList");
	}
	
	public int insert(GalleryVo galleryVo){
		return sqlSession.insert("gallery.insert", galleryVo);
	}
	
}

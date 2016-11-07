package com.bit2016.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2016.mysite.exception.UserDaoException;
import com.bit2016.mysite.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private DataSource datasource;
	
	public void update(UserVo vo){
		sqlSession.update("user.update",vo);
	}
	
	// 이메일 체크
	public UserVo get(String email){
		
		return sqlSession.selectOne("user.getByEmail",email);
	}
	
	//로그인할때 사용(인증)
	public UserVo get(String email, String password	) throws UserDaoException{
		Map<String , Object> map=new HashMap<String, Object>();
		map.put("email",email);
		map.put("password",password);
		
		return sqlSession.selectOne("user.getByEmailAndPassword",map);
		//one인데 list를 하면 에러가 난다. 
	}
	//회원정보 수정
	public UserVo get(Long no){
		
		return sqlSession.selectOne("user.getByNo", no);
		
	}
	
	public void insert(UserVo vo){
	
		sqlSession.insert("user.insert",vo);

	}
}

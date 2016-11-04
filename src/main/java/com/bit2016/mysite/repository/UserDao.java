package com.bit2016.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.bit2016.mysite.vo.UserVo;

@Repository
public class UserDao {
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 오류 :" + e);
		}

		return conn;
	}
	public boolean update(UserVo vo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result= 0;
		try{
			conn = getConnection();
			String sql ="update users set name = ? , gender = ?,password = ? where no=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName()	);
			pstmt.setString(2, vo.getGender());
			pstmt.setString(3, vo.getPassword());
			pstmt.setLong(4, vo.getNo());
			
			result = pstmt.executeUpdate();
			
		}catch(SQLException e){
			System.out.println("error : "+e);
		}finally {
			try{
				if(pstmt != null){
					pstmt.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch(SQLException e){
				System.out.println("error : "+e);
				
			}
		}
		return result == 1;
	}
	
	// 이메일 체크
	public UserVo get(String email){
		UserVo vo = null;
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = "select no , email, name from users where email= ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if( rs.next()){
				vo = new UserVo();
				vo.setNo(rs.getLong(1));
				vo.setEmail(rs.getString(2));
				vo.setName(rs.getString(3));
			}
			
		} catch (SQLException e) {
			System.out.println( "error :"+ e);		
		}finally{
			try{
				if(rs != null){
					rs.close();
				}
				if(pstmt != null){
					pstmt.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch(SQLException e){
				System.out.println( "error :"+ e);		
			}
		}
		
		return vo;
	}
	
	//로그인할때 사용(인증)
	public UserVo get(String email, String password	){
		UserVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "select no, name from users where email=? and password=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			rs =pstmt.executeQuery();
			
			if(rs.next()){
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				vo = new UserVo();
				vo.setNo(no);
				vo.setName(name);
				
			}
		} catch (SQLException e) {
			System.out.println("error :" +e);
		}finally{
			try{
				if(rs != null){
					rs.close();
				}
				if(pstmt != null){
					pstmt.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch (SQLException e){
				System.out.println("error :" +e);
			}
		}
		
		
		return vo;
		
	}
	//회원정보 수정
	public UserVo get(Long no){
		Connection conn = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		UserVo vo = null;
		try {
			conn = getConnection();
			String sql = "select no, name, email, gender from users where no= ?";
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			
			rs=pstmt.executeQuery();
			
			if( rs.next()){
//				long no1 = rs.getLong(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String gender = rs.getString(4);
				
				vo = new UserVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setEmail(email);
				vo.setGender(gender);
				
							
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally{
			try{
				
				if(rs !=null){
					rs.close();
				}
				if(pstmt !=null){
					pstmt.close();
				}
				if(conn !=null){
					conn.close();
				}
			}catch(SQLException e){
				System.out.println("error :" + e);
				
			}
		}
		
		
		return vo;
		
	}
	
	public void insert(UserVo vo){
		Connection conn = null;
		PreparedStatement pstmt = null;
				
		try {
			conn = getConnection();
			
			String sql = "insert into users values(user_seq.nextval, ?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error :"+e);
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch(SQLException e){
				System.out.println("error :"+e);
			}
		}
		
		
		
	}
}

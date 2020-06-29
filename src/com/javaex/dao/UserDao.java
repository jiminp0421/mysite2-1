package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.javaex.vo.UserVo;

public class UserDao {

	// 0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			// System.out.println("접속성공");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	private void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 회원추가
	public int insert(UserVo vo) { // DB insert에서 가져오는거야! (1)

		int count = 0;
		getConnection();

		try {
			// 3, sql문 준비 / 바인딩/ 실행
			String query = ""; // 쿼리문 문자열 만들기, ? 주의
			query += " insert into users";
			query += " values(seq_users_no.nextval, ?, ?, ?, ? )";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setString(1, vo.getId()); // 첫번째 ?
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getGender());

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return count;
		
	}
	
	

	// 로그인한 사용자 가져오기(22) (23)db에 select를 만들자! id pw가 where 정보에 똑같은 사람을 갖고오는거야
	public UserVo getUser(String id, String password) {
		UserVo vo = null;
		getConnection(); // 시작

		try {
			// 3, sql문 준비 / 바인딩/ 실행
			String query = ""; // 쿼리문 문자열 만들기, ? 주의
			query += " select no, name ";
			query += " from users ";
			query += " where id = ? ";
			query += " and password = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();

			// 4 결과처리
			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");

				vo = new UserVo();
				vo.setNo(no);
				vo.setName(name);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		return vo; // 끝
		
	
	}
	
	
	
	public UserVo getUser(int no) { //주소를 return
		UserVo vo = null;
		getConnection();
		
		
		try {
			
			// 3, sql문 준비 / 바인딩/ 실행
			String query = ""; // 쿼리문 문자열 만들기, ? 주의
			query += " select no, id, password, name, gender ";
			query += " from users ";
			query += " where no = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			// 4 결과처리
			while (rs.next()) {
				int rNo = rs.getInt("no");
				String id = rs.getString("id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String gender = rs.getString("gender");

				vo = new UserVo(rNo, id, password, name, gender); //실제메모리

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		close();
		return vo;
	}
	
	
	public int update(UserVo vo) {
		int count = 0;
		getConnection();
		
		try {
			// 3, sql문 준비 / 바인딩/ 실행
			String query = ""; // 쿼리문 문자열 만들기, ? 주의
			query += " update users ";
			query += " set name = ? , ";
			query += "     password = ? , ";
			query += "     gender = ? ";
			query += " where no = ? ";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getGender());
			pstmt.setInt(4, vo.getNo());
			
			count = pstmt.executeUpdate();
		
			
			
		}catch (SQLException e) {
			System.out.println("error:" + e);
		}
		

			close();
		return count;

	}

	
		
	
}


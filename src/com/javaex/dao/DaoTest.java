package com.javaex.dao;

import com.javaex.vo.UserVo;

public class DaoTest {

	public static void main(String[] args) {
		
		
		
		UserDao userDao = new UserDao();
		
		UserVo vo = new UserVo("hi", "1234", "이정재", "female"); //UserVo의 생성자를 가져오는거야 4개생성자만들었어 여기에서 테스트 하기!
		userDao.insert(vo);

		
	}

}

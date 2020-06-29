package com.javaex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.util.WebUtil;

@WebServlet("/main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("main"); //(1) 한번찍어봐 되나안되나 console에 main뜰거고 화면은 안뜰거야.
		
		WebUtil.forword(request, response, "/WEB-INF/views/main/index.jsp"); //(2) 원하는 그림이 안뜰거야. index.jsp경로가 틀린것임. (3) assets/css폴더에 main.css, mysite.css파일 옮겨 (4)index.jsp가서 링크바꾸고 (5)image폴더에 jpg파일넣기(6)링크넣기
		/*(7)회원가입이되는 db만들기 ppt 7page (8)db만들기 db의 nocache용어 알기 1)insert)  (9)dao만들기(insert)전에 db에 insert만들기(09) (10)vo만들기 ('10) Daotest! (11)user폴더 아래에 joinForm만들기 컨트롤러만들거야 joinForm.html joinForm.jsp파일에 다 옮기고('12) joinForm 링크바꿔주고 (12)UserController서블렛으로 만들고 주석한번봐! 
		 * 메인페이지에 회원가입을 누르면 넘어가기할거야 index.jsp로가 (13)
		 * <ul>
				<li><a href="">로그인</a></li>
				<li><a href="/mysite2/user?action=joinForm">회원가입</a></li>
		   </ul> 이렇게 해줬어!*/ 
		/*회원가입을 눌러주면 저장시켜줄거야 회원가입을 성공하면 joinOk화면을 보여줘야해
		 * (14)joinform켜주고 userController로 가!*/
		
		//WebUtil.forword(request, response, "/WEB-INF/views/main/index.jsp");가 바뀌어야해 index.jsp로가자! 로그인에 성공하면 이 화면 로그아웃이면 이 화면으로 가야해(29)
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.BookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestVo;


@WebServlet("/BookController")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/BookController--> doGet()");
	
		String action = request.getParameter("action");
		System.out.println(action);
		
		if("list".equals(action)) {
			System.out.println("list");
			
			//phonedao 객체 가져올거야
			BookDao bookDao = new BookDao();
			List<GuestVo> gList = bookDao.select(); //리스트 가져옴
			
			//리퀘스트 
			request.setAttribute("guestList", gList);
			
			//포워드 내부안에 링크 가져올거야
			WebUtil.forword(request, response, "/WEB-INF/views/guestbook/addList.jsp");
		}else if ("insert".equals(action)) {
			System.out.println("저장");
			
			String name = request.getParameter("name");
			String pw = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestVo guest = new GuestVo(name, pw, content);
			System.out.println(guest);
			
			BookDao dao = new BookDao();
			dao.insert(guest);
			
			WebUtil.redirect(request, response, "/mysite2/main");
			
		}else if ("delete".equals(action)) {
			System.out.println("삭제");
			
			int no =Integer.parseInt(request.getParameter("no"));
			String pw = request.getParameter("pw");
			
			System.out.println(no + ", " + pw);
			
			BookDao dao = new BookDao();

			dao.delete(no, pw);
			
			WebUtil.redirect(request, response, "/mysite2/main");
			
		}else if("dform".equals(action)) {
			
			int no =Integer.parseInt(request.getParameter("no"));
			request.setAttribute("no", no);
			
			WebUtil.forword(request, response, "/WEB-INF/views/guestbook/addList.jsp");
		
		}
		
		
	}//doGet

	
	
	
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}//doPost

}//servlet

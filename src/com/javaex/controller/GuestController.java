package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;

@WebServlet("/gctrl")
public class GuestController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("controller");
		
		//파라미터 action값
		String action = request.getParameter("action");
		System.out.println(action);
		
		//action은 문자열
		if("list".equals(action)) {
				System.out.println("리스트");
			
				GuestDao guestDao = new GuestDao();
				List<GuestVo> guestList = guestDao.ListAllGuest();
				
				//데이터 전달
				request.setAttribute("gList", guestList);
				
				//포워드
				RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/guestList.jsp"); 
				rd.forward(request, response);
				
		} else if("insert".equals(action)) {
				System.out.println("등록");
			
				String name = request.getParameter("name");
				String password = request.getParameter("password");
				String content = request.getParameter("content");
				
				GuestVo guestVo = new GuestVo(name, password, content);
				GuestDao guestDao = new GuestDao();
				
				/* insert 저장 */
				guestDao.guestInsert(guestVo);
				
				/* 리다이렉트 */
				response.sendRedirect("/guestbook2/gctrl?action=list");
				
		} else if("delete".equals(action)) {
				System.out.println("삭제");
			
				String password = request.getParameter("password");     //비밀번호 일치 확인
				int no = Integer.parseInt(request.getParameter("no"));  //삭제할 번호 지정
					
				GuestVo guestVo = new GuestVo(no,password);
				
				GuestDao guestDao = new GuestDao();
				
				/* delete */
				guestDao.guestDelete(guestVo);
				
				response.sendRedirect("/guestbook2/gctrl?action=list");
			
		} else if("deleteForm".equals(action)) {
				System.out.println("삭제폼");
				
				int no = Integer.parseInt(request.getParameter("no"));
	
				GuestDao guestDao = new GuestDao();
				GuestVo guestVo = guestDao.getGuest(no);
				
				//데이터 전달
				request.setAttribute("dList", guestVo);
			
				//포워드
				RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/deleteForm.jsp"); 
				rd.forward(request, response);
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

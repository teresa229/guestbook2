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
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestVo;

@WebServlet("/gctrl")
public class GuestController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("controller");
		
		//파라미터 action값
		String action = request.getParameter("action");
		System.out.println(action);
		
		/*
		//action은 문자열
		if("list".equals(action)) {
				System.out.println("리스트");
			
				//리스트 출력 처리
				GuestDao guestDao = new GuestDao();
				
				List<GuestVo> guestList = guestDao.ListAllGuest(); //select no, name, password, content, reg_date  from guestbook
				
				//데이터 전달
				request.setAttribute("gList", guestList);
				
				/* 포워드 *
				//RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/guestList.jsp"); 
				//rd.forward(request, response);
				
				WebUtil.forward(request, response,"./WEB-INF/guestList.jsp");
				
				
				TIP: 복잡한 포워드를 WebUtil로 정리한다.
				WebUtil wu = new WebUtil(); //alt + shift + o로 메모리에 올리기. 다른 패키지에 있어서 import해주어야 한다.
				wu.forward(); //가로 안에 문자열 "./WEB-INF/guestList.jsp"로 넣어준다.
				
				WebUtil.forward(request, response, path); //forward 정리 방식
				WebUtil.redirect(request, response, url);  //redirect 정리 방식
				
		*/
											
		 if("insert".equals(action)) {
				System.out.println("등록");
			
				String name = request.getParameter("name");
				String password = request.getParameter("password");
				String content = request.getParameter("content");
				
				GuestVo guestVo = new GuestVo(name, password, content);
				GuestDao guestDao = new GuestDao();
				
				/* insert 저장 */
				guestDao.guestInsert(guestVo);
				
				/* 리다이렉트 */
				//response.sendRedirect("/guestbook2/gctrl?action=list");
				
				WebUtil.redirect(request, response,"/guestbook2/gctrl?action=list");
				
		} else if("delete".equals(action)) {
				System.out.println("삭제");
			
				String password = request.getParameter("password");     //비밀번호 일치 확인
				int no = Integer.parseInt(request.getParameter("no"));  //삭제할 번호 지정
					
				GuestVo guestVo = new GuestVo(no,password);
				GuestDao guestDao = new GuestDao();
				
				/* delete */
				guestDao.guestDelete(guestVo);
				
				/* 리다이렉트 */
				//response.sendRedirect("/guestbook2/gctrl?action=list");
				
				WebUtil.redirect(request, response, "/guestbook2/gctrl?action=list");
			
		} else if("deleteForm".equals(action)) {
				System.out.println("삭제폼");
				
				int no = Integer.parseInt(request.getParameter("no"));
	
				GuestDao guestDao = new GuestDao();
				GuestVo guestVo = guestDao.getGuest(no);
				
				//데이터 전달
				request.setAttribute("dList", guestVo);
			
				/* 포워드 */
				//RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/deleteForm.jsp"); 
				//rd.forward(request, response);
				
				WebUtil.forward(request, response, "./WEB-INF/deleteForm.jsp");
				
		} else {
			
			//리스트 출력 처리
			GuestDao guestDao = new GuestDao();
			
			List<GuestVo> guestList = guestDao.ListAllGuest(); //select no, name, password, content, reg_date  from guestbook
			
			//데이터 전달
			request.setAttribute("gList", guestList);
			
			/* 포워드 */
			//RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/guestList.jsp"); 
			//rd.forward(request, response);
			
			WebUtil.forward(request, response,"./WEB-INF/guestList.jsp");
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

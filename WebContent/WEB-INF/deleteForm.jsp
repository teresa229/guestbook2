<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import = "com.javaex.vo.GuestVo" %>

<%-- 
<%
	//GuestVo guestVo = (GuestVo)request.getAttribute("dList");
	//no만 있으면 되기 때문에..
	
	int no = Integer.parseInt(request.getParameter("no"));
	
	System.out.println("===deleteForm===");
	
%>
--%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<form action="/guestbook2/gctrl" method="get">
			비밀번호 : <input type = "text" name="password">
			<button type="submit">확인</button>
			
			<input type="hidden" name="no" value="${param.no}">	
			<input type="hidden" name="action" value="delete">	
		</form>
		<br>
		<a href="/guestbook2/gctrl?action=list">메인으로 돌아가기</a>
</body>
</html>
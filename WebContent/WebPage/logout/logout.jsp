<%@ page contentType="text/html;charset=utf8" %>
<%   
	String logname=(String)session.getAttribute("userId");
	if(logname!=null){ 
    	session.invalidate();
	} 
	response.sendRedirect("/LibraryManagementSys/WebPage/login/login.jsp");
%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="java.util.List"%> 
<%@ page import="cn.maiba.*"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% User user=(User)session.getAttribute("user");
	int id=(int)session.getAttribute("id");
%>
<%--String s1=(String)session.getAttribute("s1"); --%>
<%--=request.getParameter("password") --%>


		
	<% 	String strAcount=request.getParameter("account");
		String strPwd=request.getParameter("password");
		String strName=request.getParameter("userName");
		String strAge=request.getParameter("age");
		String strEmail=request.getParameter("email");
		user.setAccount(strAcount);
		user.setPassword(strPwd);
		user.setAge(strAge);
		user.setUserName(strName);
		user.setEmail(strEmail);
		MyDataBase.update(user,id);
			%>
		<%response.sendRedirect("UserList.jsp"); %>
</body>
</html>
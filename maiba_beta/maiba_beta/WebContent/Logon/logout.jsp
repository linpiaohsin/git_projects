<%@page import="java.sql.Timestamp"%>
<%@page import="db.Dao"%>
<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
</head>
<body>
<% 
String strName=(String)session.getAttribute("username");
Dao dao=new Dao();
dao.Logout(strName, new Timestamp(System.currentTimeMillis()));
    session.invalidate() ;    
response.sendRedirect(request.getContentType()+"/UserLogon.jsp");
%>

</body>
</html>
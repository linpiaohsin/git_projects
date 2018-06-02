<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../include/head3.jsp" %>
<center>
<h3>您好：${username} [<a href="logout.jsp">注销</a>]</h3>
当前在线用户：
<table class="table table-striped">
<%
    List onlineUserList = (List) application.getAttribute("onlineUserList");
    for (int i = 0; i < onlineUserList.size(); i++) {
    String onlineUsername = (String) onlineUserList.get(i);
%>
    <tr align="center">
        <td><%=onlineUsername%></td>
    </tr>
<%
}
%></table></center>
 <%@ include file="../include/Footer.jsp" %> 
</body>
</html>
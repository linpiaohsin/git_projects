 	<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.List"%> 
<%@ page import="cn.maiba.*"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

</head>
<body>

<%@ include file="../include/Header.jsp" %>
<table class="table table-bordered table-hover " style="text-align: center;">
<tr  bgcolor="cornflowerblue" ><td colspan="10" >用户列表</td></tr>
<tr bgcolor="lightsteelblue">

	<td colspan="1">ID</td>
	<td colspan="4">账号</td> 
	<td colspan="3">密码</td>
	<td colspan="2">昵称</td>
</tr>

<c:forEach items="${userList}" var="user" > 
<tr>	<td colspan="1">${user.id }</td>
	<td colspan="4"><a href="<%=request.getContextPath()%>/Logon/UserDetail.jsp?userId=${user.id }"> ${user.account } </a></td>
	<td colspan="3">${user.password }</td>
	<td colspan="2">${user.userName }</td></tr>
</c:forEach>  


 </table>


<%@ include file="../include/Footer.jsp" %>
</body>
</html>

 	<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
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
<%List<User> list_select=new ArrayList();
 String name=request.getParameter("userName");  


String strSql="select * from t_user where account like '%"+name+"%'";
/* out.print(strSql); */
ResultSet resultSet=MyDataBase.executeQuery(strSql);
if(resultSet!=null){
	while(resultSet.next()){
		User tmp=new User();
		tmp.setId(resultSet.getInt(1));
		tmp.setAccount(resultSet.getString(2));
		tmp.setPassword(resultSet.getString(3));
		tmp.setUserName(resultSet.getString(4));
		tmp.setAge(resultSet.getString(5));
		tmp.setEmail(resultSet.getString(6));
		/* out.print(tmp.getAccount()); */
		list_select.add(tmp);
	}
}
%>
<table class="table table-bordered table-hover " style="text-align: center;">
<%if(list_select.size()!=0){ %>
<tr  bgcolor="cornflowerblue" ><td colspan="14" >用户列表</td></tr>
<tr bgcolor="lightsteelblue">

	<td colspan="1">ID</td>
	<td colspan="4">账号</td> 
	<td colspan="3">密码</td>
	<td colspan="2">昵称</td>
	<td colspan="2">年龄</td>
	<td colspan="2">邮件</td>
</tr>

<%for(int i=0;i<list_select.size();i++){
	User user_now=(User)list_select.get(i);
%>
<tr>	<td colspan="1"><%=user_now.getId() %></td>
	<td colspan="4"><a href="<%=request.getContextPath()%>/Logon/UserDetail.jsp?userId=<%=user_now.getId()%>">
	<%=user_now.getAccount() %></a></td>
	<td colspan="3"><%=user_now.getPassword() %></td>
	<td colspan="2"><%=user_now.getUserName() %></td>
	<td colspan="2"><%=user_now.getAge() %></td>
	<td colspan="2"><%=user_now.getEmail() %></td>
	</tr>


<%} %>
 </table><%}else{ 
	 out.print("<br><br><center>用户不存在！</center><br><br>");
 }%>
<%@ include file="../include/Footer.jsp" %>
</body>
</html>

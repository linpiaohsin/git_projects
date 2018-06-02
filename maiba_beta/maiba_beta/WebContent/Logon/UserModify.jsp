<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.List"%> 
<%@ page import="cn.maiba.*"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%Integer id=Integer.valueOf(request.getParameter("userId")); %>
<%User user_tmp = MyDataBase.load(User.TABLE_NAME, id);

 %>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
		

</head>
<body> 

<%@ include file="../include/Header.jsp" %>
<form  method="post" action="deal.jsp" id="form1">
<%-- String s1="ssss";
session.setAttribute("s1", s1); --%>
<% session.setAttribute("user", user_tmp);
session.setAttribute("id", id);%>
		<table class="table table-bordered table-hover " style="text-align: center;">
<tr  bgcolor="cornflowerblue" ><td colspan="3" >用户列表</td></tr>

<tr bgcolor="lightsteelblue">

	<td >账号:</td>
	<td colspan="2"><input type="text" class="form-control" name="account" value=<%=user_tmp.getAccount() %>></input></td>
</tr>
<tr bgcolor="lightsteelblue">

	<td >密码:</td>
	<td colspan="2"><input type="text" class="form-control" name="password" value=<%=user_tmp.getPassword() %>></input></td>
</tr>
<tr bgcolor="lightsteelblue">

	<td >昵称:</td>
	<td colspan="2"><input type="text" class="form-control" name="userName" value=<%=user_tmp.getUserName() %>></input></td>
</tr>
<tr bgcolor="lightsteelblue">

	<td >年龄:</td>
	<td colspan="2"><input type="text" class="form-control" name="age" value=<%=user_tmp.getAge()%>></input></td>
</tr>
<tr bgcolor="lightsteelblue">

	<td >电子邮件:</td>
	
	<td colspan="2"><input type="text" class="form-control" name="email" value=<%=user_tmp.getEmail()%>></input></td>
</tr>
<tr bgcolor="cornflowerblue" >
	<td colstyle="border-style:none" colspan="1="><input type="submit" value="修改" onclick="modify()" class="btn btn-default  active"></td>
		<td colstyle="border-style:none" colspan="2"><input type="button" onclick="javascript:window.location.href('UserList.jsp')" value="返回"></input></td>
</tr>
</table>
	</form>
<%@ include file="../include/Footer.jsp" %>
</body>
<script>
		
	function modify(){	
		
		
		alert("修改成功~");
		 
		//form1.submit();
	
		
	};
		</script>
</html>
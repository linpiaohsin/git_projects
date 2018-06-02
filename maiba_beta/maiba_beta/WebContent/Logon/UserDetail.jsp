<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.List"%> 
<%@ page import="cn.maiba.*"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%int id=Integer.valueOf(request.getParameter("userId")); %>

<%User user2 = MyDataBase.load(User.TABLE_NAME, id); %>

<script type="text/javascript">

function handle_modify(){	
 	window.location.href="<%=request.getContextPath()%>/Logon/UserModify.jsp?userId=<%=user2.getId()%>";
}

function handle_delete(){		
	 window.location.href="<%=request.getContextPath()%>/HandleUserDelete?userId=<%=user2.getId()%>";
}

function handle_list_user(){	
 window.location.href="<%=request.getContextPath()%>/Logon/UserList.jsp"; 
}

</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../include/Header.jsp" %>



<table class="table table-bordered table-hover " style="text-align: center;">
<tr  bgcolor="cornflowerblue" ><td colspan="3" >用户列表</td></tr>
<tr bgcolor="lightsteelblue">

	<td >ID:</td>
	<td colspan="2"><%=user2.getId() %></td>
</tr>
<tr bgcolor="lightsteelblue">

	<td >账号:</td>
	<td colspan="2" name="account" ><%=user2.getAccount() %></td>
</tr>
<tr bgcolor="lightsteelblue">

	<td >密码:</td>
	<td colspan="2" name="password"><%=user2.getPassword() %></td>
</tr>
<tr bgcolor="lightsteelblue">

	<td >昵称:</td>
	<td colspan="2"><%=user2.getUserName() %></td>
</tr>
<tr bgcolor="lightsteelblue">

	<td >年龄:</td>
	<td colspan="2"><%=user2.getAge() %></td>
</tr>
<tr bgcolor="lightsteelblue">

	<td >电子邮件:</td>
	<td colspan="2"><%=user2.getEmail() %></td>
</tr>
<tr bgcolor="cornflowerblue" >
	<td style="border-style:none"><input type="button" value="修改" onclick="handle_modify()" class="btn btn-default  active"></td>
		<td style="border-style:none"><input type="button" value="删除" onclick="handle_delete()" class="btn btn-default  active"></td>
	<td style="border-style:none"><input type="button" value="返回" onclick="handle_list_user()" class="btn btn-default  active"></td>
</tr>
</table>
<%@ include file="../include/Footer.jsp" %>
</body>
</html>

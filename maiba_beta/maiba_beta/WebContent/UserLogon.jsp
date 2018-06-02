<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
function isNull() {
	if((document.getElementById('name').value=='')||(document.getElementById('pwd').value=='')){
		alert("账号或者密码不能为空……");
		return false;
		
	}
	if(document.getElementById('pwd').value.length<6){
		alert("密码必须大于等于六位！");
		return false;
	}
	else {
		alert("注册中…");
		
		return true;
		}
	
}
function Select(key)
{
if(key==1)
{
form1.action="HandleUserLogon";
form1.submit();
}
else if(key==2)
{
form1.action="HandleRegistSimple";
form1.submit();
}
}

</script>
</head>
<body>

<%@ include file="include/Header.jsp" %>
		<center><table border="1px"  class="table table-bordered table-hover " style="text-align: center;">
<th colspan="2" bgcolor="cornflowerblue">用户登录</th>
<form action="HandleUserLogon" method="post" id="form1">
<tr>
<td bgcolor="lightsteelblue">用户名：</td>
<td><input type="text" name="name" id="name" class="form-control"/></td>
</tr>
<tr>
<td bgcolor="lightsteelblue">密码：</td>
<td><input type="password" name="pwd" id="pwd" class="form-control"/></td>
</tr>
<tr bgcolor="cornflowerblue"><td colspan="2" align="center"><input type="button" value="登录" onclick="Select(1)" class="btn "/>
<input type="button" value="注册" onclick="isNull(),Select(2)" class="btn "/></td></tr></form>
</table></center>
<%@ include file="../include/Footer.jsp" %>
</body>
</html>
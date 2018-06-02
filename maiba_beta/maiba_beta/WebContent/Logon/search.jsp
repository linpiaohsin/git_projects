 	<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.List"%> 
<%@ page import="cn.maiba.*"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%request.setCharacterEncoding("utf-8"); %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
function jump1() {
	form1.action="handle-search1.jsp";
	form1.submit();
}
function jump2() {
	form1.action="handle-search2.jsp";
	form1.submit();
}
</script>
</head>
<body>
<%@ include file="../include/Header.jsp" %><center>
<form  method="post" id="form1" ><br><br><br>
<input type="text" name="userName" class="form-control"><br><input type="button" onclick="jump1()" class="btn btn-info" value="模糊查询用户">
<br> <br>
<input type="text" name="tiezi" class="form-control"><br><input type="button" onclick="jump2()" class="btn btn-info" value="模糊查询帖子"> 
</form></center><br> <br><br> <br><br> <br><br> 
<%@ include file="../include/Footer.jsp" %>
</body>
</html>

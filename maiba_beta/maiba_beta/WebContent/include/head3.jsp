<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	    <%@ page import="java.util.List"%> 
    <%@ page import="cn.maiba.*"%> 
		<meta charset="UTF-8"> 
</head>
<body>
<nav class="navbar ">
  <div class="">
    <!-- Brand and toggle get grouped for better mobile display -->
    
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    
      <form class="navbar-form navbar-right">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        
        <select>
        	<option>请选择</option>
        	<option>1234</option>
        	<option>1234</option>
        	<option>1234</option>
        	<option>1234</option>
        </select>
        
        <button type="submit" class="btn btn-default">搜索</button>
      </form>
  
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
  <div class=""></div>
</nav>
<% User user=(User)session.getAttribute("user");
    		%>
	<div class="navbar-default">
	<% if(user==null) {%>	<span style="color: cornflowerblue;">游客,欢迎你！</span>
	<% }else{  out.println(user.getAccount()); %><span style="color: cornflowerblue;">,欢迎你！</span>
	<%session.setAttribute("user_info", user.getAccount());
	} %>
		<span><a href="<%=request.getContextPath() %>/Logon/article-new.jsp" class="pull-right"><u style="color: purple;">发表文章</u>&nbsp;&nbsp;&nbsp;&nbsp;</a></span>
		<span><a href="<%=request.getContextPath() %>/Logon/search.jsp" class="pull-right"><u>模糊查询</u>&nbsp;&nbsp;&nbsp;&nbsp;</a></span>
		<span ><a href="<%=request.getContextPath() %>/Logon/article-list.jsp" class="pull-right"><u>文章列表</u>&nbsp;&nbsp;&nbsp;&nbsp;</a></span>
		<span ><a href="<%=request.getContextPath() %>/Logon/my-article-list.jsp" class="pull-right"><u>我的文章</u>&nbsp;&nbsp;&nbsp;&nbsp;</a></span>
		
	</div>
</body>
</html>
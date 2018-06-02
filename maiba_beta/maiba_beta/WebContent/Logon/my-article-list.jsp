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
<p><a href="#">用户管理</a>&nbsp;&nbsp;&nbsp;<a href="#">我发表的帖子</a>&nbsp;&nbsp;&nbsp;<a href="#">我评论的帖子</a></p>
<table class="table table-bordered table-hover " style="text-align: center;">
<tr  bgcolor="cornflowerblue" ><td colspan="17" >麦吧文章列表</td></tr>
<tr bgcolor="lightsteelblue">

	<td colspan="2">点击数</td>
	<td colspan="2">回复数</td> 
	<td colspan="8">标题</td>
	<td colspan="2">作者</td>
	<td colspan="3">最后回复</td>
</tr>
<%--  <% List userList = MyDataBase.list(User.TABLE_NAME);
out.print(userList);%>
<c:forEach items="${userList}" var="user" > 
<tr>	<td colspan="1">${user.id }</td>
	<td colspan="4"><a href="<%=request.getContextPath()%>/Logon/UserDetail.jsp?userId=${user.id }"> ${user.account } </a></td>
	<td colspan="3">${user.password }</td>
	<td colspan="2">${user.userName }</td></tr>
</c:forEach>  --%>

 <%
Integer id=user.getId();
 List artticleList = MyDataBase.listmyArticle(id);
 String author=MyDataBase.getAuthor(id);
Article article;
for(int i=0; i<artticleList.size(); i++){
	article = (Article)artticleList.get(i);
	 
%>
<tr>	<td colspan="2"><%=article.getHitNum() %></td>
	<td colspan="2"><%-- <a href="<%=request.getContextPath()%>/Logon/UserDetail.jsp?userId=<%=user1.getId()%>"> --%> <%=article.getRemarkNum() %> </td>
	<td colspan="8"><a href="<%=request.getContextPath()%>/Logon/article-detail.jsp?id=<%=article.getId()%>" >
	<%=article.getTitle() %></a></td>
		<td colspan="2"><%=author %></td>
	<td colspan="3"><%=article.getLastRemarkTime() %></td></tr>
	<%}%>
</table>



<%@ include file="../include/Footer.jsp" %>
</body>
</html>

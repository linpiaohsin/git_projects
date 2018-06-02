 	<%@page import="db.Dao"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
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
<%request.setCharacterEncoding("utf-8"); %>
</head>
<body>
<%@ include file="../include/Header.jsp" %>
<%
String tiezi=request.getParameter("tiezi"); 

%>
<%List<Article> list_select=new ArrayList();

String strSql="select * from t_article where title  like '%"+tiezi+"%'"+"or content  like '%"+tiezi+"%'";
/* out.print(strSql); */
ResultSet resultSet=MyDataBase.executeQuery(strSql);
if(resultSet!=null){
	while(resultSet.next()){
		Article tmp=new Article();
		tmp.setId(resultSet.getInt(1));
		tmp.setUserId(resultSet.getInt(2));
		tmp.setTitle(resultSet.getString(3));
		tmp.setContent(resultSet.getString(4));
		tmp.setCreateTime(resultSet.getTimestamp(5));
		tmp.setHitNum(resultSet.getInt(6));
		tmp.setRemarkNum(resultSet.getInt(7));
		/* out.print(tmp.getAccount()); */
		list_select.add(tmp);
	}
}
%>
<%if(list_select.size()!=0){ %>
<table class="table table-bordered table-hover " style="text-align: center;">
<tr  bgcolor="cornflowerblue" ><td colspan="12" >用户列表</td></tr>
<tr bgcolor="lightsteelblue">

	<td colspan="1">文章ID</td>
	<td colspan="2">发布者</td> 
	<td colspan="3">标题</td>
	<td colspan="4">文章内容</td>
	<td colspan="1">点击量</td>
	<td colspan="1">评论量</td>
</tr>

<%for(int i=0;i<list_select.size();i++){
	Article article=(Article)list_select.get(i);
%>
<tr>	<td colspan="1"><%=article.getId() %></td>
	<td colspan="2">
	 <%=MyDataBase.getAuthor(article.getUserId()) %></td>
	<td colspan="3"><a href="<%=request.getContextPath()%>/Logon/article-detail.jsp?id=<%=article.getId()%>" >
	<%=article.getTitle() %></a></td>
	<td colspan="4"><%=article.getContent() %></td>
	<td colspan="1"><%=article.getHitNum() %></td>
	<td colspan="1"><%=article.getRemarkNum() %></td>
	</tr>


<%} %>
 </table><%}else{
	 out.print("<br><br><center>没有类似文章！！</center><br><br>");
 } %>
<%@ include file="../include/Footer.jsp" %>
</body>
</html>

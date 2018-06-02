<%@page import="db.Dao"%>
<%@page import="cn.maiba.Remark"%>
<%@page import="cn.maiba.MyDataBase"%>
<%@page import="cn.maiba.Article"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%request.setCharacterEncoding("utf-8"); %>
<%@ include file="../include/Header.jsp" %>
<%
String content=request.getParameter("content");
Article article=(Article) session.getAttribute("article_remark");
/* out.print(content);
out.print(article); */
if(content==""){
	out.print("评论不能为空！！！"+content+" "+article);
}else{
 	 Remark remark=new Remark();
	 remark.setArticleId(article.getId());
	 remark.setRemarkTime(article.getLastRemarkTime());
	 remark.setUserId(article.getUserId()); 
	 remark.setRemark(content);
 	/*  System.out.println(remark.getRemark()+" "+remark.getArticleId()+" "+remark.getUserId()+" ");  */
	
	 Boolean result=MyDataBase.writeRemark(remark);  
	
	 if(result){out.print("评论成功！~"); }
	 else{out.print("评论失败！");}  
	 




}%>
<br> 
<%@ include file="../include/Footer.jsp" %> 
</body>
</html>
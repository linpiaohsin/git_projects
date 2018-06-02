<%@page import="db.Dao"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript">
    function saveRemark(){

    	
    	form1.submit();
    }
    
    </script>
</head>
<body>
<%request.setCharacterEncoding("utf-8"); %>
<%@ include file="../include/head3.jsp" %>
<%int article_id=Integer.valueOf(request.getParameter("id"));
%>
<% Article article=MyDataBase.showAnArtcle(article_id);
article.setId(article_id);%>
<%String author=MyDataBase.getAuthor(article.getUserId());
String reader=user.getAccount();

%>

<form method="post" action="handleSave.jsp" id="form1">
<div align="center">
<p>标题：<%=article.getTitle() %></p>
<p>------------------------------------------------------------------------------------</p>
<p>内容：<%=article.getContent()%></p><br/><br/><br/>
<p>作者：<%=author %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <%=article.getCreateTime() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href='#'>回复此留言。</a></p><br/><br/>
 <%if(author.equals(reader)){ %><input type="button" value="修改" class="btn btn-primary">
<input type="button" value="删除" class="btn btn-danger"><%} %>  
<p>------------------------------------------------------------------------------------</p>
</div>
<%
Dao dao=new Dao();
dao.updateHit(article_id);
%>
评论：
<%int num=0;
String string="select * from t_remark where article_id="+article_id;
ResultSet resultSet2=dao.executeQuery(string);
if(resultSet2!=null){
while(resultSet2.next()){
	out.print(resultSet2.getString(3)+"<br/>"+"from:"+reader+"<br/>"+"at:"+resultSet2.getString(4)+"<br/>"+"<br/>");
	num++;
}
}
dao.updateRemark(article_id,num);
%>

<%session.setAttribute("article_remark", article); %>
<div align="center">
评论：<textarea rows="10" cols="50" id="content" name="content"></textarea><br><br/><br/><br/>

<input type="button" value="发表评论" class="btn btn-success"  onclick="saveRemark()"/></form>
</div>
 <%@ include file="../include/Footer.jsp" %> 
</body>
</html>
 	<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
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
<%@ include file="../include/head3.jsp" %>
<table class="table table-bordered table-hover " style="text-align: center;">
<tr  bgcolor="cornflowerblue" ><td colspan="17" >麦霸文章列表</td></tr>
<tr bgcolor="lightsteelblue">

	<td colspan="2">点击数</td>
	<td colspan="2">回复数</td> 
	<td colspan="8">标题</td>
	<td colspan="2">作者</td>
	<td colspan="3">最后回复</td>
</tr>

<%-- <%request.setCharacterEncoding("utf-8"); %>
 <%

 List artticleList = MyDataBase.listArticle();
Article article;
for(int i=0; i<artticleList.size(); i++){
	article = (Article)artticleList.get(i);
	
	 String author=MyDataBase.getAuthor(article.getUserId());
	 
%>
<tr>	<td colspan="2"><%=article.getHitNum() %></td>
	<td colspan="2"><a href="<%=request.getContextPath()%>/Logon/UserDetail.jsp?userId=<%=user1.getId()%>"> <%=article.getRemarkNum() %> </td>
	<td colspan="8"><a href="<%=request.getContextPath()%>/Logon/article-detail.jsp?id=<%=article.getId()%>" >
	<%=article.getTitle() %></a></td>
		<td colspan="2"><%=author %></td>
	<td colspan="3"><%=article.getLastRemarkTime() %></td></tr>
	
	<%}%>
</table> --%>

<%
int intPageSize = 10;      //一页显示的记录数
int intRowCount = 0;     //记录的总数
int intPageCount = 0;    //总页数
int intPage = 0;         //当前页面要显示的页码
String strPage=request.getParameter("page");//取得待显示的页码;
if(strPage==null){ //判断strPage是否等于null,如果是，显示第一页数据
	intPage=1;
}else{
	intPage=java.lang.Integer.parseInt(strPage); //将字符串转换为整型
}
if(intPage<1)//异常处理
{
	intPage=1;
}

 try
 {
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/maiba","root","1314");
	Statement stmt  = conn.createStatement();
	ResultSet rs=stmt.executeQuery("select * from t_article");


	rs.last();//获取记录总数
	intRowCount=rs.getRow();
	intPageCount=(intRowCount+intPageSize-1)/intPageSize; //计算机总页数
	if(intPage>intPageCount) intPage=intPageCount; //调整待显示的页码
	if(intPageCount>0)
	{
		rs.absolute((intPage-1)*intPageSize+1); //将记录指针定位到待显示页的第一条记录上
	}
	
%>
<div align="left">
共<%=intRowCount%>个记录，分<%=intPageCount%>页显示，当前页是：第<%=intPage%>页
<%
if(intPage>1){
out.print("&nbsp;&nbsp;<a href='article-list.jsp?page="+(intPage-1)+"'>前一页</a>");
}
for(int j=1;j<=intPageCount;j++)
{
	if(j==intPage)
		out.print("&nbsp;&nbsp;"+j);
	else
		out.print("&nbsp;&nbsp;<a href='article-list.jsp?page="+j+"'>"+j+"</a>");
}
if(intPage<intPageCount){
	out.print("&nbsp;&nbsp;<a href='article-list.jsp?page="+(intPage+1)+"'>下一页</a>");
}
%>
</div>
<br>


<%
	//下面用于显示一行行数据
	int i=0;
	while(i<intPageSize && !rs.isAfterLast())
	{
%>
  <tr bgcolor="white" align="center">
    <td colspan="2"><%=rs.getInt(6)%></td>
    <td colspan="2"><%=rs.getInt(7)%></td>
    <td colspan="8"><a href="<%=request.getContextPath()%>/Logon/article-detail.jsp?id=<%=rs.getInt(1)%>" >
     <%=rs.getString(3)%></a></td>
	<td colspan="2"><%=MyDataBase.getAuthor(rs.getInt(2))%></td>
	<td colspan="3"><%=rs.getString(5)%></td>
  </tr>
 <%	
		rs.next();
		i++;
	}
	rs.close();//关闭连接、释放资源
	stmt.close();
	conn.close();
 }
 catch(Exception e)
 {
 e.printStackTrace();
 }
%>
</table>

<br>
<div align="right">
共<%=intRowCount%>个记录，分<%=intPageCount%>页显示，当前页是：第<%=intPage%>页
<%
if(intPage>1){
out.print("&nbsp;&nbsp;<a href='logDeatail.jsp?page="+(intPage-1)+"'>前一页</a>");
}
for(int j=1;j<=intPageCount;j++)
{
	if(j==intPage)
		out.print("&nbsp;&nbsp;"+j);
	else
		out.print("&nbsp;&nbsp;<a href='logDeatail.jsp?page="+j+"'>"+j+"</a>");
}
if(intPage<intPageCount){
	out.print("&nbsp;&nbsp;<a href='logDeatail.jsp?page="+(intPage+1)+"'>下一页</a>");
}
%>





<%@ include file="../include/Footer.jsp" %>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    
</head>

<body>
<%request.setCharacterEncoding("utf-8"); %>
<%@ include file="../include/Header.jsp" %><center><br/><br/><br/><br/>
<%

String title=request.getParameter("title");
String content=request.getParameter("content");
//内容字数不小于5
if(title==""||content==""||content.length()<=5){
	out.println("标题不能为空，且内容字数不得小于5！"+"+"+user.getId()+" "+user.getAccount());
}else{
	Article article=new Article();
	article.setUserId(user.getId());
	article.setTitle(title);
	article.setContent(content);
	boolean result=MyDataBase.writeArticle(article);
	if(result){
	out.println("恭喜你，文章发表成功！");
	}else{
		out.println("文章发表失败！");
	}
}


%><br/><br/><br/><br/></center>
 <%@ include file="../include/Footer.jsp" %> 
</body>
</html>
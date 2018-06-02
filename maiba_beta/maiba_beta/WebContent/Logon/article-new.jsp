<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    
</head>
<body>
<%request.setCharacterEncoding("utf-8"); %>
<%@ include file="../include/head3.jsp" %>
<table class="table table-bordered table-hover" >
    <form method="post" action="handle- article-new.jsp" name="artile_form">
    <tr style="background: cornflowerblue"><td colspan="9" align="center">发表帖子</td></tr>
    <tr ><td colspan="1" style="background: #85c7ed">标题</td>
    <td><input type="text" name="title" class="form-control" ></td></tr>
    <tr ><td colspan="1" style="background: #85c7ed">文章内容</td>
        <td><textarea name="content" class="form-control"  style="max-height: 500px;" rows="10"></textarea></td></tr>
       <!--  <tr><td style="background: #85c7ed">上传附件</td><td> <input type="file" ></td></tr> -->
    <tr style="background: cornflowerblue"><td colspan="9" align="center"><input type="button" name="summit" onclick="artile_form.submit()" value="提交" class="btn btn-default">
        <input type="button" name="cancel" value="取消" class="btn btn-default" "> 
        </td></tr>
    </form>
</table>
 <%@ include file="../include/Footer.jsp" %> 
</body>
</html>
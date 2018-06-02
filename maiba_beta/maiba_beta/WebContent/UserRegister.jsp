<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css" />
    <title>a esay pay</title>

    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script>


        function isNull(){

            var age=document.getElementById('age').value;

//				alert(isNaN(age));
            if((document.getElementById('text1').value=='')||(document.getElementById('text2').value=='')){
                alert("账号或者密码不能为空……");
                return false;

            }
            if(document.getElementById('text2').value.length<6){
                alert("密码必须大于等于六位！");
                return false;


            }
            if(document.getElementById('name').value==''){
                alert("昵称不能为空！");
                return false;

            }
            if(document.getElementById('age').value==''){
                alert("年龄不能为空！");
                return false;

            }
            if(isNaN(age)==true||((age<=0)||(age>=150))){
                alert("年龄只能为数字，0-150之间！")
                return false;

            }
            if(document.getElementById('email').value==''){
                alert("邮箱不能为空！");
                return false;

            }
            var emailStr=document.getElementById('email').value;

            var emailPat=/^(.+)@(.+)$/;
            var matchArray=emailStr.match(emailPat);
            if (matchArray==null) {
                alert("电子邮件地址有误，必须包括 ( @ 和 . )")
                return false;
            }


            else {
                alert("注册中…");
                form1.submit();
                return true;
            }
        };



    </script>
</head>
<body>
<%@ include file="../include/Header.jsp" %>
<div>
    <table  border="2px" bordercolor="dodgerblue" align="center"class="table table-bordered table-hover "
           style="text-align: center;">
        <tr style="background: dodgerblue;"><th colspan="2" style="text-align: center;">用户注册</th></tr>
        <form name="form1" id="form1" method="post" action="HandleUserRegister">
            <tr >
                <td style="background-color: grey;" align="center">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
                <td ><input type="text" id="text1" name="count" class="form-control"/><span style="color: red;" /></td>
            </tr>
            <tr>
                <td style="background-color: grey;" align="center">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
                <td><input type="password" id="text2" name="pwd" class="form-control"/><span style="color: red;" /></td>
            </tr>

            <tr>
                <td style="background-color: grey;" align="center">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</td>
                <td><input type="text" id="name" name="name" class="form-control"/></td>
            </tr>
            <tr>
                <td style="background-color: grey;" align="center">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄：</td>
                <td><input type="text" id="age" name="age" class="form-control"/></td>
            </tr>
            <tr>
                <td style="background-color: grey;" align="center">电子邮件：</td>
                <td><input type="email"  id="email" name="email" class="form-control"/></td>
            </tr>

            <tr style="background: dodgerblue;">
                <td colspan="2" ><p style="text-align: center;"><input type="button" value="注册" id="btn1" onclick="isNull()" class="btn btn-info"></input></p></td>
            </tr>
        </form>
    </table>
</div>

<%@ include file="include/Footer.jsp" %>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>

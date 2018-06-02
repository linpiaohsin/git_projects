<%@page import="cn.maiba.OnlineUserBindingListener"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>


<%
/*     request.setCharacterEncoding("UTF-8");
    // 取得登录的用户名
    String username = request.getParameter("username");
    // 把用户名保存进session
    session.setAttribute("username", username);
    // 把用户名放入在线列表
    List onlineUserList = (List) application.getAttribute("onlineUserList");
    // 第一次使用前，需要初始化
    if (onlineUserList == null) {
        onlineUserList = new ArrayList();
        application.setAttribute("onlineUserList", onlineUserList);
    }
 	
    
    OnlineUserBindingListener onlineUserBindingListener=new  OnlineUserBindingListener(username);
    session.setAttribute("onlineUserBindingListener", onlineUserBindingListener);
    // 成功 */
    response.sendRedirect("online_num.jsp");
%>

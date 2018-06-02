package cn.maiba;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
 
public class OnlineUserBindingListener implements HttpSessionBindingListener {
    String username;
     
    public OnlineUserBindingListener(String username){
        this.username=username;
    }
    public void valueBound(HttpSessionBindingEvent event) {
        HttpSession session = event.getSession();
        ServletContext application = session.getServletContext();
        // ���û������������б�
        List onlineUserList = (List) application.getAttribute("onlineUserList");
        
        // ��һ��ʹ��ǰ����Ҫ��ʼ��
        if (onlineUserList == null) {
            onlineUserList = new ArrayList();
            application.setAttribute("onlineUserList", onlineUserList);
        }
        onlineUserList.add(this.username);
    }
 
    public void valueUnbound(HttpSessionBindingEvent event) {
        HttpSession session = event.getSession();
        ServletContext application = session.getServletContext();
 
        // �������б���ɾ���û���
        List onlineUserList = (List) application.getAttribute("onlineUserList");
        onlineUserList.remove(this.username);
        System.out.println(this.username + "�˳���");
 
    }
 
}

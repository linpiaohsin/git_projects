package cn.maiba;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class OnlineCounter
 *
 */
@WebListener
public class OnlineCounter implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public OnlineCounter() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    	HttpSession session = se.getSession();
		ServletContext context = session.getServletContext();
		Integer onlineCounter = (Integer)context.getAttribute("onlineCounter");
		if(null == onlineCounter) {
			onlineCounter = new Integer(1);
		}else {
			onlineCounter = new Integer(onlineCounter + 1);
		}
		//把counter属性存放在web的应用范围之内
		context.setAttribute("onlineCounter", onlineCounter);
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    	HttpSession session = se.getSession();
		ServletContext context = session.getServletContext();
		Integer onlineCounter = (Integer)context.getAttribute("onlineCounter");
		if(null == onlineCounter) {
			return;
		}else {
			onlineCounter = new Integer(onlineCounter - 1);
		}
		context.setAttribute("onlineCounter", onlineCounter);
    }
	
}

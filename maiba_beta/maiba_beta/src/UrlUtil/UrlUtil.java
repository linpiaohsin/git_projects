package UrlUtil;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.Request;

public class UrlUtil {
	public static String getURL(HttpServletRequest req) {
		String url="";
		url = req.getRequestURL()+"?";
		url+=param(req);
		return url;
	}
	public static String param(HttpServletRequest req) {
		String url="";
		Enumeration param= req.getParameterNames();
		while(param.hasMoreElements()) {
			String name=param.nextElement().toString();
			url=url+name+"="+req.getParameter(name)+"&";
		}
		if(url.endsWith("&")) {
			url=url.substring(0, url.lastIndexOf("&"));
		}
		return url;
	}
	
	
	
	
	
}

package UrlUtil;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LogonFilter
 */
@WebFilter(filterName="LogonFilter", urlPatterns="/Logon/*")
public class LogonFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LogonFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		request.setCharacterEncoding("gb2312");
        response.setCharacterEncoding("gb2312");
		HttpServletRequest req = (HttpServletRequest) request;  
        HttpServletResponse res = (HttpServletResponse) response; 
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user_info");
       
        if(user == null) {//用户没登录
        	session.setAttribute("forwordURL", UrlUtil.getURL(req));//保存URL
        	res.sendRedirect(req.getContextPath() + "/result/user_no_logon.jsp");
        }
		// pass the request along the filter chain
        else chain.doFilter(request, response);//到达用户请求页面
		// pass the request along the filter chain
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

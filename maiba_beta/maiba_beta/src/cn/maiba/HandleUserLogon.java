package cn.maiba;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Dao;

/**
 * Servlet implementation class HandleUserLogon
 */
@WebServlet("/HandleUserLogon")
public class HandleUserLogon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleUserLogon() {
    	
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		RequestDispatcher requestDispatcher=null;
		String strName=request.getParameter("name");
		String strPwd=request.getParameter("pwd");
		User user1=new User();
		User user_real =new User();
		user1.setAccount(strName);
		user1.setPassword(strPwd);
		ResultSet rs = null;
		String sql = "select * from t_user where account = '" + strName + "'";
		rs = MyDataBase.executeQuery(sql);
		try {
			if (rs.next()) {
				user_real.setId(Integer.valueOf(rs.getString(1)));
				user_real.setAccount(rs.getString(2)) ;
				user_real.setPassword(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		
	
	
			//密码正确登录成功
			if(user_real.getPassword().equals(strPwd))
			{
				request.getSession().setAttribute("user", user_real);
				String forwordURL=(String)request.getSession().getAttribute("forwordURL");

			    request.setCharacterEncoding("UTF-8");
			    // 取得登录的用户名
			    String username = strName;
			    // 把用户名保存进session
			    request.getSession().setAttribute("username", username);
			    // 把用户名放入在线列表
			    List onlineUserList = (List) this.getServletContext().getAttribute("onlineUserList");
			    // 第一次使用前，需要初始化
			    if (onlineUserList == null) {
			        onlineUserList = new ArrayList();
			        this.getServletContext().setAttribute("onlineUserList", onlineUserList);
			    }
			 	
			    
			    OnlineUserBindingListener onlineUserBindingListener=new  OnlineUserBindingListener(username);
			    request.getSession().setAttribute("onlineUserBindingListener", onlineUserBindingListener);
			    // 成功
				
			 
		
				    
				    
				if(forwordURL!=null){
					request.getSession().setAttribute("user_info", user_real.getAccount());
				
					response.sendRedirect(forwordURL);
					request.getSession().removeAttribute("forwordURL");
					
					
				
					
				}
				else{
					
				   
				  
						
				    Dao dao=new Dao();
				    dao.Login(strName, new Timestamp(System.currentTimeMillis()));
					List userList = MyDataBase.list(User.TABLE_NAME);
					request.setAttribute("userList", userList);
					requestDispatcher=request.getRequestDispatcher("Logon/UserList.jsp");
					requestDispatcher.forward(request, response);
					
				}
			
			}else{
				
				response.sendRedirect("result/DealFail.jsp");
			
			}
		
			/**/
	}

}

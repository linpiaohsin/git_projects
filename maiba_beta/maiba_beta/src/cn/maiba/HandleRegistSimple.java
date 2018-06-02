package cn.maiba;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HandleRegistSimple
 */
@WebServlet("/HandleRegistSimple")
public class HandleRegistSimple extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleRegistSimple() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		request.setCharacterEncoding("gbk");

		
		String strName=request.getParameter("name");
		String strPwd=request.getParameter("pwd");
		User user=new User(strName,strPwd);
	
		List<String> userList = null;
		try {
			userList = MyDataBase.select(User.TABLE_NAME,"account",user.getAccount(),DBOperator.OP_EQUAL);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}

	

		 if(strName==""){
			 response.sendRedirect("Logon/UserLogon.jsp");
		 }
		 else if(userList==null||userList.isEmpty()){
			 MyDataBase.save(User.TABLE_NAME, user);
			 
			
			response.sendRedirect("result/Succeess-UserRegister.jsp");
		}
		else response.sendRedirect("result/Failure-UserRegister.jsp");
		

	}

}

package cn.maiba;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HandleUserRegister
 */
@WebServlet("/HandleUserRegister")
public class HandleUserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleUserRegister() {
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

		request.setCharacterEncoding("GBK");
		String strName=request.getParameter("count");
		String strPwd=request.getParameter("pwd");
		String strNam=request.getParameter("name");
		String Age=request.getParameter("age");
		String strEmail=request.getParameter("email");
		User user=new User();
		user.setAccount(strName);
		user.setPassword(strPwd);
		user.setUserName(strNam);
		user.setAge(Age);
		user.setEmail(strEmail);

		List<String> userList = null;
		try {
			userList = MyDataBase.select(User.TABLE_NAME,"account",strName,DBOperator.OP_EQUAL);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		if(userList==null||userList.isEmpty()){
			MyDataBase.save(User.TABLE_NAME, user);
			response.sendRedirect("result/Succeess-UserRegister.jsp");
		}
		else response.sendRedirect("result/Failure-UserRegister.jsp");
		
	}

}

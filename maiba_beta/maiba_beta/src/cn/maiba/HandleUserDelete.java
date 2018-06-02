package cn.maiba;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HandleUserDelete
 */
@WebServlet("/HandleUserDelete")
public class HandleUserDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleUserDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Integer id=Integer.valueOf(request.getParameter("userId"));
		User user3 = (User)MyDataBase.load(User.TABLE_NAME, id); 
		MyDataBase.delete(User.TABLE_NAME, id);
		response.sendRedirect("result/result_UserDelete.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		Integer id=Integer.valueOf(request.getParameter("userId"));
		User user3 = (User)MyDataBase.load(User.TABLE_NAME, id); 
		MyDataBase.delete(User.TABLE_NAME, id);
		response.sendRedirect("result/result_UserDelete.jsp");
	}

}

package hk.common;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hk.dao.CommonDao;
import hk.models.Users;

/**
 * Servlet implementation class UsersLogin
 */
@WebServlet("/UsersLogin")
public class UsersLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CommonDao commondao;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		commondao=new CommonDao();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersLogin() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String id=request.getParameter("txtid");
		String pass=request.getParameter("txtpass");
		Users users =commondao.login(id, pass);
		if(users!=null)
		{
			HttpSession hs=request.getSession();
			hs.setAttribute("userdetails", users);
			String usertype=users.getUsertype();
			if(usertype.equals("user"))
			response.sendRedirect("/HouseKeeping/user/user_home.jsp");
			if(usertype.equals("company"))
				response.sendRedirect("/HouseKeeping/company/company_home.jsp");	
		}
		else
		{
			RequestDispatcher rd=request.getRequestDispatcher("/common/userslogin.jsp"); //ask this
			request.setAttribute("msg", CommonMessage.LOGIN_ERROR);
		    rd.forward(request, response);
		}
		
	}

}

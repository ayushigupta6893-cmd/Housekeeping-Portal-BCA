package hk.admin;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hk.common.CommonMessage;
import hk.dao.AdminDao;
import hk.models.Admin;

/**
 * Servlet implementation class Adminlogin
 */
@WebServlet("/Adminlogin")
public class Adminlogin extends HttpServlet
{
	private static final long serialVersionUID = 1L;
      AdminDao admindao; //global variable
      @Override
    public void init() throws ServletException 
      {
    	admindao=new AdminDao();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Adminlogin() {
        super();
        // TODO Auto-generated constructor stu
        }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("txtid");
		String pass=request.getParameter("txtpass");
		
		System.out.println(id+pass); 
		
		/* Admin admin =new Admin(id,pass); */ //bean class object
		
		Admin admin=admindao.login(id,pass);
		if(admin!=null)
		{
			HttpSession hs=request.getSession();//create a session
			hs.setAttribute("admininfo", admin); //binding value
			response.sendRedirect("/HouseKeeping/admin/admin_home.jsp");
		}
		else
		{
			RequestDispatcher rd=request.getRequestDispatcher("/admin/adminlogin.jsp"); //ask this
			request.setAttribute("msg", CommonMessage.LOGIN_ERROR);
		    rd.forward(request, response);
		
		}
	}

}

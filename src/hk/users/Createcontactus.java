package hk.users;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hk.common.CommonMessage;
import hk.dao.UserDao;
import hk.models.ContactUs;

/**
 * Servlet implementation class Createcontactus
 */
@WebServlet("/Createcontactus")
public class Createcontactus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserDao userdao;
	@Override
	public void init() throws ServletException
	{
		userdao= new UserDao();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Createcontactus() {
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
		String name=request.getParameter("txtname");
		String email=request.getParameter("txtemail");
		String phone=request.getParameter("txtphone");
		String yourtext=request.getParameter("txttext");
		java.util.Date dt=new java.util.Date();
		java.sql.Date sqldate=new java.sql.Date(dt.getTime());
		
		ContactUs c=new ContactUs(name,email,phone,yourtext,sqldate);
		int status=userdao.createContactus(c);
		if(status>0)
		{

			request.setAttribute("cmsg", CommonMessage. MESSAGE_CONTACTUS);
			RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
			
			System.out.println(name+email+phone+yourtext +sqldate);
		
			
			
		}
		
	}
}

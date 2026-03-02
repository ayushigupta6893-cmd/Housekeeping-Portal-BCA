package hk.users;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hk.common.CommonMessage;
import hk.dao.UserDao;
import hk.models.EmployeeRequest;
import hk.models.Users;

/**
 * Servlet implementation class Employeerequest
 */
@WebServlet("/Employeerequest")
public class Employeerequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao dao =null;
       @Override
    public void init() throws ServletException
       {
    	   dao=new UserDao();
    	   
       
       }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Employeerequest() {
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
		
		HttpSession hs=request.getSession(false);// return the reference of existing object
		
		Users user=	(Users)hs.getAttribute("userdetails");
		String userid=user.getId();
		
		String ptype=request.getParameter("cmbtype");
		int type=Integer.parseInt(ptype);
		String emptype=request.getParameter("emptype");
		String noofemployee=request.getParameter("txtnumber");
		int numemp=Integer.parseInt(noofemployee);
		String duration=request.getParameter("txtduration");
		java.util.Date dt=new java.util.Date();
		java.sql.Date requestdate=new java.sql.Date(dt.getTime());
		EmployeeRequest rp =new EmployeeRequest(type,numemp,emptype,duration,requestdate);
		
		rp.setUserid(userid);
			
		
		int status=dao.employeerequest(rp);
		if(status>0)
		{

				request.setAttribute("successmessage", CommonMessage.REQUEST_EMPLOYEE);
				RequestDispatcher rd=request.getRequestDispatcher("/user/user_home.jsp");
				
				rd.forward(request, response);
		}
		
		//System.out.println(type+numemp+emptype+duration);
		
		
		}

		}

		
		
	



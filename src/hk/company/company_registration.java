package hk.company;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hk.dao.CompanyDao;
import hk.models.Users;

/**
 * Servlet implementation class company_registration
 */
@WebServlet("/company_registration")
public class company_registration extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	CompanyDao companydao;
	@Override
	public void init() throws ServletException 
	{
		// TODO Auto-generated method stub
		companydao=new CompanyDao();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public company_registration() {
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
		String userid=   request.getParameter("txtid");
		String userpass= request.getParameter("txtpass");
		String username= request.getParameter("txtname");
		String userphone=request.getParameter("txtphone");
		String useremail=request.getParameter("txtemail");
		String usercity= request.getParameter("cmbcity");
		
		String usertype="company";
		String userstatus="true";
		java.util.Date dt=new java.util.Date();
		java.sql.Date sqldate=new java.sql.Date(dt.getTime());
		
		Users u=new Users(userid, userpass, username, useremail, userphone, usercity, usertype, userstatus, sqldate);
		int status=companydao.registration(u);
		if(status>0)
		{
			
			response.sendRedirect("/HouseKeeping/commoncomponents/registrationmessage.jsp");
			System.out.println("registration done");
		}
		if(usercity.equals("others"))
		{
			usercity=request.getParameter("cmbothers");
		}
		System.out.println(userid+userpass+username+userphone+useremail+usercity);
	}

	}



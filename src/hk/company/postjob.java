package hk.company;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hk.common.CommonMessage;
import hk.dao.CompanyDao;
import hk.models.PostJob;
import hk.models.Users;

/**
 * Servlet implementation class postjob
 */
@WebServlet("/postjob")
public class postjob extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CompanyDao companydao;	
	@Override
	public void init() throws ServletException 
	{
	  companydao = new CompanyDao();
	  }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public postjob() {
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
		
		
		HttpSession hs=request.getSession(false);
		Users u=(Users)hs.getAttribute("userdetails");
		
		String companyid= u.getId();
		String noofpost = request.getParameter("txtpost");
		int post=Integer.parseInt(noofpost);
		
		String jobtitle=   request.getParameter("txttitle");
		
		String  email= request.getParameter("txtemail");
		String agelimit=request.getParameter("txtage");
		String eligibility=request.getParameter("txteligible");
		String description= request.getParameter("txtdescrition");
		java.util.Date dt=new java.util.Date();
		java.sql.Date sqldate=new java.sql.Date(dt.getTime());
		PostJob p= new PostJob( post,companyid, jobtitle, email, agelimit, eligibility,description, sqldate);
		int status=companydao.postJob(p);
		if(status>0)
		{
			
			
			System.out.println(post+jobtitle+ email+ agelimit+ eligibility+description+ sqldate);
			
			request.setAttribute("postmessage", CommonMessage.POST_JOB);
			RequestDispatcher rd=request.getRequestDispatcher("/company/company_home.jsp");
			
			rd.forward(request, response);
	
	
	
	}

	}
}

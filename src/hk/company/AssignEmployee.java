package hk.company;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hk.common.CommonMessage;
import hk.dao.CompanyDao;

/**
 * Servlet implementation class FindEmployee
 */
@WebServlet("/AssignEmployee")
public class AssignEmployee extends HttpServlet {
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
    public AssignEmployee() {
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
		String[] empdata=request.getParameterValues("chk");
		java.util.Date dt=new java.util.Date();
		java.sql.Date sqldate=new java.sql.Date(dt.getTime());
		String requestid=request.getParameter("txtreq");
				System.out.println(requestid);;
		String answer=request.getParameter("txt"+requestid);
		System.out.println(answer);
		
		
		  int status=companydao.assignEmployee(empdata,requestid,answer,sqldate);
		  if(status>0)
		  
		  {
		  
		  request.setAttribute("msg", CommonMessage.EMPLOYEE_ASSIGNED);
		  RequestDispatcher
		  rd=request.getRequestDispatcher("/company/company_home.jsp");
		  rd.forward(request, response); }
		 
	}

}

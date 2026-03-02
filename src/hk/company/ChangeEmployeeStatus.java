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
 * Servlet implementation class DeleteFeedback
 */
@WebServlet("/ChangeEmployeeStatus")
public class ChangeEmployeeStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CompanyDao companydao;
	public void init() throws ServletException
	{
		companydao=new CompanyDao();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeEmployeeStatus() {
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

		String[] empids=request.getParameterValues("chk");
		System.out.println(empids.length);
		System.out.println(empids[0]);
		
		
		  int status= companydao.changeEmployeeStatus(empids); 
		  if (status>0)
		  
		  { request.setAttribute("msg", CommonMessage.CHANGE_EMPLOYEE_STATUS);
		  RequestDispatcher
		  rd=request.getRequestDispatcher("/company/company_home.jsp");
		  
		  rd.forward(request, response);
		  
		  
		  }
		 
		
	}

}

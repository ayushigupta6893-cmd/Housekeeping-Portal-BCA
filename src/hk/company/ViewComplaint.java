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
 * Servlet implementation class ViewComplaint
 */
@WebServlet("/ViewComplaint")
public class ViewComplaint extends HttpServlet {
	private static final long serialVersionUID = 1L;
       CompanyDao dao;
       @Override
    public void init() throws ServletException 
       {
    	   dao=new CompanyDao();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewComplaint() {
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
		String[] complaintids=request.getParameterValues("chk");
		System.out.println(complaintids.length);
		int status=  dao.resolveComplaint(complaintids);
		if(status>0)
		{
			request.setAttribute("msgresolve", CommonMessage.MESSAGE_RESOLVED);
			RequestDispatcher rd=request.getRequestDispatcher("/company/company_home.jsp");
			rd.forward(request, response);
		}
	}

}

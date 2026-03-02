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
import hk.models.Companydetail;
import hk.models.Employee;
import hk.models.Users;

/**
 * Servlet implementation class AddEmployee
 */
@WebServlet("/AddEmployee")
public class AddEmployee extends HttpServlet {
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
    public AddEmployee() 
    {
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
		
	Users company=(Users)hs.getAttribute("userdetails");
	String companyownerid=company.getId();
	String cid=   request.getParameter("cmbcid");
	
	
	int c_id=Integer.parseInt(cid);
	String employeeid=   request.getParameter("empid");
	String employeename = request.getParameter("empname");
	//String employeenumber= request.getParameter("empnumber");
	String employeegender=request.getParameter("empgender");
	String employeeemail=request.getParameter("empemail");
	String employeephone=request.getParameter("empnumber");
	String employeeaddress= request.getParameter("empaddress");
	String employeeexperience= request.getParameter("empexperience");
	String usertype=request.getParameter("emptype");
	 
	//(String employeeid, String companyid, String name, String experience, String email, String phone,
//	String gender, String address, String type, String status, Companydetail companydetail, int cid
Employee employee=new Employee(employeeid, companyownerid, employeename, employeeexperience, employeeemail, employeephone, employeegender, employeeaddress, usertype,  "free", c_id);

int status= companydao.addemployee(employee);

    if(status>0)
    {

    	 request.setAttribute("msg", CommonMessage.EMPLOYEE_ADDED);
			RequestDispatcher rd=request.getRequestDispatcher("/company/company_home.jsp");
			
			rd.forward(request, response);
		
    }
    
	
		
		
    

	}
	

}

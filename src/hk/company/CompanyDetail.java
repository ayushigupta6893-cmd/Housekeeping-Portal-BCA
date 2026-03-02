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
import hk.models.Users;

/**
 * Servlet implementation class CompanyDetail
 */
@WebServlet("/CompanyDetail")
public class CompanyDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       CompanyDao dao =null;
       @Override
    	public void init() throws ServletException 
       {
    	dao=new CompanyDao();
    	   
    	}
    
    public  CompanyDetail() {
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
		
		Users user=	(Users)hs.getAttribute("userdetails");
		
		String companyownerid=user.getId();
		
		String companyname=request.getParameter("txtname");
		String email=request.getParameter("txtemail");
		String phone=request.getParameter("txtphone");
		String founder=request.getParameter("txtfounder");
		String registrationnumber=request.getParameter("txtnum");
		String address=request.getParameter("txtaddress");
		String landmark=request.getParameter("txtlandmark");
	
	String companydetail=request.getParameter("txtdetail");

    String[] emptypes= request.getParameterValues("emptype");
    String workertype ="";
    for(String e:emptypes)
    {
    	
    
	System.out.println(e);
	workertype=workertype+e+',';
	
	
    }
	
	System.out.println(workertype);

	String latitude=request.getParameter("txtlat");
	String longitude=request.getParameter("txtlong");
	double lat=Double.parseDouble(latitude);
	double lng=Double.parseDouble(longitude);
	
	Companydetail rp=new Companydetail(companyownerid,companyname,email,address,phone,founder,landmark,lat,lng,registrationnumber,companydetail,workertype);
	
	int status=dao.addcompanyDetails(rp);
	
	if(status>0)
	{
		System.out.println("company added");
		//write codev to send on corrosponding home and show Modal there
		 request.setAttribute("successmessage", CommonMessage.COMPOSE_SUCCESS);
			RequestDispatcher rd=request.getRequestDispatcher("/company/company_home.jsp");
			
			rd.forward(request, response);
	}
	
	System.out.println(latitude+longitude);
	
	
	}

	}



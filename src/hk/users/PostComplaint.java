package hk.users;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hk.dao.UserDao;
import hk.models.Complaint;
import hk.models.Users;

/**
 * Servlet implementation class PostComplaint
 */
@WebServlet("/PostComplaint")
public class PostComplaint extends HttpServlet {
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
    public PostComplaint() {
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
		
		String senderid= u.getId();
		String companyid=request.getParameter("cmpid");
		
		String subject=request.getParameter("txtsubject");
		
		String complaintext=request.getParameter("txtcomplaint");
		String userstatus ="resolved";
		java.util.Date dt=new java.util.Date();
		java.sql.Date sqldate=new java.sql.Date(dt.getTime());
		Complaint c= new Complaint(senderid,companyid,subject,complaintext,userstatus,sqldate);
		int status=userdao.PostComplaint(c);
		
		if(status>0)
		{
			
			
			System.out.println(senderid+ companyid+subject+complaintext+userstatus+ sqldate);
		
			
			
		}
		
		
	}

}

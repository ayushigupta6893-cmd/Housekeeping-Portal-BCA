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
import hk.models.Feedback;
import hk.models.Users;

/**
 * Servlet implementation class User_Feedback
 */
@WebServlet("/User_Feedback")
public class User_Feedback extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao userdao;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		userdao=new UserDao();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User_Feedback() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
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
		String receiveid=request.getParameter("txtrid");
		
		String subject=request.getParameter("txtsubject");
		String rating = request.getParameter("txtrating");
		int user_rating=Integer.parseInt(rating);
		String feedbacktext=request.getParameter("txtfeedback");
		
		java.util.Date dt=new java.util.Date();
		java.sql.Date sqldate=new java.sql.Date(dt.getTime());
		
		Feedback f=new Feedback(senderid,receiveid,subject,user_rating,sqldate,feedbacktext);
		int status=userdao.PostFeedback(f);
		if(status>0)
		{
			
			
			System.out.println(senderid+receiveid+subject+user_rating+feedbacktext+sqldate);
			
			request.setAttribute("successmessage", CommonMessage.FEEDBAK_SUCCESSS);
			RequestDispatcher rd=request.getRequestDispatcher("/user/user_home.jsp");
			
			rd.forward(request, response);
		}
		
	
		
		
	
	}

}

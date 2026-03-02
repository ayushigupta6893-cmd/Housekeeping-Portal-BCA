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

import hk.models.Message;
import hk.models.Users;

/**
 * Servlet implementation class ComposeMessage
 */
@WebServlet("/ComposeMessage")
public class ComposeMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao userdao;
	@Override
	public void init() throws ServletException
	{
		userdao = new UserDao();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComposeMessage() {
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
		String senderid=user.getId();
	
		String receiveid=request.getParameter("txtrid");
		String subject=request.getParameter("txtsubject");
		String messagetext=request.getParameter("txtmessage");
		
		java.util.Date dt=new java.util.Date();
		java.sql.Date sqldate=new java.sql.Date(dt.getTime());
		String rstatus="true";
		String sstatus="true";
		 Message m=new Message(senderid,receiveid,subject,messagetext , sstatus,rstatus, sqldate);
		 
		 int status=userdao.composemessage(m);
		 if(status>0)
		 {
			 request.setAttribute("successmessage", CommonMessage.COMPOSE_SUCCESS);
				RequestDispatcher rd=request.getRequestDispatcher("/user/user_home.jsp");
				
				rd.forward(request, response);
			 System.out.println(senderid+receiveid+subject+messagetext+sqldate);
		 }
		
	}

}
/* } */
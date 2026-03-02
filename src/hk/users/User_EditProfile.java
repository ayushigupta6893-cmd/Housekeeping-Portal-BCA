package hk.users;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hk.dao.UserDao;
import hk.models.Users;

/**
 * Servlet implementation class User_EditProfile
 */
@WebServlet("/User_EditProfile")
public class User_EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserDao userdao;
      @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	userdao= new UserDao();
    } 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User_EditProfile() {
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
		String email=request.getParameter("txtemail");
		String phone=request.getParameter("txtphone");
		String city=request.getParameter("txtcity");
	HttpSession hs=request.getSession(false);// return the reference of existing object
		
	Users user=	(Users)hs.getAttribute("userdetails");
	String userid=user.getId();
	String usertype =user.getUsertype();
	
int status =userdao.editProfile(email,phone,city,userid);
	
	if(status>0)
		user.setCity(city);
	   user.setEmail(email);
	   user.setPhone(phone);
	   hs.setAttribute("userdetails", user);
	
	{
		if(usertype.equals("user"))
		
			response.sendRedirect("/HouseKeeping/user/user_viewprofile.jsp");
		if(usertype.equals("company"))
			response.sendRedirect("/HouseKeeping/company/company_viewprofile.jsp");	
			
		
		
	}
	
	
		
		
		
	}

}
